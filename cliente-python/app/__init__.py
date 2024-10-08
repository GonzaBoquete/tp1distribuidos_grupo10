from flask import Flask
import sys,grpc
sys.path.append("..")

from ..proto import producto_pb2_grpc as producto_grpc
from ..proto import tienda_pb2_grpc as tienda_grpc
from ..proto import usuario_pb2_grpc as usuario_grpc

def create_app():
    app = Flask(__name__)
    app.config['SECRET_KEY'] = 'aca va la clave'

    # Configurar conexiones gRPC
    channel_producto = grpc.insecure_channel('localhost:50051')
    app.producto_stub = producto_grpc.ProductoServiceStub(channel_producto)

    channel_tienda = grpc.insecure_channel('localhost:50052')
    app.tienda_stub = tienda_grpc.TiendaServiceStub(channel_tienda)

    channel_usuario = grpc.insecure_channel('localhost:50053')
    app.usuario_stub = usuario_grpc.UsuarioServiceStub(channel_usuario)

    # Registrar Blueprints
    from .main.routes import main
    app.register_blueprint(main)

    return app