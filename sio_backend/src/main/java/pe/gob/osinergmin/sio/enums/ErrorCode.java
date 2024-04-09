package pe.gob.osinergmin.sio.enums;


public enum ErrorCode {
	
	ERROR(1000,"Error"), 
	INCORRECT_FIELD_VALUE(2000, "Valor(es) de entrada incorrecto"), 
	INCORRECT_ACTION_VALUE(3000, "Acción invalida"), 
	NO_FOUND_ENTITY(4000,"Entidad no encontrada"),
	NO_ACTIVE_ENTITY(4001,"Entidad no activa");;
	
	private Integer errorCode;
    private String description;

    ErrorCode(Integer errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
    
    public static String getMessageByErrorCode(Integer errorCode) {
        for (ErrorCode error : ErrorCode.values()) {
            if (error.getErrorCode() == errorCode) {
                return error.getDescription();
            }
        }
        return null; 
    }

}
