package pe.gob.osinergmin.sio.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pe.gob.osinergmin.sio.enums.TipoIncidente;
import pe.gob.osinergmin.sio.ro.in.FotosIncidenteInRO;

public class ValidarFotosIncidente {

    public static String fotosFaltantes(int idTipo, FotosIncidenteInRO fotos) {
        if (fotos == null) {
            return "Fotos ausentes.";
        }

        TipoIncidente tipo = obtenerTipoIncidentePorId(idTipo);
        List<Integer> fotosRequeridas = tipo.getValores();
        Set<String> fotosFaltantes = new HashSet<>();

        if (fotosRequeridas != null) {
            for (Integer fotoNumero : fotosRequeridas) {
                String nombreFoto = obtenerNombreFotoPorNumero(fotoNumero);
                String valorFoto = obtenerValorFoto(fotos, fotoNumero);
                if (valorFoto == null || valorFoto.isEmpty()) {
                    fotosFaltantes.add(nombreFoto);
                }
            }
        }

        if (fotosFaltantes.isEmpty()) {
            return "";
        } else {
            return "Faltan las siguientes fotos: " + String.join(", ", fotosFaltantes);
        }
    }

    private static TipoIncidente obtenerTipoIncidentePorId(int idTipo) {
        for (TipoIncidente tipo : TipoIncidente.values()) {
            if (tipo.getCodigo() == idTipo) {
                return tipo;
            }
        }
        return null;
    }

    private static String obtenerNombreFotoPorNumero(int numero) {
        return "foto" + numero;
    }

    private static String obtenerValorFoto(FotosIncidenteInRO fotos, int numero) {
        switch (numero) {
            case 1:
                return fotos.getFoto1();
            case 2:
                return fotos.getFoto2();
            case 3:
                return fotos.getFoto3();
            case 4:
                return fotos.getFoto4();
            case 5:
                return fotos.getFoto5();
            case 6:
                return fotos.getFoto6();
            case 7:
                return fotos.getFoto7();
            case 8:
                return fotos.getFoto8();
            default:
                return null;
        }
    }
    
    public static void vaciarFotosNoRequeridasPorTipo(int idTipo, FotosIncidenteInRO fotos) {
        TipoIncidente tipo = obtenerTipoIncidentePorId(idTipo);
        Set<String> nombresFotosNoRequeridas = obtenerNombresFotosNoRequeridas(tipo);

        for (String nombreFoto : nombresFotosNoRequeridas) {
            asignarValorFoto(fotos, nombreFoto, null);
        }
    }
    
    private static Set<String> obtenerNombresFotosNoRequeridas(TipoIncidente tipo) {
        Set<String> nombresFotosNoRequeridas = new HashSet<>();
        for (int i = 1; i <= 8; i++) {
            if (!tipo.getValores().contains(i)) {
                nombresFotosNoRequeridas.add(obtenerNombreFotoPorNumero(i));
            }
        }
        return nombresFotosNoRequeridas;
    }
    
    private static void asignarValorFoto(FotosIncidenteInRO fotos, String nombreFoto, String valor) {
        switch (nombreFoto) {
            case "foto1":
                fotos.setFoto1(valor);
                break;
            case "foto2":
                fotos.setFoto2(valor);
                break;
            case "foto3":
                fotos.setFoto3(valor);
                break;
            case "foto4":
                fotos.setFoto4(valor);
                break;
            case "foto5":
                fotos.setFoto5(valor);
                break;
            case "foto6":
                fotos.setFoto6(valor);
                break;
            case "foto7":
                fotos.setFoto7(valor);
                break;
            case "foto8":
                fotos.setFoto8(valor);
                break;
        }
    }
}