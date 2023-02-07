package br.com.soc.sistema.dao.funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioDao extends Dao {

	public void insertFuncionario(String nomeFuncionario) {
		StringBuilder query = new StringBuilder("INSERT INTO funcionario (nm_funcionario) values (?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setString(1, nomeFuncionario);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<FuncionarioVo> findAllFuncionarios() {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ORDER BY nm_funcionario");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			FuncionarioVo vo = null;
			List<FuncionarioVo> funcionarios = new ArrayList<>();
			while (rs.next()) {
				vo = new FuncionarioVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));

				funcionarios.add(vo);
			}
			return funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<FuncionarioVo> findAllByNome(String nome) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
				.append("WHERE lower(nm_funcionario) like lower(?) ORDER BY nm_funcionario");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setString(i, "%" + nome + "%");

			try (ResultSet rs = ps.executeQuery()) {
				FuncionarioVo vo = null;
				List<FuncionarioVo> funcionarios = new ArrayList<>();

				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));

					funcionarios.add(vo);
				}
				return funcionarios;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public FuncionarioVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
				.append("WHERE rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setInt(1, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				FuncionarioVo vo = null;

				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteFuncionario(Integer codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM funcionario WHERE rowid = ?");
		int verificadorModificacao = 0;

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;

			ps.setInt(i, codigo);
			verificadorModificacao = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (verificadorModificacao > 0 ? true : false);
	}

	public boolean deleteFuncionarioComAgendamento(Integer codigo) {
		StringBuilder query1 = new StringBuilder("DELETE FROM funcionario WHERE rowid = ?;");
		StringBuilder query2 = new StringBuilder("DELETE FROM agendamento WHERE cd_funcionario = ?;");
		boolean isQueryExecutada = false;

		try (Connection con = getConexao(); 
				PreparedStatement ps1 = con.prepareStatement(query1.toString());
				PreparedStatement ps2 = con.prepareStatement(query2.toString())) {
			con.setAutoCommit(false);
			
			ps1.setInt(1, codigo);
			ps2.setInt(1, codigo);
			
			int count1 = ps1.executeUpdate();
			int count2 = ps2.executeUpdate();
			
			if (count1 > 0 && count2 > 0) {
		        con.commit();
		        isQueryExecutada = true;
		      } else {
		        con.rollback();
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isQueryExecutada;
	}

	public boolean editarFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder("UPDATE funcionario SET nm_funcionario = ? WHERE rowid = ?");
		int verificadorModificacao = 0;

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int cod = Integer.parseInt(funcionarioVo.getRowid());
			ps.setString(1, funcionarioVo.getNome());
			ps.setInt(2, cod);
			verificadorModificacao = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (verificadorModificacao > 0 ? true : false);

	}

}
