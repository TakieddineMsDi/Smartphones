$(function(){
    //original field values
    var field_values = {
            //id        :  value
            'username'  : 'username',
            'password'  : 'password',
            'cpassword' : 'password',
            'email'  : 'email address',
            'nom'  : 'first name',
            'prenom'  : 'last name',
            'adresse'  : 'Adresse',
            'age'  : 'Age',
            'creditcard'  : 'Credit Card',
            
    };


    //inputfocus
    $('input#username').inputfocus({ value: field_values['username'] });
    $('input#password').inputfocus({ value: field_values['password'] });
    $('input#cpassword').inputfocus({ value: field_values['cpassword'] }); 
    $('input#email').inputfocus({ value: field_values['email'] });
    $('input#nom').inputfocus({ value: field_values['nom'] });
    $('input#prenom').inputfocus({ value: field_values['prenom'] });
    $('input#adresse').inputfocus({ value: field_values['adresse'] });
    $('input#age').inputfocus({ value: field_values['age'] });
    $('input#creditcard').inputfocus({ value: field_values['creditcard'] });




    //reset progress bar
    $('#progress').css('width','0');
    $('#progress_text').html('0% Complete');

    //first_step
    //$('form').submit(function(){ return false; });
    $('#submit_first').click(function(){
        //remove classes
        $('#first_step input').removeClass('error').removeClass('valid');

        //ckeck if inputs aren't empty
        var fields = $('#first_step input[type=text], #first_step input[type=password]');
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        var error = 0;
        fields.each(function(){
            var value = $(this).val();
			if( value.length<1 || value==field_values[$(this).attr('id')] || ( $(this).attr('id')=='email' && !emailPattern.test(value) ) ) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                error++;
            }
            else if( value.length<4 || value==field_values[$(this).attr('id')] ) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                
                error++;
            } else {
                $(this).addClass('valid');
            }
        });        
        
        if(!error) {
            if( $('#password').val() != $('#cpassword').val() ) {
                    $('#first_step input[type=password]').each(function(){
                        $(this).removeClass('valid').addClass('error');
                        $(this).effect("shake", { times:3 }, 50);
                    });
                    
                    return false;
            } else {   
                //update progress bar
                $('#progress_text').html('33% Complete');
                $('#progress').css('width','113px');
                
                //slide steps
                $('#first_step').slideUp();
                $('#second_step').slideDown();     
            }               
        } else return false;
    });


    $('#submit_second').click(function(){
        //remove classes
        $('#second_step input').removeClass('error').removeClass('valid');
        var fields = $('#second_step input[type=text]');
        var error = 0;
        fields.each(function(){
            var value = $(this).val();
            var numbertest = /^[0-9]{1,2}$/;
            if( value.length<1 || value==field_values[$(this).attr('id')] || ( $(this).attr('id')=='age' && !numbertest.test(value) ) ) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                error++;
            }
            else if( value.length<1 || value==field_values[$(this).attr('id')] ) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                
                error++;
            } else {
                $(this).addClass('valid');
            }
        });

        if(!error) {
                //update progress bar
                $('#progress_text').html('66% Complete');
                $('#progress').css('width','226px');
                
                //slide steps
                $('#second_step').slideUp();
                $('#third_step').slideDown();     
        } else return false;

    });


    $('#submit_third').click(function(){
    	
        $('#third_step input').removeClass('error').removeClass('valid');
        var fields = $('#third_step input[type=text]');
        var error = 0;
        fields.each(function(){
            var value = $(this).val();
            var numbertest = /^[0-9]{14,16}$/;
            if( value.length<1 || value==field_values[$(this).attr('id')] || ( $(this).attr('id')=='creditcard' && !numbertest.test(value) ) ) {
                $(this).addClass('error');
                $(this).effect("shake", { times:3 }, 50);
                error++;
            } else {
                $(this).addClass('valid');
            }
        });
        if(!error){
        //update progress bar
        $('#progress_text').html('100% Complete');
        $('#progress').css('width','339px');

        //prepare the fourth step
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
        /*var tr = $('#fourth_step tr');
        tr.each(function(){
            //alert( fields[$(this).index()] )
            $(this).children('td:nth-child(2)').html(fields[$(this).index()]);
        });*/
                
        //slide steps
        $('#third_step').slideUp();
        $('#fourth_step').slideDown();     
        }
    });


    $('#submit_fourth').click(function(){
        //send information to server
        return true;
    });

});