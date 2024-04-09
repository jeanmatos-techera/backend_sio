package pe.gob.osinergmin.sio.ro.out;

public class UsuarioOROutRO {
	private Integer idUsuarioOR;
	private Integer idUsuario;
	private Integer idOficinaRegional;
	
	public UsuarioOROutRO(Integer idUsuarioOR, Integer idUsuario, Integer idOficinaRegional) {
		super();
		this.idUsuarioOR = idUsuarioOR;
		this.idUsuario = idUsuario;
		this.idOficinaRegional = idOficinaRegional;
	}
	
	public UsuarioOROutRO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdUsuarioOR() {
		return idUsuarioOR;
	}
	public void setIdUsuarioOR(Integer idUsuarioOR) {
		this.idUsuarioOR = idUsuarioOR;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdOficinaRegional() {
		return idOficinaRegional;
	}
	public void setIdOficinaRegional(Integer idOficinaRegional) {
		this.idOficinaRegional = idOficinaRegional;
	}
	
}
