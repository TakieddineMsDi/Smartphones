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
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
	<!-- The styles -->
	<link id="bs-css" href="././assets/assetsadmin/css/bootstrap-united.css" rel="stylesheet">
	<link id="bs-css" href="././assets/assetsadmin/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="././assets/assetsadmin/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="././assets/assetsadmin/css/charisma-app.css" rel="stylesheet">
	<link href="././assets/assetsadmin/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='././assets/assetsadmin/css/fullcalendar.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='././assets/assetsadmin/css/chosen.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/uniform.default.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/colorbox.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/jquery.noty.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/noty_theme_default.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/elfinder.min.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/elfinder.theme.css' rel='stylesheet'>
	<link href='././assets/assetsadmin/css/jquery.iphone.toggle.css' rel='stylesheet'>
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
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="index.html"> <span>Smartphones</span></a>
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> <%=admin.getUsername() %></span>
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
								<input placeholder="Search" class="search-query span2" name="query" type="text">
							</form>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
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
						<li><a class="ajax-link" href="AdminPanel.DashBoard"><i class="icon-home"></i><span class="hidden-tablet"> Dashboard</span></a></li>
						<li><a class="ajax-link" href="AdminPanel.ManagerClient"><i class="icon-user"></i><span class="hidden-tablet"> Manage Clients</span></a></li>
						<li><a class="ajax-link" href="AdminPanel.ManagerProduct"><i class="icon-briefcase"></i><span class="hidden-tablet"> Manage Products</span></a></li>
						<li><a class="ajax-link" href="AdminPanel.ManagerActualite"><i class="icon-list-alt"></i><span class="hidden-tablet"> Manage Actualities</span></a></li>
<li><a class="ajax-link" href="AdminPanel.ManagerCommande"><i class="icon-font"></i><span class="hidden-tablet"> Manage Commandes</span></a></li>
					</ul>
					<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">Products</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-briefcase"></i> Products</h2>
						<div class="box-icon">
						<a class="btn" href="#" onclick='javascript:dorefresh()'><i class="icon-refresh"></i></a>
							<a href="AdminPanel.Do?addproduit=1" class="btn btn-round"><i class="icon-plus"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
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
								  <th>Quantite</th>
								  <th>Since</th>
								  <th>Owner</th>
								  <th>Status</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody id="ajaxresult" name="ajaxresult">
						  <core:set var="produits" value="<%=admin.get(\"produits\", \"\", \"\", \"\", \"\", \"DateSubmit\", \"DESC\", \"\", \"List\") %>"></core:set>
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
								<td class="center">${produit.getPrixUnitaire()}</td>
								<td class="center">${produit.getQuantite()}</td>
								<td class="center">
									${produit.getDateSubmit()}
								</td>
								<td class="center">
								   <core:if test="${produit.isTrustWorthy()}">
								   <span class="label label-success">${produit.getROwner().getUsername()}</span>
								   </core:if>
								   <core:if test="${!produit.isTrustWorthy()}">
								   <a href="AdminPanel.Do?editclient=${produit.getROwner().getID()}"><span class="label label-info">${produit.getROwner().getUsername()}</span></a>
								   </core:if>
								</td>
								<td class="center">
								<core:if test="${produit.isActif()}">
								   <span class="label label-success">Actif</span>
								   </core:if>
									<core:if test="${produit.isInactif()}">
								   <span class="label label-warning">Inactif</span>
								   </core:if>
								   <core:if test="${produit.isArchive()}">
								   <span class="label label-info">Archived</span>
								   </core:if>
								   <core:if test="${produit.isBanned()}">
								   <span class="label label-important">Banned</span>
								   </core:if>
								</td>
								<td class="center">
								<div class="btn-group">
								  <a class="btn btn-primary" href="#"><i class="icon-th icon-white"></i> product</a>
								  <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
								  <ul class="dropdown-menu">
									<li><a href="AdminPanel.Do?editproduit=${produit.getID()}"><i class="icon-pencil"></i> Edit</a></li>
									<li><a href="#" onclick='javascript:dothis("deleteproduit","${produit.getID()}")'><i class="icon-trash"></i> Delete</a></li>
							    <core:if test="${produit.isInactif()}">
								<li><a href="#" onclick='javascript:dothis("activeproduit","${produit.getID()}")'><i class="icon-ok-circle"></i> Activate</a></li>
								</core:if>
								<core:if test="${produit.isArchive()}">
							    <li><a href="#" onclick='javascript:dothis("restoreproduit","${produit.getID()}")'><i class="icon-ban-circle"></i> restore</a></li>
							    </core:if>
							    <core:if test="${!produit.isArchive()}">
							    <li><a href="#" onclick='javascript:dothis("archiveproduit","${produit.getID()}")'><i class="icon-ok-circle"></i> Archive</a></li>
							   </core:if>
							   <core:if test="${!produit.isBanned()}">
								<li><a href="#" onclick='javascript:dothis("banproduit","${produit.getID()}")'><i class="icon-check"></i> Ban</a></li>
								</core:if>
								<core:if test="${produit.isBanned()}">
								<li><a href="#" onclick='javascript:dothis("unbanproduit","${produit.getID()}")'><i class="icon-remove-circle"></i> Unban</a></li>
								</core:if>
								  </ul>
								</div>
								</td>
							</tr>
							</core:forEach>
							</core:if>
							
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->

			<div class="row-fluid sortable">
				
				
			
			</div><!--/row-->
			
			<div class="row-fluid sortable">	
				
				
				
			
			</div><!--/row-->
			
			<div class="row-fluid sortable">	
				
			</div><!--/row-->
    
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
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
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<footer>
			</footer>
		
	</div><!--/.fluid-container-->
	
		</core:if>
<!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

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
function dothis(action,id)
{
   update(action,id);	
}
function update(action,id) {
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
	self.xmlHttpReq.open('POST', "AdminPanel", true);
	self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	self.xmlHttpReq.onreadystatechange = function() {
	if (self.xmlHttpReq.readyState == 4) {
	Writeresult(self.xmlHttpReq.responseText,id);
	}
	}
	self.xmlHttpReq.send("actionproduit="+action+","+id);
	}

	function Writeresult(str,id){
		var x = document.getElementById(id);
		x.innerHTML = str;
		//x.innerHTML = str;
		}
function dorefresh()
{
	refresh();	
}
function refresh() {
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
	self.xmlHttpReq.open('POST', "AdminPanel", true);
	self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	self.xmlHttpReq.onreadystatechange = function() {
	if (self.xmlHttpReq.readyState == 4) {
		refreshresult(self.xmlHttpReq.responseText);
	}
	}
	self.xmlHttpReq.send("refreshproduit=1");
	}

	function refreshresult(str){
		var x = document.getElementById("ajaxresult");
		x.innerHTML = str;
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