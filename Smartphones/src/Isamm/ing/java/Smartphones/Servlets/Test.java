package Isamm.ing.java.Smartphones.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.DAO.ClientsDAO;
import Isamm.ing.java.Smartphones.Models.Actualites;
import Isamm.ing.java.Smartphones.Models.Administrateurs;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Countries;
import Isamm.ing.java.Smartphones.Models.Marques;
import Isamm.ing.java.Smartphones.Models.NewsLetters;
import Isamm.ing.java.Smartphones.Models.Produits;
import Isamm.ing.java.Smartphones.Models.Typecard;
import Isamm.ing.java.Smartphones.Models.Ville;
import Isamm.ing.java.Smartphones.doAll.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Date dNow = new Date();
		java.sql.Date sqldate = new java.sql.Date(Date.parse("2014/05/26"));
		System.out.println(sqldate);
		/*Clients client = new Clients(DBAccessControl.getNewID(Clients.getTableName()), "username22", "password", "minemail@mail.com25", "nom", "prenom", 1, "adresse", 1, "0000000000000000", 0, sqldate, 1, 1, 1, 25, "Male");
	    for(int i=0;i<80;i++)
	    {
	    	Do.add(Clients.getTableName(), "this", client);
	    	client.setID(client.getID()+1);
	    	client.setUsername("username"+i);
	    	client.setEmail("monemail@mail.com"+i);
	    }*/
	}
}
