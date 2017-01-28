	function CheckLogin(strURL,login,pass) {
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
		self.xmlHttpReq.open('POST', strURL+"?user="+login+"&pass="+pass, true);
		self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		self.xmlHttpReq.onreadystatechange = function() {
		if (self.xmlHttpReq.readyState == 4) {
		Writeresult(self.xmlHttpReq.responseText);
		}
		}
		self.xmlHttpReq.send(null);
		}

		function Writeresult(str){
			var result = document.login.errors;
			result.value = str;
			if(result.value=="Found"){
				parent.window.location = "Home";
			 }
			}
		
		
		function CheckSignUp(querry) {
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
			self.xmlHttpReq.open('POST', "SignUp", true);
			self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
			if (self.xmlHttpReq.readyState == 4) {
				WriteSignUp(self.xmlHttpReq.responseText,Err);
			}
			}
			self.xmlHttpReq.send(querry);
			}

			function WriteSignUp(str){
				alert(str);
				}
			
			function validateForm()
			{ 
		        var fields = new Array(
		                $('#username').val(),
		                $('#password').val(),
		                $('#email').val(),
		                $('#nom').val() + ' ' + $('#prenom').val(),
		                $('#country').val(),
		                $('#ville').val(),
		                $('#adresse').val(),   
		                $('#age').val() ,
		                $('#gender').val(),
		                $('#typecard').val(),
		                $('#creditcard').val()
		            );
				querry="username="+$('#username').val()+"&pass="+$('#password').val()+"&email="+$('#email').val();
				querry+="&nom="+$('#nom').val()+"&prenom="+$('#prenom').val()+"&country="+$('#country').val()+"&ville="+$('#ville').val()+"&address="+$('#adresse').val()+"&age="+$('#age').val()+"&gender="+$('#gender').val();
				querry+="&typecard="+$('#typecard').val()+"&creditcard="+$('#creditcard').val();
				var Marques=document.all ? document.all["Marques"] : document.getElementById ? document.getElementById("Marques") : "";
				alert(Marques.innerHTML);
				for(var i=0;i<Marques.childNodes.length;i++){
						if(Marques.childNodes[i].innerHTML){
							if(Marques.childNodes[i].childNodes[1].checked)
							querry+="&"+Marques.childNodes[i].childNodes[1].name+"="+Marques.childNodes[i].childNodes[1].name;}
						}
				CheckSignUp(querry,err);
				return false;
			}
			
			
			function validateUpForm()
			{   var fieldsets = document.forms[0].getElementsByTagName('FIELDSET');
			    var querry="";
				var email = null;
				var username = null;
				var pass = null;
				for(var i=0;i<fieldsets[0].childNodes.length;i++){
					if(fieldsets[0].childNodes[i].name=="email"){
						email = fieldsets[0].childNodes[i];
					}
					if(fieldsets[0].childNodes[i].name=="username"){
						username = fieldsets[0].childNodes[i];
					}
					if(fieldsets[0].childNodes[i].name=="pass"){
						pass = fieldsets[0].childNodes[i];
					}
				}
				querry="UP=1&username="+username.value+"&pass="+pass.value+"&email="+email.value;
			    var nom=null;
			    var prenom=null;
			    var ville=null;
			    var adress=null;
				for(var i=0;i<fieldsets[1].childNodes.length;i++){
					if(fieldsets[1].childNodes[i].name=="Nom"){
						nom = fieldsets[1].childNodes[i];
					}
					if(fieldsets[1].childNodes[i].name=="Prenom"){
						prenom = fieldsets[1].childNodes[i];
					}
					if(fieldsets[1].childNodes[i].name=="Ville"){
						ville = fieldsets[1].childNodes[i];
					}
					if(fieldsets[1].childNodes[i].name=="Address"){
						adress = fieldsets[1].childNodes[i];
					}
				}
				querry+="&Nom="+nom.value+"&Prenom="+prenom.value+"&Ville="+ville.value+"&Address="+adress.value;
				var creditcard = null;
				var typeCard = null;
				for(var i=0;i<fieldsets[2].childNodes.length;i++){
					if(fieldsets[2].childNodes[i].name=="CreditCard"){
						creditcard = fieldsets[2].childNodes[i];
					}
					if(fieldsets[2].childNodes[i].name=="TypeCard"){
						typeCard = fieldsets[2].childNodes[i]
					}
				}
				querry+="&TypeCard="+typeCard.options[typeCard.selectedIndex].value+"&CreditCard="+creditcard.value;
				var Marques=null
				for(var i=0;i<fieldsets[0].childNodes.length;i++){
					if(fieldsets[0].childNodes[i].id=="Marques")
						Marques=fieldsets[0].childNodes[i];
				}
				for(var i=0;i<Marques.childNodes.length;i++){
						if(Marques.childNodes[i].innerHTML){
							if(Marques.childNodes[i].childNodes[1].checked)
							querry+="&"+Marques.childNodes[i].childNodes[1].name+"="+Marques.childNodes[i].childNodes[1].name;}
						}
				var err=null;
				for(var i=0;i<fieldsets[2].childNodes.length;i++){
					if(fieldsets[2].childNodes[i].id=="err")
						err=fieldsets[2].childNodes[i]
				}
				CheckSignUp(querry,err);
				return false;
			}
			
			
			function validateSubForm(){
				var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
				var fieldsets = document.forms[0].getElementsByTagName('FIELDSET');
				var marque = null;
				var produit = null;
				var prix = null;
				var quantite = null;
				var description = null;
				var url = null;
				var err = null;
				err = document.all ? document.all["err"] : document.getElementById ? document.getElementById("err") : ""
				//typeCard.options[typeCard.selectedIndex].value
				for(var i=0;i<fieldsets[0].childNodes.length;i++){
					if(fieldsets[0].childNodes[i].name=="Marque")
						marque = fieldsets[0].childNodes[i];
					if(fieldsets[0].childNodes[i].name=="Libelle")
						produit = fieldsets[0].childNodes[i];
					if(fieldsets[0].childNodes[i].name=="PrixUnitaire")
						prix = fieldsets[0].childNodes[i];
					if(fieldsets[0].childNodes[i].name=="Quantite")
						quantite = fieldsets[0].childNodes[i];
					if(fieldsets[0].childNodes[i].name=="Description")
						description = fieldsets[0].childNodes[i];
					if(fieldsets[0].childNodes[i].name=="file")
						url = fieldsets[0].childNodes[i];
				}
				if(produit.value==""||prix.value==""||quantite.value==""||url.value==""){
					if(produit.value=="")
						produit.focus();
					else if(prix.value=="")
						prix.focus();
					else if(quantite.value=="")
						quantite.focus();
					else if(description.value=="")
						description.focus();
					else{
						err.innerHTML="no file chosen!!";
					}
					return false;
				}
				else{
					if(isNaN(prix.value)||isNaN(quantite.value)){
						if(isNaN(prix.value))
							{
							prix.focus();
							err.innerHTML = "prix invalide";
							}
						else if(isNaN(quantite.value)){
							quantite.focus();
							err.innerHTML = "quantite invalide";
						}
						return false;
					}
					else{
						for (var j = 0; j < _validFileExtensions.length; j++) {
							var blnValid = false;
		                    var sCurExtension = _validFileExtensions[j];
		                    if (url.value.substr(url.value.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
		                        blnValid = true;
		                        break;
		                    }
		                    if(!blnValid){
		                    	err.innerHTML = "jpg, jpeg, bmp, gif, png are allowed";
		                    	return false;
		                    }
		                }
					}
				}
					err.innerHTML="Uploading";
				return true;
			}