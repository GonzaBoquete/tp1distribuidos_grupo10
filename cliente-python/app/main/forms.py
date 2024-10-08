from flask_wtf import FlaskForm
from wtforms import StringField, BooleanField, SubmitField
from wtforms.validators import DataRequired, Length
from wtforms import PasswordField, SelectField
from wtforms import MultipleSelectField, FileField


class CrearTiendaForm(FlaskForm):
    codigo = StringField('Código', validators=[DataRequired(), Length(max=20)])
    direccion = StringField('Dirección', validators=[DataRequired(), Length(max=100)])
    ciudad = StringField('Ciudad', validators=[DataRequired(), Length(max=50)])
    provincia = StringField('Provincia', validators=[DataRequired(), Length(max=50)])
    habilitada = BooleanField('Habilitada', default=True)
    submit = SubmitField('Crear Tienda')

class CrearUsuarioForm(FlaskForm):
    nombre_usuario = StringField('Nombre de Usuario', validators=[DataRequired(), Length(max=20)])
    contraseña = PasswordField('Contraseña', validators=[DataRequired(), Length(min=6)])
    tienda = SelectField('Tienda', choices=[], coerce=str, validators=[DataRequired()])
    nombre = StringField('Nombre', validators=[DataRequired(), Length(max=50)])
    apellido = StringField('Apellido', validators=[DataRequired(), Length(max=50)])
    habilitado = BooleanField('Habilitado', default=True)
    submit = SubmitField('Crear Usuario')

class CrearProductoForm(FlaskForm):
    nombre = StringField('Nombre', validators=[DataRequired(), Length(max=50)])
    codigo = StringField('Código Único', render_kw={'readonly': True})  # Generado automáticamente
    talle = StringField('Talle', validators=[DataRequired(), Length(max=10)])
    foto = FileField('Foto')  # Para subir una imagen
    color = StringField('Color', validators=[DataRequired(), Length(max=20)])
    tiendas = SelectField('Asignar a Tiendas', choices=[], coerce=str, validators=[DataRequired()], render_kw={"multiple": True})
    submit = SubmitField('Crear Producto')
