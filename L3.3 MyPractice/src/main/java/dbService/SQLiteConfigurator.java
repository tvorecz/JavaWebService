package dbService;

import dbService.dataSets.UsersDataSet;
import main.Strings;
import org.hibernate.cfg.Configuration;

public class SQLiteConfigurator implements Configurator, Strings {
	public Configuration getConfigurtion() {
		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(UsersDataSet.class);

		configuration.setProperty("hibernate.dialect", SQLITE_DIALECT);
		configuration.setProperty("hibernate.connection.driver_class", SQLITE_DRIVER);
		configuration.setProperty("hibernate.connection.url", DB_URL);
		configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);

		return configuration;
	}
}
