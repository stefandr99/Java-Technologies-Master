package com.fii.laboratory_7.controllers;

import com.fii.laboratory_7.entities.User;
import com.fii.laboratory_7.repositories.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestScoped
@Named
public class UserController {
    @Inject
    UserRepository userRepository;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @Inject
    FacesContext facesContext;

    @Inject
    SecurityContext securityContext;

    User user;

    public UserController() {
        this.user = new User();
    }

    public String register() {
        String pass = user.getPassword();
        user.setPassword(passwordHasher.generate(pass.toCharArray()));
        user.setRole("author");

        userRepository.create(user);
        return "index";
    }

    public void execute() throws IOException {
        switch(processAuthentication()){
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
                break;
            case SUCCESS:
                getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/faces/addDocument.xhtml");
                break;
        }
    }

    private AuthenticationStatus processAuthentication() {
        ExternalContext ec = getExternalContext();
        return securityContext.authenticate((HttpServletRequest)ec.getRequest(),
                (HttpServletResponse)ec.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(user.getUsername(), user.getPassword())));
    }

    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
