# app/main/routes.py
from flask import Blueprint, render_template, redirect, url_for, flash, current_app, request
import uuid
from werkzeug.utils import secure_filename
import os, grpc
from .forms import CrearTiendaForm
from .forms import CrearUsuarioForm
from .forms import CrearProductoForm
import proto.tienda_pb2 as tienda_pb2
import proto.tienda_pb2_grpc as tienda_grpc
import proto.usuario_pb2 as usuario_pb2
import proto.usuario_pb2_grpc as usuario_grpc
import proto.producto_pb2 as producto_pb2
import proto.producto_pb2_grpc as producto_grpc


main = Blueprint('main', __name__)

@main.route('/')
def home():
    return render_template('home.html')

# Ruta para listar tiendas
@main.route('/tiendas')
def listar_tiendas():
    request_proto = tienda_pb2.Empty()  # Asumiendo que existe un mensaje Empty
    response = current_app.tienda_stub.getAll(request_proto)
    tiendas = response.tiendas  # Ajusta según tu definición de ProductoList
    return render_template('tiendas/listar.html', tiendas=tiendas)

# Ruta para crear una nueva tienda
@main.route('/tiendas/crear', methods=['GET', 'POST'])
def crear_tienda():
    form = CrearTiendaForm()
    if form.validate_on_submit():
        nueva_tienda = tienda_pb2.Tienda(
            codigo=form.codigo.data,
            direccion=form.direccion.data,
            ciudad=form.ciudad.data,
            provincia=form.provincia.data,
            habilitada=form.habilitada.data
        )
        try:
            response = current_app.tienda_stub.add(nueva_tienda)
            flash('Tienda creada exitosamente!', 'success')
            return redirect(url_for('main.listar_tiendas'))
        except grpc.RpcError as e:
            flash(f'Error al crear tienda: {e.details()}', 'danger')
    return render_template('tiendas/crear.html', form=form)

# Ruta para eliminar una tienda
@main.route('/tiendas/eliminar/<codigo>', methods=['POST'])
def eliminar_tienda(codigo):
    delete_request = tienda_pb2.TiendaCodigoRequest(codigo=codigo)
    try:
        current_app.tienda_stub.delete(delete_request)
        flash('Tienda eliminada exitosamente!', 'success')
    except grpc.RpcError as e:
        flash(f'Error al eliminar tienda: {e.details()}', 'danger')
    return redirect(url_for('main.listar_tiendas'))

@main.route('/tiendas/detalle/<codigo>', methods=['GET', 'POST'])
def detalle_tienda(codigo):
    # Obtener la tienda
    request_proto = tienda_pb2.TiendaCodigoRequest(codigo=codigo)
    try:
        tienda = current_app.tienda_stub.getOneById(request_proto)
    except grpc.RpcError as e:
        flash(f'Error al obtener tienda: {e.details()}', 'danger')
        return redirect(url_for('main.listar_tiendas'))

    form = CrearTiendaForm(obj=tienda)  # Asignar los datos actuales al formulario
    if form.validate_on_submit():
        tienda_actualizada = tienda_pb2.Tienda(
            codigo=form.codigo.data,
            direccion=form.direccion.data,
            ciudad=form.ciudad.data,
            provincia=form.provincia.data,
            habilitada=form.habilitada.data
        )
        try:
            response = current_app.tienda_stub.update(tienda_actualizada)
            flash('Tienda actualizada exitosamente!', 'success')
            return redirect(url_for('main.listar_tiendas'))
        except grpc.RpcError as e:
            flash(f'Error al actualizar tienda: {e.details()}', 'danger')

    return render_template('tiendas/detalle.html', form=form, tienda=tienda)


@main.route('/usuarios')
def listar_usuarios():
    # Implementar filtros basados en parámetros de consulta
    nombre_usuario = request.args.get('nombre_usuario', '')
    tienda = request.args.get('tienda', '')
    # Construir la solicitud basada en los filtros
    filter_request = usuario_pb2.UsuarioFilter(
        nombre_usuario=nombre_usuario,
        tienda=tienda
    )
    try:
        response = current_app.usuario_stub.getAll(filter_request)
        usuarios = response.usuarios
    except grpc.RpcError as e:
        flash(f'Error al obtener usuarios: {e.details()}', 'danger')
        usuarios = []
    return render_template('usuarios/listar.html', usuarios=usuarios)

@main.route('/usuarios/crear', methods=['GET', 'POST'])
def crear_usuario():
    form = CrearUsuarioForm()
    # Obtener las tiendas para llenar el campo de selección
    try:
        tiendas_response = current_app.tienda_stub.getAll(tienda_pb2.Empty())
        tiendas = tiendas_response.tiendas
    except grpc.RpcError as e:
        flash(f'Error al obtener tiendas: {e.details()}', 'danger')
        tiendas = []
    # Agregar opción para "Casa Central"
    form.tienda.choices = [('casa_central', 'Casa Central')] + [(tienda.codigo, tienda.nombre) for tienda in tiendas]
    
    if form.validate_on_submit():
        nueva_usuario = usuario_pb2.Usuario(
            nombre_usuario=form.nombre_usuario.data,
            contraseña=form.contraseña.data,  # En una aplicación real, asegúrate de encriptar las contraseñas
            tienda=form.tienda.data,
            nombre=form.nombre.data,
            apellido=form.apellido.data,
            habilitado=form.habilitado.data
        )
        try:
            response = current_app.usuario_stub.add(nueva_usuario)
            flash('Usuario creado exitosamente!', 'success')
            return redirect(url_for('main.listar_usuarios'))
        except grpc.RpcError as e:
            flash(f'Error al crear usuario: {e.details()}', 'danger')
    return render_template('usuarios/crear.html', form=form)

