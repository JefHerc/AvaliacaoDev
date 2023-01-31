package br.com.soc.sistema.vo;

public class LoginVo {

	private Integer rowid;
	private String user;
	private String password;

	public LoginVo(Integer rowid, String user, String password) {
		super();
		this.rowid = rowid;
		this.user = user;
		this.password = password;
	}

	public LoginVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVo [rowid=" + rowid + ", user=" + user + ", password=" + password + "]";
	}

}
