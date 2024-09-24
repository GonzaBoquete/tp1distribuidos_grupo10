import grpc
import proto.stock_pb2
import proto.stock_pb2_grpc
from google.protobuf import empty_pb2

class StockCliente:
    def __init__(self, host='localhost', port=9090):
        self.channel = grpc.insecure_channel(f'{host}:{port}')
        self.stub = proto.stock_pb2_grpc.StockServiceStub(self.channel)

    def add_stock(self, tienda, producto_codigo, cantidad):
        nuevo_stock = proto.stock_pb2.Stock(
            tienda=tienda,
            producto_codigo=producto_codigo,
            cantidad=cantidad
        )
        return self.stub.add(nuevo_stock)

    def get_stock(self, id):
        id_request = proto.stock_pb2.StockIdRequest(id=id)
        return self.stub.getOneById(id_request)

    def get_all_stocks(self):
        return self.stub.getAll(empty_pb2.Empty())

    def update_stock(self, id, tienda, producto_codigo, cantidad):
        stock_actualizado = proto.stock_pb2.Stock(
            id=id,
            tienda=tienda,
            producto_codigo=producto_codigo,
            cantidad=cantidad
        )
        return self.stub.update(stock_actualizado)

    def update_stock_quantity(self, id, cantidad):
        update_request = proto.stock_pb2.StockUpdateRequest(id=id, cantidad=cantidad)
        return self.stub.updateStockQuantity(update_request)

    def delete_stock(self, id):
        id_request = proto.stock_pb2.StockIdRequest(id=id)
        return self.stub.delete(id_request)

    def close(self):
        self.channel.close()


    
