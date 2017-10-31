$(document).ready(function(){
	$(".data").datepicker({
		dateFormat: "dd/mm/yy",
	});
	$(".data").mask("99/99/9999");		
});
	
$body = $("body");
	
$(document).on({
	ajaxStart: function() { $body.addClass("loading");    },
	ajaxStop: function() { $body.removeClass("loading"); }    
});

function exibirMsg(titulo, msg){
	$( "#dialog-message" ).attr("title", titulo);
	$( "#dialog-message p" ).html("<p>" + msg + "</p>");
	$( "#dialog-message" ).dialog({
        modal: true,
        buttons: {
          Ok: function() {
        	  $( this ).dialog( "close" );
          }
        }
	});
}

function exibirMsgRedir(titulo, msg, link){
	$( "#dialog-message" ).attr("title", titulo);
	$( "#dialog-message p" ).html("<p>" + msg + "</p>");
	$( "#dialog-message" ).dialog({
        modal: true,
        buttons: {
          Ok: function() {
        	  $( this ).dialog( "close" );
        	  location.href = link;
          }
        }
	});
}