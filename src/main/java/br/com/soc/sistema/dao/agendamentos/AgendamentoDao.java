package br.com.soc.sistema.dao.agendamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.AgendamentoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class AgendamentoDao extends Dao{

	public void insertAgendamento(AgendamentoVo agendamentoVo) {
		StringBuilder query = new StringBuilder("INSERT INTO agendamento (cd_funcionario, cd_exame, data_agendamento) VALUES (?, ?, ?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int cdFuncionario = Integer.parseInt(agendamentoVo.getFuncionario().getRowid());
			int cdExame = Integer.parseInt(agendamentoVo.getExame().getRowid());
			ps.setInt(1, cdFuncionario);
			ps.setInt(2, cdExame);
			ps.setObject(3, agendamentoVo.getDataAgendamento());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<AgendamentoVo> findAllAgendamentos() {
		StringBuilder query = new StringBuilder();
			query.append("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, age.cd_exame, exa.nm_exame, age.data_agendamento ");
			query.append("FROM agendamento age ");
			query.append("JOIN funcionario func ON age.cd_funcionario = func.rowid ");
			query.append("JOIN exame exa ON age.cd_exame = exa.rowid;");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			AgendamentoVo vo = null;
			List<AgendamentoVo> agendamentos = new ArrayList<>();
			while (rs.next()) {
				vo = new AgendamentoVo();
				vo.setRowid(rs.getInt("rowid"));
				vo.setFuncionario(new FuncionarioVo(rs.getString("cd_funcionario"), rs.getString("nm_funcionario")));
				vo.setExame(new ExameVo(rs.getString("cd_exame"), rs.getString("nm_exame")));
				vo.setDataAgendamento(rs.getObject("data_agendamento", LocalDate.class));

				agendamentos.add(vo);
			}
			return agendamentos;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
	
	public AgendamentoVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, age.cd_exame, exa.nm_exame, age.data_agendamento ");
				query.append("FROM agendamento age ");
				query.append("JOIN funcionario func ON age.cd_funcionario = func.rowid ");
				query.append("JOIN exame exa ON age.cd_exame = exa.rowid ");
				query.append("WHERE age.rowid = ?");


		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				AgendamentoVo vo = null;

				while (rs.next()) {
					vo = new AgendamentoVo();
					vo.setRowid(rs.getInt("rowid"));
					vo.setFuncionario(new FuncionarioVo(rs.getString("cd_funcionario"), rs.getString("nm_funcionario")));
					vo.setExame(new ExameVo(rs.getString("cd_exame"), rs.getString("nm_exame")));
					vo.setDataAgendamento(rs.getObject("data_agendamento", LocalDate.class));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


//	public List<AgendamentoVo> findAllByData(LocalDate data) {
//		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
//				.append("WHERE lower(nm_funcionario) like lower(?)");
//
//		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
//			int i = 1;
//
//			ps.setString(i, "%" + nome + "%");
//
//			try (ResultSet rs = ps.executeQuery()) {
//				FuncionarioVo vo = null;
//				List<FuncionarioVo> funcionarios = new ArrayList<>();
//
//				while (rs.next()) {
//					vo = new FuncionarioVo();
//					vo.setRowid(rs.getString("id"));
//					vo.setNome(rs.getString("nome"));
//
//					funcionarios.add(vo);
//				}
//				return funcionarios;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Collections.emptyList();
//	}


	public void deleteAgendamento(Integer codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM agendamento WHERE rowid = ?");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setInt(1, codigo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
//	public void editarAgendamento(AgendamentoVo agendamentoVo) {
//		StringBuilder query = new StringBuilder("UPDATE agendamento SET cd_funcionario = ?, cd_exame = ?, data_agendamento = ? WHERE rowid = ?");
//		
//		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
//			int cdFuncionario = Integer.parseInt(agendamentoVo.getCdFuncionario());
//			int cdExame = Integer.parseInt(agendamentoVo.getCdExame());
//			int rowid = Integer.parseInt(agendamentoVo.getRowid());
//			ps.setInt(1, cdFuncionario);
//			ps.setInt(2, cdExame);
//			ps.setObject(3, agendamentoVo.getDataAgendamento());
//			ps.setInt(4, rowid);
//			ps.executeUpdate() ;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
