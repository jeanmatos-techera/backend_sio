package pe.gob.osinergmin.sio.ro.out;

public class ConsultaRucOutRO extends BaseOutRO{
	private String tipoContribuyente;
	private String nombreComercial;
	
	public ConsultaRucOutRO() {
		
	}
	
	public String getTipoContribuyente() {
		return tipoContribuyente;
	}
	public void setTipoContribuyente(String tipoContribuyente) {
		this.tipoContribuyente = tipoContribuyente;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
}
