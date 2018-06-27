/**
 * Esta inferfaz representa una maquina de estados. Las siguientes son transiciones validas
 * entre estados
 * 
 * APAGADO -> LISTO
 * LISTO -> APAGADO
 * LISTO -> OPERANDO
 * LISTO -> FALLA
 * LISTO -> MANTENIMIENTO
 * MANTENIMIENTO -> LISTO
 * OPERANDO -> LISTO
 * OPERANDO -> FALLA
 */
public interface MaquinaDeEstados {

	/**
	 * Cambia al estado a OPERANDO  
	 * 
	 * @throws IllegalStateException cuando se intenta operar desde un estado no valido
	 */
	void operar();
	
	/**
	 * Restablece la maquina desde cualquier estado 
	 * Cambia al estado LISTO (si no encuentra fallas)
	 * Cambia al estado FALLA (si existe alguna condicion de error)
	 * 
	 * @throws IllegalStateException cuando se intenta operar desde un estado no valido
	 */
	void restablecer();
	
	/**
	 * Enciende la maquina (solo desde el estado APAGADO) 
	 * Cambia al estado LISTO (si no encuentra fallas)
	 * Cambia al estado FALLA (si existe alguna condicion de error)
	 * 
	 * @throws IllegalStateException cuando se intenta operar desde un estado no valido
	 */
	void encender();
	
	/**
	 * Apaga la maquina.
	 * Cambia al estado APAGADO (si no encuentra fallas)
	 * No es posible apagar la maquina si esta OPERANDO
	 * 
	 * @throws IllegalStateException cuando se intenta operar desde un estado no valido
	 */
	void apagar();
	
	/** 
	 * Lleva la maquina a estado de MANTENIMIENTO
	 * Cambia al estado 
	 * 
	 */
	void mantener();
	
	/**
	 * Retorna el estado actual de la maquina.
	 * 
	 * @return estado actual de la maquina.
	 */
	Estado getEstado ();

}
