$(function() {
	$("form.bv-form").each(function(index, element) {
		$form = $(element);
		var obj = create_form_validation($form);
		$form.bootstrapValidator(obj);
	})

	var create_form_validation = function(form) {
		var obj = {
			"feedbackIcons" : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			"fields" : create_fields_object(form)
		};
		return obj;
	}
	function create_fields_object(form) {
		var fields = {};
		$("input.bv-input", form).each(function(index, element) {
			var validators = {
				"validators" : create_validators($(element))
			};
			fields[$(this).attr("name")] = validators;
		});
		return fields;

	}

	function create_validators(element) {
		var obj = {

		};
		var p = element.parent();
		$("div.validators > div", p).each(function(index, element) {
			var e = $(element);
			var c = e.attr("class");
			obj[c] = create_validator_parameters(e);
		});
		return obj;
	}

	function create_validator_parameters(div) {
		var obj = {};
		$("div", div).each(function(index, element) {
			var e = $(element);
			var key = e.attr("class");
			var val = e.text();
			obj[key] = val;
		});
		return obj;
	}

});
