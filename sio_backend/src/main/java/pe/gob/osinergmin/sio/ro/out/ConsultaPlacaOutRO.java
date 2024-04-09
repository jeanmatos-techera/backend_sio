package pe.gob.osinergmin.sio.ro.out;

public class ConsultaPlacaOutRO{
	private String placa;
	private String reglamento;
	private String falta;
	private String numeroDocumento;
	private String fechaInfraccion;
	private Double importe;
	private Double gastos;
	private Double descuento;
	private Double deuda;
	private String estado;
	private String licencia;
	private String tipoDocumentoIdentidad;
	private String nroDocumentoIdentidad;
	
	public ConsultaPlacaOutRO() {
		
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getReglamento() {
		return reglamento;
	}
	public void setReglamento(String reglamento) {
		this.reglamento = reglamento;
	}
	public String getFalta() {
		return falta;
	}
	public void setFalta(String falta) {
		this.falta = falta;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getFechaInfraccion() {
		return fechaInfraccion;
	}
	public void setFechaInfraccion(String fechaInfraccion) {
		this.fechaInfraccion = fechaInfraccion;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Double getGastos() {
		return gastos;
	}
	public void setGastos(Double gastos) {
		this.gastos = gastos;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public Double getDeuda() {
		return deuda;
	}
	public void setDeuda(Double deuda) {
		this.deuda = deuda;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getLicencia() {
		return licencia;
	}
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public String getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	public String getNroDocumentoIdentidad() {
		return nroDocumentoIdentidad;
	}

	public void setNroDocumentoIdentidad(String nroDocumentoIdentidad) {
		this.nroDocumentoIdentidad = nroDocumentoIdentidad;
	}
}
