package Isamm.ing.java.Smartphones.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.Classes.Encription;
import Isamm.ing.java.Smartphones.Models.Actualites;
import Isamm.ing.java.Smartphones.Models.Administrateurs;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Commandes;
import Isamm.ing.java.Smartphones.Models.Countries;
import Isamm.ing.java.Smartphones.Models.NewsLetters;
import Isamm.ing.java.Smartphones.Models.Produits;
import Isamm.ing.java.Smartphones.Models.Ville;
import Isamm.ing.java.Smartphones.doAll.Do;

/**
 * Servlet implementation class AdministrateurLogIn
 */
@WebServlet("/AdministrateurLogIn")
public class AdministrateurLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrateurLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("LogOut")==null)
		{
		request.getRequestDispatcher("WEB-INF/AdminPanel/login.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("SmartphonesAdminID", null);
			response.sendRedirect("AdminPanel");
		}
		}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(request.getParameter("getcities")!=null)
	{
		String result = "<select id=\"ville\" data-rel=\"chosen\">";
		  List<Ville> list = (List<Ville>)Do.get(Ville.getTableName(), "Statut&IDCountry", "<>&=", "0&"+request.getParameter("getcities"), "AND", "", "", "", "List");
		for (int i = 0; i < list.size(); i++) {
			Ville ville = list.get(i);
			result+="<option id=\""+ville.getID()+"\" value=\""+ville.getID()+"\">"+ville.getLibelle()+"</option>";
		}
				  result+="</select>";
				  response.getWriter().println(result);
	}
		else if(request.getParameter("refresh")!=null)
		{
List<Clients> clients = (List<Clients>)Do.get("clients", "", "", "", "", "DateInscription", "DESC", "", "List");
String result ="";
             if(clients!=null&&!clients.get(0).isEmpty())
             {
			for(Clients clientt : clients)
			{
				result+="<tr id="+clientt.getID()+" name="+clientt.getID()+">"
						+ "<td style=\"visibility: collapse;display: none;\">"+clientt.getID()+"</td>"
						+ "<td>"+clientt.getUsername()+"</td>"
						+ "<td class=\"center\">"+clientt.getEmail()+"</td>"+
					"<td class=\"center\">"+clientt.getRCountry().getLibelle()+"</td>"+
					"<td class=\"center\">"+clientt.getDateInscription()+"</td>";
				    if(clientt.isAllowedToSubmit())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-success\">Allowed</span>"
				    		+ "</td>";}
				    else
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-important\">Not Allowed</span>"
				    		+ "</td>";}
					if(clientt.isActif())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-success\">Active</span>"
							+ "</td>";}
					else if(clientt.isArchive())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-info\">Archived</span>"
							+ "</td>";}
					else if(clientt.isBanned())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-important\">Banned</span>"
				    		+ "</td>";}
					else if(clientt.isInactif())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-warning\">Pending</span>"
				    		+ "</td>";}

					result+="<td class=\"center\">"
							+ "<div class=\"btn-group\">"
							+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
							+ "<i class=\"icon-user icon-white\"></i> user</a>"
							+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
							+ "<span class=\"caret\"></span></a>"+
					  "<ul class=\"dropdown-menu\">"
					  + "<li><a href=\"AdminPanel.Do?editclient="+clientt.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deleteclient\",\""+clientt.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
					
				    if(clientt.isInactif())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activeclient\",\""+clientt.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
					if(clientt.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restoreclient\",\""+clientt.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
				    if(!clientt.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archiveclient\",\""+clientt.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
					if(clientt.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbanclient\",\""+clientt.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
					if(!clientt.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"banclient\",\""+clientt.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
					if(!clientt.isAllowedToSubmit())
					result+="<li><a href=\"#\" onclick='javascript:dothis(\"allowsubmitclient\",\""+clientt.getID()+"\")'><i class=\"icon-check\"></i> Allow Submit</a></li>";
					if(clientt.isAllowedToSubmit())
					result+="<li><a href=\"#\" onclick='javascript:dothis(\"bansubmitclient\",\""+clientt.getID()+"\")'><i class=\"icon-remove-circle\"></i> Ban Submit</a></li>";
					  result+="</ul></div></td></tr>";

			}
		}
			response.getWriter().println(result);
		}
		else if(request.getParameter("refreshproduit")!=null)
		{
			List<Produits> produits = (List<Produits>)Do.get("produits", "", "", "", "", "DateSubmit", "DESC", "", "List");
			String result ="";
			if(produits!=null&&!produits.get(0).isEmpty())
			{
						for(Produits produit : produits)
						{
							result+="<tr id="+produit.getID()+" name="+produit.getID()+">"+
									"<td style=\"visibility: collapse;display: none;\">"+produit.getID()+"</td>"+
									"<td><li class=\"thumbnail\">"+
							        "<a style=\"background:url(img/gallery/thumbs/1.jpg)\" href=\"././assets/images/ProductImages/"+produit.getURL()+"\"><img class=\"grayscale\" src=\"././assets/images/ProductImages/"+produit.getURL()+"\" alt=\""+produit.getLibelle()+"\"></a>"+
						            "</li></td>"+										"<td>"+produit.getLibelle()+"</td>"+
									"<td class=\"center\">"+produit.getRMarque().getMarque()+"</td>"+
								"<td class=\"center\">"+produit.getPrixUnitaire()+"</td>"+
								"<td class=\"center\">"+produit.getQuantite()+"</td>"+
								"<td class=\"center\">"+produit.getDateSubmit()+"</td>";
							    if(produit.isTrustWorthy())
							    {result+="<td class=\"center\">"
							    		+ "<span class=\"label label-success\">"+((Administrateurs)produit.getROwner()).getUsername()+"</span>"
							    		+ "</td>";}
							    else
							    {result+="<td class=\"center\">"
							    		+ "<a href=\"AdminPanel.Do?editclient="+((Clients)produit.getROwner()).getID()+"\"><span class=\"label label-info\">"+((Clients)produit.getROwner()).getUsername()+"</span></a>"
							    		+ "</td>";}
								if(produit.isActif())
								{result+="<td class=\"center\">"
										+ "<span class=\"label label-success\">Active</span>"
										+ "</td>";}
								else if(produit.isInactif())
								{result+="<td class=\"center\">"
										+ "<span class=\"label label-warning\">Inactive</span>"
										+ "</td>";}
								else if(produit.isArchive())
							    {result+="<td class=\"center\">"
							    		+ "<span class=\"label label-info\">Archived</span>"
							    		+ "</td>";}
								else if(produit.isBanned())
							    {result+="<td class=\"center\">"
							    		+ "<span class=\"label label-important\">Banned</span>"
							    		+ "</td>";}

								result+="<td class=\"center\">"
										+ "<div class=\"btn-group\">"
										+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
										+ "<i class=\"icon-th icon-white\"></i> produit</a>"
										+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
										+ "<span class=\"caret\"></span></a>"+
								  "<ul class=\"dropdown-menu\">"
								  + "<li><a href=\"AdminPanel.Do?editproduit="+produit.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deleteproduit\",\""+produit.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
								
							    if(produit.isInactif())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activeproduit\",\""+produit.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
								if(produit.isArchive())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restoreproduit\",\""+produit.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
								else
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archiveproduit\",\""+produit.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
								if(produit.isBanned())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbanproduit\",\""+produit.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
								else
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"banproduit\",\""+produit.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
								  result+="</ul></div></td></tr>";

						}
			}
						response.getWriter().println(result);
		}
		else if(request.getParameter("actionproduit")!=null)
		{
			String action = request.getParameter("actionproduit").split(",")[0];
			int id = Integer.parseInt(request.getParameter("actionproduit").split(",")[1]);
			String command = "deleteproduit,activeproduit,restoreproduit,archiveproduit,unbanproduit,banproduit";
			Produits produit = (Produits)Do.get(Produits.getTableName(), "ID", "=", ""+id, "", "", "", "", "Unique");
			System.out.println(produit);
			int res = 0;
			if(action.equals(command.split(",")[0]))
			{
                res = produit.delete();
            }	
			else if(action.equals(command.split(",")[1]))
			{
				produit.setStatut(DBAccessControl.Actif);
				res = produit.save();
			}
				else if(action.equals(command.split(",")[2]))
				{
					produit.setStatut(DBAccessControl.Actif);
					res = produit.save();
				}	
					else if(action.equals(command.split(",")[3]))
					{
						produit.setStatut(DBAccessControl.Archive);
						res = produit.save();
					}	
						else if(action.equals(command.split(",")[4]))
						{
							produit.setStatut(DBAccessControl.Actif);
							res = produit.save();	
						}	
							else if(action.equals(command.split(",")[5]))
							{
								produit.setStatut(DBAccessControl.Banned);
								res = produit.save();
							}
			String result ="";
			if(res!=DBAccessControl.Error)
			{
				if(!action.equals(command.split(",")[0]))
				{result+="<tr id="+produit.getID()+" name="+produit.getID()+">"+
						"<td style=\"visibility: collapse;display: none;\">"+produit.getID()+"</td>"+
						"<td><li class=\"thumbnail\">"+
				        "<a style=\"background:url(img/gallery/thumbs/1.jpg)\" href=\"././assets/images/ProductImages/"+produit.getURL()+"\"><img class=\"grayscale\" src=\"././assets/images/ProductImages/"+produit.getURL()+"\" alt=\""+produit.getLibelle()+"\"></a>"+
			            "</li></td>"+	
						"<td class=\"center\">"+produit.getLibelle()+"</td>"+
						"<td class=\"center\">"+produit.getRMarque().getMarque()+"</td>"+
					"<td class=\"center\">"+produit.getPrixUnitaire()+"</td>"+
					"<td class=\"center\">"+produit.getQuantite()+"</td>"+
					"<td class=\"center\">"+produit.getDateSubmit()+"</td>";
				    if(produit.isTrustWorthy())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-success\">"+((Administrateurs)produit.getROwner()).getUsername()+"</span>"
				    		+ "</td>";}
				    else
				    {result+="<td class=\"center\">"
				    		+ "<a href=\"AdminPanel.Do?editclient="+((Clients)produit.getROwner()).getID()+"\"><span class=\"label label-info\">"+((Clients)produit.getROwner()).getUsername()+"</span></a>"
				    		+ "</td>";}
					if(produit.isActif())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-success\">Active</span>"
							+ "</td>";}
					else if(produit.isInactif())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-warning\">Inactive</span>"
							+ "</td>";}
					else if(produit.isArchive())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-info\">Archived</span>"
				    		+ "</td>";}
					else if(produit.isBanned())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-important\">Banned</span>"
				    		+ "</td>";}

					result+="<td class=\"center\">"
							+ "<div class=\"btn-group\">"
							+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
							+ "<i class=\"icon-th icon-white\"></i> produit</a>"
							+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
							+ "<span class=\"caret\"></span></a>"+
					  "<ul class=\"dropdown-menu\">"
					  + "<li><a href=\"AdminPanel.Do?editproduit="+produit.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deleteproduit\",\""+produit.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
					
				    if(produit.isInactif())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activeproduit\",\""+produit.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
					if(produit.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restoreproduit\",\""+produit.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
					else
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archiveproduit\",\""+produit.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
					if(produit.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbanproduit\",\""+produit.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
					else
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"banproduit\",\""+produit.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
					  result+="</ul></div></td></tr>";
				}
				else{
					result ="";
				}

		}
			else{
				result = "Error ! No Data ...";
			}
			response.getWriter().println(result);
		}
		else if(request.getParameter("refreshcommande")!=null)
		{
			List<Commandes> commandes = (List<Commandes>)Do.get("commandes", "", "", "", "", "Date", "DESC", "", "List");
			String result ="";
			if(commandes!=null&&!commandes.get(0).isEmpty())
			{
						for(Commandes commande : commandes)
						{
							result+="<tr id="+commande.getID()+" name="+commande.getID()+">"+
									"<td style=\"visibility: collapse;display: none;\">"+commande.getID()+"</td>"+
									"<td><li class=\"thumbnail\">"+
							        "<a style=\"background:url(img/gallery/thumbs/1.jpg)\" href=\"././assets/images/ProductImages/"+commande.getProduit().getURL()+"\"><img class=\"grayscale\" src=\"././assets/images/ProductImages/"+commande.getProduit().getURL()+"\" alt=\""+commande.getProduit().getLibelle()+"\"></a>"+
						            "</li></td>"
						            + "<td class=\"center\"><a href=\"AdminPanel.Do?editproduit="+commande.getProduit().getID()+"\"><span class=\"label label-info\">"+commande.getProduit().getLibelle()+"</span></td>"+
						            "<td class=\"center\">"+commande.getQuantite()+"</td>"
						            + "<td class=\"center input-prepend\">"+
													"<span class=\"add-on\">$</span><span class=\"add-on uneditable-input\">"+commande.getPrice()+"</span></td>"+
																"<td class=\"center\">"+
								   "<a href=\"AdminPanel.Do?editclient="+commande.getOwner().getID()+"\"><span class=\"label label-info\">"+commande.getOwner().getUsername()+"</span></a>"+
								"</td><td class=\"center\">";							
						            if(commande.isValidated()){
						            	result+="<span class=\"label label-success\">"+commande.getValidator().getUsername()+"</span>";
						            }
						            else{
						            	result+="<input type=\"text\" class=\"add-on input-small datepicker\" id=\"v"+commande.getID()+"\" value=\"Date\">";
						            }
								result+="</td><td class=\"center\">";
								if(commande.isActive())
								{result+=""
										+ "<span class=\"label label-success\">Actif</span>"
										+ "";}
								else if(commande.isInactive())
								{result+=""
										+ "<span class=\"label label-warning\">Inactif</span>"
										+ "";}
								else if(commande.isArchive())
							    {result+=""
							    		+ "<span class=\"label label-info\">Archived</span>"
							    		+ "";}
								else if(commande.isBanned())
							    {result+=""
							    		+ "<span class=\"label label-important\">Banned</span>"
							    		+ "";}
								result+="</td>";

								result+="<td class=\"center\">"
										+ "<div class=\"btn-group\">"
										+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
										+ "<i class=\"icon-user icon-white\"></i> commande</a>"
										+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
										+ "<span class=\"caret\"></span></a>"+
								  "<ul class=\"dropdown-menu\">";
								  if(commande.isValidated())
								  {
									  result += "<li><a href=\"#\" onclick='javascript:dothis(\"validecommande\",\"${commande.getID()}\")'><i class=\"icon-pencil\"></i> Validate</a></li>";
								  }
								  result+= "<li><a href=\"AdminPanel.Do?editcommande="+commande.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deletecommande\",\""+commande.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
								
							    if(commande.isInactive())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activecommande\",\""+commande.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
								if(commande.isArchive())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restorecommande\",\""+commande.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
								else
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archivecommande\",\""+commande.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
								if(commande.isBanned())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbancommande\",\""+commande.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
								else
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"bancommande\",\""+commande.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
								  result+="</ul></div></td></tr>";

						}
			}
						response.getWriter().println(result);
		}
	
		else if(request.getParameter("actioncommande")!=null)
		{
			String action = request.getParameter("actioncommande");
			String commandee = action.split(",")[0];
			String command = "deletecommande,activecommande,restorecommande,archivecommande,unbancommande,bancommande,validecommande";
			int id = Integer.parseInt(action.split(",")[1]);
			String date = null;
			if(commandee.equals(command.split(",")[6]))
			{
				date = action.replaceFirst(commandee+","+id+",", "");
			}
			System.out.println("inside : "+action);
			Commandes commande = (Commandes)Do.get(Commandes.getTableName(), "ID", "=", ""+id, "", "", "", "", "Unique");
			int res = 0;
			if(commandee.equals(command.split(",")[0]))
			{
                res = commande.delete();
            }	
			else if(commandee.equals(command.split(",")[1]))
			{
				commande.setStatut(DBAccessControl.Actif);
				res = commande.save();
			}
				else if(commandee.equals(command.split(",")[2]))
				{
					commande.setStatut(DBAccessControl.Actif);
					res = commande.save();
				}	
					else if(commandee.equals(command.split(",")[3]))
					{
						commande.setStatut(DBAccessControl.Archive);
						res = commande.save();
					}	
						else if(commandee.equals(command.split(",")[4]))
						{
							commande.setStatut(DBAccessControl.Actif);
							res = commande.save();	
						}	
							else if(commandee.equals(command.split(",")[5]))
							{
								commande.setStatut(DBAccessControl.Banned);
								res = commande.save();
							}
							else if(commandee.equals(command.split(",")[6]))
							{
								
							}
			String result ="";
			if(res!=DBAccessControl.Error)
			{
				if(!commandee.equals(command.split(",")[0]))
				{
					result+="<tr id="+commande.getID()+" name="+commande.getID()+">"+
						"<td style=\"visibility: collapse;display: none;\">"+commande.getID()+"</td>"+
						"<td><li class=\"thumbnail\">"+
				        "<a style=\"background:url(img/gallery/thumbs/1.jpg)\" href=\"././assets/images/ProductImages/"+commande.getProduit().getURL()+"\"><img class=\"grayscale\" src=\"././assets/images/ProductImages/"+commande.getProduit().getURL()+"\" alt=\""+commande.getProduit().getLibelle()+"\"></a>"+
			            "</li></td>"
			            + "<td class=\"center\"><a href=\"AdminPanel.Do?editproduit="+commande.getProduit().getID()+"\"><span class=\"label label-info\">"+commande.getProduit().getLibelle()+"</span></td>"+
			            "<td class=\"center\">"+commande.getQuantite()+"</td>"
			            + "<td class=\"center input-prepend\">"+
										"<span class=\"add-on\">$</span><span class=\"add-on uneditable-input\">"+commande.getPrice()+"</span></td>"+
													"<td class=\"center\">"+
					   "<a href=\"AdminPanel.Do?editclient="+commande.getOwner().getID()+"\"><span class=\"label label-info\">"+commande.getOwner().getUsername()+"</span></a>"+
					"</td><td class=\"center\">";							
			            if(commande.isValidated()){
			            	result+="<span class=\"label label-success\">"+commande.getValidator().getUsername()+"</span>";
			            }
			            else{
			            	result+="<input type=\"text\" class=\"add-on input-small datepicker\" id=\"v"+commande.getID()+"\" value=\"Date\">";
			            }
					result+="</td><td class=\"center\">";
					if(commande.isActive())
					{result+=""
							+ "<span class=\"label label-success\">Actif</span>"
							+ "";}
					else if(commande.isInactive())
					{result+=""
							+ "<span class=\"label label-warning\">Inactif</span>"
							+ "";}
					else if(commande.isArchive())
				    {result+=""
				    		+ "<span class=\"label label-info\">Archived</span>"
				    		+ "";}
					else if(commande.isBanned())
				    {result+=""
				    		+ "<span class=\"label label-important\">Banned</span>"
				    		+ "";}
					result+="</td>";

					result+="<td class=\"center\">"
							+ "<div class=\"btn-group\">"
							+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
							+ "<i class=\"icon-user icon-white\"></i> commande</a>"
							+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
							+ "<span class=\"caret\"></span></a>"+
					  "<ul class=\"dropdown-menu\">";
					  if(commande.isValidated())
					  {
						  result += "<li><a href=\"#\" onclick='javascript:dothis(\"validecommande\",\"${commande.getID()}\")'><i class=\"icon-pencil\"></i> Validate</a></li>";
					  }
					  result+= "<li><a href=\"AdminPanel.Do?editcommande="+commande.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deletecommande\",\""+commande.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
					
				    if(commande.isInactive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activecommande\",\""+commande.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
					if(commande.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restorecommande\",\""+commande.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
					else
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archivecommande\",\""+commande.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
					if(commande.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbancommande\",\""+commande.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
					else
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"bancommande\",\""+commande.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
					  result+="</ul></div></td></tr>";
				}
				else{
					result ="";
				}

		}
			else{
				result = "Error ! No Data ...";
			}
			response.getWriter().println(result);
		}
		else if(request.getParameter("refreshactualite")!=null)
		{
			List<Actualites> actualites = (List<Actualites>)Do.get("actualites", "", "", "", "", "Date", "DESC", "", "List");
			String result ="";
			if(actualites!=null&&!actualites.get(0).isEmpty())
			{
						for(Actualites actualite : actualites)
						{
							result+="<tr id="+actualite.getID()+" name="+actualite.getID()+">"+
									"<td style=\"visibility: collapse;display: none;\">"+actualite.getID()+"</td>"+
									"<td><li class=\"thumbnail\">"+
							        "<a style=\"background:url(img/gallery/thumbs/1.jpg)\" href=\"././assets/images/ActualitesImages/"+actualite.getURL()+"\"><img class=\"grayscale\" src=\"././assets/images/ActualitesImages/"+actualite.getURL()+"\" alt=\""+actualite.getTitre()+"\"></a>"+
						            "</li></td>"+										"<td>"+actualite.getTitre()+"</td>"+
									"<td class=\"center\" style=\"word-break: break-all;white-space: normal;\">"+actualite.getDescription()+"</td>"+
								"<td class=\"center\">"+actualite.getDate()+"</td>"+
								"<td class=\"center\">"+actualite.getOwner().getUsername()+"</td>";
								
								if(actualite.isActive())
								{result+="<td class=\"center\">"
										+ "<span class=\"label label-success\">Active</span>"
										+ "</td>";}
								else if(actualite.isInactive())
								{result+="<td class=\"center\">"
										+ "<span class=\"label label-warning\">Inactive</span>"
										+ "</td>";}
								else if(actualite.isArchive())
							    {result+="<td class=\"center\">"
							    		+ "<span class=\"label label-info\">Archived</span>"
							    		+ "</td>";}
								else if(actualite.isBanned())
							    {result+="<td class=\"center\">"
							    		+ "<span class=\"label label-important\">Banned</span>"
							    		+ "</td>";}

								result+="<td class=\"center\">"
										+ "<div class=\"btn-group\">"
										+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
										+ "<i class=\"icon-user icon-white\"></i> actualite</a>"
										+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
										+ "<span class=\"caret\"></span></a>"+
								  "<ul class=\"dropdown-menu\">"
								  + "<li><a href=\"AdminPanel.Do?editactualite="+actualite.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deleteactualite\",\""+actualite.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
								
							    if(actualite.isInactive())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activeactualite\",\""+actualite.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
								if(actualite.isArchive())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restoreactualite\",\""+actualite.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
								else
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archiveactualite\",\""+actualite.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
								if(actualite.isBanned())
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbanactualite\",\""+actualite.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
								else
							    result+="<li><a href=\"#\" onclick='javascript:dothis(\"banactualite\",\""+actualite.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
								  result+="</ul></div></td></tr>";

						}
			}
						response.getWriter().println(result);
		}
		else if(request.getParameter("actionactualite")!=null)
		{
			String action = request.getParameter("actionactualite").split(",")[0];
			int id = Integer.parseInt(request.getParameter("actionactualite").split(",")[1]);
			String command = "deleteactualite,activeactualite,restoreactualite,archiveactualite,unbanactualite,banactualite";
			Actualites actualite = (Actualites)Do.get(Actualites.getTableName(), "ID", "=", ""+id, "", "", "", "", "Unique");
			int res = 0;
			if(action.equals(command.split(",")[0]))
			{
                res = actualite.delete();
            }	
			else if(action.equals(command.split(",")[1]))
			{
				actualite.setStatut(DBAccessControl.Actif);
				res = actualite.save();
			}
				else if(action.equals(command.split(",")[2]))
				{
					actualite.setStatut(DBAccessControl.Actif);
					res = actualite.save();
				}	
					else if(action.equals(command.split(",")[3]))
					{
						actualite.setStatut(DBAccessControl.Archive);
						res = actualite.save();
					}	
						else if(action.equals(command.split(",")[4]))
						{
							actualite.setStatut(DBAccessControl.Actif);
							res = actualite.save();	
						}	
							else if(action.equals(command.split(",")[5]))
							{
								actualite.setStatut(DBAccessControl.Banned);
								res = actualite.save();
							}
			String result ="";
			if(res!=DBAccessControl.Error)
			{
				if(!action.equals(command.split(",")[0]))
				{result+="<tr id="+actualite.getID()+" name="+actualite.getID()+">"+
						"<td style=\"visibility: collapse;display: none;\">"+actualite.getID()+"</td>"+
						"<td><li class=\"thumbnail\">"+
				        "<a style=\"background:url(img/gallery/thumbs/1.jpg)\" href=\"././assets/images/ActualitesImages/"+actualite.getURL()+"\"><img class=\"grayscale\" src=\"././assets/images/ActualitesImages/"+actualite.getURL()+"\" alt=\""+actualite.getTitre()+"\"></a>"+
			            "</li></td>"+	
						"<td class=\"center\">"+actualite.getTitre()+"</td>"+
						"<td class=\"center\"  style=\"word-break: break-all;white-space: normal;\">"+actualite.getDescription()+"</td>"+
					"<td class=\"center\">"+actualite.getDate()+"</td>"+
					"<td class=\"center\">"+actualite.getOwner().getUsername()+"</td>";
					if(actualite.isActive())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-success\">Active</span>"
							+ "</td>";}
					else if(actualite.isInactive())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-warning\">Inactive</span>"
							+ "</td>";}
					else if(actualite.isArchive())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-info\">Archived</span>"
				    		+ "</td>";}
					else if(actualite.isBanned())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-important\">Banned</span>"
				    		+ "</td>";}

					result+="<td class=\"center\">"
							+ "<div class=\"btn-group\">"
							+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
							+ "<i class=\"icon-user icon-white\"></i> produit</a>"
							+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
							+ "<span class=\"caret\"></span></a>"+
					  "<ul class=\"dropdown-menu\">"
					  + "<li><a href=\"AdminPanel.Do?editactualite="+actualite.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deleteactualite\",\""+actualite.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
					
				    if(actualite.isInactive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activeactualite\",\""+actualite.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
					if(actualite.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restoreactualite\",\""+actualite.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
					else
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archiveactualite\",\""+actualite.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
					if(actualite.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbanactualite\",\""+actualite.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
					else
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"banactualite\",\""+actualite.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
					  result+="</ul></div></td></tr>";
				}
				else{
					result ="";
				}

		}
			else{
				result = "Error ! No Data ...";
			}
			response.getWriter().println(result);
		}
		else if(request.getParameter("action")!=null)
		{
			String action = request.getParameter("action").split(",")[0];
			int id = Integer.parseInt(request.getParameter("action").split(",")[1]);
			String command = "deleteclient,activeclient,restoreclient,archiveclient,unbanclient,banclient,allowsubmitclient,bansubmitclient";
			Clients clientt = (Clients)Do.get(Clients.getTableName(), "ID", "=", ""+id, "", "", "", "", "Unique");
			System.out.println(clientt);
			int res = 0;
			if(action.equals(command.split(",")[0]))
			{
                res = clientt.delete();
            }	
			else if(action.equals(command.split(",")[1]))
			{
				clientt.setActif(DBAccessControl.Actif);
				res = clientt.save();
			}
				else if(action.equals(command.split(",")[2]))
				{
					clientt.setActif(DBAccessControl.Actif);
					res = clientt.save();
				}	
					else if(action.equals(command.split(",")[3]))
					{
						clientt.setActif(DBAccessControl.Archive);
						res = clientt.save();
					}	
						else if(action.equals(command.split(",")[4]))
						{
							clientt.setActif(DBAccessControl.Actif);
							res = clientt.save();	
						}	
							else if(action.equals(command.split(",")[5]))
							{
								clientt.setActif(DBAccessControl.Banned);
								res = clientt.save();
							}	
								else if(action.equals(command.split(",")[6]))
								{
									clientt.setAllowedToSubmit(1);
									res = clientt.save();
								}	
									else if(action.equals(command.split(",")[7]))
									{
										clientt.setAllowedToSubmit(0);
										res = clientt.save();
									}
			String result ="";
			if(res!=DBAccessControl.Error)
			{
				if(!action.equals(command.split(",")[0]))
				{
				result+="<tr id="+clientt.getID()+" name="+clientt.getID()+">"
						+ "<td style=\"visibility: collapse;display: none;\">"+clientt.getID()+"</td>"
						+ "<td>"+clientt.getUsername()+"</td>"
						+ "<td class=\"center\">"+clientt.getEmail()+"</td>"+
					"<td class=\"center\">"+clientt.getRCountry().getLibelle()+"</td>"+
					"<td class=\"center\">"+clientt.getDateInscription()+"</td>";
				    if(clientt.isAllowedToSubmit())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-success\">Allowed</span>"
				    		+ "</td>";}
				    else
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-important\">Not Allowed</span>"
				    		+ "</td>";}
					if(clientt.isActif())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-success\">Active</span>"
							+ "</td>";}
					else if(clientt.isArchive())
					{result+="<td class=\"center\">"
							+ "<span class=\"label label-info\">Archived</span>"
							+ "</td>";}
					else if(clientt.isBanned())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-important\">Banned</span>"
				    		+ "</td>";}
					else if(clientt.isInactif())
				    {result+="<td class=\"center\">"
				    		+ "<span class=\"label label-warning\">Pending</span>"
				    		+ "</td>";}

					result+="<td class=\"center\">"
							+ "<div class=\"btn-group\">"
							+ "<a class=\"btn btn-primary btn-setting\" href=\"#\">"
							+ "<i class=\"icon-user icon-white\"></i> user</a>"
							+ "<a class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"
							+ "<span class=\"caret\"></span></a>"+
					  "<ul class=\"dropdown-menu\">"
					  + "<li><a href=\"AdminPanel.Do?editclient="+clientt.getID()+"\"><i class=\"icon-pencil\"></i> Edit</a></li><li><a href=\"#\" onclick='javascript:dothis(\"deleteclient\",\""+clientt.getID()+"\")'><i class=\"icon-trash\"></i> Delete</a></li>";
					
				    if(clientt.isInactif())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"activeclient\",\""+clientt.getID()+"\")'><i class=\"icon-ok\"></i> Activate</a></li>";
					if(clientt.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"restoreclient\",\""+clientt.getID()+"\")'><i class=\"icon-flag\"></i> Restore</a></li>";
				    if(!clientt.isArchive())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"archiveclient\",\""+clientt.getID()+"\")'><i class=\"icon-book\"></i> Archive</a></li>";
					if(clientt.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"unbanclient\",\""+clientt.getID()+"\")'><i class=\"icon-ok-circle\"></i> UnBan</a></li>";
					if(!clientt.isBanned())
				    result+="<li><a href=\"#\" onclick='javascript:dothis(\"banclient\",\""+clientt.getID()+"\")'><i class=\"icon-ban-circle\"></i> Ban</a></li>";
					if(!clientt.isAllowedToSubmit())
					result+="<li><a href=\"#\" onclick='javascript:dothis(\"allowsubmitclient\",\""+clientt.getID()+"\")'><i class=\"icon-check\"></i> Allow Submit</a></li>";
					if(clientt.isAllowedToSubmit())
					result+="<li><a href=\"#\" onclick='javascript:dothis(\"bansubmitclient\",\""+clientt.getID()+"\")'><i class=\"icon-remove-circle\"></i> Ban Submit</a></li>";
					  result+="</ul></div></td></tr>";
				}
				else{
					result ="";
				}

		}
			else{
				result = "Error ! No Data ...";
			}
			response.getWriter().println(result);
		}
		else{
		Administrateurs admin = (Administrateurs) Do.login(Administrateurs.getTableName(), request.getParameter("admin"), request.getParameter("pass"));
		System.out.println(request.getParameter("admin")+" , "+request.getParameter("pass"));
		if (admin == null) {
			response.getWriter().println("Erreur Connection!");
		} else {
			if (admin.getID() == DBAccessControl.Empty || admin.getLevel() == DBAccessControl.ArchiveAdmin) {
				response.getWriter().println("Account Not Found");
			} else if (admin.getLevel() == DBAccessControl.InactifAdmin) {
				response.getWriter().println("Account Not Actif");
			}else if (admin.getLevel() == DBAccessControl.BannedAdmin) {
				response.getWriter().println("Accound Suspended");
			} else {
				admin.setPassword(Encription.encrypt(admin.getPassword(),
						Encription.getKey("password")));
				request.getSession().setAttribute("SmartphonesAdminID", admin);
				response.getWriter().println("Found");
	}
		}
		}
	}
}