package servlets;

import accounts.AccountService;
import main.Strings;
import dbService.DBService;
import dbService.JDBCConnector;
import dbService.SQLiteConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet implements Strings  {
	private final AccountService accountService;

	public SignInServlet(AccountService accountService) {
		this.accountService = accountService;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login  = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);

		response.setContentType(ENCODING);

		try {
			if(login == null || password == null || (login.equals(EMPTY) || password.equals(EMPTY )) || (checkSpaces(login) || checkSpaces(password))) {
				response.getWriter().println(ERROR_EMPTY);
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

				return;
			}

			if(!accountService.checkUserByLogin(login)) {
				response.getWriter().println(ERROR_EMPTY);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				return;
			}

			if(accountService.checkUserByPassword(login, password)) {
				response.getWriter().println(Strings.concatenateStrings(AUTHORIZED, login));
				response.setStatus(HttpServletResponse.SC_OK);

				return;
			}

			else {
				response.getWriter().println(ERROR_EMPTY);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				return;
			}

		}

		catch (Exception ex) {
			response.getWriter().println(ex.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	private boolean checkSpaces(String forCheck) {
		for (char character : forCheck.toCharArray()) {
			if (character != ' ') return false;
		}

		return true;
	}
}
