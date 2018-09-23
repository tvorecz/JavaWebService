package dbService;

//import dbService.dao.UsersDAO;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

public class DBService {
	private Service service;

	public DBService(Connector connector) {
		service = new DBServiceJDBC(connector);
	}

	public DBService(Configurator configurator) {
		service = new DBServiceHibernate(configurator);
	}

	UsersDataSet getUser(long id) throws DBException {
		return service.getUser(id);
	}

	long addUser(String login, String password) throws DBException {
		return service.addUser(login, password);
	}
}

interface Service {
	UsersDataSet getUser(long id) throws DBException;
	long addUser(String login, String password) throws DBException;
}

class DBServiceJDBC implements Service {
	private final Connection connection;

	public DBServiceJDBC(Connector connector) {
		connection = connector.getConnection();
	}

	public UsersDataSet getUser(long id) throws DBException {
		try {
			UsersDAO dao = new UsersDAO(connection);
			return dao.getUser(id);
		}

		catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public long addUser(String login, String password) throws DBException {
		try {
			connection.setAutoCommit(false);
			UsersDAO dao = new UsersDAO(connection);
			dao.insertUser(login, password);
			connection.commit();
			return dao.getUserId(login);
		}

		catch (SQLException e) {
			try {
				connection.rollback();
			}

			catch (SQLException ignore) {
			}

			throw new DBException(e);
		}

		finally {
			try {
				connection.setAutoCommit(true);
			}

			catch (SQLException ignore) {
			}
		}
	}
}

class DBServiceHibernate implements Service {
	private final SessionFactory sessionFactory;

	DBServiceHibernate(Configurator configurator) {
		Configuration configuration = configurator.getConfigurtion();
		sessionFactory = createSessionFactory(configuration);
	}

	public UsersDataSet getUser(long id) throws DBException {
		try {
			Session session = sessionFactory.openSession();
			UsersDAO dao = new UsersDAO(session);
			UsersDataSet user = dao.getUser(id);
			session.close();
			return user;
		}

		catch (SQLException e) {
			throw  new DBException(e);
		}
	}

	public long addUser(String login, String password) throws DBException {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			UsersDAO dao = new UsersDAO(session);
			dao.insertUser(login, password);
			transaction.commit();
			session.close();
			return getUserId(login);
		}

		catch (SQLException e) {
			throw new DBException(e);
		}
	}

	private  long getUserId(String login) throws DBException {
		try {
			Session session = sessionFactory.openSession();
			UsersDAO dao = new UsersDAO(session);
			session.close();
			return dao.getUserId(login);
		}

		catch (SQLException e) {
			throw new DBException(e);
		}
	}

	private SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
