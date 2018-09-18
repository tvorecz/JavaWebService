package dbService;
import java.sql.Connection;


public interface Connector {
	public Connection getConnection();
}
