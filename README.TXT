El proyecto representa parte de un sistema de maquina de cafe.

La maquina posee Recipientes que contienen los Ingredientes necesarios
para preparar diferentes tipos de Producto. Ademas, se puede
configurar para despachar diferentes productos. Cada producto tiene
asociada una Receta.

1. Implementar la clase Silo que extiende de Recipiente, implementando
   los metodos requeridos para ello. Ademas, implementar los siguientes
   constructores:

	public Silo (Ingrediente ingredienteAlmacenado, int capacidadMaxima)
	public Silo (Ingrediente ingredienteAlmacenado)
    
2. Implementar los metodos de la clase Receta segun la documentacion:

	public void agregarIngrediente(Ingrediente ing, Integer cantidad)
	public int getCantidadDeIngrediente (Ingrediente ingrediente)
	public int volumenTotalReceta()
	public String toString()

3. Implementar los metodos metodos faltantes de la clase
   MaquinaDeCafe, segun la documentacion:

     public void prepararProducto(Producto seleccion)
     public void validarProducto(Producto p) throws ProductoException
     public List<Ingrediente> getFaltantes()
     public boolean hayIngredienteDisponible (Ingrediente ingrediente, int candidadRequerida)
     public Recipiente getRecipiente (Ingrediente ingrediente)
     public void agregarReceta (Receta r, Producto p)
     public void limpiarRecetas ()
     public void setSeleccion(Producto seleccion) 

4. Implementar los metodos de la interfaz MaquinaDeEstados en la clase
   MaquinaDeCafe.

  + encender:
     - lanza IllegalStateException si la maquina no esta APAGADA
     - cambia el Estado de la maquina a LISTO
     - establece la seleccion de producto en null
  + apagar:
     - lanza IllegalStateException si la maquina esta OPERANDO
     - cambia el Estado de la maquina a APAGADO.
  + operar:
     - verifica si puede despachar el producto seleccionado
     - lanza IllegalStateException si la maquina no esta LISTA
     - despacha el producto seleccionado
     - cambia el Estado de la maquina a OPERANDO
     - incrementa la cuenta de productos despachados
  + restablecer:
     - cambia el Estado de la maquina a LISTO
     - establece la seleccion de producto en null	
  + mantener:
     - lanza IllegalStateException si la maquina no esta LISTA
     - cambia el Estado de la maquina a MANTENIMIENTO
	
