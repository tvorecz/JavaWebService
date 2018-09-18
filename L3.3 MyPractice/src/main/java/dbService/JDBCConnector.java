package dbService;

import main.Strings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;

public class JDBCConnector implements Connector, Strings {
	public Connection getConnection() {
		try{
			//registrate jdbc-driver fpr sqlite in driver manager
			DriverManager.registerDriver( (Driver) Class.forName(SQLiteDriver).newInstance());
			String[] connectionStrings = {dbSettings, dbName};
			Connection connection = DriverManager.getConnection(Strings.concatenateStrings(connectionStrings));

			return connection;
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
