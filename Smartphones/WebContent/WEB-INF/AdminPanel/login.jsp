<%@page import="Isamm.ing.java.Smartphones.Classes.DBAccessControl"%>
<%@page import="Isamm.ing.java.Smartphones.Models.Administrateurs"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
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
    		response.sendRedirect("AdminPanel.DashBoard");
    	}
    }
    %>
<!DOCTYPE html>
<html lang="en">
<head>
	<!--
		Charisma v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="utf-8">
	<title>Welcome to Smartphones backend</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">
<link id="bs-css" href="././assets/assetsadmin/css/bootstrap-united.css" rel="stylesheet">
	<!-- The styles -->
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
		<div class="container-fluid">
		<div class="row-fluid">
		
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>Welcome to Smartphones</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div id="result" class="alert alert-info">
						Please login with your Username and Password.
					</div>
					<input type="text" id="state" style="visibility: collapse;display: none;">
					<form class="form-horizontal" action="#" method="post" onsubmit="return false;">
						<fieldset>
							<div class="input-prepend" title="Username" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="username" id="username" type="text" value="Username" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="password" id="password" type="password" value="Password" />
							</div>
							<div class="clearfix"></div>
							<div class="clearfix"></div>

							<p class="center span5">
							<button type="submit" class="btn btn-primary" OnClick="clickedButton()">Login</button>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->

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
//makes login header display username when submit button is clicked
  function clickedButton() {
    var x = document.getElementById('username').value;
    var y = document.getElementById('password').value;
    if ((x!==""&&y!="")&&(x!="Username"&&y!="Password")) {
        Login();
        return false;
    }
    else {
    	document.getElementById('result').innerHTML = "Wrong Parameters!";
    	return false;
        // resets login header and clears text boxes
        
    }
  }
  
	function Login() {
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
	    var x = document.getElementById('username').value;
	    var y = document.getElementById('password').value;
		self.xmlHttpReq.open('POST', "AdminPanel?admin="+x+"&pass="+y, true);
		self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		self.xmlHttpReq.onreadystatechange = function() {
		if (self.xmlHttpReq.readyState == 4) {
		Writeresult(self.xmlHttpReq.responseText);
		}
		}
		self.xmlHttpReq.send(null);
		}

		function Writeresult(str){
			document.getElementById('result').innerHTML = str;
			document.getElementById('state').value = str;
			if(document.getElementById('state').value == "Found")
				{
				document.location.href = "AdminPanel.DashBoard";
				}
			}
  // resets login header and clears text boxes when reset button is clicked
  </script>
		
</body>
</html>
