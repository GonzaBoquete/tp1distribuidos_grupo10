from flask import Flask, render_template, request, redirect, url_for, session
from modelo.ProductoCliente import ProductoCliente
from modelo.StockCliente import StockCliente
from modelo.TiendaCliente import TiendaCliente
from modelo.UsuarioCliente import UsuarioCliente
from functools import wraps

app = Flask(__name__)
app.secret_key = 'stockearte'

# Instanciar los clientes gRPC
producto_cliente = ProductoCliente()
stock_cliente = StockCliente()
tienda_cliente = TiendaCliente()
usuario_cliente = UsuarioCliente()

def require_login(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if 'usuario' not in session:
            return redirect(url_for('login_usuario'))
        return f(*args, **kwargs)
    return decorated_function

@app.teardown_request
def cleanup_session(exception=None):
    # Cerrar sesión si hay un usuario en la sesión
    if 'usuario' in session:
        session.pop('usuario', None)
        session.pop('usuario_rol', None)

@app.route('/')
@require_login
def home():
    return render_template('home.html')

# Rutas para Productos
@app.route('/productos')
@require_login
def listar_productos():
    productos = producto_cliente.get_all_productos()

    # Filtrar productos según los parámetros de la consulta
    codigo = request.args.get('codigo', '')
    nombre = request.args.get('nombre', '')
    talle = request.args.get('talle', '')
    color = request.args.get('color', '')

    if codigo:
        productos = [p for p in productos if p.codigo == int(codigo)]
    if nombre:
        productos = [p for p in productos if nombre.lower() in p.nombre.lower()]
    if talle:
        productos = [p for p in productos if p.talle.lower() == talle.lower()]
    if color:
        productos = [p for p in productos if p.color.lower() == color.lower()]

    return render_template('listar_productos.html', productos=productos, request=request)


@app.route('/detalle_producto/<codigo>')
@require_login
def detalle_producto(codigo):
    producto = producto_cliente.get_producto(codigo)
    # Obtén todos los stocks y filtra solo los que coincidan con el código de producto
    stocks = stock_cliente.get_all_stocks().stocks
    tiendas_ids = {stock.idTienda for stock in stocks if stock.idProducto == int(codigo)}
    
    # Obtén todas las tiendas de la base de datos
    todas_las_tiendas = tienda_cliente.get_all_tiendas().tiendas
    
    return render_template('detalle_producto.html', producto=producto, tiendas=todas_las_tiendas, tiendas_ids=tiendas_ids)


@app.route('/producto/agregar', methods=['GET', 'POST'])
@require_login
def agregar_producto():
    if request.method == 'POST':
        codigo = request.form['codigo']
        nombre = request.form['nombre']
        talle = request.form['talle']
        foto = request.form['foto']
        color = request.form['color']
        producto_cliente.add_producto(codigo, nombre, talle, foto, color)
        return redirect(url_for('listar_productos'))
    return render_template('agregar_producto.html')

@app.route('/producto/editar/<int:codigo>', methods=['GET', 'POST'])
@require_login
def editar_producto(codigo):
    producto_actual = producto_cliente.get_producto(codigo)

    if request.method == 'POST':
        nombre = request.form['nombre']
        talle = request.form['talle']
        foto = request.form['foto']
        color = request.form['color']
        # Llamar al método update_producto para actualizar los datos
        producto_cliente.update_producto(codigo, nombre, talle, foto, color)

        return redirect(url_for('listar_productos'))

    # Enviar el producto actual a la plantilla para rellenar el formulario
    return render_template('editar_producto.html', producto=producto_actual)

@app.route('/producto/agregar_stock/<codigo>', methods=['POST'])
@require_login
def agregar_stock_producto(codigo):
    tienda = request.form['tienda']
    cantidad = 0
    stock_cliente.add_stock(tienda, codigo, cantidad)
    
    return redirect(url_for('detalle_producto', codigo=int(codigo)))


# Rutas para Stocks
@app.route('/stocks', methods=['GET'])
@require_login
def listar_stocks():
    # Obtener el ID de la tienda del usuario de la sesión
    tienda_id = session.get('tienda_id')
    stocks = stock_cliente.get_all_stocks()
    
    stocks_filtrados = [stock for stock in stocks.stocks if stock.idTienda == tienda_id]

    tiendas = tienda_cliente.get_all_tiendas().tiendas  # Obtener la lista de tiendas
    productos = producto_cliente.get_all_productos()  # Obtener la lista de productos
    tienda_dict = {tienda.codigo: tienda.ciudad for tienda in tiendas}
    
    producto_dict = {
        producto.codigo: {
            'nombre': producto.nombre,
            'talle': producto.talle,
            'color': producto.color
        }
        for producto in productos
    }

    codigo_filtro = request.args.get('codigo')
    nombre_filtro = request.args.get('nombre')
    talle_filtro = request.args.get('talle')
    color_filtro = request.args.get('color')

    if codigo_filtro:
        stocks_filtrados = [stock for stock in stocks_filtrados if stock.idProducto == int(codigo_filtro)]
    if nombre_filtro:
        stocks_filtrados = [stock for stock in stocks_filtrados if nombre_filtro.lower() in producto_dict.get(stock.idProducto, {}).get('nombre', '').lower()]
    if talle_filtro:
        stocks_filtrados = [stock for stock in stocks_filtrados if talle_filtro.lower() in producto_dict.get(stock.idProducto, {}).get('talle', '').lower()]
    if color_filtro:
        stocks_filtrados = [stock for stock in stocks_filtrados if color_filtro.lower() in producto_dict.get(stock.idProducto, {}).get('color', '').lower()]


    stocks_con_nombres = []
    for stock in stocks_filtrados:
        producto_info = producto_dict.get(stock.idProducto, {"nombre": "Producto no encontrado", "talle": "N/A", "color": "N/A"})
        stock_info = {
            'id': stock.id,
            'idProducto': stock.idProducto,
            'nombre_tienda': tienda_dict.get(stock.idTienda, "Tienda no encontrada"),
            'nombre_producto': producto_info['nombre'],
            'talle': producto_info['talle'],
            'color': producto_info['color'],
            'cantidad': stock.cantidad
        }
        stocks_con_nombres.append(stock_info)

    return render_template('listar_stocks.html', stocks=stocks_con_nombres)


@app.route('/stock/<int:codigo>')
@require_login
def detalle_stock(codigo):
    stock = stock_cliente.get_stock(codigo)
    producto = producto_cliente.get_producto(stock.idProducto)
    return render_template('detalle_stock.html', stock=stock, producto = producto)

@app.route('/stock/agregar', methods=['GET', 'POST'])
@require_login
def agregar_stock():
    if request.method == 'POST':
        tienda = request.form['tienda']
        producto_codigo = request.form['producto_codigo']
        cantidad = request.form['cantidad']
        stock_cliente.add_stock(tienda, producto_codigo, cantidad)
        return redirect(url_for('listar_stocks'))
    return render_template('agregar_stock.html')

@app.route('/stock/eliminar/<int:id>')
@require_login
def eliminar_stock(id):
    stock_cliente.delete_stock(id)
    return redirect(url_for('listar_stocks'))

@app.route('/stock/editar/<int:id>', methods=['GET', 'POST'])
@require_login
def editar_stock(id):
    stock_actual = stock_cliente.get_stock(id)

    if request.method == 'POST':
        tienda = request.form['tienda']
        producto_codigo = request.form['producto_codigo']
        cantidad = request.form['cantidad']
        stock_cliente.update_stock(id, tienda, producto_codigo, cantidad)
        return redirect(url_for('listar_stocks'))
    return render_template('editar_stock.html', stock=stock_actual)

@app.route('/stock/editar_cantidad/<int:id>', methods=['GET', 'POST'])
@require_login
def editar_stock_cantidad(id):
    stock_actual = stock_cliente.get_stock(id)
    producto = producto_cliente.get_producto(stock_actual.idProducto)
    if request.method == 'POST':
        cantidad = request.form['cantidad']
        stock_cliente.update_stock_quantity(id, int(cantidad))
        return redirect(url_for('listar_stocks'))
    return render_template('editar_stock_cantidad.html', stock=stock_actual, producto=producto)

# Rutas para Tiendas
@app.route('/tiendas')
@require_login
def listar_tiendas():
    # Obtener los parámetros de búsqueda del formulario
    codigo = request.args.get('codigo', '')
    estado = request.args.get('estado', '')

    # Obtener todas las tiendas
    tiendas_response = tienda_cliente.get_all_tiendas()
    tiendas = tiendas_response.tiendas  # Asumiendo que esto devuelve un objeto tipo 'TiendaList'

    # Filtrar por código
    if codigo:
        tiendas = [t for t in tiendas if codigo.lower() in t.codigo.lower()]

    # Filtrar por estado
    if estado:
        estado_bool = estado == '1'  # Convertir a booleano
        tiendas = [t for t in tiendas if t.habilitada == estado_bool]

    return render_template('listar_tiendas.html', tiendas=tiendas)



@app.route('/tienda/<int:codigo>')
@require_login
def detalle_tienda(codigo):
    tienda = tienda_cliente.get_tienda(str(codigo))
    if tienda is None:
        return "Tienda no encontrada", 404

    # Obtener los usuarios asociados a la tienda
    usuarios = usuario_cliente.get_usuarios_por_tienda(codigo).usuarios

    return render_template('detalle_tienda.html', tienda=tienda, usuarios=usuarios)


@app.route('/tienda/agregar', methods=['GET', 'POST'])
@require_login
def agregar_tienda():
    if request.method == 'POST':
        codigo = request.form['codigo']
        direccion = request.form['direccion']
        ciudad = request.form['ciudad']
        provincia = request.form['provincia']
        habilitada = request.form.get('habilitada') == 'on' 
        
        tienda_cliente.add_tienda(codigo, direccion, ciudad, provincia, habilitada)
        return redirect(url_for('listar_tiendas'))
    return render_template('agregar_tienda.html')


@app.route('/tienda/editar/<int:codigo>', methods=['GET', 'POST'])
@require_login
def editar_tienda(codigo):  
    # Obtener los detalles de la tienda existente
    tienda_actual = tienda_cliente.get_tienda(str(codigo))
    if request.method == 'POST':
        direccion = request.form['direccion']
        ciudad = request.form['ciudad']
        provincia = request.form['provincia']
        habilitada = request.form.get('habilitada') == 'on'  # Convierte a booleano
        tienda_cliente.update_tienda(str(codigo), direccion, ciudad, provincia, habilitada)
        return redirect(url_for('detalle_tienda', codigo=str(codigo)))  # Redirigir a la vista de la tienda

    return render_template('editar_tienda.html', tienda=tienda_actual)

@app.route('/tienda/deshabilitar/<int:codigo>', methods=['POST'])
@require_login
def cambiar_estado(codigo):
    tienda_cliente.cambiar_estado(str(codigo))
    return redirect(url_for('listar_tiendas'))


# Rutas para Usuarios
@app.route('/usuarios')
@require_login
def listar_usuarios():
    # Obtener los parámetros de búsqueda del formulario
    nombre_usuario = request.args.get('nombreUsuario', '')
    tienda = request.args.get('idTienda', '')

    # Obtener todos los usuarios
    usuarios = usuario_cliente.get_all_usuarios()

    usuarios_lista = usuarios.usuarios  
    
    # Filtrar por nombre de usuario
    if nombre_usuario:
        usuarios_lista = [u for u in usuarios_lista if nombre_usuario.lower() in u.nombreUsuario.lower()]
    
    # Filtrar por idTienda
    if tienda:
        # Asegúrate de que idTienda en el objeto u sea del mismo tipo que el filtro
        usuarios_lista = [u for u in usuarios_lista if u.idTienda and str(tienda) in str(u.idTienda)]

    return render_template('listar_usuarios.html', usuarios=usuarios_lista)

@app.route('/usuario/agregar', methods=['GET', 'POST'])
@require_login
def agregar_usuario():
    if request.method == 'POST':
        nombre_usuario = request.form['nombre_usuario']
        contrasena = request.form['contrasena']
        tienda = request.form['tienda']
        nombre = request.form['nombre']
        apellido = request.form['apellido']
        rol = request.form['rol']
        habilitado = request.form['habilitado']
        usuario_cliente.add_usuario(nombre_usuario, contrasena, str(tienda), nombre, apellido, rol, habilitado)
        return redirect(url_for('listar_usuarios'))
    return render_template('agregar_usuario.html')

# Rutas de login para usuarios
@app.route('/login', methods=['GET', 'POST'])
def login_usuario():
    if request.method == 'POST':
        nombre_usuario = request.form['nombre_usuario']
        contrasena = request.form['contrasena']
        usuario = usuario_cliente.login(nombre_usuario, contrasena)
        if usuario:
            session['usuario'] = nombre_usuario
            session['usuario_rol'] = usuario.rol
            session['tienda_id'] = usuario.idTienda  # Asegúrate de tener este campo
            return redirect(url_for('home'))
        else:
            return render_template('login.html', error="Error en el inicio de sesión")
    return render_template('login.html')



@app.route('/usuario/<int:id>', methods=['GET'])
@require_login
def ver_usuario(id):
    usuario = usuario_cliente.get_usuario(id)
    if not usuario:
        return "Usuario no encontrado", 404
    
    tienda = None
    if usuario.idTienda:  
        tienda = tienda_cliente.get_tienda(str(usuario.idTienda))
    
    return render_template('detalle_usuario.html', usuario=usuario, tienda=tienda)


@app.route('/usuario/editar/<int:id>', methods=['GET', 'POST'])
@require_login
def editar_usuario(id):
    usuario_actual = usuario_cliente.get_usuario(id)
    tiendas = tienda_cliente.get_all_tiendas()  # Obtener las tiendas disponibles
    if request.method == 'POST':
        nombre_usuario = request.form['nombre_usuario']
        contrasena = request.form['contrasena']
        tienda = str(request.form['tienda'])
        nombre = request.form['nombre']
        apellido = request.form['apellido']
        rol = request.form['rol']
        habilitado = request.form.get('habilitado') == 'on'
        usuario_cliente.update_usuario(id, nombre_usuario, contrasena, tienda, nombre, apellido, rol, habilitado)
        return redirect(url_for('ver_usuario', id=id))
    return render_template('editar_usuario.html', usuario=usuario_actual, tiendas=tiendas.tiendas)

@app.route('/usuario/buscar', methods=['GET', 'POST'])
@require_login
def buscar_usuario():
    if request.method == 'POST':
        nombre = request.form['nombre']
        tienda = request.form['tienda']
        usuarios = usuario_cliente.buscar_usuario(nombre, tienda)
        return render_template('resultado_busqueda_usuario.html', usuarios=usuarios)
    return render_template('buscar_usuario.html')

@app.route('/logout', methods=['POST'])
@require_login
def logout():
    session.pop('usuario', None)  # Elimina el usuario de la sesión
    return redirect(url_for('login_usuario'))  # Redirige al login

# Ejecutar la aplicación
if __name__ == '__main__':
    app.run(debug=True)
