package com.stockearte.tp1_grupo10.grpcServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.protobuf.Empty;
import com.stockearte.tp1_grupo10.grpc.Tienda;
import com.stockearte.tp1_grupo10.grpc.TiendaIdRequest;
import com.stockearte.tp1_grupo10.grpc.TiendaList;
import com.stockearte.tp1_grupo10.grpc.TiendaServiceGrpc.TiendaServiceImplBase;
import com.stockearte.tp1_grupo10.service.TiendaService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcTiendaServiceImpl extends TiendaServiceImplBase {

	@Autowired
	private TiendaService tiendaService;

	@Override
	public void add(Tienda request, StreamObserver<Tienda> responseObserver) {
		// Crear el objeto Tienda a partir del mensaje gRPC
		com.stockearte.tp1_grupo10.model.Tienda tienda = new com.stockearte.tp1_grupo10.model.Tienda();
		tienda.setCodigo(tienda.getCodigo());
		tienda.setDireccion(request.getDireccion());
		tienda.setCiudad(request.getCiudad());
		tienda.setProvincia(request.getProvincia());
		tienda.setHabilitada(request.getHabilitada());

		// Llamar al servicio para agregar la tienda
		com.stockearte.tp1_grupo10.model.Tienda tiendaCreada = tiendaService.add(tienda);

		// Construir el objeto Tienda gRPC para devolverlo
		Tienda grpcTienda = Tienda.newBuilder().setCodigo(tiendaCreada.getCodigo().toString())
				.setDireccion(tiendaCreada.getDireccion()).setCiudad(tiendaCreada.getCiudad())
				.setProvincia(tiendaCreada.getProvincia()).setHabilitada(tiendaCreada.isHabilitada()).build();

		responseObserver.onNext(grpcTienda);
		responseObserver.onCompleted();
	}

	@Override
	public void getOneById(TiendaIdRequest request, StreamObserver<Tienda> responseObserver) {
		// Obtener la tienda por su ID desde el servicio
		com.stockearte.tp1_grupo10.model.Tienda tienda = tiendaService.getOneById(Long.valueOf(request.getCodigo()));

		// Construir el objeto Tienda de gRPC para devolverlo
		Tienda grpcTienda = Tienda.newBuilder().setCodigo(tienda.getCodigo().toString())
				.setDireccion(tienda.getDireccion()).setCiudad(tienda.getCiudad()).setProvincia(tienda.getProvincia())
				.setHabilitada(tienda.isHabilitada()).build();

		responseObserver.onNext(grpcTienda);
		responseObserver.onCompleted();
	}

	@Override
	public void getAll(Empty request, StreamObserver<TiendaList> responseObserver) {
		// Obtener la lista de tiendas desde el servicio
		List<com.stockearte.tp1_grupo10.model.Tienda> tiendasEncontradas = tiendaService.getAll();

		// Crear el builder de TiendaList
		TiendaList.Builder tiendaListBuilder = TiendaList.newBuilder();

		// Convertir cada objeto Tienda a su formato gRPC y agregarlo a TiendaList
		for (com.stockearte.tp1_grupo10.model.Tienda tienda : tiendasEncontradas) {
			Tienda grpcTienda = Tienda.newBuilder().setCodigo(tienda.getCodigo().toString())
					.setDireccion(tienda.getDireccion()).setCiudad(tienda.getCiudad())
					.setProvincia(tienda.getProvincia()).setHabilitada(tienda.isHabilitada()).build();

			// Agregar cada tienda al builder
			tiendaListBuilder.addTiendas(grpcTienda);
		}

		// Construir la lista de tiendas final
		TiendaList tiendaList = tiendaListBuilder.build();

		// Enviar la respuesta
		responseObserver.onNext(tiendaList);
		responseObserver.onCompleted();
	}

	@Override
	public void update(Tienda request, StreamObserver<Tienda> responseObserver) {
		// Obtener la tienda existente por su código
		com.stockearte.tp1_grupo10.model.Tienda tiendaExistente = tiendaService
				.getOneById(Long.valueOf(request.getCodigo()));

		// Actualizar los campos de la tienda
		tiendaExistente.setDireccion(request.getDireccion());
		tiendaExistente.setCiudad(request.getCiudad());
		tiendaExistente.setProvincia(request.getProvincia());
		tiendaExistente.setHabilitada(request.getHabilitada());

		// Llamar al servicio para actualizar la tienda
		com.stockearte.tp1_grupo10.model.Tienda tiendaActualizada = tiendaService.update(tiendaExistente,
				tiendaExistente.getCodigo());

		// Construir el objeto Tienda actualizado para devolverlo
		Tienda grpcTienda = Tienda.newBuilder().setCodigo(tiendaActualizada.getCodigo().toString())
				.setDireccion(tiendaActualizada.getDireccion()).setCiudad(tiendaActualizada.getCiudad())
				.setProvincia(tiendaActualizada.getProvincia()).setHabilitada(tiendaActualizada.isHabilitada()).build();

		responseObserver.onNext(grpcTienda);
		responseObserver.onCompleted();
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

}
