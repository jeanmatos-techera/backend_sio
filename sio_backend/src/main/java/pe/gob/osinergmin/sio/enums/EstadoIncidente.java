package pe.gob.osinergmin.sio.enums;

public enum EstadoIncidente {
    EN_VERIFICACION(1, "En Verificación"),
    EN_PROCESO(2, "En Proceso"),
    ATENDIDO(3, "Atendido");

    private Integer id;
    private String accion;

    EstadoIncidente(Integer id, String accion) {
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
        for (EstadoIncidente estado : EstadoIncidente.values()) {
            if (estado.getId().equals(id)) {
                return estado.getAccion();
            }
        }
        return null;
    }
}
