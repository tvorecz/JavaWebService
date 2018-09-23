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

	@Column(name = "name", unique = true, updatable = false)
	private String name;

	@SuppressWarnings("UnusedDeclaration")
	public UsersDataSet() { //for hibernate
	}

	public UsersDataSet(String name) {
		this.name = name;
		id = -1;
	}

	public UsersDataSet(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String[] strings = {new Long(id).toString(), Strings.DOT, Strings.SPACE, name};
		return Strings.concatenateStrings();
	}
}
