package pe.gob.osinergmin.sio.util;

import pe.gob.osinergmin.sio.ro.out.UsuarioOutRO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static String convertirUsuarioAJson(UsuarioOutRO usuarioOutRO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(usuarioOutRO);
    }
}
