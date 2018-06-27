import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaquinaDeCafe implements MaquinaDeEstados {
	protected Producto seleccion;
	protected Estado estado = Estado.APAGADO;
	protected List <Recipiente> recipientes;
	protected Map <Producto, Receta> recetas;
	protected int totalProductosServidos;

	
	/**
	 * Constructor sin parametros.
	 */
	public MaquinaDeCafe(){
		recipientes = new ArrayList<Recipiente>();
		recetas = new HashMap<Producto,Receta>();
		totalProductosServidos = 0;
	}
	
	/**
	 * Retorna la cantidad total de productos servidos por esta maquina.
	 * 
	 * @return cantidad total de productos servidos
	 */
	public int getTotalProductosServidos() {
		return totalProductosServidos;
	}
	

	/**
	 * Retorna los productos y recetas configurados en esta
	 * maquina de cafe
	 * 
	 * @return mapa con productos y recetas asociadas
	 */
	public Map<Producto, Receta> getRecetas() {
		return recetas;
	}

	/**
	 * Retorna los recipientes configurados en esta maquina de cafe
	 * 
	 * @return lista de los recipientes
	 */
	public List<Recipiente> getRecipientes() {
		return recipientes;
	}
	
	/**
	 * Retorna el producto (receta) seleccionado
	 * 
	 * @return el producto seleccionada en la maquina
	 */
	public Producto getSeleccion() {
		return seleccion;
	}

	/**
	 * Valida si es posible despachar el producto especificaod
	 * 
	 * @param p el producto a despachar
	 * @throws ProductoException si el producto especificado es null, o no esta
	 * configurado en esta maquina, o existen ingredientes faltantes en la receta.
	 * 
	 */
	public void validarProducto(Producto p) throws ProductoException{
		if (p==null || !recetas.containsKey(p) )
			throw new ProductoException("No existe receta para la seleccion "+ seleccion);
		
		Receta r = recetas.get(p);
		for (Ingrediente ing: r.getIngredientes()){
			if (!hayIngredienteDisponible(ing, r.getCantidadDeIngrediente(ing)))
				throw new ProductoException("Hay faltantes para la seleccion "+seleccion);
		}
    }

// A PARTIR DE AQUI SON METODOS A MODIFICAR EN EL EXAMEN (NO MODIFICAR LOS ANTERIORIES)
		
	/**
	 * Ejecuta la receta para el producto especificado, extrayendo
	 * de cada recipiente la cantidad necesaria de ingredientes
	 * para preparar la receta
	 * 
	 * @param el producto seleccionado
	 * @throws ProductoException si por algun motivo no se puede
	 * extraer la cantidad requerida de cada ingrediente o no
     * hay receta de ese producto
	 */
	public void prepararProducto(Producto seleccion) throws ProductoException {
		if(recetas.get(seleccion)==null)
			throw new ProductoException("No existe receta de este producto");
		try {
			for(Ingrediente i: recetas.get(seleccion).getIngredientes()) {
				getRecipiente(i).extraer(recetas.get(seleccion).getCantidadDeIngrediente(i));
			}
		}
		catch(CapacidadExcedidaException e1) {
			throw new ProductoException("No se pueden extraer ingredientes para este producto");
		}
	}
	
	
	
	/**
	 * Verifica si existe suficiente cantidad de un ingrediente
	 * especificado
	 * 
	 * @param ingrediente el ingrediente requerido
	 * @param cantidadRequerida la cantidad requerida
	 * @return true si existe suficiente ingrediente, false en
	 * caso contrario.
	 */
	public boolean hayIngredienteDisponible (Ingrediente ingrediente, int cantidadRequerida){
		if(getRecipiente(ingrediente).getCantidadIngredienteDisponible() >= cantidadRequerida)
			return true;
		else 
			return false;
	}

	
	/**
	 * Retorna el recipiente para el producto especificado
	 * 
	 * @param ingrediente el ingrediente requerido
	 * @return el recipiente que contiene el ingrediente
	 * requerido, o null si no existe un recipiente para ese
	 * ingrediente.
	 */
	public Recipiente getRecipiente (Ingrediente ingrediente){
		for(Recipiente r: recipientes) 
			if(r.getTipoIngredienteAlmacenado().equals(ingrediente)) 
					return r;
		
		
		return null;
		
}
	
	/**
	 * Agrega una receta para un producto de la maquina de
	 * cafe. Solo es valido en modo MANTENIMIENTO
	 * 
	 * @param r la Receta correspondiente al producto a agregar.
	 * @param p el Producto a agregar y configurar en la maquina.
	 * @throws IllegalStateException si la maquina no esta en modo MANTENIMIENTO
	 */
	public void agregarReceta (Receta r, Producto p){
		if(estado.equals(estado.MANTENIMIENTO)) {
			recetas.put(p, r);
		}
		else {
			throw new IllegalStateException("Colocar en MANTENIMIENTO");
		}
	}
	
	/**
	 * Borra los productos y recetas cargadas en la maquina. Solo
	 * es valido en modo MANTENIMIENTO
	 * 
	 * @throws IllegalStateException si la maquina no esta en modo MANTENIMIENTO
	 */
	public void limpiarRecetas (){
		if(estado.equals(estado.MANTENIMIENTO)) {
			totalProductosServidos=0;
			recetas.clear();
		}
		else {
			throw new IllegalStateException("Colocar en MANTENIMIENTO");
		}
	}

	/**
	 * Selecciona un producto para despachar. Solo es posible si
	 * la maquina esta lista para despachar el producto
	 * 
	 * @param seleccion el producto a despachar
	 * @throws IllegalStateException si la maquina no esta lista
	 */
	public void setSeleccion(Producto seleccion) {
		this.seleccion = seleccion;
	}

    /* Metodos de la interface */

    @Override
	public Estado getEstado() {
		return estado;
	}
	
	@Override
	public void operar() {
		if(estado == estado.LISTO)
		estado = estado.OPERANDO;
		else {
			throw new IllegalStateException("Estado debe ser LISTO");
		}
	}

	@Override
	public void restablecer() {
		estado= estado.LISTO;
		seleccion=null;
	}

	@Override
	public void encender() {
		if(estado == estado.APAGADO) {
			estado = estado.LISTO;
			seleccion=null;
		}
		else {
			throw new IllegalStateException("Estado debe ser APAGADO");
		}
	}

	
	@Override
	public void apagar() {
		if(estado == estado.LISTO)
			estado=estado.APAGADO;
		else if(estado==estado.OPERANDO) {
			throw new IllegalStateException("Maquina esta OPERANDO");
		}
	}

	@Override
	public void mantener() {
		if(estado== estado.LISTO)
			estado= estado.MANTENIMIENTO;
		else {
			throw new IllegalStateException("Estado debe ser LISTO");
		}
	}
	
}
