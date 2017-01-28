<%@page import="Isamm.ing.java.Smartphones.Models.NewsLetters"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@page import="Isamm.ing.java.Smartphones.Models.Commandes"%>
<%@page import="Isamm.ing.java.Smartphones.Models.Clients"%>
<%@page import="Isamm.ing.java.Smartphones.doAll.Do"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Isamm.ing.java.Smartphones.Classes.DBAccessControl"%>
<%@page import="Isamm.ing.java.Smartphones.Models.Administrateurs"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	Administrateurs admin = (Administrateurs)request.getSession().getAttribute("SmartphonesAdminID");
    boolean LoggedIn = false;
     if(admin!=null)
    {
    	if(admin.getID()!=DBAccessControl.Empty)
    	{
    		LoggedIn = true;
    	}
    }
    if(LoggedIn==false)
    {
    	response.sendRedirect("AdminPanel");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link id="bs-css" href="././assets/assetsadmin/css/bootstrap-united.css"
	rel="stylesheet">
<link id="bs-css"
	href="././assets/assetsadmin/css/bootstrap-cerulean.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="././assets/assetsadmin/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="././assets/assetsadmin/css/charisma-app.css"
	rel="stylesheet">
<link href="././assets/assetsadmin/css/jquery-ui-1.8.21.custom.css"
	rel="stylesheet">
<link href='././assets/assetsadmin/css/fullcalendar.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='././assets/assetsadmin/css/chosen.css' rel='stylesheet'>
<link href='././assets/assetsadmin/css/uniform.default.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/colorbox.css' rel='stylesheet'>
<link href='././assets/assetsadmin/css/jquery.cleditor.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/jquery.noty.css' rel='stylesheet'>
<link href='././assets/assetsadmin/css/noty_theme_default.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/elfinder.min.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/elfinder.theme.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/jquery.iphone.toggle.css'
	rel='stylesheet'>
<link href='././assets/assetsadmin/css/opa-icons.css' rel='stylesheet'>
<link href='././assets/assetsadmin/css/uploadify.css' rel='stylesheet'>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="././assets/assetsadmin/img/favicon.ico">
</head>
<body>
	<core:if test="<%=LoggedIn==true%>">
		<!-- topbar starts -->
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
					</a> <a class="brand" href="Home"> <span>SmartPhones</span></a>

					<!-- user dropdown starts -->
					<div class="btn-group pull-right">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="icon-user"></i><span class="hidden-phone"> <%=admin.getUsername()%></span>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#">Profile</a></li>
							<li class="divider"></li>
							<li><a href="AdminPanel?LogOut=1">Logout</a></li>
						</ul>
					</div>
					<!-- user dropdown ends -->

					<div class="top-nav nav-collapse">
						<ul class="nav">
							<li><a href="Home">Visit Site</a></li>
							<li>
								<form class="navbar-search pull-left">
									<input placeholder="Search" class="search-query span2"
										name="query" type="text">
								</form>
							</li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<!-- topbar ends -->
		<div class="container-fluid">
			<div class="row-fluid">

				<!-- left menu starts -->
				<div class="span2 main-menu-span">
					<div class="well nav-collapse sidebar-nav">
						<ul class="nav nav-tabs nav-stacked main-menu">
							<li class="nav-header hidden-tablet">Main</li>
							<li><a class="ajax-link" href="AdminPanel.DashBoard"><i
									class="icon-home"></i><span class="hidden-tablet">
										Dashboard</span></a></li>
							<li><a class="ajax-link" href="AdminPanel.ManagerClient"><i
									class="icon-eye-open"></i><span class="hidden-tablet">
										Manage Clients</span></a></li>
							<li><a class="ajax-link" href="AdminPanel.ManagerProduct"><i
									class="icon-briefcase"></i><span class="hidden-tablet">
										Manage Products</span></a></li>
							<li><a class="ajax-link" href="AdminPanel.ManagerActualite"><i
									class="icon-list-alt"></i><span class="hidden-tablet">
										Manage Actualites</span></a></li>
							<li><a class="ajax-link" href="AdminPanel.ManagerCommande"><i class="icon-font"></i><span class="hidden-tablet"> Manage Commandes</span></a></li>

							
						</ul>
						<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input
							id="is-ajax" type="checkbox"> Ajax on menu</label>
					</div>
					<!--/.well -->
				</div>
				<!--/span-->
				<!-- left menu ends -->

				<noscript>
					<div class="alert alert-block span10">
						<h4 class="alert-heading">Warning!</h4>
						<p>
							You need to have <a
								href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
							enabled to use this site.
						</p>
					</div>
				</noscript>

				<div id="content" class="span10">
					<!-- content starts -->


					<div>
						<ul class="breadcrumb">
							<li><a href="#">Home</a> <span class="divider">/</span></li>
							<li><a href="#">Manager</a></li>
						</ul>
					</div>

					<div class="row-fluid sortable">
						<div class="box span12">
							<div class="box-header well" data-original-title>
								<h2>
									<i class="icon-edit"></i> Query Handler
								</h2>
								<div class="box-icon">
									<a href="#" class="btn btn-minimize btn-round"><i
										class="icon-chevron-up"></i></a> <a href="#"
										class="btn btn-close btn-round"><i class="icon-remove"></i></a>
								</div>
							</div>
							
							<!-- Manage commandes -->
							
														<!-- edit actualite start-->

							<core:if test="<%=request.getParameter(\"editcommande\")!=null%>">
								<core:set var="commande"
									value="<%=admin.get(\"commandes\",\"ID\",\"=\",\"\"+request.getParameter(\"editcommande\"),\"\",\"\",\"\",\"\",\"Unique\")%>"></core:set>
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do"
										onsubmit="return editcommande()">
										<div class="control-group">
											<label class="control-label" for="client">Client
												</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on" id="client"><i class="icon-user"></i><a href="AdminPanel.Do?editclient=${commande.getOwner().getID() }">${commande.getOwner().getUsername() }</a></span>
												</div>
											</div>
										</div>
																               	<span class="add-on" id="produit"><i class="icon-th"></i><a href="AdminPanel.Do?editproduit=${commande.getProduit().getID() }">${commande.getProduit().getLibelle() }</a></span>
										
						               <div class="control-group">
											<label class="control-label" for="produit">Produit
												</label>
											<div class="controls">
												<div class="input-prepend">
												<li class="thumbnail">
								            <a style="background:url(img/gallery/thumbs/1.jpg)" title="" href="AdminPanel.Do?editproduit=${commande.getProduit().getID() }"><img class="grayscale" src="././assets/images/ProductImages/${commande.getProduit().getURL()}" alt="${commande.getProduit().getLibelle()}"></a>
							                      </li>
												</div>
											</div>
										</div>
										

										
										
										<div id="signupresult" name="signupresult"></div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Save
												changes</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>
							<!-- edit actualite end -->
							
							<!-- Manage commandes -->
                            <!-- Manage Actualite -->
							<core:if test="<%=request.getParameter(\"addactualite\")!=null%>">
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do?addactualite=1"
										onsubmit='return valideactualite(true)' target="result"
										enctype="multipart/form-data">
										<div class="control-group">
											<label class="control-label" for="titre">Titre</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon"></i></span><input
														id="titre" name="titre" required="required"
														type="text">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="description">Description</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon"></i></span><textarea class="autogrow" id="description"
														name="description">${produit.getDescription() }</textarea>
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="file">Image</label>
											<div class="controls">
												<input class="input-file uniform_on" required="required"
													id="image" name="image" type="file">
											</div>
										</div>
										<iframe name="result" id="result" style="visibility: collapse;display: none;"></iframe>
										<div id="produitresult" name="produitresult">
										<div class="alert alert-info " id="res" name="res"><button type="button" class="close" data-dismiss="alert">×</button><h4 class="alert-heading" id="tit" val="tit"></h4><p id="val" name="val">please entre actialitie description</p></div>
										</div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Add
												actualitie</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>

							<!-- edit actualite start-->

							<core:if test="<%=request.getParameter(\"editactualite\")!=null%>">
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do?editactualite=<%=request.getParameter("editactualite")%>"
										onsubmit='return valideactualite(false)' target="result"
										enctype="multipart/form-data">
										
											<core:set var="actualite"
												value="<%=admin.get(\"actualites\",\"ID\",\"=\",\"\"+request.getParameter(\"editactualite\"),\"\",\"\",\"\",\"\",\"Unique\")%>"></core:set>
											
										<div class="control-group">
											<label class="control-label" for="titre">Titre</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon"></i></span><input
														id="titre" name="titre" required="required"
														type="text" value="${actualite.getTitre() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="description">Description</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon"></i></span>
														<textarea class="autogrow" id="description"
														name="description">${actualite.getDescription() }</textarea>
												</div>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="aimage">Actual
												image</label>
											<div class="controls">
												<li class="thumbnail"><a id="aimage" name="aimage"
													style="background: url(img/gallery/thumbs/1.jpg)" title=""
													href="././assets/images/ActualitesImages/${actualite.getURL()}"><img
														class="grayscale"
														src="././assets/images/ActualitesImages/${actualite.getURL()}"
														alt="${actualite.getTitre()}"></a></li>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="image">change image</label>
											<div class="controls">
												<input class="input-file uniform_on"
													id="image" name="image" type="file">
											</div>
										</div>
										<iframe name="result" id="result"
											style="visibility: collapse; display: none;"></iframe>
										<div id="produitresult" name="produitresult">
										<div class="alert alert-info " id="res" name="res"><button type="button" class="close" data-dismiss="alert">×</button><h4 class="alert-heading" id="tit" val="tit"></h4><p id="val" name="val">please entre Actualitie description</p></div>
										</div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Save
												Changes</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>
							<!-- edit actualite end -->
                            <!-- Manage Actualite -->
                            
							<!-- Manage products -->
							<core:if test="<%=request.getParameter(\"addproduit\")!=null%>">
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do?addproduit=1"
										onsubmit='return valideproduit(true)' target="result"
										enctype="multipart/form-data">
										<div class="control-group">
											<label class="control-label" for="country">Product
												marque</label>
											<div class="controls">
												<select id="marque" name="marque" data-rel="chosen">
													<core:forEach var="marque"
														items="<%=admin.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<option id="${marque.getID() }" value="${marque.getID() }"
															selected="selected">${marque.getMarque() }</option>
													</core:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="libelle">Libelle</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon"></i></span><input
														id="libelle" name="libelle" required="required"
														type="text">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="prix">prix unitaire</label>
											<div class="controls">
												<div class="input-prepend input-append">
													<span class="add-on">$</span><input id="prix" name="prix"
														required="required" required="required" type="number"><span
														class="add-on">.00</span>
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="quantite">quantite</label>
											<div class="controls">
												<div>
													<span><i></i></span><input id="quantite" name="quantite"
														required="required" required="required" type="number">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="description">description</label>
											<div class="controls">
												<div>
													<span><i></i></span>
													<textarea class="autogrow" id="description"
														name="description"></textarea>
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="file">product image</label>
											<div class="controls">
												<input class="input-file uniform_on" required="required"
													id="image" name="image" type="file">
											</div>
										</div>
										<iframe name="result" id="result" style="visibility: collapse;display: none;"></iframe>
										<div id="produitresult" name="produitresult">
										<div class="alert alert-info " id="res" name="res"><button type="button" class="close" data-dismiss="alert">×</button><h4 class="alert-heading" id="tit" val="tit"></h4><p id="val" name="val">please entre your product descriptions</p></div>
										</div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Add
												product</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>

							<!-- edit produit start-->

							<core:if test="<%=request.getParameter(\"editproduit\")!=null%>">
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do?editproduit=<%=request.getParameter("editproduit")%>"
										onsubmit='return valideproduit(false)' target="result"
										enctype="multipart/form-data">
										<div class="control-group">
											<core:set var="produit"
												value="<%=admin.get(\"produits\",\"ID\",\"=\",\"\"+request.getParameter(\"editproduit\"),\"\",\"\",\"\",\"\",\"Unique\")%>"></core:set>
											<label class="control-label" for="country">product
												marque</label>
											<div class="controls">
												<select id="marque" name="marque" data-rel="chosen">
													<core:forEach var="marque"
														items="<%=admin.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<core:if test="${produit.getMarque()== marque.getID()}">
															<option id="${marque.getID() }"
																value="${marque.getID() }" selected="selected">${marque.getMarque() }</option>
														</core:if>
														<core:if test="${produit.getMarque()!= marque.getID()}">
															<option id="${marque.getID() }"
																value="${marque.getID() }">${marque.getMarque() }</option>
														</core:if>
													</core:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="libelle">Libelle</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon"></i></span><input
														id="libelle" name="libelle" required="required"
														type="text" value="${produit.getLibelle() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="prix">prix unitaire</label>
											<div class="controls">
												<div class="input-prepend input-append">
													<span class="add-on">$</span><input id="prix" name="prix"
														required="required" required="required" type="number" value="${produit.getPrixUnitaire() }"><span
														class="add-on">.00</span>
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="quantite">quantite</label>
											<div class="controls">
												<div>
													<span><i></i></span><input id="quantite" name="quantite"
														required="required" required="required" type="number" value="${produit.getQuantite() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="description">description</label>
											<div class="controls">
												<div>
													<span><i></i></span>
													<textarea class="autogrow" id="description"
														name="description">${produit.getDescription() }</textarea>
												</div>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="aimage">Actual
												image</label>
											<div class="controls">
												<li class="thumbnail"><a id="aimage" name="aimage"
													style="background: url(img/gallery/thumbs/1.jpg)" title=""
													href="././assets/images/ProductImages/${produit.getURL()}"><img
														class="grayscale"
														src="././assets/images/ProductImages/${produit.getURL()}"
														alt="${produit.getLibelle()}"></a></li>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="image">change image</label>
											<div class="controls">
												<input class="input-file uniform_on"
													id="image" name="image" type="file">
											</div>
										</div>
										<iframe name="result" id="result"
											style="visibility: collapse; display: none;"></iframe>
										<div id="produitresult" name="produitresult">
										<div class="alert alert-info " id="res" name="res"><button type="button" class="close" data-dismiss="alert">×</button><h4 class="alert-heading" id="tit" val="tit"></h4><p id="val" name="val">please entre your product descriptions</p></div>
										</div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Save
												Changes</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>
							<!-- edit produit end -->
							<!-- Manage produit -->

							<!-- Manage Clients -->
							<core:if test="<%=request.getParameter(\"addclient\")!=null%>">
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do"
										onsubmit="return signup()">
										<div class="control-group">
											<label class="control-label" for="email">Email
												address</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-envelope"></i></span><input
														id="email" name="email" required="required" type="email">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="username">username</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-user"></i></span><input
														id="username" name="username" required="required"
														type="text">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="password">password</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-lock"></i></span><input
														id="password" name="password" required="required"
														type="password">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="nom">nom</label>
											<div class="controls">
												<div>
													<span class="add-on"><i></i></span><input id="nom"
														name="nom" required="required" type="text">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="prenom">prenom</label>
											<div class="controls">
												<div>
													<span class="add-on"><i></i></span><input id="prenom"
														name="prenom" required="required" type="text">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="age">your age</label>
											<div class="controls">
												<div>
													<span><i></i></span><input id="age" name="age"
														required="required" type="number">
												</div>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="gender">your gender</label>
											<div class="controls">
												<select id="gender">
													<option id="Male" value="Male" selected="selected">Male</option>
													<option id="Female" value="Female">Female</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="country">your
												country</label>
											<div class="controls">
												<select id="country" data-rel="chosen"
													onchange='javascript:choose()'>
													<core:forEach var="country"
														items="<%=admin.get(\"countries\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<option id="${country.getID() }"
															value="${country.getID() }" selected="selected">${country.getLibelle() }</option>
													</core:forEach>
												</select>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="ville">your city</label>
											<div class="controls" id="villeconainer">
												<select id="ville" data-rel="chosen">
													<core:forEach var="ville"
														items="<%=admin.get(\"ville\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<option id="${ville.getID() }" value="${ville.getID() }"
															selected="selected">${ville.getLibelle() }</option>
													</core:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="adresse">your
												adresse </label>
											<div class="controls">
												<input type="text" class="span6 typeahead" id="adresse"
													name="adresse" required="required">

											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="typecard">your card
												type</label>
											<div class="controls">
												<select id="typecard" data-rel="chosen">
													<core:forEach var="typecard"
														items="<%=admin.get(\"typecard\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<option id="${typecard.getID() }"
															value="${typecard.getID() }" selected="selected">${typecard.getLibelle() }</option>
													</core:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="creditcard">your
												credit card number</label>
											<div class="controls">
												<div>
													<span><i></i></span><input id="creditcard"
														name="creditcard" required="required" required="required"
														type="text">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="marques">Subscribe
												in our newsletters</label>
											<div class="controls">
												<select id="marques" multiple data-rel="chosen">
													<core:forEach var="marque"
														items="<%=admin.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<option id="${marque.getID() }" value="${marque.getID() }">${marque.getMarque() }</option>
													</core:forEach>

												</select>
											</div>
										</div>
										<div id="signupresult" name="signupresult"></div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Add
												client</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>

							<!-- edit client start-->

							<core:if test="<%=request.getParameter(\"editclient\")!=null%>">
								<core:set var="client"
									value="<%=admin.get(\"clients\",\"ID\",\"=\",\"\"+request.getParameter(\"editclient\"),\"\",\"\",\"\",\"\",\"Unique\")%>"></core:set>
								<div class="box-content">
									<form class="form-horizontal" method="post" action="AdminPanel.Do"
										onsubmit="return edit()">
										<div class="control-group">
											<label class="control-label" for="email">Email
												address</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-envelope"></i></span><input
														id="email" name="email" required="required" type="email"
														value="${client.getEmail() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="username">username</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-user"></i></span><input
														id="username" name="username" required="required"
														type="text" value="${client.getUsername() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="password">password</label>
											<div class="controls">
												<div class="input-prepend">
													<span class="add-on"><i class="icon-lock"></i></span><input
														id="password" name="password" required="required"
														type="password" value="${client.getPassword() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="nom">nom</label>
											<div class="controls">
												<div>
													<span class="add-on"><i></i></span><input id="nom"
														name="nom" required="required" type="text"
														value="${client.getNom() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="prenom">prenom</label>
											<div class="controls">
												<div>
													<span class="add-on"><i></i></span><input id="prenom"
														name="prenom" required="required" type="text"
														value="${client.getPrenom() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="age">your age</label>
											<div class="controls">
												<div>
													<span><i></i></span><input id="age" name="age"
														required="required" type="number"
														value="${client.getAge() }">
												</div>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="gender">your gender</label>
											<div class="controls">
												<select id="gender">
													<core:if test="${client.getGender() == \"Male\" }">
														<option id="Male" value="Male" selected="selected">Male</option>
													</core:if>
													<core:if test="${client.getGender() != \"Male\" }">
														<option id="Male" value="Male">Male</option>
													</core:if>
													<core:if test="${client.getGender() == \"Female\" }">
														<option id="Female" value="Female" selected="selected">Female</option>
													</core:if>
													<core:if test="${client.getGender() != \"Female\" }">
														<option id="Female" value="Female">Female</option>
													</core:if>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="country">your
												country</label>
											<div class="controls">
												<select id="country" data-rel="chosen"
													onchange='javascript:choose()'>
													<core:forEach var="country"
														items="<%=admin.get(\"countries\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<core:if test="${client.getCountry() == country.getID() }">
															<option id="${country.getID() }"
																value="${country.getID() }" selected="selected">${country.getLibelle() }</option>
														</core:if>
														<core:if test="${client.getCountry() != country.getID() }">
															<option id="${country.getID() }"
																value="${country.getID() }">${country.getLibelle() }</option>
														</core:if>
													</core:forEach>
												</select>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="ville">your city</label>
											<div class="controls" id="villeconainer">
												<select id="ville" data-rel="chosen">
													<core:forEach var="ville"
														items="<%=admin.get(\"ville\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<core:if test="${client.getVille() == ville.getID() }">
															<option id="${ville.getID() }" value="${ville.getID() }"
																selected="selected">${ville.getLibelle() }</option>
														</core:if>
														<core:if test="${client.getVille() != ville.getID() }">
															<option id="${ville.getID() }" value="${ville.getID() }">${ville.getLibelle() }</option>
														</core:if>
													</core:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="adresse">your
												adresse </label>
											<div class="controls">
												<input type="text" class="span6 typeahead" id="adresse"
													name="adresse" required="required"
													value="${client.getAdresse() }">

											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="typecard">your card
												type</label>
											<div class="controls">
												<select id="typecard" data-rel="chosen">
													<core:forEach var="typecard"
														items="<%=admin.get(\"typecard\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<core:if
															test="${client.getTypeCard() == typecard.getID() }">
															<option id="${typecard.getID() }"
																value="${typecard.getID() }" selected="selected">${typecard.getLibelle() }</option>
														</core:if>
														<core:if
															test="${client.getTypeCard() != typecard.getID() }">
															<option id="${typecard.getID() }"
																value="${typecard.getID() }">${typecard.getLibelle() }</option>
														</core:if>
													</core:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="creditcard">your
												credit card number</label>
											<div class="controls">
												<div>
													<span><i></i></span><input id="creditcard"
														name="creditcard" required="required" required="required"
														type="text" value="${client.getNumeroCard() }">
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="marques">Subscribe
												in our newsletters</label>
											<div class="controls">
												<select id="marques" multiple data-rel="chosen">
													<core:set var="news"
														value="<%=Do.convertToMarques((List<NewsLetters>)admin.get(\"newsletters\",\"IDClient\",\"=\",\"\"+request.getParameter(\"editclient\"),\"\",\"\",\"\",\"\",\"List\"))%>"></core:set>
													<core:forEach var="marque"
														items="<%=admin.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
														<core:if test="${marque.imISubscribed(news) }">
															<option id="${marque.getID() }"
																value="${marque.getID() }" selected="selected">${marque.getMarque() }</option>
														</core:if>
														<core:if test="${!marque.imISubscribed(news) }">
															<option id="${marque.getID() }"
																value="${marque.getID() }">${marque.getMarque() }</option>
														</core:if>
													</core:forEach>

												</select>
											</div>
										</div>
										<div id="signupresult" name="signupresult"></div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Save
												changes</button>
											<button type="reset" class="btn">Cancel</button>
										</div>
										</fieldset>
									</form>

								</div>
							</core:if>
							<!-- edit client end -->
							<!-- Manage Clients -->
						</div>
						<!--/span-->

					</div>
					<!--/row-->






					<!-- content ends -->
				</div>
				<!--/#content.span10-->
			</div>
			<!--/fluid-row-->

			<hr>

			<div class="modal hide fade" id="myModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>Settings</h3>
				</div>
				<div class="modal-body">
					<p>Here settings can be configured...</p>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#"
						class="btn btn-primary">Save changes</a>
				</div>
			</div>

			<footer>
			
			</footer>

		</div>
		<!--/.fluid-container-->
	</core:if>

	<!-- jQuery -->

	<script src="././assets/assetsadmin/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="././assets/assetsadmin/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="././assets/assetsadmin/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="././assets/assetsadmin/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="././assets/assetsadmin/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="././assets/assetsadmin/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="././assets/assetsadmin/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="././assets/assetsadmin/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="././assets/assetsadmin/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="././assets/assetsadmin/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="././assets/assetsadmin/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="././assets/assetsadmin/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="././assets/assetsadmin/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="././assets/assetsadmin/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="././assets/assetsadmin/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="././assets/assetsadmin/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='././assets/assetsadmin/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='././assets/assetsadmin/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="././assets/assetsadmin/js/excanvas.js"></script>
	<script src="././assets/assetsadmin/js/jquery.flot.min.js"></script>
	<script src="././assets/assetsadmin/js/jquery.flot.pie.min.js"></script>
	<script src="././assets/assetsadmin/js/jquery.flot.stack.js"></script>
	<script src="././assets/assetsadmin/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->
	<script type="text/javascript">
	function valideactualite(type) {
		if(type==true)
			{
		var _validFileExtensions = [ ".jpg", ".jpeg", ".bmp", ".gif",".png" ];
		//alert($('#marque').val()+" "+$('#libelle').val()+" "+$('#prix').val()+" "+$('#quantite').val()+" "+$('#description').val()+" "+$('#image').val());
		var blnValid = false;
		for (var j = 0; j < _validFileExtensions.length; j++) {
			var sCurExtension = _validFileExtensions[j];
			if ($('#image').val().substr(
					$('#image').val().length - sCurExtension.length,
					sCurExtension.length).toLowerCase() == sCurExtension
					.toLowerCase()) {
				blnValid = true;
				break;
			}
		}
			if (!blnValid) {
                document.getElementById('res').className = "alert alert-error";
	            document.getElementById('tit').innerHTML = "invalid image";
	            document.getElementById('val').innerHTML = ".jpg, .jpeg, .bmp, .gif, .png";
				return false;
			}
			}
		//document.getElementById("produitresult").innerHTML = "<div class=\"alert alert-info \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">correct parameters!</h4><p>sending to server</p></div>";
		return true;
	}
		function valideproduit(type) {
			if(type==true)
				{
			var _validFileExtensions = [ ".jpg", ".jpeg", ".bmp", ".gif",".png" ];
			//alert($('#marque').val()+" "+$('#libelle').val()+" "+$('#prix').val()+" "+$('#quantite').val()+" "+$('#description').val()+" "+$('#image').val());
			var blnValid = false;
			for (var j = 0; j < _validFileExtensions.length; j++) {
				var sCurExtension = _validFileExtensions[j];
				if ($('#image').val().substr(
						$('#image').val().length - sCurExtension.length,
						sCurExtension.length).toLowerCase() == sCurExtension
						.toLowerCase()) {
					blnValid = true;
					break;
				}
			}
				if (!blnValid) {
	                document.getElementById('res').className = "alert alert-error";
		            document.getElementById('tit').innerHTML = "invalid image";
		            document.getElementById('val').innerHTML = ".jpg, .jpeg, .bmp, .gif, .png";
					return false;
				}
				}
			//document.getElementById("produitresult").innerHTML = "<div class=\"alert alert-info \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">correct parameters!</h4><p>sending to server</p></div>";
			return true;
		}
		function signup() {
			var querry = "";
			querry = "username=" + $('#username').val() + "&password="
					+ $('#password').val() + "&email=" + $('#email').val();
			querry += "&nom=" + $('#nom').val() + "&prenom="
					+ $('#prenom').val() + "&country=" + $('#country').val()
					+ "&ville=" + $('#ville').val() + "&adresse="
					+ $('#adresse').val() + "&age=" + $('#age').val()
					+ "&gender=" + $('#gender').val();
			querry += "&typecard=" + $('#typecard').val() + "&creditcard="
					+ $('#creditcard').val() + "&marques=0";
			$("#marques :selected").map(function(i, el) {
				querry += "," + $(el).val();
			});
			var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
			if (!emailPattern.test(document.getElementById("email").value)) {
				document.getElementById("signupresult").innerHTML = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">Warning!</h4><p>incorrect email adresse</p></div>";
				return false;
			}
			var numbertest = /^[0-9]{14,16}$/;
			if (!numbertest.test(document.getElementById("creditcard").value)) {
				document.getElementById("signupresult").innerHTML = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">Warning!</h4><p>incorrect credit card number</p></div>";
				return false;
			}
			document.getElementById("signupresult").innerHTML = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">sending!</h4><p>correct format now sending ...</p></div>";
			dosignup(querry);
			return false;
		}

		function edit() {
			var querry = "";
			querry = "username=" + $('#username').val() + "&password="
					+ $('#password').val() + "&email=" + $('#email').val();
			querry += "&nom=" + $('#nom').val() + "&prenom="
					+ $('#prenom').val() + "&country=" + $('#country').val()
					+ "&ville=" + $('#ville').val() + "&adresse="
					+ $('#adresse').val() + "&age=" + $('#age').val()
					+ "&gender=" + $('#gender').val();
			querry += "&typecard=" + $('#typecard').val() + "&creditcard="
					+ $('#creditcard').val() + "&marques=0";
			$("#marques :selected").map(function(i, el) {
				querry += "," + $(el).val();
			});
			var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
			if (!emailPattern.test(document.getElementById("email").value)) {
				document.getElementById("signupresult").innerHTML = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">Warning!</h4><p>incorrect email adresse</p></div>";
				return false;
			}
			var numbertest = /^[0-9]{14,16}$/;
			if (!numbertest.test(document.getElementById("creditcard").value)) {
				document.getElementById("signupresult").innerHTML = "<div class=\"alert alert-error \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">Warning!</h4><p>incorrect credit card number</p></div>";
				return false;
			}
			document.getElementById("signupresult").innerHTML = "<div class=\"alert alert-success \"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button><h4 class=\"alert-heading\">sending!</h4><p>correct format now sending ...</p></div>";
			doedit(querry);
			return false;
		}
		function doedit(querry) {
			var xmlHttpReq = false;
			var self = this;
			// Mozilla/Safari
			if (window.XMLHttpRequest) {
				self.xmlHttpReq = new XMLHttpRequest();
			}
			// IE
			else if (window.ActiveXObject) {
				self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			self.xmlHttpReq
					.open(
							'POST',
							"AdminPanel.Do?editclient="
									+
	<%=request.getParameter("editclient")!=null?request.getParameter("editclient"):0%>
		,
							true);
			self.xmlHttpReq.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4) {
					signupresult(self.xmlHttpReq.responseText);
				}
			}
			self.xmlHttpReq.send(querry);
		}
		function dosignup(querry) {
			var xmlHttpReq = false;
			var self = this;
			// Mozilla/Safari
			if (window.XMLHttpRequest) {
				self.xmlHttpReq = new XMLHttpRequest();
			}
			// IE
			else if (window.ActiveXObject) {
				self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			self.xmlHttpReq.open('POST', "AdminPanel.Do?addclient=1", true);
			self.xmlHttpReq.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4) {
					signupresult(self.xmlHttpReq.responseText);
				}
			}
			self.xmlHttpReq.send(querry);
		}

		function signupresult(str) {
			document.getElementById("signupresult").innerHTML = str;
		}
		function choose() {
			dochoose();
		}
		function dochoose() {
			var xmlHttpReq = false;
			var self = this;
			// Mozilla/Safari
			if (window.XMLHttpRequest) {
				self.xmlHttpReq = new XMLHttpRequest();
			}
			// IE
			else if (window.ActiveXObject) {
				self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			self.xmlHttpReq.open('POST', "AdminPanel?getcities="
					+ document.getElementById("country").value, true);
			self.xmlHttpReq.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4) {
					chooseresult(self.xmlHttpReq.responseText);
				}
			}
			self.xmlHttpReq.send("refresh=1");
		}

		function chooseresult(str) {
			document.getElementById("villeconainer").innerHTML = str;
		}
	</script>
	<!-- select or dropdown enhancer -->
	<script src="././assets/assetsadmin/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="././assets/assetsadmin/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="././assets/assetsadmin/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="././assets/assetsadmin/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="././assets/assetsadmin/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="././assets/assetsadmin/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="././assets/assetsadmin/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="././assets/assetsadmin/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="././assets/assetsadmin/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="././assets/assetsadmin/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="././assets/assetsadmin/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="././assets/assetsadmin/js/charisma.js"></script>
	<script type="text/javascript">
</body>
</html>