@main.route('/usuarios/detalle/<nombre_usuario>', methods=['GET', 'POST'])
def detalle_usuario(nombre_usuario):
    # Obtener el usuario
    request_proto = usuario_pb2.UsuarioNombreRequest(nombre_usuario=nombre_usuario)
    try:
        usuario = current_app.usuario_stub.getOneById(request_proto)
    except grpc.RpcError as e:
        flash(f'Error al obtener usuario: {e.details()}', 'danger')
        return redirect(url_for('main.listar_usuarios'))

    form = CrearUsuarioForm(obj=usuario)
    # Obtener las tiendas para llenar el campo de selección
    try:
        tiendas_response = current_app.tienda_stub.getAll(tienda_pb2.Empty())
        tiendas = tiendas_response.tiendas
    except grpc.RpcError as e:
        flash(f'Error al obtener tiendas: {e.details()}', 'danger')
        tiendas = []
    form.tienda.choices = [('casa_central', 'Casa Central')] + [(tienda.codigo, tienda.nombre) for tienda in tiendas]

    if form.validate_on_submit():
        usuario_actualizado = usuario_pb2.Usuario(
            nombre_usuario=form.nombre_usuario.data,
            contraseña=form.contraseña.data,
            tienda=form.tienda.data,
            nombre=form.nombre.data,
            apellido=form.apellido.data,
            habilitado=form.habilitado.data
        )
        try:
            response = current_app.usuario_stub.update(usuario_actualizado)
            flash('Usuario actualizado exitosamente!', 'success')
            return redirect(url_for('main.listar_usuarios'))
        except grpc.RpcError as e:
            flash(f'Error al actualizar usuario: {e.details()}', 'danger')

    return render_template('usuarios/detalle.html', form=form, usuario=usuario)

@main.route('/productos')
def listar_productos():
    # Implementar filtros basados en parámetros de consulta
    nombre = request.args.get('nombre', '')
    codigo = request.args.get('codigo', '')
    talle = request.args.get('talle', '')
    color = request.args.get('color', '')
    # Construir la solicitud basada en los filtros
    filter_request = producto_pb2.ProductoFilter(
        nombre=nombre,
        codigo=codigo,
        talle=talle,
        color=color
    )
    try:
        response = current_app.producto_stub.getAll(filter_request)
        productos = response.productos
    except grpc.RpcError as e:
        flash(f'Error al obtener productos: {e.details()}', 'danger')
        productos = []
    return render_template('productos/listar.html', productos=productos)

@main.route('/productos/crear', methods=['GET', 'POST'])
def crear_producto():
    form = CrearProductoForm()
    # Obtener las tiendas para llenar el campo de selección
    try:
        tiendas_response = current_app.tienda_stub.getAll(tienda_pb2.Empty())
        tiendas = tiendas_response.tiendas
    except grpc.RpcError as e:
        flash(f'Error al obtener tiendas: {e.details()}', 'danger')
        tiendas = []
    form.tiendas.choices = [(tienda.codigo, tienda.nombre) for tienda in tiendas]
    
    if form.validate_on_submit():
        # Generar código único de 10 caracteres
        codigo_unico = str(uuid.uuid4()).replace('-', '')[:10]
        
        # Manejar la subida de la foto
        foto_filename = ''
        if form.foto.data:
            foto = form.foto.data
            foto_filename = secure_filename(foto.filename)
            foto.save(os.path.join('app/static/uploads', foto_filename))
        
        nuevo_producto = producto_pb2.Producto(
            codigo=codigo_unico,
            nombre=form.nombre.data,
            talle=form.talle.data,
            foto=foto_filename,
            color=form.color.data
        )
        try:
            # Asignar a múltiples tiendas
            tiendas_asignadas = form.tiendas.data  # Lista de códigos de tiendas
            for tienda_codigo in tiendas_asignadas:
                # Crear un objeto que asigne el producto a la tienda
                asignacion = producto_pb2.ProductoAsignacion(
                    producto_codigo=codigo_unico,
                    tienda_codigo=tienda_codigo,
                    stock=0  # Stock inicial en 0
                )
                current_app.producto_stub.asignar_a_tienda(asignacion)
            
            # Añadir el producto
            response = current_app.producto_stub.add(nuevo_producto)
            flash('Producto creado exitosamente!', 'success')
            return redirect(url_for('main.listar_productos'))
        except grpc.RpcError as e:
            flash(f'Error al crear producto: {e.details()}', 'danger')
    return render_template('productos/crear.html', form=form)