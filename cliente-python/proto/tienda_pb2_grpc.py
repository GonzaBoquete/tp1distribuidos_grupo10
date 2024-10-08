# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
import proto.tienda_pb2 as tienda__pb2


class TiendaServiceStub(object):
    """Definición de servicios gRPC para Tienda
    """

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.add = channel.unary_unary(
                '/TiendaService/add',
                request_serializer=tienda__pb2.Tienda.SerializeToString,
                response_deserializer=tienda__pb2.Tienda.FromString,
                )
        self.getOneById = channel.unary_unary(
                '/TiendaService/getOneById',
                request_serializer=tienda__pb2.TiendaIdRequest.SerializeToString,
                response_deserializer=tienda__pb2.Tienda.FromString,
                )
        self.getAll = channel.unary_unary(
                '/TiendaService/getAll',
                request_serializer=google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
                response_deserializer=tienda__pb2.TiendaList.FromString,
                )
        self.update = channel.unary_unary(
                '/TiendaService/update',
                request_serializer=tienda__pb2.Tienda.SerializeToString,
                response_deserializer=tienda__pb2.Tienda.FromString,
                )


class TiendaServiceServicer(object):
    """Definición de servicios gRPC para Tienda
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


def add_TiendaServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'add': grpc.unary_unary_rpc_method_handler(
                    servicer.add,
                    request_deserializer=tienda__pb2.Tienda.FromString,
                    response_serializer=tienda__pb2.Tienda.SerializeToString,
            ),
            'getOneById': grpc.unary_unary_rpc_method_handler(
                    servicer.getOneById,
                    request_deserializer=tienda__pb2.TiendaIdRequest.FromString,
                    response_serializer=tienda__pb2.Tienda.SerializeToString,
            ),
            'getAll': grpc.unary_unary_rpc_method_handler(
                    servicer.getAll,
                    request_deserializer=google_dot_protobuf_dot_empty__pb2.Empty.FromString,
                    response_serializer=tienda__pb2.TiendaList.SerializeToString,
            ),
            'update': grpc.unary_unary_rpc_method_handler(
                    servicer.update,
                    request_deserializer=tienda__pb2.Tienda.FromString,
                    response_serializer=tienda__pb2.Tienda.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'TiendaService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class TiendaService(object):
    """Definición de servicios gRPC para Tienda
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
        return grpc.experimental.unary_unary(request, target, '/TiendaService/add',
            tienda__pb2.Tienda.SerializeToString,
            tienda__pb2.Tienda.FromString,
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
        return grpc.experimental.unary_unary(request, target, '/TiendaService/getOneById',
            tienda__pb2.TiendaIdRequest.SerializeToString,
            tienda__pb2.Tienda.FromString,
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
        return grpc.experimental.unary_unary(request, target, '/TiendaService/getAll',
            google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
            tienda__pb2.TiendaList.FromString,
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
        return grpc.experimental.unary_unary(request, target, '/TiendaService/update',
            tienda__pb2.Tienda.SerializeToString,
            tienda__pb2.Tienda.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)