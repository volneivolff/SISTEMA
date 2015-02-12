
package br.com.code;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Volnei
*/
@ManagedBean
@ViewScoped
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public String logout()
{
    System.out.println("Ação do Usuário: LOGOFF");
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();

    final HttpServletRequest r = (HttpServletRequest)ec.getRequest();
    r.getSession( false ).invalidate();

    return "index.xhtml?faces-redirect=true";
}
    
}
