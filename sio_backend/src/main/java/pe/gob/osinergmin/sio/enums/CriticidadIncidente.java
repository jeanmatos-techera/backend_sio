package pe.gob.osinergmin.sio.enums;

import java.util.ArrayList;
import java.util.List;

import pe.gob.osinergmin.sio.ro.out.CriticidadIncidenteOutRO;

public enum CriticidadIncidente {
    ALTO(1, "Alto"),
    MEDIO(2, "Medio"),
    BAJO(3, "Bajo"),
    POR_DEFINIR(4, "Por definir");

    private Integer id;
    private String accion;

    CriticidadIncidente(Integer id, String accion) {
        this.id = id;
        this.accion = accion;
    }

    public Integer getId() {
        return id;
    }

    public String getAccion() {
        return accion;
    }
    public static String obtenerAccionPorId(Integer id) {
        for (CriticidadIncidente criticidad : CriticidadIncidente.values()) {
            if (criticidad.getId().equals(id)) {
                return criticidad.getAccion();
            }
        }
        return null;
    }
    
    public static List<CriticidadIncidenteOutRO> obtener() {
    	List<CriticidadIncidenteOutRO> listaOutRO = new ArrayList<>();
    	for (CriticidadIncidente criticidadIncidente : CriticidadIncidente.values()) {
    		CriticidadIncidenteOutRO criticidadIncidenteOutRO = new CriticidadIncidenteOutRO(); 
    		criticidadIncidenteOutRO.setId(criticidadIncidente.getId());
    		criticidadIncidenteOutRO.setNombre(criticidadIncidente.getAccion());
    		listaOutRO.add(criticidadIncidenteOutRO);
        }
    	return listaOutRO;
    }
}
