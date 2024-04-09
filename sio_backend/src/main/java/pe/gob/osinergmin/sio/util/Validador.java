package pe.gob.osinergmin.sio.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Validador {
	private List<String> fechas = new ArrayList<>();
	private List<String> numeros = new ArrayList<>();
	private List<String> caracteres = new ArrayList<>();

	private List<String> fechasOpcional = new ArrayList<>();
	private List<String> numerosOpcional = new ArrayList<>();
	private List<String> caracteresOpcional = new ArrayList<>();
	private String mensaje = "";
	private List<String> fechasErradas = new ArrayList<String>();
	private List<String> numerosErrados = new ArrayList<String>();
	private List<String> caracteresErrados = new ArrayList<String>();
	private boolean esValido = true;
	private boolean existeFiltro = false;
	private int contadorVariablesCorrectas = 0;
	private int cantidadVariablesEnviadas = 0;
	private boolean esOpcional = false;
	public void validarParamatros() {
		try {

			if (fechas.size() > 0) {
				for (String fecha : fechas) {
					if (!validarFechas(fecha)) {
						fechasErradas.add(fecha);
						esValido = false;
					} else {
						contadorVariablesCorrectas++;
					}
				}
			}

			if (numeros.size() > 0) {
				for (String numero : numeros) {
					if (!validarNumeros(numero)) {
						numerosErrados.add(numero);
						esValido = false;
					} else {
						contadorVariablesCorrectas++;
					}
				}
			}

			if (caracteres.size() > 0) {
				for (String caracter : caracteres) {
					if (!validarCaracter(caracter)) {
						caracteresErrados.add(caracter);
						esValido = false;
					} else {
						contadorVariablesCorrectas++;
					}
				}
			}
			if(esOpcional) {
				verificarParametrosOpcionales();
			}
			
			if(!condicionales.isEmpty()){
				varificarCondicionales();	
			}
			
			if (!fechasErradas.isEmpty()) {
				mensaje += "Los siguientes valores no corresponde a una fecha valida: ["
						+ String.join(",", fechasErradas) + "]. ";
			}

			if (!numerosErrados.isEmpty()) {
				mensaje += "Los siguientes valores no corresponde a un número valido: ["
						+ String.join(",", numerosErrados) + "]. ";
			}

			if (!caracteresErrados.isEmpty()) {
				mensaje += "Se encontraron valores vacios en alguno de los parametros donde se esperaba una cadena. ";
			}

		} catch (NullPointerException npe) {
			npe.printStackTrace();
			mensaje = "Es necesario tener al menos un parámetro para validar";
		}

	}

	private void varificarCondicionales() {
		for (Map.Entry<String, Boolean> entry: condicionales.entrySet()) {
			if(entry.getValue() && valoresRequeridos.get(entry.getKey())) {
				mensaje += mensajes.get(entry.getKey()) + ". ";
				esValido = false;
			}
		}
	}

	private void verificarParametrosOpcionales() {
		try {
			if (fechasOpcional.size() > 0) {
				for (String fecha : fechasOpcional) {
					if (!validarFechas(fecha)) {
						esValido = false;
						fechasErradas.add(fecha);
					}
				}
			}

			if (numerosOpcional.size() > 0) {
				for (String numero : numerosOpcional) {
					if (!validarNumeros(numero)) {
						esValido = false;
						numerosErrados.add(numero);
					}
				}
			}

			if (caracteresOpcional.size() > 0) {
				for (String caracter : caracteresOpcional) {
					if (!validarCaracter(caracter)) {
						esValido = false;
						caracteresErrados.add(caracter);
					}
				}
			}
		} catch (Exception e) {
			//error no importante
		}
	}

	private boolean validarFechas(String fecha) {
		try {
			LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean validarNumeros(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private boolean validarCaracter(String caracter) {
		return !caracter.trim().isEmpty();
	}

	public void setFechas(String... fechas) {
		for (String fecha : fechas) {
			if (!fecha.isEmpty()) {
				existeFiltro = true;
				if(esOpcional) {
					this.fechasOpcional.add(fecha);
				} else {
					cantidadVariablesEnviadas++;
					this.fechas.add(fecha);
				}
			}
		}
	}

	public void setNumeros(String... numeros) {
		for (String numero : numeros) {
			if (!numero.isEmpty()) {
				existeFiltro = true;
				if(esOpcional){
					this.numerosOpcional.add(numero);
				} else {
					cantidadVariablesEnviadas++;
					this.numeros.add(numero);
				}
			}
		}
	}

	public void setCaracteres(String... caracteres) {
		for (String caracter : caracteres) {
			existeFiltro = true;
			if(esOpcional){
				this.caracteresOpcional.add(caracter);
			} else {
				cantidadVariablesEnviadas++;
				this.caracteres.add(caracter);
			}
		}
	}

	private HashMap<String, String> valores = new HashMap<>();
	private HashMap<String, String> mensajes = new HashMap<>();
	private String key;
	private HashMap<String, Boolean> condicionales = new HashMap<>();
	private HashMap<String, Boolean> valoresRequeridos = new HashMap<>();
	public void si(String valor){
		key = valor;
		valores.put(key, valor);
    }
    
    public void comparar(String aComparar) {
		condicionales.put(key, valores.get(key).equals(aComparar));
    }

    public void requiere(String requerido) {
	    if(condicionales.get(key)) {
			mensajes.put(key, "Uno de los valores requeridos no fue encontrado. ");
			valoresRequeridos.put(key, requerido == null || requerido.isEmpty());
        }
    }

    public void mensajeError(String mensaje){
		mensajes.put(key, mensaje);
	}

	public void opcionales() {
		esOpcional = true;
	}

	public void setCaracteres() {
		existeFiltro = true;
	}

	public void setNumeros() {
		existeFiltro = true;
	}

	public void setFechas() {
		existeFiltro = true;
	}

	public String mensajeDeValidacion() {
		return mensaje;
	}

	public boolean esValido() {
		return esValido;
	}

	public boolean hayParametros() {
		return existeFiltro;
	}

	public boolean estanCorrectoTodosLosParametros() {
		return contadorVariablesCorrectas == cantidadVariablesEnviadas;
	}
}
