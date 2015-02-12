package br.com.code;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class Growl implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public Growl() {

    }

    public void addMessage(String status, String mensagem) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(status, mensagem));

    }
    
}
