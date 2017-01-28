var w3c=document.getElementById && !document.all;
var ie=document.all;

if (ie||w3c) {
 // d�claration des variables uniquement pour IE vive microsoft ?!!
 var overlay;
 var my_window;
}

function affiche_overlay_window(image_fond_overlay,adresse_page,s,w,h,t,ml,tc,lc){
	// creation de l'overlay et affichage de l'image
	montreoverlay("<table class = 'image_calque ' valign = 'center' border = '0' align = 'center'><tr><td> <IMG  SRC='"+image_fond_overlay+"'></td></tr>");
	// creation de la fen�tre
	montrefenetre(adresse_page,s,w,h,t,ml,tc,lc);


}

function click(){
    alert("clicked");
}
function montreoverlay(text) {
  if (w3c||ie){
    overlay = document.all ? document.all["overlay"] : document.getElementById ? document.getElementById("overlay") : ""
    if(overlay==null){
    overlay=my_window = document.all ? parent.document.all['overlay'] : parent.document.getElementById ? parent.document.getElementById('overlay') : ""
    }
    overlay.style.top ="60px";
    overlay.innerHTML = text; // fixe le code HTML dans l'overlay balise (div)
    overlay.style.visibility = "visible"; // modification du style

  }
}

function montrefenetre(html,s,w,h,t,ml,tc,lc) {
  if (w3c||ie){
    //affichage de la fenetre
	my_window = document.all ? document.all['window'] : document.getElementById ? document.getElementById('window') : ""
	    if(my_window==null){
	    	my_window=my_window = document.all ? parent.document.all['window'] : parent.document.getElementById ? parent.document.getElementById('window') : ""
	     }
	my_window.style.visibility = "visible";
    my_window.style.width = w;
    my_window.style.height = h;
    my_window.style.top = t;
    my_window.style.left = ml;
	// affichage du corps de la fen�tre (balise iframe)
    my_window = document.all ? document.all['contempwindow'] : document.getElementById ? document.getElementById('contempwindow') : ""
	    if(my_window==null){
	    	my_window=my_window = document.all ? parent.document.all['contempwindow'] : parent.document.getElementById ? parent.document.getElementById('contempwindow') : ""
	     }
    	my_window.style.visibility = "visible";
    my_window.style.width = w;
    my_window.style.height = h;
    my_window.style.top = t;
    my_window.style.left = ml;
    my_window.scrolling = s;
    parent.frames['contempwindow'].location.href= html ;
    my_window = document.all ? document.all['close'] : document.getElementById ? document.getElementById('close') : ""
	    if(my_window==null){
	    	my_window=my_window = document.all ? parent.document.all['close'] : parent.document.getElementById ? parent.document.getElementById('close') : ""
	     }
    	my_window.style.visibility = "visible";
    my_window.style.top = tc;
    my_window.style.left = lc;
    
  }
}




function cachetout() {
	if (w3c||ie){

	// masque la fenetre (balise div [window] )
	my_window = parent.document.getElementById('window');
	my_window.style.visibility = "hidden";

	// masque le contenu (balise iframe [contempwindow])
	my_window = parent.document.getElementById('contempwindow');
	my_window.style.visibility = "hidden";

	// masque l'overlay (balise div [overlay])
	my_window = document.all ? parent.document.all['overlay'] : parent.document.getElementById ? parent.document.getElementById('overlay') : ""
	my_window.style.visibility = "hidden";

       	my_window = document.all ? parent.document.all['close'] : parent.document.getElementById ? parent.document.getElementById('close') : ""
        my_window.style.visibility = "hidden";
	}


}

function cachetFromParent() {
	if (w3c||ie){

	// masque la fenetre (balise div [window] )
	my_window = document.getElementById('window');
	my_window.style.visibility = "hidden";

	// masque le contenu (balise iframe [contempwindow])
	my_window = document.getElementById('contempwindow');
	my_window.style.visibility = "hidden";

	// masque l'overlay (balise div [overlay])
	my_window = document.all ? document.all['overlay'] : document.getElementById ? document.getElementById('overlay') : ""
	my_window.style.visibility = "hidden";

        my_window = document.all ? parent.document.all['close'] : parent.document.getElementById ? parent.document.getElementById('close') : ""
        my_window.style.visibility = "hidden";
       

	}


}