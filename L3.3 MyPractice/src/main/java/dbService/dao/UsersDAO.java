package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
	private DAO dao;

	public UsersDAO(Connection connection) throws SQLException{
		dao = new UsersDaoJDBC(connection);
	}

	public UsersDAO(Session session) {
		dao = new UsersDaoHibernate(session);
	}

	public long getUserId(String login) throws SQLException {
		return dao.getUserId(login);
	}

	public UsersDataSet getUser(long id) throws SQLException {
		return dao.getUser(id);
	}

	public void insertUser(String login, String password) throws SQLException {
		dao.insertUser(login, password);
	}
}

interface DAO {
	long getUserId(String login) throws SQLException;
	UsersDataSet getUser(long id)  throws SQLException;
	void insertUser(String login, String password) throws SQLException;
}

class  UsersDaoJDBC implements DAO {
	private Executor executor;

	UsersDaoJDBC(Connection connection) throws SQLException {
		executor = new Executor(connection);

		createTable();
	}

	public long getUserId(String name) throws SQLException{
		return executor.select("SELECT * FROM users WHERE login = '" + name + "'", result -> {
			result.next();
			return result.getLong("id");
		});
	}

	public UsersDataSet getUser(long id) throws SQLException {
		return executor.select("SELECT * FROM users WHERE id = " + id, result -> {
			result.next();
			return new UsersDataSet(result.getLong("id"), result.getString("login"), result.getString("password"));
		});
	}

	public void insertUser(String login, String password) throws SQLException {
		executor.update("INSERT INTO users (name, password) values ('" + login + "', '" + password + "')");
	}

	private void createTable() throws SQLException  {
		executor.update("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, login TEXT NOT NULL, password TEXT NOT NULL)");
	}
}

class UsersDaoHibernate implements DAO {
	private Session session;

	UsersDaoHibernate(Session session) {
		this.session = session;
	}

	public long getUserId(String login) throws SQLException{
		Criteria criteria = session.createCriteria(UsersDataSet.class);
		return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
	}

	public UsersDataSet getUser(long id) throws SQLException {
		return (UsersDataSet) session.get(UsersDataSet.class, id);
	}

	public void insertUser(String login, String password) throws SQLException {
		session.save(new UsersDataSet(login, password));
	}
}