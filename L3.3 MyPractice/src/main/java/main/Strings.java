package main;

public interface Strings {
	public final static String SQLiteDriver = "org.sqlite.JDBC";
	public final static String dbSettings = "jdbc:sqlite:";
	public final static String dbName = "data.db";

	public static String concatenateStrings(String... strings) {
		StringBuilder result = new StringBuilder();

		for (String string : strings) {
			result.append(string);
		}

		return result.toString();
	}
}
