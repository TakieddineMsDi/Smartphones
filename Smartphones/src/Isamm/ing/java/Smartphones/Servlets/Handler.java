package Isamm.ing.java.Smartphones.Servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Models.Actualites;
import Isamm.ing.java.Smartphones.Models.Administrateurs;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Marques;
import Isamm.ing.java.Smartphones.Models.NewsLetters;
import Isamm.ing.java.Smartphones.Models.Produits;
import Isamm.ing.java.Smartphones.doAll.Do;

public class Handler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Handler() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/AdminPanel/Handler.jsp").forward(
				request, response);
	}

	private long sizeInBytes;
	private String fieldName;
	private boolean isInMemory;

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isInMemory() {
		return isInMemory;
	}

	public void setInMemory(boolean isInMemory) {
		this.isInMemory = isInMemory;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		//Handle Actualite
		if (request.getParameter("addactualite") != null
				|| request.getParameter("editactualite") != null) {
			int Owner = 0;
			Actualites actualite = null;
			String action = null;
			if (request.getParameter("addactualite") != null) {
				Owner = ((Administrateurs) request.getSession()
						.getAttribute("SmartphonesAdminID")).getID();
				action = "add";
			}
			if (request.getParameter("editactualite") != null) {
				actualite = (Actualites) Do.get(
						Actualites.getTableName(), "ID", "=",
						"" + request.getParameter("editactualite"), "", "",
						"", "", "Unique");
				action = "edit";
			}
			int ID = 0;
			if(action.equals("add")){ID = DBAccessControl.getNewID("actualites");}
			if(action.equals("edit")){ ID = actualite.getID();}
			String Titre = null;
			String description = null;
			String URL = null;
			File file;
			int maxFileSize = 5000 * 1024;
			int maxMemSize = 5000 * 1024;
			ServletContext context = request.getServletContext();
			String filePath = context.getInitParameter("actualites-upload");
			// Verify the content type
			String contentType = request.getContentType();
			if ((contentType.indexOf("multipart/form-data") >= 0)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// maximum size that will be stored in memory
				factory.setSizeThreshold(maxMemSize);
				// Location to save data that is larger than maxMemSize.
				factory.setRepository(new File(context
						.getInitParameter("temp-upload")));

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// maximum file size to be uploaded.
				upload.setSizeMax(maxFileSize);
				try {
					// Parse the request to get file items.
					List<?> fileItems = upload.parseRequest(request);
					// Process the uploaded file items
					Iterator<?> i = fileItems.iterator();
					
					while (i.hasNext()) {
						FileItem fi = (FileItem) i.next();
						if (fi.isFormField()) {
							if (fi.getFieldName().equals("titre")){
								Titre = fi.getString();}
							if (fi.getFieldName().equals("description")){
								description = fi.getString();}
						}
					}
					i = fileItems.iterator();
					while (i.hasNext()) {
						FileItem fi = (FileItem) i.next();
						if (!fi.isFormField()) {
							// Get the uploaded file parameters
							setFieldName(fi.getFieldName());
							URL = fi.getName();
							setInMemory(fi.isInMemory());
							setSizeInBytes(fi.getSize());
							if(actualite==null)
							{
								actualite = new Actualites();
							}
							if(!URL.equals("")&&URL!=null)
							{
								URL = Titre+"_"+ID+"_"+Owner+"_"+Do.Count(Actualites.getTableName(), "", "", "", "", "", "", "")+"_"+Do.Count(Actualites.getTableName(), "IDAdmin", "=", ""+Owner, "", "", "", "")+URL.substring(URL.lastIndexOf("."));
								file = new File(filePath + URL);
						        fi.write(file);
								actualite.setURL(URL);
								response.getWriter().println(
										"<script type=\"text/javascript\">"+
							                "parent.document.getElementById('res').className = \"alert alert-success\";"
							               + "parent.document.getElementById('tit').innerHTML = \"image successfuly uploaded\";"
							               + "parent.document.getElementById('val').innerHTML = \"\";"+
												 "</script>");
							if(action.equals("add"))
							{
							Date dNow = new Date();
							java.sql.Date sqldate = new java.sql.Date(dNow.getTime());
							actualite.setDate(sqldate);
							actualite.setIDAdmin(Owner);
							actualite.setStatut(DBAccessControl.Inactif);
							actualite.setID(ID);
							}
							}
								actualite.setTitre(Titre);
								actualite.setDescription(description);
						}
					}
					int res = 0;
					if(action.equals("add"))
					{
					res = Do.add(Actualites.getTableName(), "this", actualite);
					if(res == DBAccessControl.Cool)
					{
						response.getWriter().println(
								"<script type=\"text/javascript\">"+
					                "parent.document.getElementById('res').className = \"alert alert-success\";"
					               + "parent.document.getElementById('tit').innerHTML = \"Actualitie added successfully\";"
					               + "parent.document.getElementById('val').innerHTML = \"want to add another ?\";"+
										 "</script>");
					}
					else{
						response.getWriter().println(
								"<script type=\"text/javascript\">"+
					                "parent.document.getElementById('res').className = \"alert alert-error\";"
					               + "parent.document.getElementById('tit').innerHTML = \"actualitie was not added\";"
					               + "parent.document.getElementById('val').innerHTML = \"please try again\";"+
										 "</script>");
					}
					}
					if(action.equals("edit"))
					{
						res = actualite.save();
						if(res == DBAccessControl.Cool)
						{
							response.getWriter().println(
									"<script type=\"text/javascript\">"+
						                "parent.document.getElementById('res').className = \"alert alert-success\";"
						               + "parent.document.getElementById('tit').innerHTML = \"changes saved\";"
						               + "parent.document.getElementById('val').innerHTML = \"your actualitie was successfully updated\";"+
											 "</script>");
						}
						else if(res == DBAccessControl.NoChange)
						{
							response.getWriter().println(
									"<script type=\"text/javascript\">"+
						                "parent.document.getElementById('res').className = \"alert alert-warning\";"
						               + "parent.document.getElementById('tit').innerHTML = \"no changes\";"
						               + "parent.document.getElementById('val').innerHTML = \"attributes are the same\";"+
											 "</script>");
						}
						else{
							response.getWriter().println(
									"<script type=\"text/javascript\">"+
						                "parent.document.getElementById('res').className = \"alert alert-error\";"
						               + "parent.document.getElementById('tit').innerHTML = \"update fails\";"
						               + "parent.document.getElementById('val').innerHTML = \"please try again\";"+
											 "</script>");
						}
					}
				}catch(Exception e)
				{
					response.getWriter().println(
							"<script type=\"text/javascript\">"+
					                "parent.document.getElementById('res').className = \"alert alert-error\";"
					               + "parent.document.getElementById('tit').innerHTML = \"An error occured\";"
					               + "parent.document.getElementById('val').innerHTML = \""+e.getMessage()+"\";"+
										 "</script>");
				
				}
		
			}
		}
		//Handle Actaultie
		if (request.getParameter("addproduit") != null
				|| request.getParameter("editproduit") != null) {
			int Owner = 0;
			Produits produit = null;
			Object owner = null;
			String action = null;
			int idowner = 0;
			if (request.getParameter("addproduit") != null) {
				Owner = Integer.parseInt(request
						.getParameter("addproduit"));
				owner = null;
				System.out.println(Owner);
				if (Owner == 1) {
					owner = ((Administrateurs) request.getSession()
							.getAttribute("SmartphonesAdminID"));
					idowner = ((Administrateurs)owner).getID();
				} else {
					owner = ((Clients) request.getSession().getAttribute(
							"SmartphonesUserID"));
					idowner = ((Clients)owner).getID();				
					}
				action = "add";
			}
			if (request.getParameter("editproduit") != null) {
				produit = (Produits) Do.get(
						Produits.getTableName(), "ID", "=",
						"" + request.getParameter("editproduit"), "", "",
						"", "", "Unique");
				action = "edit";
			}
			int ID = 0;
			if(action.equals("add")){ID = DBAccessControl.getNewID("produits");}
			if(action.equals("edit")){ ID = produit.getID();}
			int marque = 0;
			String libelle = null;
			double prix = 0;
			int quantite = 0;
			String description = null;
			String URL = null;
			File file;
			int maxFileSize = 5000 * 1024;
			int maxMemSize = 5000 * 1024;
			ServletContext context = request.getServletContext();
			String filePath = context.getInitParameter("file-upload");
			// Verify the content type
			String contentType = request.getContentType();
			if ((contentType.indexOf("multipart/form-data") >= 0)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// maximum size that will be stored in memory
				factory.setSizeThreshold(maxMemSize);
				// Location to save data that is larger than maxMemSize.
				factory.setRepository(new File(context
						.getInitParameter("temp-upload")));

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// maximum file size to be uploaded.
				upload.setSizeMax(maxFileSize);
				try {
					// Parse the request to get file items.
					List<?> fileItems = upload.parseRequest(request);
					// Process the uploaded file items
					Iterator<?> i = fileItems.iterator();
					
					while (i.hasNext()) {
						FileItem fi = (FileItem) i.next();
						if (fi.isFormField()) {
							if (fi.getFieldName().equals("marque")){
								marque = Integer.parseInt(fi.getString());}
							if (fi.getFieldName().equals("libelle")){
								libelle = fi.getString();}
							if (fi.getFieldName().equals("prix")){
								prix = Double.parseDouble(fi.getString());}
							if (fi.getFieldName().equals("quantite")){
								quantite = Integer.parseInt(fi.getString());}
							if (fi.getFieldName().equals("description")){
								description = fi.getString();}
						}
					}
					i = fileItems.iterator();
					while (i.hasNext()) {
						FileItem fi = (FileItem) i.next();
						if (!fi.isFormField()) {
							// Get the uploaded file parameters
							setFieldName(fi.getFieldName());
							URL = fi.getName();
							setInMemory(fi.isInMemory());
							setSizeInBytes(fi.getSize());
							if(produit==null)
							{
								produit = new Produits();
							}
							if(!URL.equals("")&&URL!=null)
							{
								URL = libelle+"_"+((Marques)Do.get(Marques.getTableName(), "ID", "=", ""+marque, "", "", "", "", "Unique")).getMarque()+"_"+ID+"_"+Owner+"_"+idowner+"_"+marque+"_"+Do.Count(Produits.getTableName(), "", "", "", "", "", "", "")+"_"+Do.Count(Produits.getTableName(), "IDOwner", "=", ""+idowner, "", "", "", "")+"_"+Do.Count(Produits.getTableName(), "Owner", "=", ""+Owner, "", "", "", "")+URL.substring(URL.lastIndexOf("."));
								file = new File(filePath + URL);
						        fi.write(file);
								produit.setURL(URL);
								response.getWriter().println(
										"<script type=\"text/javascript\">"+
							                "parent.document.getElementById('res').className = \"alert alert-success\";"
							               + "parent.document.getElementById('tit').innerHTML = \"image successfuly uploaded\";"
							               + "parent.document.getElementById('val').innerHTML = \"\";"+
												 "</script>");
							if(action.equals("add"))
							{
							Date dNow = new Date();
							java.sql.Date sqldate = new java.sql.Date(dNow.getTime());
							produit.setDateSubmit(sqldate);
							produit.setOwner(Owner);
							produit.setIDOnwer(idowner);
							produit.setStatut(DBAccessControl.Inactif);
							produit.setID(ID);
							produit.setDownloads(0);
							produit.setRating(0);
							}
							}
								produit.setLibelle(libelle);
								produit.setPrixUnitaire(prix);
								produit.setQuantite(quantite);
								produit.setMarque(marque);
								produit.setDescription(description);
						}
					}
					int res = 0;
					if(action.equals("add"))
					{
					res = Do.add(Produits.getTableName(), "this", produit);
					if(res == DBAccessControl.Cool)
					{
						response.getWriter().println(
								"<script type=\"text/javascript\">"+
					                "parent.document.getElementById('res').className = \"alert alert-success\";"
					               + "parent.document.getElementById('tit').innerHTML = \"product successfully added\";"
					               + "parent.document.getElementById('val').innerHTML = \"want to add another ?\";"+
										 "</script>");
					}
					else{
						response.getWriter().println(
								"<script type=\"text/javascript\">"+
					                "parent.document.getElementById('res').className = \"alert alert-error\";"
					               + "parent.document.getElementById('tit').innerHTML = \"your product was not added\";"
					               + "parent.document.getElementById('val').innerHTML = \"please try again\";"+
										 "</script>");
					}
					}
					if(action.equals("edit"))
					{
						res = produit.save();
						if(res == DBAccessControl.Cool)
						{
							response.getWriter().println(
									"<script type=\"text/javascript\">"+
						                "parent.document.getElementById('res').className = \"alert alert-success\";"
						               + "parent.document.getElementById('tit').innerHTML = \"changes saved\";"
						               + "parent.document.getElementById('val').innerHTML = \"your product was successfully updated\";"+
											 "</script>");
						}
						else if(res == DBAccessControl.NoChange)
						{
							response.getWriter().println(
									"<script type=\"text/javascript\">"+
						                "parent.document.getElementById('res').className = \"alert alert-warning\";"
						               + "parent.document.getElementById('tit').innerHTML = \"no changes\";"
						               + "parent.document.getElementById('val').innerHTML = \"attributes are the same\";"+
											 "</script>");
						}
						else{
							response.getWriter().println(
									"<script type=\"text/javascript\">"+
						                "parent.document.getElementById('res').className = \"alert alert-error\";"
						               + "parent.document.getElementById('tit').innerHTML = \"update fails\";"
						               + "parent.document.getElementById('val').innerHTML = \"please try again\";"+
											 "</script>");
						}
					}
				}catch(Exception e)
				{
					response.getWriter().println(
							"<script type=\"text/javascript\">"+
					                "parent.document.getElementById('res').className = \"alert alert-error\";"
					               + "parent.document.getElementById('tit').innerHTML = \"An error occured\";"
					               + "parent.document.getElementById('val').innerHTML = \""+e.getMessage()+"\";"+
										 "</script>");
				
				}
		
			}
		}
		// Handle Client
		if (request.getParameter("addclient") != null
				|| request.getParameter("editclient") != null) {
			Clients NewClient = null;
			int idclient = DBAccessControl.getNewID("clients");
			Date dNow = new Date();
			java.sql.Date sqldate = new java.sql.Date(dNow.getTime());
			List<NewsLetters> news = new ArrayList<NewsLetters>();
			int id = DBAccessControl.getNewID("newsletters");
			if (!request.getParameter("marques").equals("0")) {
				String marques = request.getParameter("marques").replaceFirst(
						"0,", "");
				for (int i = 0; i < marques.split(",").length; i++) {
					NewsLetters New = new NewsLetters(id, idclient,
							Integer.parseInt(marques.split(",")[i]));
					news.add(New);
					id++;
				}
			} else {
				NewsLetters New = new NewsLetters(DBAccessControl.Empty, 0, 0);
				news.add(New);
			}
			NewClient = new Clients(idclient, request.getParameter("username"),
					request.getParameter("password"),
					request.getParameter("email"), request.getParameter("nom"),
					request.getParameter("prenom"), Integer.parseInt(request
							.getParameter("ville")),
					request.getParameter("adresse"), Integer.parseInt(request
							.getParameter("typecard")),
					request.getParameter("creditcard"), news.size() == 0 ? 0
							: news.get(0).getID() != DBAccessControl.Empty ? 1
									: 0, sqldate, 1, DBAccessControl.Inactif,
					Integer.parseInt(request.getParameter("country")),
					Integer.parseInt(request.getParameter("age")),
					request.getParameter("gender"));
			NewClient.setNewNewsLetters(news);
			int res = 0;
			String output = "";
			if (request.getParameter("addclient") != null) {
				res = Do.add(Clients.getTableName(), "this", NewClient);
				if (res == DBAccessControl.Error) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">warning!</h4><p>cannot connect to server!!</p></div>";
				} else if (res == DBAccessControl.EmailUsed) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">email already used!</h4><p>choose another email adresse</p></div>";
				} else if (res == DBAccessControl.UsernameUsed) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">username already used!</h4><p>choose another username</p></div>";
				} else if (res == DBAccessControl.UsernameAndEmailused) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">username and email already used!</h4><p>choose another username and email adresse</p></div>";
				} else if (res == DBAccessControl.Cool) {
					output = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">congratulation!</h4><p>you'r successfully signed up :) !</p></div>";
				} else {
					output = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">error!</h4><p>unknown error try again !</p></div>";
				}
			} else if (request.getParameter("editclient") != null) {
				Clients thisone = (Clients)Do.get(Clients.getTableName(), "ID", "=",""+Integer.parseInt(request
						.getParameter("editclient")) , "", "", "", "", "Unique");
				NewClient.setID(Integer.parseInt(request
						.getParameter("editclient")));
				NewClient.setActif(thisone.getActif());
				for (NewsLetters newsss : news) {
					newsss.setIDClient(NewClient.getID());
				}
				res = NewClient.save();
				if (res == DBAccessControl.Error) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">warning!</h4><p>cannot connect to server!!</p></div>";
				} else if (res == DBAccessControl.EmailUsed) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">email already used!</h4><p>choose another email adresse</p></div>";
				} else if (res == DBAccessControl.UsernameUsed) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">username already used!</h4><p>choose another username</p></div>";
				} else if (res == DBAccessControl.UsernameAndEmailused) {
					output = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">username and email already used!</h4><p>choose another username and email adresse</p></div>";
				} else if (res == DBAccessControl.NoChange) {
					output = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">no change saved</h4><p>no changes</p></div>";
				} else if (res == DBAccessControl.Cool) {
					output = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">congratulation!</h4><p>updates saved successfully :) !</p></div>";
				} else {
					output = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">error!</h4><p>unknown error try again !</p></div>";
				}
			}
			response.getWriter().println(output);
		}
		// Handle Client
	}

}
