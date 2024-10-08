import grpc
import proto.producto_pb2
import proto.producto_pb2_grpc
from google.protobuf import empty_pb2
from modelo.ProductoCliente import ProductoCliente
from modelo.StockCliente import StockCliente
from modelo.TiendaCliente import TiendaCliente
from modelo.UsuarioCliente import UsuarioCliente

if __name__ == '__main__':
    producto = ProductoCliente()
    stock = StockCliente()
    tienda = TiendaCliente()
    usuario = UsuarioCliente()

    # Ejemplo: Agregar un producto
    producto_agregado = producto.add_producto('124', 'Remera', 'M', 'url_de_la_foto', 'Rojo')
    print(f'Producto agregado: {producto_agregado}')

    # Ejemplo: Obtener un producto
    producto_obtenido = producto.get_producto('124')
    print(f'Producto obtenido: {producto_obtenido}')

    # Ejemplo: Obtener todos los productos
    productos_lista = producto.get_all_productos()
    print(f'Lista de productos: {productos_lista}')

    # Ejemplo: Actualizar un producto
    producto_actualizado = producto.update_producto('124', 'Remera', 'L', 'url_de_la_foto', 'Azul')
    print(f'Producto actualizado: {producto_actualizado}')
    
    # Ejemplo: Agregar una tienda
    tienda_agregada = tienda.add_tienda( '001','Calle Falsa 124', 'Ciudad Ejemplo', 'Provincia Ejemplo', True)
    print(f'Tienda agregada: {tienda_agregada}')

    # Ejemplo: Obtener una tienda por código
    tienda_obtenida = tienda.get_tienda('001')
    print(f'Tienda obtenida: {tienda_obtenida}')

    # Ejemplo: Obtener todas las tiendas
    tiendas_lista = tienda.get_all_tiendas()
    print(f'Lista de tiendas: {tiendas_lista}')

    # Ejemplo: Actualizar una tienda
    tienda_actualizada = tienda.update_tienda('001', 'Avenida Siempre Viva 742', 'Ciudad Ejemplo', 'Provincia Ejemplo', True)
    print(f'Tienda actualizada: {tienda_actualizada}')


    # Ejemplo: Agregar un stock
    nuevo_stock = stock.add_stock(tienda='001', producto_codigo='124', cantidad=10)
    print(f'Stock agregado: {nuevo_stock}')

    # Ejemplo: Obtener un stock por ID
    stock_obtenido = stock.get_stock(id=nuevo_stock.id)
    print(f'Stock obtenido: {stock_obtenido}')

    # Ejemplo: Obtener todos los stocks
    stocks_lista = stock.get_all_stocks()
    print(f'Lista de stocks: {stocks_lista}')

    # Ejemplo: Actualizar un stock
    stock_actualizado = stock.update_stock(id=nuevo_stock.id, tienda='001', producto_codigo='124', cantidad=20)
    print(f'Stock actualizado: {stock_actualizado}')

    # Ejemplo: Actualizar la cantidad de un stock
    stock_cantidad_actualizada = stock.update_stock_quantity(id=nuevo_stock.id, cantidad=15)
    print(f'Cantidad de stock actualizada: {stock_cantidad_actualizada}')


    # Ejemplo: Agregar un usuario
    nuevo_usuario = usuario.add_usuario('usuario20', 'contrasena124', '001', 'Juan', 'Pérez', 'TIENDA', True)
    print(f'Usuario agregado: {nuevo_usuario}')

    # Ejemplo: Obtener un usuario por ID
    usuario_obtenido = usuario.get_usuario(nuevo_usuario.id)
    print(f'Usuario obtenido: {usuario_obtenido}')

    # Ejemplo: Obtener todos los usuarios
    usuarios_lista = usuario.get_all_usuarios()
    print(f'Lista de usuarios: {usuarios_lista}')

    # Ejemplo: Actualizar un usuario
    usuario_actualizado = usuario.update_usuario(nuevo_usuario.id, 'usuario20', 'nueva_contrasena', '001', 'Juan', 'Pérez', 'TIENDA', True)
    print(f'Usuario actualizado: {usuario_actualizado}')

    # Ejemplo: Iniciar sesión
    usuario_logueado = usuario.login('usuario1', 'contrasena124')
    print(f'Usuario logueado: {usuario_logueado}')

    # Ejemplo: Buscar un usuario
    usuario_buscado = usuario.buscar_usuario('Juan', '001')
    print(f'Usuario encontrado: {usuario_buscado}')


    # Ejemplo: Eliminar un stock
    stock.delete_stock(id=nuevo_stock.id)
    print('Stock eliminado.')


    # Cierra la conexión
    stock.close()

    # Cierra la conexión
    usuario.close()

    # Cierra la conexión
    producto.close()

    # Cierra la conexión
    tienda.close()

