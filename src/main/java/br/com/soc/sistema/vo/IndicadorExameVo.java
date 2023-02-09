package br.com.soc.sistema.vo;

public class IndicadorExameVo {
	
	private int rowid;
	private String nomeExame;
	private String qtdExame;
	
	public IndicadorExameVo() {
	}
	
	public IndicadorExameVo(int rowid, String nomeExame, String qtdExame) {
		super();
		this.rowid = rowid;
		this.nomeExame = nomeExame;
		this.qtdExame = qtdExame;
	}
	
	public int getRowid() {
		return rowid;
	}
	
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	
	public String getNomeExame() {
		return nomeExame;
	}
	
	public void setNomeExame(String nomeExame) {
		this.nomeExame = nomeExame;
	}
	
	public String getQtdExame() {
		return qtdExame;
	}
	
	public void setQtdExame(String qtdExame) {
		this.qtdExame = qtdExame;
	}

	@Override
	public String toString() {
		return "IndicadorExameVo [rowid=" + rowid + ", nomeExame=" + nomeExame + ", qtdExame=" + qtdExame + "]";
	}
	
}
