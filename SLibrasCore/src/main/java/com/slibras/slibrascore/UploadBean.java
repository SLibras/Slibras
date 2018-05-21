package com.slibras.slibrascore;


import com.slibras.apachepoi.ManipuladorApachePoi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class UploadBean implements Serializable {

    private static int idx = 0;
    
    /**
     * Creates a new instance of UploadBean
     */
    public void upload(FileUploadEvent event) {
        
        try {
            
            UploadedFile uploadedFile = event.getFile();
            File file = new File("B:/ADS/inter 201702/", uploadedFile.getFileName());
            OutputStream out = new FileOutputStream(file);
            out.write(uploadedFile.getContents());
            out.close();
            
            ManipuladorApachePoi.gerarImagens(file.getAbsolutePath(), "pasta "+ (++idx));
            
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Upload completo",
                            "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));

        }
    }
}
