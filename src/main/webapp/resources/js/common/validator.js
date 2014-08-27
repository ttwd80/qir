$(function() {
	function create_validator_parameters(element) {
		var obj = {};
		$("div", element).each(function() {
			var e = $(this);
			var key = e.attr("class");
			var val = e.text();
			obj[key] = val;
		});
		return obj;
	}
	function create_validators(input) {
		var obj = {

		};
		$("div.validators > div", input.parent()).each(function() {
			var e = $(this);
			var c = e.attr("class");
			obj[c] = create_validator_parameters(e);
		});
		return obj;
	}
	function create_fields_object(form) {
		var fields = {};
		$("input.bv-input", form).each(function(index, input) {
			var validators = {
				"validators" : create_validators($(this))
			};
			fields[$(this).attr("name")] = validators;
		});
		return fields;

	}
	$("form.bv-form").each(function(index, element) {
		var obj = {
			"feedbackIcons" : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			"fields" : create_fields_object($(this))
		};

		$(this).bootstrapValidator(obj);
	});
});
