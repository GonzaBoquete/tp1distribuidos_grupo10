import grpc
import proto.producto_pb2
import proto.producto_pb2_grpc
from google.protobuf import empty_pb2

class ProductoCliente:
    def __init__(self, host='localhost', port=9090):
        self.channel = grpc.insecure_channel(f'{host}:{port}')
        self.stub = proto.producto_pb2_grpc.ProductoServiceStub(self.channel)

    def add_producto(self, codigo, nombre, talle, foto, color):
        nuevo_producto = proto.producto_pb2.Producto(
            codigo=int(codigo),
            nombre=nombre,
            talle=talle,
            foto=foto,
            color=color
        )
        return self.stub.add(nuevo_producto)

    def get_producto(self, codigo):
        codigo_request = proto.producto_pb2.ProductoCodigoRequest(codigo=int(codigo))
        return self.stub.getOneById(codigo_request)

    def get_all_productos(self):
        return self.stub.getAll(empty_pb2.Empty())

    def update_producto(self, codigo, nombre, talle, foto, color):
        producto_actualizado = proto.producto_pb2.Producto(
            codigo=int(codigo),
            nombre=nombre,
            talle=talle,
            foto=foto,
            color=color
        )
        return self.stub.update(producto_actualizado)

    def delete_producto(self, codigo):
        codigo_request = proto.producto_pb2.ProductoCodigoRequest(codigo=codigo)
        return self.stub.delete(codigo_request)

    def close(self):
        self.channel.close()

# Uso de la clase

