package servlets;

import main.Strings;
import dbService.DBService;
import dbService.JDBCConnector;
import dbService.SQLiteConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet implements Strings  {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {


	}

	private boolean checkSpaces(String forCheck) {
		for (char character : forCheck.toCharArray()) {
			if (character != ' ') return false;
		}

		return true;
	}
}
