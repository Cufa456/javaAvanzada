
public class Silo extends Recipiente{
	private Ingrediente ingredienteAlmacenado;
	
    public Silo(Ingrediente ingredienteAlmacenado, int capacidadMaxima){
        super(capacidadMaxima);
        if (ingredienteAlmacenado != null || capacidadMaxima>0){
        	
        	this.ingredienteAlmacenado=ingredienteAlmacenado;
        }
        else{
        	throw new IllegalArgumentException("Argumentos no validos");   
    }
    }

    public Silo(Ingrediente ingredienteAlmacenado)
    {
        super();
        this.ingredienteAlmacenado=ingredienteAlmacenado;
    }
  
	
    public Ingrediente getTipoIngredienteAlmacenado() {
		return ingredienteAlmacenado;
	}
	
}
