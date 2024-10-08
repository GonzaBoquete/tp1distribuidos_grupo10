# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
import proto.usuario_pb2 as usuario__pb2


class UsuarioServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.add = channel.unary_unary(
                '/UsuarioService/add',
                request_serializer=usuario__pb2.Usuario.SerializeToString,
                response_deserializer=usuario__pb2.Usuario.FromString,
                )
        self.getOneById = channel.unary_unary(
                '/UsuarioService/getOneById',
                request_serializer=usuario__pb2.UsuarioIdRequest.SerializeToString,
                response_deserializer=usuario__pb2.Usuario.FromString,
                )
        self.getAll = channel.unary_unary(
                '/UsuarioService/getAll',
                request_serializer=google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
                response_deserializer=usuario__pb2.UsuarioList.FromString,
                )
        self.update = channel.unary_unary(
                '/UsuarioService/update',
                request_serializer=usuario__pb2.Usuario.SerializeToString,
                response_deserializer=usuario__pb2.Usuario.FromString,
                )
        self.login = channel.unary_unary(
                '/UsuarioService/login',
                request_serializer=usuario__pb2.UsuarioLoginRequest.SerializeToString,
                response_deserializer=usuario__pb2.Usuario.FromString,
                )
        self.buscarUsuario = channel.unary_unary(
                '/UsuarioService/buscarUsuario',
                request_serializer=usuario__pb2.UsuarioBusquedaRequest.SerializeToString,
                response_deserializer=usuario__pb2.UsuarioList.FromString,
                )


class UsuarioServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

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

    def login(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def buscarUsuario(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_UsuarioServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'add': grpc.unary_unary_rpc_method_handler(
                    servicer.add,
                    request_deserializer=usuario__pb2.Usuario.FromString,
                    response_serializer=usuario__pb2.Usuario.SerializeToString,
            ),
            'getOneById': grpc.unary_unary_rpc_method_handler(
                    servicer.getOneById,
                    request_deserializer=usuario__pb2.UsuarioIdRequest.FromString,
                    response_serializer=usuario__pb2.Usuario.SerializeToString,
            ),
            'getAll': grpc.unary_unary_rpc_method_handler(
                    servicer.getAll,
                    request_deserializer=google_dot_protobuf_dot_empty__pb2.Empty.FromString,
                    response_serializer=usuario__pb2.UsuarioList.SerializeToString,
            ),
            'update': grpc.unary_unary_rpc_method_handler(
                    servicer.update,
                    request_deserializer=usuario__pb2.Usuario.FromString,
                    response_serializer=usuario__pb2.Usuario.SerializeToString,
            ),
            'login': grpc.unary_unary_rpc_method_handler(
                    servicer.login,
                    request_deserializer=usuario__pb2.UsuarioLoginRequest.FromString,
                    response_serializer=usuario__pb2.Usuario.SerializeToString,
            ),
            'buscarUsuario': grpc.unary_unary_rpc_method_handler(
                    servicer.buscarUsuario,
                    request_deserializer=usuario__pb2.UsuarioBusquedaRequest.FromString,
                    response_serializer=usuario__pb2.UsuarioList.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'UsuarioService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class UsuarioService(object):
    """Missing associated documentation comment in .proto file."""

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
        return grpc.experimental.unary_unary(request, target, '/UsuarioService/add',
            usuario__pb2.Usuario.SerializeToString,
            usuario__pb2.Usuario.FromString,
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
        return grpc.experimental.unary_unary(request, target, '/UsuarioService/getOneById',
            usuario__pb2.UsuarioIdRequest.SerializeToString,
            usuario__pb2.Usuario.FromString,
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
        return grpc.experimental.unary_unary(request, target, '/UsuarioService/getAll',
            google_dot_protobuf_dot_empty__pb2.Empty.SerializeToString,
            usuario__pb2.UsuarioList.FromString,
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
        return grpc.experimental.unary_unary(request, target, '/UsuarioService/update',
            usuario__pb2.Usuario.SerializeToString,
            usuario__pb2.Usuario.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def login(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/UsuarioService/login',
            usuario__pb2.UsuarioLoginRequest.SerializeToString,
            usuario__pb2.Usuario.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def buscarUsuario(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/UsuarioService/buscarUsuario',
            usuario__pb2.UsuarioBusquedaRequest.SerializeToString,
            usuario__pb2.UsuarioList.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
