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

public class SignUpServlet extends HttpServlet implements Strings {
	private final AccountService accountService;

	public SignUpServlet(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);

		if ((login == null || password == null) || (login.equals(EMPTY) || password.equals(EMPTY)) || (checkSpaces(login) || checkSpaces(password))) {
			response.setContentType(ENCODING);
			response.getWriter().println(ERROR_EMPTY);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (accountService.getUserByLogin(login) != null) {
			response.setContentType(ENCODING);
			response.getWriter().println(ERROR_COLLISION);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		UserProfile profile = new UserProfile(login, password, EMPTY);
		accountService.addNewUser(profile);
		response.setContentType(ENCODING);
		response.getWriter().println(REG_SUCCESS);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	private boolean checkSpaces(String forCheck) {
		for (char character : forCheck.toCharArray()) {
			if (character != ' ') return false;
		}

		return true;
	}
}
