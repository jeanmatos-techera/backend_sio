package pe.gob.osinergmin.sio.ro.out;

public class BaseOutRO {
	private Integer resultCode;
	private String message;
	private Integer errorCode;
	
	public BaseOutRO() {
		
	}
	
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
