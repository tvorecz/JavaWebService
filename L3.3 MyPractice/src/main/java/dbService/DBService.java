package dbService;

//import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
	private Service service;

	public DBService(JDBCConnector connector) {
		service = new DBServiceJDBC(connector);
	}

	UsersDataSet getUser(long id) throws DBException {
		return service.getUser(id);
	}

	long addUser(String name) throws DBException {
		return service.addUser(name);
	}

	void cleanUp() throws DBException {
		service.cleanUp();
	}
}

interface Service {
	UsersDataSet getUser(long id) throws DBException;
	long addUser(String name) throws DBException;
	void cleanUp() throws DBException;
}

class DBServiceJDBC implements Service {
	private final Connection connection;

	public DBServiceJDBC(JDBCConnector connector) {
		connection = connector.getConnection();
	}

	UsersDataSet getUser(long id) throws DBException {

	}

	long addUser(String name) throws DBException {

	}

	void cleanUp() throws DBException {

	}

}

class DBServiceHibernate {
	UsersDataSet getUser(long id) throws DBException {

	}

	long addUser(String name) throws DBException {

	}

	void cleanUp() throws DBException {

	}
}
