package br.com.soc.sistema.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.LoginVo;

public class LoginDao extends Dao {

	public boolean isLoginValido(LoginVo login) {
		StringBuilder query = new StringBuilder("SELECT * FROM login WHERE usuario = ? AND senha = ?");
		boolean isValido = false;
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setString(1, login.getUser());
			ps.setString(2, login.getPassword());
			try (ResultSet rs = ps.executeQuery()) {
				isValido = rs.next();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isValido;
	}

}
