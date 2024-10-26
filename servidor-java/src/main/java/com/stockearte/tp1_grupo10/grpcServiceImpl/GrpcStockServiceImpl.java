package com.stockearte.tp1_grupo10.grpcServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.protobuf.Empty;
import com.stockearte.tp1_grupo10.grpc.Stock;
import com.stockearte.tp1_grupo10.grpc.StockIdRequest;
import com.stockearte.tp1_grupo10.grpc.StockList;
import com.stockearte.tp1_grupo10.grpc.StockServiceGrpc.StockServiceImplBase;
import com.stockearte.tp1_grupo10.grpc.StockUpdateRequest;
import com.stockearte.tp1_grupo10.service.StockService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcStockServiceImpl extends StockServiceImplBase {

	@Autowired
	private StockService stockService;

	@Override
	public void add(Stock request, StreamObserver<Stock> responseObserver) {
		// Crear el objeto Stock a partir del mensaje gRPC
		com.stockearte.tp1_grupo10.model.Stock stock = new com.stockearte.tp1_grupo10.model.Stock();
		stock.setCantidad(request.getCantidad());

		// Llamar al servicio para agregar el stock
		com.stockearte.tp1_grupo10.model.Stock stockCreado = stockService.add(stock,
				Long.valueOf(request.getIdTienda()), Long.valueOf(request.getIdProducto()));

		// Construir el objeto Stock de gRPC para devolverlo
		Stock grpcStock = Stock.newBuilder().setId(stockCreado.getId()).setCantidad(stockCreado.getCantidad())
				.setIdProducto(stockCreado.getProducto().getCodigo()).setIdTienda(stockCreado.getTienda().getCodigo())
				.build();

		responseObserver.onNext(grpcStock);
		responseObserver.onCompleted();
	}

	@Override
	public void getOneById(StockIdRequest request, StreamObserver<Stock> responseObserver) {
		// Obtener el stock desde el servicio
		com.stockearte.tp1_grupo10.model.Stock stock = stockService.getOneById(request.getId());

		// Construir el objeto Stock de gRPC para devolverlo
		Stock grpcStock = Stock.newBuilder().setId(stock.getId()).setCantidad(stock.getCantidad())
				.setIdProducto(stock.getProducto().getCodigo()).setIdTienda(stock.getTienda().getCodigo()).build();

		responseObserver.onNext(grpcStock);
		responseObserver.onCompleted();
	}

	@Override
	public void getAll(Empty request, StreamObserver<StockList> responseObserver) {
		// Obtener la lista de stocks desde el servicio
		List<com.stockearte.tp1_grupo10.model.Stock> stocksEncontrados = stockService.getAll();

		StockList.Builder stockListBuilder = StockList.newBuilder();

		for (com.stockearte.tp1_grupo10.model.Stock stock : stocksEncontrados) {
			Stock grpcStock = Stock.newBuilder().setId(stock.getId()).setCantidad(stock.getCantidad())
					.setIdProducto(stock.getProducto().getCodigo()).setIdTienda(stock.getTienda().getCodigo()).build();

			stockListBuilder.addStocks(grpcStock);
		}

		StockList stockList = stockListBuilder.build();

		// Enviar la respuesta
		responseObserver.onNext(stockList);
		responseObserver.onCompleted();
	}

	@Override
	public void update(Stock request, StreamObserver<Stock> responseObserver) {
		// Obtener el stock existente por ID
		com.stockearte.tp1_grupo10.model.Stock stockExistente = stockService.getOneById(request.getId());

		// Actualizar los campos del stock existente
		stockExistente.setCantidad(request.getCantidad());

		// Llamar al servicio para actualizar el stock
		com.stockearte.tp1_grupo10.model.Stock stockActualizado = stockService.update(stockExistente,
				stockExistente.getId());

		// Construir el objeto Stock actualizado para devolverlo
		Stock grpcStock = Stock.newBuilder().setId(stockActualizado.getId()).setCantidad(stockActualizado.getCantidad())
				.setIdProducto(stockActualizado.getProducto().getCodigo())
				.setIdTienda(stockActualizado.getTienda().getCodigo()).build();

		responseObserver.onNext(grpcStock);
		responseObserver.onCompleted();
	}

	@Override
	public void updateStockQuantity(StockUpdateRequest request, StreamObserver<Stock> responseObserver) {
		// Actualizar solo la cantidad de stock por ID
		com.stockearte.tp1_grupo10.model.Stock stockActualizado = stockService.update(request.getCantidad(),
				request.getId());

		// Construir el objeto Stock actualizado para devolverlo
		Stock grpcStock = Stock.newBuilder().setId(stockActualizado.getId()).setCantidad(stockActualizado.getCantidad()).build();

		responseObserver.onNext(grpcStock);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(StockIdRequest request, StreamObserver<Empty> responseObserver) {
		// Eliminar el stock por ID
		stockService.delete(request.getId());

		// Enviar un objeto vacío como respuesta
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
}
