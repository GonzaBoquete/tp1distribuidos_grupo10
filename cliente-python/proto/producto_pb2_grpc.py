# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
import proto.producto_pb2 as producto__pb2


class ProductoServiceStub(object):
    """Definición de servicios gRPC
    """

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.add = channel.unary_unary(
                '/ProductoService/add',
                request_serializer=producto__pb2.Producto.SerializeToString,
                response_deserializer=producto__pb2.Producto.FromString,
                )
        self.getOneById = channel.unary_unary(
                '/ProductoService/getOneById',
                request_serializer=producto__pb2.ProductoCodigoRequest.SerializeToString,
                response_deserializer=producto__pb2.Producto.FromString,
                )
        self.getAll = channel.unary_unary(
                '/ProductoService/getAll',
                request_serializer=google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
                response_deserializer=producto__pb2.ProductoList.FromString,
                )
        self.update = channel.unary_unary(
                '/ProductoService/update',
                request_serializer=producto__pb2.Producto.SerializeToString,
                response_deserializer=producto__pb2.Producto.FromString,
                )
        self.delete = channel.unary_unary(
                '/ProductoService/delete',
                request_serializer=producto__pb2.ProductoCodigoRequest.SerializeToString,
                response_deserializer=google_dot_protobuf_dot_empty__pb2.Empty.FromString,
                )


class ProductoServiceServicer(object):
    """Definición de servicios gRPC
    """

    def add(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def getOneById(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def getAll(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def update(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def delete(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_ProductoServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'add': grpc.unary_unary_rpc_method_handler(
                    servicer.add,
                    request_deserializer=producto__pb2.Producto.FromString,
                    response_serializer=producto__pb2.Producto.SerializeToString,
            ),
            'getOneById': grpc.unary_unary_rpc_method_handler(
                    servicer.getOneById,
                    request_deserializer=producto__pb2.ProductoCodigoRequest.FromString,
                    response_serializer=producto__pb2.Producto.SerializeToString,
            ),
            'getAll': grpc.unary_unary_rpc_method_handler(
                    servicer.getAll,
                    request_deserializer=google_dot_protobuf_dot_empty__pb2.Empty.FromString,
                    response_serializer=producto__pb2.ProductoList.SerializeToString,
            ),
            'update': grpc.unary_unary_rpc_method_handler(
                    servicer.update,
                    request_deserializer=producto__pb2.Producto.FromString,
                    response_serializer=producto__pb2.Producto.SerializeToString,
            ),
            'delete': grpc.unary_unary_rpc_method_handler(
                    servicer.delete,
                    request_deserializer=producto__pb2.ProductoCodigoRequest.FromString,
                    response_serializer=google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'ProductoService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class ProductoService(object):
    """Definición de servicios gRPC
    """

    @staticmethod
    def add(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ProductoService/add',
            producto__pb2.Producto.SerializeToString,
            producto__pb2.Producto.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def getOneById(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ProductoService/getOneById',
            producto__pb2.ProductoCodigoRequest.SerializeToString,
            producto__pb2.Producto.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def getAll(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ProductoService/getAll',
            google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
            producto__pb2.ProductoList.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def update(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ProductoService/update',
            producto__pb2.Producto.SerializeToString,
            producto__pb2.Producto.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def delete(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ProductoService/delete',
            producto__pb2.ProductoCodigoRequest.SerializeToString,
            google_dot_protobuf_dot_empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
