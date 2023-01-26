package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarAgendamentos {
	ID("1", "ID"), 
	FUNCIONARIO("2", "FUNCIONÁRIO"),
	EXAME("3", "EXAME"), 
	DATA("4", "DATA");
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarAgendamentos> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarAgendamentos.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarAgendamentos(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarAgendamentos buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarAgendamentos opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarAgendamentos> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}