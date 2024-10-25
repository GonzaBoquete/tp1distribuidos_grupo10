package com.stockearte.tp1_grupo10.grpcServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.protobuf.Empty;
import com.stockearte.tp1_grupo10.grpc.Producto;
import com.stockearte.tp1_grupo10.grpc.ProductoCodigoRequest;
import com.stockearte.tp1_grupo10.grpc.ProductoList;
import com.stockearte.tp1_grupo10.grpc.ProductoServiceGrpc.ProductoServiceImplBase;
import com.stockearte.tp1_grupo10.service.ProductoService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcProductoServiceImpl extends ProductoServiceImplBase {

	@Autowired
	private ProductoService productoService;

	@Override
	public void getOneById(ProductoCodigoRequest request, StreamObserver<Producto> responseObserver) {
		// Utilizamos el servicio de productoService para encontrar el objeto recibido
		// por parametro
		com.stockearte.tp1_grupo10.model.Producto producto = getProductoService()
				.getOneById(Long.valueOf(request.getCodigo()));
		// Crear el builder de Producto
		Producto.Builder productoBuilder = Producto.newBuilder().setCodigo(producto.getCodigo().toString())
				.setNombre(producto.getNombre()).setTalle(producto.getTalle()).setFoto(producto.getFoto())
				.setColor(producto.getColor());

		// Construye el producto a retornar
		Producto grpcProducto = productoBuilder.build();

		// Estos metodos son de tipo void, por lo que no retornan un producto, sino
		// que se completa el objeto de tipo responseObserver
		responseObserver.onNext(grpcProducto);
		responseObserver.onCompleted();
	}

	@Override
	public void getAll(Empty request, StreamObserver<ProductoList> responseObserver) {
		// Obtener la lista de productos del servicio
		List<com.stockearte.tp1_grupo10.model.Producto> productosEncontrados = productoService.getAll();

		// Crear el builder de ProductoList
		ProductoList.Builder productoListBuilder = ProductoList.newBuilder();

		// Convertir cada producto de la lista al objeto gRPC Producto y agregarlo a
		// ProductoList
		for (com.stockearte.tp1_grupo10.model.Producto producto : productosEncontrados) {
			Producto grpcProducto = Producto.newBuilder().setCodigo(producto.getCodigo().toString())
					.setNombre(producto.getNombre()).setTalle(producto.getTalle()).setFoto(producto.getFoto())
					.setColor(producto.getColor()).build();

			// Agregar cada producto al builder
			productoListBuilder.addProductos(grpcProducto);
		}

		// Construir el ProductoList final
		ProductoList productoList = productoListBuilder.build();

		// Enviar la respuesta al cliente
		responseObserver.onNext(productoList);
		responseObserver.onCompleted();
	}

	@Override
	public void add(Producto request, StreamObserver<Producto> responseObserver) {
		// Crear el producto a partir del objeto gRPC
		com.stockearte.tp1_grupo10.model.Producto producto = new com.stockearte.tp1_grupo10.model.Producto();
		producto.setCodigo(Long.valueOf(request.getCodigo()));
		producto.setNombre(request.getNombre());
		producto.setTalle(request.getTalle());
		producto.setFoto(request.getFoto());
		producto.setColor(request.getColor());

		// Llamar al servicio para agregar el producto
		com.stockearte.tp1_grupo10.model.Producto productoCreado = productoService.add(producto);

		// Construir el producto para retornar a gRPC
		Producto grpcProducto = Producto.newBuilder().setCodigo(productoCreado.getCodigo().toString())
				.setNombre(productoCreado.getNombre()).setTalle(productoCreado.getTalle())
				.setFoto(productoCreado.getFoto()).setColor(productoCreado.getColor()).build();

		responseObserver.onNext(grpcProducto);
		responseObserver.onCompleted();
	}

	@Override
	public void update(Producto request, StreamObserver<Producto> responseObserver) {
		// Obtener el producto existente por código
		com.stockearte.tp1_grupo10.model.Producto productoExistente = productoService
				.getOneById(Long.valueOf(request.getCodigo()));

		// Actualizar los campos del producto existente
		productoExistente.setNombre(request.getNombre());
		productoExistente.setTalle(request.getTalle());
		productoExistente.setFoto(request.getFoto());
		productoExistente.setColor(request.getColor());

		// Llamar al servicio para actualizar el producto
		com.stockearte.tp1_grupo10.model.Producto productoActualizado = productoService.update(productoExistente,
				Long.valueOf(request.getCodigo()));

		// Construir el producto actualizado para retornar a gRPC
		Producto grpcProducto = Producto.newBuilder().setCodigo(productoActualizado.getCodigo().toString())
				.setNombre(productoActualizado.getNombre()).setTalle(productoActualizado.getTalle())
				.setFoto(productoActualizado.getFoto()).setColor(productoActualizado.getColor()).build();

		responseObserver.onNext(grpcProducto);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(ProductoCodigoRequest request, StreamObserver<Empty> responseObserver) {
		// Eliminar el producto por su código
		productoService.delete(Long.valueOf(request.getCodigo()));

		// Completar la respuesta con un objeto vacío
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

}
