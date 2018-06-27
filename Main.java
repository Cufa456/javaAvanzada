
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		    Silo s = new Silo(Ingrediente.LECHE, 500);
		    System.out.println(s.getTipoIngredienteAlmacenado());
		    System.out.println(s.getCapacidadMaxima());
		    Receta r = new Receta();
		    System.out.println(r.getMapaDeIngredientes().size());
		    r.agregarIngrediente(Ingrediente.AZUCAR, 5);
		    r.agregarIngrediente(Ingrediente.CAFE, 15);
		    r.agregarIngrediente(Ingrediente.AGUA, 150);
		    System.out.println(r.getMapaDeIngredientes().size());
		    System.out.println(r);
		    MaquinaDeCafe m = new MaquinaDeCafe();
		    Recipiente agua = new Silo(Ingrediente.AGUA);
		    Recipiente cafe = new Silo(Ingrediente.CAFE);
		    agua.agregar(1500);
		    cafe.agregar(200);
		    m.getRecipientes().add(agua);
		    m.getRecipientes().add(cafe);
		    Receta r1 = new Receta();
		    r1.agregarIngrediente(Ingrediente.CAFE, 5);
		    r1.agregarIngrediente(Ingrediente.AGUA, 100);
		    m.getRecetas().put(Producto.EXPRESO,r1);
		    m.prepararProducto(Producto.EXPRESO);
		    System.out.println(m.recipientes.get(0).getCantidadIngredienteDisponible());
		    System.out.println(m.recipientes.get(1).getCantidadIngredienteDisponible());
		}
		catch (Exception e) { 
		    System.out.println ("FALLO");
		}
		/*RESULTADO ESPERADO
		LECHE
		500
		0
		3
		Receta: CAFE[15];AZUCAR[5];AGUA[150];
		1400
		195
		 */
	}

}
