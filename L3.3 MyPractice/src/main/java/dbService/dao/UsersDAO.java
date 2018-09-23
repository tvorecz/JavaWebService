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

	public UsersDAO(Connection connection) {
		dao = new UsersDaoJDBC(connection);
	}

	public UsersDAO(Session session) {
		dao = new UsersDaoHibernate(session);
	}

	public long getUserId(String name) throws SQLException {
		return dao.getUserId(name);
	}

	public UsersDataSet getUser(long id) throws SQLException {
		return dao.getUser(id);
	}

	public void insertUser(String name) throws SQLException {
		dao.insertUser(name);
	}
}

interface DAO {
	long getUserId(String name) throws SQLException;
	UsersDataSet getUser(long id)  throws SQLException;
	void insertUser(String name) throws SQLException;
}

class  UsersDaoJDBC implements DAO {
	private Executor executor;

	UsersDaoJDBC(Connection connection) {
		executor = new Executor(connection);
	}

	public long getUserId(String name) throws SQLException{
		return executor.select("SELECT * FROM users WHERE name = '" + name + "'", result -> {
			result.next();
			return result.getLong("id");
		});
	}

	public UsersDataSet getUser(long id) throws SQLException {
		return executor.select("SELECT * FROM users WHERE id = " + id, result -> {
			result.next();
			return new UsersDataSet(result.getLong("id"), result.getString("name"));
		});
	}

	public void insertUser(String name) throws SQLException {
		executor.update("INSERT INTO users (name) values ('" + name + "')");
	}
}

class UsersDaoHibernate implements DAO {
	private Session session;

	UsersDaoHibernate(Session session) {
		this.session = session;
	}

	public long getUserId(String name) throws SQLException{
		Criteria criteria = session.createCriteria(UsersDataSet.class);
		return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
	}

	public UsersDataSet getUser(long id) throws SQLException {
		return (UsersDataSet) session.get(UsersDataSet.class, id);
	}

	public void insertUser(String name) throws SQLException {
		session.save(new UsersDataSet(name));
	}
}