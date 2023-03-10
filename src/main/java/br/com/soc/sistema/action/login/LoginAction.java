package br.com.soc.sistema.action.login;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import br.com.soc.sistema.business.LoginBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.LoginVo;

public class LoginAction extends Action implements SessionAware {

	private LoginBusiness business = new LoginBusiness();
	private LoginVo loginVo = new LoginVo();
	private Map<String, Object> session;

	public String login() {
		if ((loginVo.getUser() == null || loginVo.getUser() == "")
				&& (loginVo.getPassword() == null || loginVo.getPassword() == "")) {
			return "login";
		}

		boolean loginValido = business.validarLogin(loginVo);
		if (loginValido) {
			session.put("userLogado", loginVo);
			return SUCCESS;
		} else {
			exibirMensagemErro("Login inválido");
			return "login";
		}
	}

	public String logout() {
		session.remove("userLogado");
		return "login";
	}

	public LoginVo getLogin() {
		return loginVo;
	}

	public void setLogin(LoginVo loginVo) {
		this.loginVo = loginVo;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = (SessionMap<String, Object>) map;
	}

}
