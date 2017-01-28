package Isamm.ing.java.Smartphones.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.doAll.Do;

public class ClientSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/Login.jsp").forward(req,
				resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		Clients Client = (Clients) Do.login(Clients.getTableName(), req.getParameter("user"), req.getParameter("pass"));
		System.out.println(req.getParameter("user")+" , "+req.getParameter("pass"));
		if (Client == null) {
			resp.getWriter().println("Check Your Connection");
		} else {
			if (Client.getID() == DBAccessControl.Empty || Client.getActif() == DBAccessControl.Archive) {
				resp.getWriter().println("<div class=\"alert alert-important\">"+
		"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"+
		"<strong>Account Not Found!</strong>"+
		"</div>");
			} else if (Client.getActif() == DBAccessControl.Inactif) {
				resp.getWriter().println("<div class=\"alert alert-inverse\">"+
		"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"+
		"<strong>Account Not actif!</strong>"+
		"</div>");
			}else if (Client.getActif() == DBAccessControl.Banned) {
				resp.getWriter().println("<div class=\"alert alert-important\">"+
						"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"+
						"<strong>Account Suspended!</strong>"+
						"</div>");
			} else {
				Client.setPassword(Encription.encrypt(Client.getPassword(),
						Encription.getKey("password")));
				req.getSession().setAttribute("SmartphonesUserID", Client);
				resp.getWriter().println("<div class=\"alert alert-success\">"+
						"<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"+
						"<strong>you'r successfully signed in :)!</strong>"+
						"</div>");
			}
		}
	}
}
