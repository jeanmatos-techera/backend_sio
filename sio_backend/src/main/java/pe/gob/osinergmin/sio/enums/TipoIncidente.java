package pe.gob.osinergmin.sio.enums;

import java.util.Arrays;
import java.util.List;

public enum TipoIncidente {
	CALIDAD_PRODUCTO_SERVICIO(1, Arrays.asList(5,6,7,8)), 
	DISPONIBILIDAD_PRODUCTO_SERVICIO(2, Arrays.asList(1,2,3,4,5,6,7)),
	ATENCION_CLIENTE(3, Arrays.asList(1,2,3,4,5,6,7)),
	FACTURACION_PRECIOS(4, Arrays.asList(4,5,6,7,8));
	//DEVOLUCIONES_REEMBOLSOS(5, Arrays.asList(1,2,3,4,5,6,7,8));
	
	private final int codigo;
    private final List<Integer> valores;

    TipoIncidente(int codigo, List<Integer> valores) {
        this.codigo = codigo;
        this.valores = valores;
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Integer> getValores() {
        return valores;
    }

}
