
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
