package pe.gob.osinergmin.sio.util;

import java.security.SecureRandom;
import java.util.Random;

public class Generador {

	public static String generarCodigoVerificacionAleatorio(int longitud) {
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
                
        for (int i = 0; i < longitud; i++) {
            int digito = random.nextInt(10);
            codigo.append(digito);
        }
        
        return codigo.toString();
    }
	
	public static String generarClaveSeguraAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder clave = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int indice = secureRandom.nextInt(caracteres.length());
            clave.append(caracteres.charAt(indice));
        }

        return clave.toString();
    }
}
