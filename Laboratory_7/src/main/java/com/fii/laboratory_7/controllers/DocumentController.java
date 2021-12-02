package com.fii.laboratory_7.controllers;

import com.fii.laboratory_7.entities.Document;
import com.fii.laboratory_7.entities.Document2;
import com.fii.laboratory_7.entities.User;
import com.fii.laboratory_7.helpers.DocumentEJB;
import com.fii.laboratory_7.helpers.NumberProducer;
import com.fii.laboratory_7.helpers.TimeDecorator;
import com.fii.laboratory_7.repositories.DocumentRepository;
import com.fii.laboratory_7.repositories.UserRepository;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class DocumentController {
    @Inject
    SecurityContext securityContext;

    @Inject
    DocumentRepository documentRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    FacesContext facesContext;

    @Inject
    NumberProducer numberProducer;

    Document document;

    UploadedFile uploadedFile;

    String documentName;

    @Autowired
    private HttpServletRequest request;

    List<Document2> document2s;


    public DocumentController() {
        document = new Document();
        document2s = new ArrayList<>();
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        UploadedFile file = event.getFile();
        try{
            FileOutputStream fos = new FileOutputStream(file.getFileName());
            InputStream is = file.getInputStream();
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            int a;
            while(true){
                a = is.read(buffer);
                if(a < 0) break;
                fos.write(buffer, 0, a);
                fos.flush();
            }
            fos.close();
            is.close();
        }catch(IOException ignored){
        }
    }

    public String upload() throws IOException {

        /*Path folder = Paths.get(".");
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);

        try (InputStream input = uploadedFile.getInputStream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }*/
        /*LocalTime start = LocalTime.parse( "09:00:00" );
        LocalTime stop = LocalTime.parse( "22:00:00" );
        LocalTime now = LocalTime.now();*/
        TimeDecorator timeDecorator = new TimeDecorator();

        boolean inInterval = timeDecorator.checkInterval();
        if(!inInterval) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The time for submissions is 09:00 - 22:00", null));
            return "";
        }

        InputStream inputStream = uploadedFile.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        for (int i; (i = inputStream.read(buffer)) > 0;){
            byteArrayOutputStream.write(buffer, 0, i);
        }

        String username = securityContext.getCallerPrincipal().getName();
        User user = userRepository.getByUsername(username);

        try {
            InitialContext ic = new InitialContext();
            DocumentEJB bean = (DocumentEJB) ic
                    .lookup("java:global/Laboratory_7/src/main/java/com.fii.laboratory_7/helpers/DocumentEJB!"
                            + "com.fii.laboratory_7.helpers.DocumentEJB");

            bean.printMessage("Document saved");

        } catch (NamingException e) {

            e.printStackTrace();
        }

        Document2 doc = new Document2(documentName, byteArrayOutputStream.toByteArray(), user, numberProducer.produceRegistrationNumber());
        documentRepository.create2(doc);

        return "showDocs?faces-redirect=true";
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public List<Document2> getDocument2s() {
        document2s = documentRepository.get();

        return document2s;
    }

    public void setDocument2s(List<Document2> document2s) {
        this.document2s = document2s;
    }
}
