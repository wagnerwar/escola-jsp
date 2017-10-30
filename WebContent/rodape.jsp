<div class="modal"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/jquery.maskedinput.js"></script>
<script type="text/javascript">
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

</script>