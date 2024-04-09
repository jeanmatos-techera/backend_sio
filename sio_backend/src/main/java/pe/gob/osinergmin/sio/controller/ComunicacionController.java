package pe.gob.osinergmin.sio.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspose.words.WordML2003SaveOptions;

import pe.gob.osinergmin.sio.enums.ErrorCode;
import pe.gob.osinergmin.sio.enums.InvocationResult;
import pe.gob.osinergmin.sio.ro.in.FotosComunicacionInRO;
import pe.gob.osinergmin.sio.ro.in.FotosTareaInRO;
import pe.gob.osinergmin.sio.ro.in.RegistroComunicacionInRO;
import pe.gob.osinergmin.sio.ro.out.ComunicacionOutRO;
import pe.gob.osinergmin.sio.ro.out.RegistroComunicacionOutRO;
import pe.gob.osinergmin.sio.service.ComunicacionService;
import pe.gob.osinergmin.sio.util.Validador;
import pe.gob.osinergmin.sio.util.ValidadorBuilder;

@RestController
@RequestMapping("/comunicacion")
public class ComunicacionController {

	private static Logger logger = LoggerFactory.getLogger(ComunicacionController.class);

	@Autowired
	private ComunicacionService comunicacionService;

	@PostMapping("/registrar")
	public RegistroComunicacionOutRO registrar(@RequestBody RegistroComunicacionInRO registroComunicacionInRO) {
		RegistroComunicacionOutRO registroComunicacionOutRO = new RegistroComunicacionOutRO();
		try {

			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(registroComunicacionInRO.getIdIncidente()))
					.caracteres(registroComunicacionInRO.getFamiliasAfectadas(),
							registroComunicacionInRO.getPersonasAfectadas(),
							registroComunicacionInRO.getViviendasAfectadas())
					.validar().build();

			if (!validador.esValido()) {
				registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroComunicacionOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroComunicacionOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));

				return registroComunicacionOutRO;
			}

			registroComunicacionOutRO = comunicacionService.registrarComunicacion(registroComunicacionInRO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroComunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroComunicacionOutRO.setMessage("Error al registrar comunicacion");
		}
		return registroComunicacionOutRO;
	}

	@PostMapping("/actualizar")
	public RegistroComunicacionOutRO actualizar(@RequestBody RegistroComunicacionInRO registroComunicacionInRO) {
		RegistroComunicacionOutRO registroComunicacionOutRO = new RegistroComunicacionOutRO();
		try {

			Validador validador = ValidadorBuilder.builder()
					.numeros(String.valueOf(registroComunicacionInRO.getIdIncidente()))
					.caracteres(registroComunicacionInRO.getFamiliasAfectadas(),
							registroComunicacionInRO.getPersonasAfectadas(),
							registroComunicacionInRO.getViviendasAfectadas())
					.validar().build();

			if (!validador.esValido()) {
				registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
				registroComunicacionOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
				registroComunicacionOutRO
						.setMessage(ErrorCode.getMessageByErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode()));

				return registroComunicacionOutRO;
			}
			
			if(registroComunicacionInRO.getFotos() != null) {
				Integer fotosEliminadas = 0;
				for(FotosComunicacionInRO foto: registroComunicacionInRO.getFotos()) {
					if(foto.getAccion().equals("D")) {
						fotosEliminadas++;
					}
				}
				
				if(registroComunicacionInRO.getFotos().size() - fotosEliminadas > 4) {
					registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
					registroComunicacionOutRO.setErrorCode(ErrorCode.INCORRECT_FIELD_VALUE.getErrorCode());
					registroComunicacionOutRO.setMessage("máximo 4 fotos por tarea");
				
					return registroComunicacionOutRO;
				}
				
			}
			

			registroComunicacionOutRO = comunicacionService.actualizarComunicacion(registroComunicacionInRO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			registroComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			registroComunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			registroComunicacionOutRO.setMessage("Error al actualizar comunicacion");
		}
		return registroComunicacionOutRO;
	}

	@GetMapping("/obtener/{idIncidente}")
	public ComunicacionOutRO obtenerComunicacionPorIdIncidente(@PathVariable("idIncidente") Integer idIncidente) {
		ComunicacionOutRO ComunicacionOutRO = new ComunicacionOutRO();
		try {
			ComunicacionOutRO = comunicacionService.obtenerComunicacionPorIdIncidente(idIncidente);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ComunicacionOutRO.setResultCode(InvocationResult.FAILED.getCode());
			ComunicacionOutRO.setErrorCode(ErrorCode.ERROR.getErrorCode());
			ComunicacionOutRO.setMessage("Error al obtener comunicacion por incidente");
		}
		return ComunicacionOutRO;
	}
    
    @GetMapping("/descarga/word/{idIncidente}")
    public ResponseEntity<Resource> getAsposeWord(@PathVariable("idIncidente") Integer idIncidente) {
        try {
            ComunicacionOutRO comunicacionOutRO = comunicacionService.obtenerComunicacionPorIdIncidente(idIncidente);
            
            if (comunicacionOutRO.getResultCode() == InvocationResult.SUCCESS.getCode()) {
            	com.aspose.words.Document doc = new com.aspose.words.Document();
                
            	com.aspose.words.DocumentBuilder builder = new com.aspose.words.DocumentBuilder(doc);
                builder.insertHtml(comunicacionOutRO.getDescripcion());
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                doc.save(outputStream, com.aspose.words.SaveFormat.DOCX);
                
                outputStream.close();
                
                ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
                
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comunicacion-" + idIncidente + ".docx")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/descarga/pdf/{idIncidente}")
    public ResponseEntity<Resource> getPdf(@PathVariable("idIncidente") Integer idIncidente) {
        try {
            ComunicacionOutRO comunicacionOutRO = comunicacionService.obtenerComunicacionPorIdIncidente(idIncidente);
            
            if (comunicacionOutRO.getResultCode() == InvocationResult.SUCCESS.getCode()) {
                com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document();
                com.aspose.pdf.Page page = pdfDocument.getPages().add();
                com.aspose.pdf.HtmlFragment htmlFragment = new com.aspose.pdf.HtmlFragment(comunicacionOutRO.getDescripcion());
                page.getParagraphs().add(htmlFragment);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                pdfDocument.save(outputStream);

                outputStream.close();

                ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comunicacion-" + idIncidente + ".pdf")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
   
}
