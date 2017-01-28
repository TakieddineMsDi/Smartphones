<%@page import="Isamm.ing.java.Smartphones.doAll.Do"%>
<%@page import="Isamm.ing.java.Smartphones.Models.Marques"%>
<%@page import="java.util.List"%>
<%@page import="Isamm.ing.java.Smartphones.Classes.DBAccessControl"%>
<%@page import="Isamm.ing.java.Smartphones.Models.Clients"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<jsp:useBean id="Client" class="Isamm.ing.java.Smartphones.Models.Clients"></jsp:useBean>
<%
	Client = (Clients) request.getSession().getAttribute(
			"SmartphonesUserID");
	if (Client != null) {
		Client = (Clients) Do.get(Clients.getTableName(),
				"Username&Password", "=&=", Client.getUsername() + "&"
						+ Client.getPassword(), "AND", "", "", "",
				"Unique");
	}
	boolean LoggedIn = false;
	if (Client != null) {
		if (!Client.isEmpty()) {
			LoggedIn = true;
		}
	}
%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<script type="text/javascript" src="./assets/js/Ajax.js"></script>
<!--Slider-in icons-->

<head>
<meta charset="utf-8">
<!-- The styles -->
<link id="bs-css" href="./assets/assetsadmin/css/bootstrap-united.css"
	rel="stylesheet">
<link id="bs-css" href="./assets/assetsadmin/css/bootstrap-cerulean.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="./assets/assetsadmin/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="./assets/assetsadmin/css/charisma-app.css" rel="stylesheet">
<link href="./assets/assetsadmin/css/jquery-ui-1.8.21.custom.css"
	rel="stylesheet">
<link href='./assets/assetsadmin/css/fullcalendar.css' rel='stylesheet'>
<link href='./assets/assetsadmin/css/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='./assets/assetsadmin/css/chosen.css' rel='stylesheet'>
<link href='./assets/assetsadmin/css/uniform.default.css'
	rel='stylesheet'>
<link href='./assets/assetsadmin/css/colorbox.css' rel='stylesheet'>
<link href='./assets/assetsadmin/css/jquery.cleditor.css'
	rel='stylesheet'>
<link href='./assets/assetsadmin/css/jquery.noty.css' rel='stylesheet'>
<link href='./assets/assetsadmin/css/noty_theme_default.css'
	rel='stylesheet'>
<link href='./assets/assetsadmin/css/elfinder.min.css' rel='stylesheet'>
<link href='./assets/assetsadmin/css/elfinder.theme.css'
	rel='stylesheet'>
<link href='./assets/assetsadmin/css/jquery.iphone.toggle.css'
	rel='stylesheet'>
