package main;

public interface Strings {
	public final static String SPACE = " ";
	public final static String DOT = ".";
	public final static String SQLITE_DRIVER = "org.sqlite.JDBC";
	public final static String SQLITE_DIALECT = "org.hibernate.dialect.SQLiteDialect";
	public final static String DB_URL = "jdbc:sqlite:./data.db";
	public final static String DB_SETTINGS = "jdbc:sqlite:";
	public final static String DB_NAME = "data.db";
	public static final String HIBERNATE_SHOW_SQL = "true";
	public static final String HIBERNATE_HBM2DDL_AUTO = "create";

	public static String concatenateStrings(String... strings) {
		StringBuilder result = new StringBuilder();

		for (String string : strings) {
			result.append(string);
		}

		return result.toString();
	}
}
