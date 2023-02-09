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
import br.com.soc.sistema.vo.IndicadorExameVo;

public class AgendamentoDao extends Dao {

	public void insertAgendamento(AgendamentoVo agendamentoVo) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO agendamento (cd_funcionario, cd_exame, data_agendamento) VALUES (?, ?, ?)");
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
		StringBuilder query = new StringBuilder("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, ")
				.append("age.cd_exame, exa.nm_exame, age.data_agendamento ")
				.append("FROM agendamento age JOIN funcionario func ON age.cd_funcionario = func.rowid ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid ORDER BY age.data_agendamento;");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			AgendamentoVo vo = null;
			List<AgendamentoVo> agendamentos = new ArrayList<>();
			while (rs.next()) {
				vo = setAgendamentoVo(rs);
				agendamentos.add(vo);
			}
			return agendamentos;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public void deleteAgendamento(int codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM agendamento WHERE rowid = ?");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setInt(1, codigo);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isFuncionairoAgendado(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT EXISTS(SELECT 1 FROM agendamento WHERE cd_funcionario = ?)");
		boolean isAgendado = false;
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setInt(1, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					isAgendado = rs.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAgendado;
	}

	public void editarAgendamento(AgendamentoVo agendamentoVo) {
		StringBuilder query = new StringBuilder("UPDATE agendamento SET cd_funcionario = ?, ")
				.append("cd_exame = ?, data_agendamento = ? WHERE rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int cdFuncionario = Integer.parseInt(agendamentoVo.getFuncionario().getRowid());
			int cdExame = Integer.parseInt(agendamentoVo.getExame().getRowid());
			ps.setInt(1, cdFuncionario);
			ps.setInt(2, cdExame);
			ps.setObject(3, agendamentoVo.getDataAgendamento());
			ps.setInt(4, agendamentoVo.getRowid());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AgendamentoVo findByCodigo(int codigo) {
		StringBuilder query = new StringBuilder("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, ")
				.append("age.cd_exame, exa.nm_exame, age.data_agendamento FROM agendamento age ")
				.append("JOIN funcionario func ON age.cd_funcionario = func.rowid ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid WHERE age.rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				AgendamentoVo vo = null;

				while (rs.next()) {
					vo = setAgendamentoVo(rs);
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<AgendamentoVo> findAllByFuncionario(String nomeFuncionario) {
		StringBuilder query = new StringBuilder("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, ")
				.append("age.cd_exame, exa.nm_exame, age.data_agendamento FROM agendamento age ")
				.append("JOIN funcionario func ON age.cd_funcionario = func.rowid ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid ")
				.append("WHERE LOWER(func.nm_funcionario) LIKE LOWER(?)");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setString(1, "%" + nomeFuncionario + "%");
			try (ResultSet rs = ps.executeQuery()) {
				AgendamentoVo vo = null;
				List<AgendamentoVo> agendamentos = new ArrayList<>();
				while (rs.next()) {
					vo = setAgendamentoVo(rs);

					agendamentos.add(vo);
				}
				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<AgendamentoVo> findAllByExame(String nomeExame) {
		StringBuilder query = new StringBuilder("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, ")
				.append("age.cd_exame, exa.nm_exame, age.data_agendamento ")
				.append("FROM agendamento age JOIN funcionario func ON age.cd_funcionario = func.rowid ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid ")
				.append("WHERE LOWER(exa.nm_exame) LIKE LOWER(?)");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setString(1, "%" + nomeExame + "%");
			try (ResultSet rs = ps.executeQuery()) {
				AgendamentoVo vo = null;
				List<AgendamentoVo> agendamentos = new ArrayList<>();
				while (rs.next()) {
					vo = setAgendamentoVo(rs);

					agendamentos.add(vo);
				}
				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<AgendamentoVo> findAllByData(LocalDate data) {
		StringBuilder query = new StringBuilder("SELECT age.rowid, age.cd_funcionario, func.nm_funcionario, ")
				.append("age.cd_exame, exa.nm_exame, age.data_agendamento ")
				.append("FROM agendamento age JOIN funcionario func ON age.cd_funcionario = func.rowid ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid WHERE data_agendamento = ?;");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setObject(1, data);
			try (ResultSet rs = ps.executeQuery()) {
				AgendamentoVo vo = null;
				List<AgendamentoVo> agendamentos = new ArrayList<>();
				while (rs.next()) {
					vo = setAgendamentoVo(rs);
					agendamentos.add(vo);
				}
				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public boolean isExamePossuiAgendamentos(Integer cod) {
		boolean exameAgendado = false;
		StringBuilder query = new StringBuilder("SELECT EXISTS(SELECT 1 FROM agendamento WHERE cd_exame = ?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setInt(1, cod);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					exameAgendado = rs.getBoolean(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exameAgendado;
	}

	public boolean isAgendado(AgendamentoVo agendamentoVo) {
		boolean isAgendado = false;
		StringBuilder query = new StringBuilder("SELECT EXISTS(SELECT 1 FROM agendamento WHERE cd_exame = ? ")
				.append("AND cd_funcionario = ? AND data_agendamento = ?);");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setInt(1, Integer.parseInt(agendamentoVo.getExame().getRowid()));
			ps.setInt(2, Integer.parseInt(agendamentoVo.getFuncionario().getRowid()));
			ps.setObject(3, agendamentoVo.getDataAgendamento());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					isAgendado = rs.getBoolean(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isAgendado;
	}

	public List<AgendamentoVo> findAllByRangeData(LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder query = new StringBuilder("SELECT age.cd_funcionario, func.nm_funcionario, ")
				.append("age.cd_exame, exa.nm_exame, age.data_agendamento ")
				.append("FROM agendamento age JOIN funcionario func ON age.cd_funcionario = func.rowid ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid ")
				.append("WHERE (data_agendamento >= ? AND data_agendamento <= ?) ORDER BY data_agendamento;");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setObject(1, dataInicio);
			ps.setObject(2, dataFim);
			try (ResultSet rs = ps.executeQuery()) {
				AgendamentoVo vo = null;
				List<AgendamentoVo> agendamentos = new ArrayList<>();
				while (rs.next()) {
					vo = new AgendamentoVo();
					vo.setFuncionario(
							new FuncionarioVo(rs.getString("cd_funcionario"), rs.getString("nm_funcionario")));
					vo.setExame(new ExameVo(rs.getString("cd_exame"), rs.getString("nm_exame")));
					vo.setDataAgendamento(rs.getObject("data_agendamento", LocalDate.class));

					agendamentos.add(vo);
				}
				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public AgendamentoVo setAgendamentoVo(ResultSet rs) throws SQLException {
		AgendamentoVo vo = new AgendamentoVo();
		vo.setRowid(rs.getInt("rowid"));
		vo.setFuncionario(new FuncionarioVo(rs.getString("cd_funcionario"), rs.getString("nm_funcionario")));
		vo.setExame(new ExameVo(rs.getString("cd_exame"), rs.getString("nm_exame")));
		vo.setDataAgendamento(rs.getObject("data_agendamento", LocalDate.class));
		return vo;
	}

	public List<IndicadorExameVo> indicadorByRangeData(LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder query = new StringBuilder("SELECT age.cd_exame, exa.nm_exame, COUNT(age.cd_exame) as total ")
				.append("FROM agendamento age ")
				.append("JOIN exame exa ON age.cd_exame = exa.rowid ")
				.append("WHERE (data_agendamento >= ? AND data_agendamento <= ?) ")
				.append("GROUP BY age.cd_exame ")
				.append("ORDER BY total DESC ")
				.append("LIMIT 5;");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			ps.setObject(1, dataInicio);
			ps.setObject(2, dataFim);
			try (ResultSet rs = ps.executeQuery()) {
				IndicadorExameVo vo = null;
				List<IndicadorExameVo> agendamentos = new ArrayList<>();
				while (rs.next()) {
					vo = new IndicadorExameVo();
					vo.setRowid(rs.getInt("cd_exame"));
					vo.setNomeExame(rs.getString("nm_exame"));
					vo.setQtdExame(rs.getString("total"));
					agendamentos.add(vo);
				}
				return agendamentos;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
