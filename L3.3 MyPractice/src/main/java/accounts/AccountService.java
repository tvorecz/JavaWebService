package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import main.Strings;
import org.sqlite.core.DB;

public class AccountService {
	private final DBService dbService;

	public AccountService(DBService dbService) {
		this.dbService = dbService;
	}

	public void addNewUser(String login, String password) throws DBException {
		dbService.addUser(login, password);
	}

	public boolean checkUserByPassword(String login, String password) throws DBException {
		return password.equals(dbService.getPasswordByLogin(login));
	}

	public boolean checkUserByLogin(String login) throws DBException {
		return dbService.getUserIdByLogin(login) != - 1 ? true : false;
	}
}
