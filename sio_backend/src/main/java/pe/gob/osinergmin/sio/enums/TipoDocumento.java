package pe.gob.osinergmin.sio.enums;

public enum TipoDocumento {
	DNI(1);

	private Integer code;

	TipoDocumento(Integer code) {
	        this.code = code;
	    }

	public Integer getCode() {
		return code;
	}

	public static TipoDocumento get(Integer code) {
		if (code != null) {
			if (code.intValue() == 1) {
				return DNI;
			}
			return null;
		}
		return null;
	}
}
