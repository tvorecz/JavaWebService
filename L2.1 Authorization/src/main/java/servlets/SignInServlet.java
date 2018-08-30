package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import service.Strings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet implements Strings {
	private final AccountService accountService;

	public SignInServlet(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);

		if ((login == null || password == null) || (login.equals(EMPTY) || password.equals(EMPTY)) || (checkSpaces(login) || checkSpaces(password))) {
			response.setContentType(ENCODING);
			response.getWriter().println(UNAUTHORIZED);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		if (accountService.getUserByLogin(login) != null) {
			StringBuffer stringResponse = new StringBuffer(AUTHORIZED);
			stringResponse.append(login);
			response.setContentType(ENCODING);
			response.getWriter().println(stringResponse.toString());
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	private boolean checkSpaces(String forCheck) {
		for (char character : forCheck.toCharArray()) {
			if (character != ' ') return false;
		}

		return true;
	}
}
