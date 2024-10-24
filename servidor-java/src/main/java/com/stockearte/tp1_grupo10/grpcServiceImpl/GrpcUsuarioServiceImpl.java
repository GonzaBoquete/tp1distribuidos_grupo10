package com.stockearte.tp1_grupo10.grpcServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.protobuf.Empty;
import com.stockearte.tp1_grupo10.enumerators.Rol;
import com.stockearte.tp1_grupo10.grpc.Usuario;
import com.stockearte.tp1_grupo10.grpc.UsuarioBusquedaRequest;
import com.stockearte.tp1_grupo10.grpc.UsuarioIdRequest;
import com.stockearte.tp1_grupo10.grpc.UsuarioList;
import com.stockearte.tp1_grupo10.grpc.UsuarioLoginRequest;
import com.stockearte.tp1_grupo10.grpc.UsuarioServiceGrpc.UsuarioServiceImplBase;
import com.stockearte.tp1_grupo10.service.TiendaService;
import com.stockearte.tp1_grupo10.service.UsuarioService;

import io.grpc.stub.StreamObserver;
import java.util.Optional;  // Para usar la clase Optional
import io.grpc.Status;  // Para manejar los códigos de estado gRPC
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcUsuarioServiceImpl extends UsuarioServiceImplBase {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TiendaService tiendaService;
	
	@Override
	public void add(Usuario request, StreamObserver<Usuario> responseObserver) {
		// Mapear el mensaje gRPC Usuario al modelo Usuario de la capa de servicios
				com.stockearte.tp1_grupo10.model.Usuario usuario = new com.stockearte.tp1_grupo10.model.Usuario();
				usuario.setNombreUsuario(request.getNombreUsuario());
				usuario.setContrasena(request.getContrasena());
				usuario.setNombre(request.getNombre());
				usuario.setApellido(request.getApellido());
				usuario.setRol(Rol.valueOf(request.getRol()));
				usuario.setHabilitado(request.getHabilitado());

				// Llamar al servicio para agregar el usuario
				com.stockearte.tp1_grupo10.model.Usuario usuarioAgregado = usuarioService.add(usuario, request.getIdTienda());

				// Mapear el resultado de vuelta al objeto gRPC
				Usuario grpcUsuario = Usuario.newBuilder().setId(usuarioAgregado.getId())
						.setNombreUsuario(usuarioAgregado.getNombreUsuario()).setContrasena(usuarioAgregado.getContrasena())
						.setNombre(usuarioAgregado.getNombre()).setApellido(usuarioAgregado.getApellido())
						.setRol(usuarioAgregado.getRol().name()).setHabilitado(usuarioAgregado.isHabilitado()).setIdTienda(usuarioAgregado.getTienda().getCodigo()).build();

				// Enviar la respuesta
				responseObserver.onNext(grpcUsuario);
				responseObserver.onCompleted();
	}

	@Override
	public void getOneById(UsuarioIdRequest request, StreamObserver<Usuario> responseObserver) {
		// Llamar al servicio para obtener un usuario por ID
		com.stockearte.tp1_grupo10.model.Usuario usuario = usuarioService.getOneById(request.getId());

		Usuario grpcUsuario = Usuario.newBuilder()
	            .setId(usuario.getId())
	            .setNombreUsuario(usuario.getNombreUsuario())
	            .setContrasena(usuario.getContrasena())
	            .setNombre(usuario.getNombre())
	            .setApellido(usuario.getApellido())
	            .setRol(usuario.getRol().name())
	            .setHabilitado(usuario.isHabilitado())
	            .setIdTienda(usuario.getTienda().getCodigo())  // Asegúrate de que esto esté correctamente definido
	            .build();

		// Enviar la respuesta
		responseObserver.onNext(grpcUsuario);
		responseObserver.onCompleted();
	}

	@Override
	public void getAll(Empty request, StreamObserver<UsuarioList> responseObserver) {
		// Llamar al servicio para obtener todos los usuarios
		List<com.stockearte.tp1_grupo10.model.Usuario> usuarios = usuarioService.getAll();

		// Mapear la lista de usuarios al objeto gRPC UsuarioList
		UsuarioList.Builder usuarioListBuilder = UsuarioList.newBuilder();
		for (com.stockearte.tp1_grupo10.model.Usuario usuario : usuarios) {
			Usuario grpcUsuario = Usuario.newBuilder().setId(usuario.getId())
					.setNombreUsuario(usuario.getNombreUsuario()).setContrasena(usuario.getContrasena())
					.setNombre(usuario.getNombre()).setApellido(usuario.getApellido()).setRol(usuario.getRol().name())
					.setHabilitado(usuario.isHabilitado()).build();
			usuarioListBuilder.addUsuarios(grpcUsuario);
		}

		// Enviar la respuesta
		UsuarioList usuarioList = usuarioListBuilder.build();
		responseObserver.onNext(usuarioList);
		responseObserver.onCompleted();
	}

	@Override
	public void update(Usuario request, StreamObserver<Usuario> responseObserver) {
		
		// Buscar el usuario existente por ID
	    com.stockearte.tp1_grupo10.model.Usuario usuarioExistente = usuarioService.getOneById(request.getId());
	   	    
	 // Mapear el mensaje gRPC Usuario al modelo Usuario de la capa de servicios
	    usuarioExistente.setNombreUsuario(request.getNombreUsuario());
	    usuarioExistente.setContrasena(request.getContrasena());
	    usuarioExistente.setNombre(request.getNombre());
	    usuarioExistente.setApellido(request.getApellido());
	    usuarioExistente.setRol(Rol.valueOf(request.getRol()));
	    usuarioExistente.setHabilitado(request.getHabilitado());
	    usuarioExistente.setTienda(tiendaService.getOneById(request.getIdTienda()));

		// Llamar al servicio para actualizar el usuario
	    com.stockearte.tp1_grupo10.model.Usuario usuarioActualizado = usuarioService.update(usuarioExistente,usuarioExistente.getId());

        // Mapear el resultado de vuelta al objeto gRPC
        Usuario grpcUsuario = Usuario.newBuilder()
                .setId(usuarioActualizado.getId())
                .setNombreUsuario(usuarioActualizado.getNombreUsuario())
                .setContrasena(usuarioActualizado.getContrasena())
                .setNombre(usuarioActualizado.getNombre())
                .setApellido(usuarioActualizado.getApellido())
                .setRol(usuarioActualizado.getRol().name())
                .setHabilitado(usuarioActualizado.isHabilitado())
                .setIdTienda(usuarioActualizado.getTienda().getCodigo())
                .build();

        // Enviar la respuesta
        responseObserver.onNext(grpcUsuario);
        responseObserver.onCompleted();
	}

	@Override
	public void login(UsuarioLoginRequest request, StreamObserver<Usuario> responseObserver) {
		 try {
		        // Llamar al servicio para realizar el login
		        com.stockearte.tp1_grupo10.model.Usuario usuario = usuarioService.login(request.getNombreUsuario(),
		                request.getContrasena());

		        // Mapear el resultado al objeto gRPC
		        Usuario grpcUsuario = Usuario.newBuilder()
		                .setId(usuario.getId())
		                .setNombreUsuario(usuario.getNombreUsuario())
		                .setContrasena(usuario.getContrasena())
		                .setNombre(usuario.getNombre())
		                .setApellido(usuario.getApellido())
		                .setRol(usuario.getRol().name())
		                .setHabilitado(usuario.isHabilitado())
		                .build();

		        // Enviar la respuesta
		        responseObserver.onNext(grpcUsuario);
		        responseObserver.onCompleted();

		    } catch (Exception e) {
		        // Manejo de errores generales
		        responseObserver.onError(Status.INTERNAL.withDescription("Error al procesar la solicitud").withCause(e).asRuntimeException());
		    }
	}

	@Override
	public void buscarUsuario(UsuarioBusquedaRequest request, StreamObserver<UsuarioList> responseObserver) {
		// Llamar al servicio para buscar un usuario
		List<com.stockearte.tp1_grupo10.model.Usuario> usuarios = usuarioService.buscarUsuario(request.getNombre(),
				getTiendaService().getOneById(request.getIdTienda()));

		// Mapear la lista de usuarios a la respuesta gRPC
	    UsuarioList.Builder usuarioListBuilder = UsuarioList.newBuilder();
	    for (com.stockearte.tp1_grupo10.model.Usuario usuario : usuarios) {
	        Usuario grpcUsuario = Usuario.newBuilder()
	                .setId(usuario.getId())
	                .setNombreUsuario(usuario.getNombreUsuario())
	                .setContrasena(usuario.getContrasena())
	                .setNombre(usuario.getNombre())
	                .setApellido(usuario.getApellido())
	                .setRol(usuario.getRol().name())
	                .setHabilitado(usuario.isHabilitado())
	                .build();
	        
	        usuarioListBuilder.addUsuarios(grpcUsuario);
	    }

	    // Enviar la respuesta
	    responseObserver.onNext(usuarioListBuilder.build());
	    responseObserver.onCompleted();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

}