<link href='./assets/assetsadmin/css/opa-icons.css' rel='stylesheet'>
<link href='./assets/assetsadmin/css/uploadify.css' rel='stylesheet'>
	<link href='./assets/F1AgEicuA-Vh7KAhE4UhjfesZW2xOQ-xsNqO47m55DA.woff' rel='stylesheet' type='text/css'>
	<link href='./assets/hh4Od9pccylRpuf5LUPgEobN6UDyHWBl620a-IRfuBk.woff' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="./assets/styles.css" />
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a> <a class="brand pull-left" href="Home"> <span>Smartphones</span></a>
				<!-- user dropdown starts -->
				<core:if test="<%=LoggedIn == false%>">
					<div class="btn-group pull-right">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="icon-user"></i><span class="hidden-phone">Guest</span>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#">Welcome Guest</a></li>
							<li class="divider"></li>
							<li><a href="" class="btn-setting" onclick='dothis("login")'>Login</a></li>
							<li><a href="" class="btn-setting"
								onclick='dothis("signup")'>SignUp</a></li>
						</ul>
					</div>
				</core:if>
				<core:if test="<%=LoggedIn == true%>">
					<div class="btn-group pull-right">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="icon-user"></i><span class="hidden-phone"><%=Client.getUsername()%></span>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#">Welcome <%=Client.getUsername()%></a></li>
							<li class="divider"></li>
							<li><a href="" class="btn-setting"
								onclick='javascript:doaction("ups")'>Update Account</a></li>
							<li><a href="LogOut">Logout</a></li>
						</ul>
					</div>
					<core:if test="<%=Client.hasProducts()%>">
						<div class="btn-group pull-right">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-th"></i><span class="hidden-phone">My
									Products(<%=Client.CountProducts()%>)
							</span> <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="Show?Mine=<%=Client.getID() %>">View Products</a></li>
								<core:if test="<%=Client.isAllowedToSubmit() %>"><li><a href="#" class="btn-setting"
									onclick='javascript:doaction("addprod")'>Add a product</a></li>
								<li class="divider"></li></core:if>
							</ul>
						</div>
					</core:if>
						<div class="btn-group pull-right">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-th"></i><span class="hidden-phone">My
									Product
									<ul class="dropdown-menu" >
									<li>taki</li>
									</ul>
							</span> <span class="caret"></span>
							</a>
							
						</div>
				</core:if>
				<!-- user dropdown ends -->
				<!-- theme selector starts -->
				<!-- theme selector ends -->
				<div class="top-nav nav-collapse">
					<ul class="nav">
						<li><a href="Home">Home</a></li>
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
	<!-- Show All stats -->
	<div class="row-fluid sortable">
	<div class="row-fluid sortable">
	<div class="row-fluid" style="margin-left: 4%;width: 92%;">
				<div class="box span12">
					<div class="box-header well">
						<h2><i class="icon-info-sign"></i> Actualities</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
							<span id="sl_play" class="sl_command">&nbsp;</span>
	<span id="sl_pause" class="sl_command">&nbsp;</span>
	<span id="sl_i1" class="sl_command sl_i">&nbsp;</span>
	<span id="sl_i2" class="sl_command sl_i">&nbsp;</span>
	<span id="sl_i3" class="sl_command sl_i">&nbsp;</span>
	<span id="sl_i4" class="sl_command sl_i">&nbsp;</span>
	
	
	<section id="slideshow">
	
		<a class="commands prev commands1" href="#sl_i4" title="Go to last slide">&lt;</a>
		<a class="commands next commands1" href="#sl_i2" title="Go to 2nd slide">&gt;</a>
		<a class="commands prev commands2" href="#sl_i1" title="Go to 1rst slide">&lt;</a>
		<a class="commands next commands2" href="#sl_i3" title="Go to 3rd slide">&gt;</a>
		<a class="commands prev commands3" href="#sl_i2" title="Go to 2nd slide">&lt;</a>
		<a class="commands next commands3" href="#sl_i4" title="Go to 4th slide">&gt;</a>
		<a class="commands prev commands4" href="#sl_i3" title="Go to 3rd slide">&lt;</a>
		<a class="commands next commands4" href="#sl_i1" title="Go to first slide">&gt;</a>
		
		<a class="play_commands pause" href="#sl_pause" title="Maintain paused">Pause</a>
		<a class="play_commands play" href="#sl_play" title="Play the animation">Play</a>
		
		<div class="container">
			<div class="c_slider"></div>
			<div class="slider">
				<figure>
					<img src="./assets\images\MarquesLogos/SMARTPHONE.png" alt="" width="640" height="310" />
					<figcaption>The mirror of soul</figcaption>
				</figure><!--
				--><figure>
					<img src="./assets\images\MarquesLogos/SMARTPHONE.png" alt="" width="640" height="310" />
					<figcaption>Let's cross that bridge when we come to it</figcaption>
				</figure><!--
				--><figure>
					<img src="./assets\images\MarquesLogos/SMARTPHONE.png" alt="" width="640" height="310" />
					<figcaption>Sushi<em>(do)</em> time</figcaption>
				</figure><!--
				--><figure>
					<img src="./assets\images\MarquesLogos/SMARTPHONE.png" alt="" width="640" height="310" />
					<figcaption>Waking Life</figcaption>
				</figure>
			</div>
		</div>
		
		<span id="timeline"></span>
		
		<ul class="dots_commands"><!--
			--><li><a title="Show slide 1" href="#sl_i1">Slide 1</a></li><!--
			--><li><a title="Show slide 2" href="#sl_i2">Slide 2</a></li><!--
			--><li><a title="Show slide 3" href="#sl_i3">Slide 3</a></li><!--
			--><li><a title="Show slide 4" href="#sl_i4">Slide 4</a></li>
		</ul>
		
	</section>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
	</div>
	<div style="margin-left: 4%;width: 92%;">
				<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-briefcase"></i> Products</h2>
						<div class="box-icon">
													<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
							     <th style="visibility: collapse;display: none;"></th>
								  <th>View</th>
								  <th>Libelle</th>
								  <th>Marque</th>
								  <th>Price</th>
								  <th>Since</th>
								  <th>Owner</th>
								  <th>Action</th>
							  </tr>
						  </thead>   
						  <tbody id="ajaxresult" name="ajaxresult">
						  <core:set var="produits" value="<%=Do.get(\"produits\", \"Statut\", \"=\", \"\"+DBAccessControl.Actif, \"\", \"DateSubmit\", \"DESC\", \"\", \"List\") %>"></core:set>
						  <core:if test="${produits!=null&&!produits.get(0).isEmpty() }">
						  <core:forEach var="produit" items="${produits }">
							<tr id="${produit.getID() }" name="${produit.getID()}">
							    <td style="visibility: collapse;display: none;">${produit.getID()}</td>
								<td style="word-break: break-all;white-space: normal;">
								<li class="thumbnail">
								<a style="background:url(img/gallery/thumbs/1.jpg)" title="" href="././assets/images/ProductImages/${produit.getURL()}"><img class="grayscale" src="././assets/images/ProductImages/${produit.getURL()}" alt="${produit.getLibelle()}"></a>
							    </li>
							    </td>
								<td class="center">${produit.getLibelle()}</td>
								<td class="center">${produit.getRMarque().getMarque()}</td>
								<td class="center input-prepend">
													<span class="add-on">$</span><span class="add-on uneditable-input">${produit.getPrixUnitaire()}</span></td>
								<td class="center">
									${produit.getDateSubmit()}
								</td>
								<td class="center">
								   <core:if test="${produit.isTrustWorthy()}">
								   <span class="label label-success">${produit.getROwner().getUsername()}(WEBMASTER)</span>
								   </core:if>
								   <core:if test="${!produit.isTrustWorthy()}">
								   <span class="label label-info">${produit.getROwner().getUsername()}(User)</span>
								   </core:if>
								</td>
								<td>
								<a class="btn btn-success" href="#"><i class="icon-shopping-cart icon-white"></i> Buy</a>
								</td>
							</tr>
							</core:forEach>
							</core:if>
							
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div>
				</div>
				</div>
				<!--/span-->
	<!-- Show All ends -->
	
	<core:if test="<%=LoggedIn == false%>">
		<div class="modal hide fade" id="myModal" name="myModal">
			<div id="login" name="login"
				style="visibility: collapse; display: none;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3 id="hed" name="hed">Login</h3>
				</div>
				<div class="modal-body" id="bod" name="bod">
					<div class="control-group">
						<label class="control-label" for="username"></label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span><input
									id="username" name="username" required="required" type="text"
									value="">
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password"></label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span><input
									id="password" name="password" required="required"
									type="password" value="">
							</div>
						</div>
					</div>
					<div id="cont" name="cont">
						<div class="alert alert-info">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>you'r singing up!</strong>please enter your login
							information
						</div>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#"
							class="btn btn-primary" id="sav" name="sav"
							onclick='javascript:preparelogin(document.getElementById("username").value,document.getElementById("password").value,document.getElementById("cont"));'>Login</a>
					</div>
				</div>
			</div>

			<div id="registre" name="resgistre"
				style="visibility: collapse; display: none;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3 id="hed" name="hed">Sign Up</h3>
				</div>
				<div class="modal-body" id="bod" name="bod">
					<div class="box-content">
						<form class="form-horizontal" method="post" action="AdminPanel.Do"
							onsubmit="return signup()">
							<div class="control-group">
								<label class="control-label" for="email">Email address</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-envelope"></i></span><input
											id="email" name="email" required="required" type="email">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="user">username</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span><input
											id="user" name="user" required="required" type="text">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="pass">password</label>
								<div class="controls">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span><input
											id="pass" name="pass" required="required" type="password">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="nom">nom</label>
								<div class="controls">
									<div>
										<span class="add-on"><i></i></span><input id="nom" name="nom"
											required="required" type="text">
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
								<label class="control-label" for="country">your country</label>
								<div class="controls">
									<select id="country" data-rel="chosen"
										onchange='javascript:choose()'>
										<core:forEach var="country"
											items="<%=Do.get(\"countries\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
											<option id="${country.getID() }" value="${country.getID() }"
												selected="selected">${country.getLibelle() }</option>
										</core:forEach>
									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="ville">your city</label>
								<div class="controls" id="villeconainer">
									<select id="ville" data-rel="chosen">
										<core:forEach var="ville"
											items="<%=Do.get(\"ville\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
											<option id="${ville.getID() }" value="${ville.getID() }"
												selected="selected">${ville.getLibelle() }</option>
										</core:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="adresse">your adresse
								</label>
								<div class="controls">
									<input type="text" class="input x-larqe" id="adresse"
										name="adresse" required="required">

								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="typecard">your card
									type</label>
								<div class="controls">
									<select id="typecard" data-rel="chosen">
										<core:forEach var="typecard"
											items="<%=Do.get(\"typecard\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
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
										<span><i></i></span><input id="creditcard" name="creditcard"
											required="required" required="required" type="text">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="marques">Subscribe in
									our newsletters</label>
								<div class="controls">
									<select id="marques" multiple data-rel="chosen">
										<core:forEach var="marque"
											items="<%=Do.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
											<option id="${marque.getID() }" value="${marque.getID() }">${marque.getMarque() }</option>
										</core:forEach>

									</select>
								</div>
							</div>
							<div id="signupresult" name="signupresult"></div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Sign Up</button>
								<button type="reset" class="btn">Cancel</button>
							</div>
							</fieldset>
						</form>

					</div>
				</div>
			</div>
	</core:if>
	<core:if test="<%=LoggedIn == true%>">
		<div class="modal hide fade" id="myModal">
		<core:if test="<%=Client.isAllowedToSubmit() %>">
			<div id="addprod" name="addprod"
				style="visibility: collapse; display: none">
				<div class="box-content">
					<form class="form-horizontal" method="post"
						action="AdminPanel.Do?addproduit=2"
						onsubmit='return valideproduit(true)' target="result"
						enctype="multipart/form-data">
						<div class="control-group">
							<label class="control-label" for="country">Product marque</label>
							<div class="controls">
								<select id="marque" name="marque" data-rel="chosen">
									<core:forEach var="marque"
										items="<%=Do.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
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
										id="libelle" name="libelle" required="required" type="text">
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
									<textarea class="autogrow" id="description" name="description"></textarea>
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
						<iframe name="result" id="result"
							style="visibility: collapse; display: none;"></iframe>
						<div id="produitresult" name="produitresult">
							<div class="alert alert-info " id="res" name="res">
								<button type="button" class="close" data-dismiss="alert">×</button>
								<h4 class="alert-heading" id="tit" val="tit"></h4>
								<p id="val" name="val">please entre your product
									descriptions</p>
							</div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Add
								product</button>
							<button type="reset" class="btn">Cancel</button>
						</div>
						</fieldset>
					</form>

				</div>
			</div>
			</core:if>
			<div class="box-content" id="ups" name="ups"
				style="visibility: collapse; display: none;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3 id="hed" name="hed">Update Account</h3>
				</div>
				<div class="modal-body" id="bod" name="bod">
					<core:set var="client" value="<%=Client%>"></core:set>
					<form class="form-horizontal" method="post" action="AdminPanel.Do"
						onsubmit="return edit()">
						<div class="control-group">
							<label class="control-label" for="email">Email address</label>
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
										id="username" name="username" required="required" type="text"
										value="${client.getUsername() }">
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
									<span class="add-on"><i></i></span><input id="nom" name="nom"
										required="required" type="text" value="${client.getNom() }">
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
										required="required" type="number" value="${client.getAge() }">
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
							<label class="control-label" for="country">your country</label>
							<div class="controls">
								<select id="country" data-rel="chosen"
									onchange='javascript:choose()'>
									<core:forEach var="country"
										items="<%=Do.get(\"countries\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
										<core:if test="${client.getCountry() == country.getID() }">
											<option id="${country.getID() }" value="${country.getID() }"
												selected="selected">${country.getLibelle() }</option>
										</core:if>
										<core:if test="${client.getCountry() != country.getID() }">
											<option id="${country.getID() }" value="${country.getID() }">${country.getLibelle() }</option>
										</core:if>
									</core:forEach>
								</select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="ville">your city</label>
							<div class="controls" id="villeconainer">
								<select id="ville" data-rel="chosen">
									<core:forEach var="ville" items="<%=Do.get(\"ville\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
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
							<label class="control-label" for="adresse">your adresse </label>
							<div class="controls">
							<input class="input-xlarge focused" id="adresse" name="adresse" type="text" value="${client.getAdresse() }">
								

							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="typecard">your card
								type</label>
							<div class="controls">
								<select id="typecard" data-rel="chosen">
									<core:forEach var="typecard"
										items="<%=Do.get(\"typecard\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
										<core:if test="${client.getTypeCard() == typecard.getID() }">
											<option id="${typecard.getID() }"
												value="${typecard.getID() }" selected="selected">${typecard.getLibelle() }</option>
										</core:if>
										<core:if test="${client.getTypeCard() != typecard.getID() }">
											<option id="${typecard.getID() }"
												value="${typecard.getID() }">${typecard.getLibelle() }</option>
										</core:if>
									</core:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="creditcard">your credit
								card number</label>
							<div class="controls">
								<div>
									<span><i></i></span><input id="creditcard" name="creditcard"
										required="required" required="required" type="text"
										value="${client.getNumeroCard() }">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="marques">Subscribe in
								our newsletters</label>
							<div class="controls">
								<select id="marques" multiple data-rel="chosen">
									<core:set var="news"
										value="<%=Do.convertToMarques(Client.getOldNewsLetters())%>"></core:set>
									<core:forEach var="marque"
										items="<%=Do.get(\"marques\",\"Statut\",\"<>\",\"0\",\"\",\"\",\"\",\"\",\"List\")%>">
										<core:if test="${marque.imISubscribed(news) }">
											<option id="${marque.getID() }" value="${marque.getID() }"
												selected="selected">${marque.getMarque() }</option>
										</core:if>
										<core:if test="${!marque.imISubscribed(news) }">
											<option id="${marque.getID() }" value="${marque.getID() }">${marque.getMarque() }</option>
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
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">Close</a>
				</div>
			</div>
		</div>
	</core:if>
	<!--/.fluid-container-->

	<!-- external javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->

	<script src="./assets/assetsadmin/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="./assets/assetsadmin/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="./assets/assetsadmin/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="./assets/assetsadmin/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="./assets/assetsadmin/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="./assets/assetsadmin/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="./assets/assetsadmin/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="./assets/assetsadmin/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="./assets/assetsadmin/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="./assets/assetsadmin/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="./assets/assetsadmin/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="./assets/assetsadmin/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="./assets/assetsadmin/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="./assets/assetsadmin/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="./assets/assetsadmin/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="./assets/assetsadmin/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='./assets/assetsadmin/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='./assets/assetsadmin/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="./assets/assetsadmin/js/excanvas.js"></script>
	<script src="./assets/assetsadmin/js/jquery.flot.min.js"></script>
	<script src="./assets/assetsadmin/js/jquery.flot.pie.min.js"></script>
	<script src="./assets/assetsadmin/js/jquery.flot.stack.js"></script>
	<script src="./assets/assetsadmin/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->
	<script type="text/javascript">
	function display(action)
	{
		var calques = ["addprod", "ups" ];
		var Model = "myModal";
		for (var j = 0; j < calques.length; j++) {
			var curcalque = calques[j];
			if(curcalque != action)
				{
				document.getElementById(curcalque).style.visibility = "collapse";
				document.getElementById(curcalque).style.display = "none";
				}
			if(curcalque == action)
				{
				document.getElementById(curcalque).style.visibility = "visible";
				document.getElementById(curcalque).style.display = "inherit";
				document.getElementById(Model).style.width = "50%";
				document.getElementById(Model).style.left = "50%";
				}
		}
	}
		function doaction(action) {
			display(action);
		}
		function valideproduit(type) {
			if (type == true) {
				var _validFileExtensions = [ ".jpg", ".jpeg", ".bmp", ".gif",
						".png" ];
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
			querry = "username=" + $('#user').val() + "&password="
					+ $('#pass').val() + "&email=" + $('#email').val();
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
		function dothis(action) {
			if (action == "login") {
				document.getElementById("login").style.visibility = "visible";
				document.getElementById("login").style.display = "inherit";
				document.getElementById("registre").style.visibility = "collapse";
				document.getElementById("registre").style.display = "none";
			}
			if (action == "signup") {
				document.getElementById("registre").style.visibility = "visible";
				document.getElementById("registre").style.display = "inherit";
				document.getElementById("login").style.visibility = "collapse";
				document.getElementById("login").style.display = "none";
			}
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
			self.xmlHttpReq.open('POST', "AdminPanel.Do?editclient="
					+
	<%=LoggedIn == true ? Client.getID() : 0%>
		, true);
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
		function preparelogin(user, pass, cont) {
			if (user == "" && pass == "") {
				cont.innerHTML = "<div class=\"alert alert-warning\">"
						+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"
						+ "<strong>please enter a correct username and password!</strong>"
				"</div>";
			} else if (user == "") {
				cont.innerHTML = "<div class=\"alert alert-warning\">"
						+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"
						+ "<strong>please enter a correct username!</strong>"
				"</div>";
			} else if (pass == "") {
				cont.innerHTML = "<div class=\"alert alert-warning\">"
						+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"
						+ "<strong>please enter a correct password!</strong>"
				"</div>";
			} else {
				cont.innerHTML = "<div class=\"alert alert-info\">"
						+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>"
						+ "<strong>checking login please wait!</strong>"
				"</div>";
				CheckLogin(user, pass, cont);
			}
		}
		function CheckLogin(login, pass, cont) {
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
			self.xmlHttpReq.open('POST', "SignIn?user=" + login + "&pass="
					+ pass, true);
			self.xmlHttpReq.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4) {
					Writeresult(self.xmlHttpReq.responseText, cont);
				}
			}
			self.xmlHttpReq.send(null);
		}

		function Writeresult(str, cont) {
			cont.innerHTML = str;
			if (cont.innerHTML.indexOf("successfully") != -1) {
				document.location.href = "Home";
			}
		}
		function changeMe(text) {
			var x = document.getElementById("Save");
			x.href = text;
			alert(text);
		}
	</script>
	<!-- select or dropdown enhancer -->
	<script src="./assets/assetsadmin/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="./assets/assetsadmin/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="./assets/assetsadmin/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="./assets/assetsadmin/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="./assets/assetsadmin/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="./assets/assetsadmin/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="./assets/assetsadmin/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="./assets/assetsadmin/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="./assets/assetsadmin/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="./assets/assetsadmin/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="./assets/assetsadmin/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="./assets/assetsadmin/js/charisma.js"></script>
</body>
</html>
