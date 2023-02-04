package br.com.soc.sistema.dao.exames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;

public class ExameDao extends Dao {

	public void insertExame(String nomeExame) {
		StringBuilder query = new StringBuilder("INSERT INTO exame (nm_exame) values (?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setString(1, nomeExame);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ExameVo> findAllExames() {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			ExameVo vo = null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));

				exames.add(vo);
			}
			return exames;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<ExameVo> findAllByNome(String nome) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame ")
				.append("WHERE lower(nm_exame) like lower(?)");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setString(1, "%" + nome + "%");

			try (ResultSet rs = ps.executeQuery()) {
				
				ExameVo vo = null;
				List<ExameVo> exames = new ArrayList<>();

				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));

					exames.add(vo);
				}
				return exames;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public ExameVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame ")
				.append("WHERE rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setInt(1, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				ExameVo vo = null;

				while (rs.next()) {
					vo = new ExameVo();
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

	public boolean deleteExame(Integer codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM exame WHERE rowid = ?");
		int verificadorModificacao = 0;
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setInt(1, codigo);
			verificadorModificacao = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (verificadorModificacao > 0 ? true : false);
	}

	public boolean editarExame(ExameVo exameVo) {
		StringBuilder query = new StringBuilder("UPDATE exame SET nm_exame = ? WHERE rowid = ?");
		int verificadorModificacao = 0;

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int cod = Integer.parseInt(exameVo.getRowid());
			ps.setString(1, exameVo.getNome());
			ps.setInt(2, cod);
			verificadorModificacao = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (verificadorModificacao > 0 ? true : false);

	}

}