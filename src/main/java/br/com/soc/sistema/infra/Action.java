package br.com.soc.sistema.infra;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import br.com.soc.sistema.vo.LoginVo;

public class Action extends ActionSupport {
	public static final String REDIRECT = "redirect";
	
    protected boolean isUserAutenticado() {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
    	Object isLogado = session.getAttribute("userLogado");
    	LoginVo login = (LoginVo) isLogado;
        return (login != null && !login.getUser().equals(""));
    }
}
