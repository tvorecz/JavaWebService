package dbService.dataSets;

import main.Strings;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { //for hibernate
	private static final long serialVersionUID = 8706689714326132798L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "login", unique = true, updatable = false)
	private String login;

	@Column(name = "password", unique = false, updatable = true)
	private String password;

	@SuppressWarnings("UnusedDeclaration")
	public UsersDataSet() { //for hibernate
	}

	public UsersDataSet(String login, String password) {
		this.login = login;
		this.password = password;
		id = -1;
	}

	public UsersDataSet(long id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String[] strings = {new Long(id).toString(), Strings.DOT, Strings.SPACE, login, Strings.SPACE, Strings.LEFT_BRAKET, password, Strings.RIGHT_BRAKET};
		return Strings.concatenateStrings();
	}
}
