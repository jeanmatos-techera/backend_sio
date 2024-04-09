package pe.gob.osinergmin.sio.ro.out;

import java.util.List;

public class ListTipoDocumentoOutRO extends BaseOutRO{

	private List<TipoDocumentoOutRO> tiposDocumento;

	public List<TipoDocumentoOutRO> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(List<TipoDocumentoOutRO> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}
}
