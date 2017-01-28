package Isamm.ing.java.Smartphones.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.DAO.ClientsDAO;
import Isamm.ing.java.Smartphones.DAO.MarquesDAO;
import Isamm.ing.java.Smartphones.DAO.NewsLettersDAO;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Marques;
import Isamm.ing.java.Smartphones.Models.NewsLetters;
import Isamm.ing.java.Smartphones.doAll.Do;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/SignUp.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Clients NewClient = null;
		int idclient = DBAccessControl.getNewID("clients");
		Date dNow = new Date();
		java.sql.Date sqldate = new java.sql.Date(dNow.getTime());
		List<Marques> marques = (List<Marques>) Do.get(Marques.getTableName(), "Statut", "=", ""+DBAccessControl.Actif, "", "", "", "", "List");
		List<NewsLetters> news = new ArrayList<NewsLetters>();
		int id = DBAccessControl.getNewID("newsletters");
		for (Marques marque : marques) {
			if (request.getParameter("" + marque.getID()) != null) {
				NewsLetters New = new NewsLetters(id, idclient, marque.getID());
				news.add(New);
				id++;
			}
		}
		if(news.size() == 0)
		{
			NewsLetters New = new NewsLetters(DBAccessControl.Empty, 0, 0);
			news.add(New);
		}
		NewClient = new Clients(idclient,
				request.getParameter("username"),
				request.getParameter("password"),
				request.getParameter("email"), request.getParameter("nom"),
				request.getParameter("prenom"),
				Integer.parseInt(request.getParameter("ville")),
				request.getParameter("adresse"), Integer.parseInt(request
						.getParameter("typecard")),
				request.getParameter("creditcard"), news.size()==0?0:news.get(0).getID()!=DBAccessControl.Empty?1:0,
				sqldate, 1, DBAccessControl.Inactif,Integer.parseInt(request.getParameter("country")),Integer.parseInt(request.getParameter("age")),request.getParameter("gender"));
		NewClient.setNewNewsLetters(news);
		int res;
		String output = "";
		if (request.getParameter("UP") == null) {
			res = Do.add(Clients.getTableName(), "this", NewClient);
			if(res==DBAccessControl.Error) {output = "check your connection!";}
			else if(res==DBAccessControl.EmailUsed) {output = "Email used";}
			else if(res==DBAccessControl.UsernameUsed) {output = "Username used";}
			else if(res==DBAccessControl.UsernameAndEmailused) {output = "Username and email used";}
			else if(res==DBAccessControl.Cool) {output = "Successufully Signed Up";}
			response.getWriter().println(output);
		} else {
			Clients OldClient = (Clients) request.getSession().getAttribute("SmartphonesUserID");
			NewClient.setID(OldClient.getID());
			NewClient.setActif(OldClient.getActif());
			res = NewClient.save();
			if(res==DBAccessControl.Error) {output = "check your connection!";}
			else if(res==DBAccessControl.EmailUsed) {output = "Email used";}
			else if(res==DBAccessControl.UsernameUsed) {output = "Username used";}
			else if(res==DBAccessControl.UsernameAndEmailused) {output = "Username and email used";}
			else if(res==DBAccessControl.Cool) {output = "Successufully Signed Up";}
			response.getWriter().println(output);
		}
	}
}