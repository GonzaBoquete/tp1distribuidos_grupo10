import grpc
import proto.tienda_pb2
import proto.tienda_pb2_grpc
from google.protobuf import empty_pb2

class TiendaCliente:
    def __init__(self, host='localhost', port=9090):
        self.channel = grpc.insecure_channel(f'{host}:{port}')
        self.stub = proto.tienda_pb2_grpc.TiendaServiceStub(self.channel)

    def add_tienda(self, codigo, direccion, ciudad, provincia, habilitada):
        nueva_tienda = proto.tienda_pb2.Tienda(
            codigo=codigo,
            direccion=direccion,
            ciudad=ciudad,
            provincia=provincia,
            habilitada=habilitada
        )
        return self.stub.add(nueva_tienda)

    def get_tienda(self, codigo):
        codigo_request = proto.tienda_pb2.TiendaIdRequest(codigo=codigo)
        return self.stub.getOneById(codigo_request)

    def get_all_tiendas(self):
        return self.stub.getAll(empty_pb2.Empty())

    def update_tienda(self, codigo, direccion, ciudad, provincia, habilitada):
        tienda_actualizada = proto.tienda_pb2.Tienda(
            codigo=codigo,
            direccion=direccion,
            ciudad=ciudad,
            provincia=provincia,
            habilitada=habilitada
        )
        return self.stub.update(tienda_actualizada)

    def cambiar_estado(self, codigo):
        # Obtiene la tienda actual
        tienda = self.get_tienda(codigo)
        
        # Cambia el estado de habilitación
        nueva_habilitacion = not tienda.habilitada
        
        # Llama a update con el nuevo estado
        return self.update_tienda(
            codigo=codigo,
            direccion=tienda.direccion,
            ciudad=tienda.ciudad,
            provincia=tienda.provincia,
            habilitada=nueva_habilitacion
        )


    def close(self):
        self.channel.close()


