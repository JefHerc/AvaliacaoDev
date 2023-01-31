package br.com.soc.sistema.business;

import br.com.soc.sistema.dao.login.LoginDao;
import br.com.soc.sistema.vo.LoginVo;

public class LoginBusiness {

	private LoginDao dao = new LoginDao();
	
	public boolean validarLogin(LoginVo login) {
		boolean isValido = dao.isLoginValido(login);
		return isValido;
	}
	
}
