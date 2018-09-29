package main;

public interface Strings {
	public final static String SPACE = " ";
	public final static String DOT = ".";
	public final static String LEFT_BRAKET = "(";
	public final static String RIGHT_BRAKET = ")";
	public final static String SQLITE_DRIVER = "org.sqlite.JDBC";
	public final static String SQLITE_DIALECT = "org.hibernate.dialect.SQLiteDialect";
	public final static String DB_URL = "jdbc:sqlite:./data.db";
	public final static String DB_SETTINGS = "jdbc:sqlite:";
	public final static String DB_NAME = "data.db";
	public static final String HIBERNATE_SHOW_SQL = "true";
	public static final String HIBERNATE_HBM2DDL_AUTO = "create";

	static public final String ENCODING = "text/html;charset=utf-8";
	static public final String LOGIN = "login";
	static public final String PASSWORD = "password";
	static public final String EMAIL = "email";
	static public final String EMPTY = "";
	static public final String ERROR_EMPTY = "Пустая строка!";
	static public final String ERROR_COLLISION = "Такой пользователь уже есть!";
	static public final String REG_SUCCESS = "Пользователь зарегистрирован!";
	static public final String AUTHORIZED = "Authorized: ";
	static public final String UNAUTHORIZED = "Unauthorized";

	public static String concatenateStrings(String... strings) {
		StringBuilder result = new StringBuilder();

		for (String string : strings) {
			result.append(string);
		}

		return result.toString();
	}
}
