package pe.gob.osinergmin.sio.enums;

public enum InvocationResult {
	SUCCESS(1), FAILED(2), ALREADYEXIST(-1), NORESULT(0);
    private Integer code;

    InvocationResult(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static InvocationResult get(Integer code) {
        if (code != null) {
            if (code.intValue() == 1) {
                return SUCCESS;
            } else if (code.intValue() == 2) {
                return FAILED;
            } else if (code.intValue() == -1) {
                return ALREADYEXIST;
            } else if (code.intValue() == 0) {
                return NORESULT;
            }
            return null;
        }
        return null;
    }
}
