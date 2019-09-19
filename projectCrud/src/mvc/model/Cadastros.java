package mvc.model;

public class Cadastros {
	private Integer id = 0;
	private String login;
	private String senha;
	private String senhaCheck;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenhaCheck() {
		return this.senhaCheck;
	}

	public void setSenhaCheck(String senhaCheck) {
		this.senhaCheck = senhaCheck;
	}
}