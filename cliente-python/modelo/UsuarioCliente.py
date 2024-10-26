import grpc
import proto.usuario_pb2
import proto.usuario_pb2_grpc
from google.protobuf import empty_pb2

class UsuarioCliente:
    def __init__(self, host='localhost', port=9090):
        self.channel = grpc.insecure_channel(f'{host}:{port}')
        self.stub = proto.usuario_pb2_grpc.UsuarioServiceStub(self.channel)

    def add_usuario(self, nombre_usuario, contrasena, tienda, nombre, apellido, rol, habilitado):
        try:
            nuevo_usuario = proto.usuario_pb2.Usuario(
                nombreUsuario=nombre_usuario,
                contrasena=contrasena,
                idTienda=int(tienda),
                nombre=nombre,
                apellido=apellido,
                rol=rol,
                habilitado=habilitado
            )
            return self.stub.add(nuevo_usuario)
        except grpc.RpcError as e:
            # Verificar si la excepción es del tipo "usuario ya existe"
            if e.code() == grpc.StatusCode.ALREADY_EXISTS:
                print("El usuario ya existe")
            else:
                print(f"Ocurrió un error al agregar el usuario: {e}")
            return None

    def get_usuario(self, id):
        id_request = proto.usuario_pb2.UsuarioIdRequest(id=id)
        return self.stub.getOneById(id_request)

    def get_all_usuarios(self):
        return self.stub.getAll(empty_pb2.Empty())

    def update_usuario(self, id, nombre_usuario, contrasena, tienda, nombre, apellido, rol, habilitado):
        usuario_actualizado = proto.usuario_pb2.Usuario(
            id=id,
            nombreUsuario=nombre_usuario,
            contrasena=contrasena,
            idTienda=int(tienda),
            nombre=nombre,
            apellido=apellido,
            rol=rol,
            habilitado=habilitado
        )
        return self.stub.update(usuario_actualizado)

    def login(self, nombre_usuario, contrasena):
        try:
            login_request = proto.usuario_pb2.UsuarioLoginRequest(nombreUsuario=nombre_usuario, contrasena=contrasena)
            return self.stub.login(login_request)
        except:
            print("Error de inicio de sesión")
            return None

    def buscar_usuario(self, nombre, tienda):
        busqueda_request = proto.usuario_pb2.UsuarioBusquedaRequest(nombre=nombre, idTienda=int(tienda))
        return self.stub.buscarUsuario(busqueda_request)
    
    def get_usuarios_por_tienda(self, id_tienda):
        request = proto.usuario_pb2.UsuarioBusquedaRequest(idTienda=int(id_tienda))
        return self.stub.buscarUsuario(request)

    def close(self):
        self.channel.close()


