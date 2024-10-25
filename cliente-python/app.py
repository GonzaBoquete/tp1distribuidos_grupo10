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
    return render_template('listar_productos.html', productos=productos)

@app.route('/producto/<int:codigo>')
@require_login
def detalle_producto(codigo):
    producto = producto_cliente.get_producto(codigo)
    return render_template('detalle_producto.html', producto=producto)

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


# Rutas para Stocks
@app.route('/stocks')
@require_login
def listar_stocks():
    stocks = stock_cliente.get_all_stocks()
    
    # Obtener todos los productos y tiendas
    tiendas = tienda_cliente.get_all_tiendas().tiendas  # Obtener la lista de tiendas
    productos = producto_cliente.get_all_productos()  # Obtener la lista de productos

    # Crear diccionarios para acceder fácilmente por ID
    tienda_dict = {tienda.codigo: tienda.ciudad for tienda in tiendas}
    producto_dict = {producto.codigo: producto.nombre for producto in productos}
    
    # Combinar la información
    stocks_con_nombres = []
    for stock in stocks.stocks:
        stock_info = {
            'id': stock.id,
            'nombre_tienda': tienda_dict.get(stock.idTienda, "Tienda no encontrada"),
            'nombre_producto': producto_dict.get(stock.idProducto, "Producto no encontrado"),
            'cantidad': stock.cantidad
        }
        stocks_con_nombres.append(stock_info)
    
    return render_template('listar_stocks.html', stocks=stocks_con_nombres)

@app.route('/stock/<int:codigo>')
@require_login
def detalle_stock(codigo):
    stock = stock_cliente.get_stock(codigo)
    return render_template('detalle_stock.html', stock=stock)

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
    if request.method == 'POST':
        cantidad = request.form['cantidad']
        stock_cliente.update_stock_quantity(id, cantidad)
        return redirect(url_for('listar_stocks'))
    return render_template('editar_stock_cantidad.html', stock=stock_actual)

# Rutas para Tiendas
@app.route('/tiendas')
@require_login
def listar_tiendas():
    tiendas = tienda_cliente.get_all_tiendas()
    return render_template('listar_tiendas.html', tiendas=tiendas.tiendas)

@app.route('/tienda/<int:codigo>')
@require_login
def detalle_tienda(codigo):
    tienda = tienda_cliente.get_tienda(str(codigo))
    if tienda is None:
        return "Tienda no encontrada", 404
    return render_template('detalle_tienda.html', tienda=tienda)


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


# Rutas para Usuarios
@app.route('/usuarios')
@require_login
def listar_usuarios():
    usuarios = usuario_cliente.get_all_usuarios()
    return render_template('listar_usuarios.html', usuarios=usuarios.usuarios)

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
    tienda = tienda_cliente.get_tienda(usuario.idTienda)
    
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
