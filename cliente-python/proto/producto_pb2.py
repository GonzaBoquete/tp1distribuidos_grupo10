# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: producto.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2


DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0eproducto.proto\x1a\x1bgoogle/protobuf/empty.proto\"V\n\x08Producto\x12\x0e\n\x06\x63odigo\x18\x01 \x01(\x03\x12\x0e\n\x06nombre\x18\x02 \x01(\t\x12\r\n\x05talle\x18\x03 \x01(\t\x12\x0c\n\x04\x66oto\x18\x04 \x01(\t\x12\r\n\x05\x63olor\x18\x05 \x01(\t\"\'\n\x15ProductoCodigoRequest\x12\x0e\n\x06\x63odigo\x18\x01 \x01(\x03\",\n\x0cProductoList\x12\x1c\n\tproductos\x18\x01 \x03(\x0b\x32\t.Producto2\xea\x01\n\x0fProductoService\x12\x1b\n\x03\x61\x64\x64\x12\t.Producto\x1a\t.Producto\x12/\n\ngetOneById\x12\x16.ProductoCodigoRequest\x1a\t.Producto\x12/\n\x06getAll\x12\x16.google.protobuf.Empty\x1a\r.ProductoList\x12\x1e\n\x06update\x12\t.Producto\x1a\t.Producto\x12\x38\n\x06\x64\x65lete\x12\x16.ProductoCodigoRequest\x1a\x16.google.protobuf.EmptyB#\n\x1f\x63om.stockearte.tp1_grupo10.grpcP\x01\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'producto_pb2', _globals)
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  DESCRIPTOR._serialized_options = b'\n\037com.stockearte.tp1_grupo10.grpcP\001'
  _globals['_PRODUCTO']._serialized_start=47
  _globals['_PRODUCTO']._serialized_end=133
  _globals['_PRODUCTOCODIGOREQUEST']._serialized_start=135
  _globals['_PRODUCTOCODIGOREQUEST']._serialized_end=174
  _globals['_PRODUCTOLIST']._serialized_start=176
  _globals['_PRODUCTOLIST']._serialized_end=220
  _globals['_PRODUCTOSERVICE']._serialized_start=223
  _globals['_PRODUCTOSERVICE']._serialized_end=457
# @@protoc_insertion_point(module_scope)
