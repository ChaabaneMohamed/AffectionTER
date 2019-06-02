
function progress(i) {
	for(var index = 0; index < i+1; index++){
		$('.progress .circle:nth-of-type(' + (index) + ')').addClass('done');
	}
	$('.progress .circle:nth-of-type(' + i + ')').addClass('active');
}

$(function(){
    var requiredCheckboxes = $('.groupes :checkbox[required]');
    requiredCheckboxes.change(function(){
        if(requiredCheckboxes.is(':checked')) {
            requiredCheckboxes.removeAttr('required');
        } else {
            requiredCheckboxes.attr('required', 'required');
        }
    });
});

function masquer_div(id)
{
  if (document.getElementById(id).style.display == 'none') {
       document.getElementById(id).style.display = 'block';
  }
  else {
       document.getElementById(id).style.display = 'none';
  }
}


function validation(max) {
	var form = document.getElementsByTagName('form')[0];
	var choixOp = document.getElementByName('choixOp');
	
	

	form.addEventListener("submit", function(event) {
		// Each time the user tries to send the data, we check
		// if the email field is valid.
		if (choixOp > max) {

			// If the field is not valid, we display a custom
			// error message.
			error.innerHTML = "I expect an e-mail, darling!";
			error.className = "error active";
			// And we prevent the form from being sent by canceling the event
			event.preventDefault();
		}
	}, false);
}