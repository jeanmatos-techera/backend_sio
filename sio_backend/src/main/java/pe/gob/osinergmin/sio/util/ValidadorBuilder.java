package pe.gob.osinergmin.sio.util;

public class ValidadorBuilder {
	
	private Validador validador;
	
	private ValidadorBuilder() {
		validador = new Validador();
	}
	
	public static ValidadorBuilder builder() {
		return new ValidadorBuilder();
	}

	
	public ValidadorBuilder fechas(String... fechas) {
		validador.setFechas(fechas);
		return this;
	}
	
	public ValidadorBuilder numeros(String... numeros) {
		validador.setNumeros(numeros);
		return this;
	}
	
	public ValidadorBuilder caracteres(String... caracteres) {
		validador.setCaracteres(caracteres);
		return this;
	}
	
	public ValidadorBuilder caracteres() {
		validador.setCaracteres();
		return this;
	}
	
	public ValidadorBuilder numeros() {
		validador.setNumeros();
		return this;
	}
	
	public ValidadorBuilder fechas() {
		validador.setFechas();
		return this;
	}
	
	public ValidadorBuilder validar() {
		validador.validarParamatros();
		return this;
	}	

	public ValidadorBuilder opcional() {
		validador.opcionales();
		return this;
	}

	public ValidadorBuilder si(String valor) {
		validador.si(valor);
		return this;
	}

	public ValidadorBuilder comparar(String aComparar) {
		validador.comparar(aComparar);
		return this;
	}

	public ValidadorBuilder requiere(String requerido) {
		validador.requiere(requerido);
		return this;
	}

	public ValidadorBuilder mensajeError(String mensaje){
		validador.mensajeError(mensaje);
		return this;
	}

	public Validador build() {
		return validador;
	}
}
