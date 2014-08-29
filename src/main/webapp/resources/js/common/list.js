$(function() {
	var dataTable = $("#table").DataTable({});

	$("#dialog-confirm-delete").dialog({
		"resizable" : false,
		"autoOpen" : false,
		"modal" : true
	});

	$("#form-delete").attr("action", $("#link-action-delete").attr("href"));

	function create_delete_message(label) {
		var dialog = $("#dialog-confirm-delete");
		var text = $(".dialog-message-template", dialog).text();
		var data = {
			"id" : label
		};
		var template = Hogan.compile(text);
		var rendered = template.render(data);
		$(".dialog-message", dialog).text(rendered);
	}

	function table_delete_row(link) {
		var tr = link.parents("tr");
		var label = $(".list-item-label", tr).text();
		var id = $(".list-item-id", tr).text();
		create_delete_message(label);
		$("#form-delete-id").val(id);
		delete_row(tr, label);
		return false;

		function delete_row(tr, label) {
			var element = $("#dialog-confirm-delete");
			var buttons = {};
			element.dialog("option", "buttons", [ {
				"id" : "dialog-confirm-delete-button-no",
				"text" : $("#dialog-confirm-delete-label-no").text(),
				"click" : function() {
					$(this).dialog("close");
				}
			}, {
				"id" : "dialog-confirm-delete-button-yes",
				"text" : $("#dialog-confirm-delete-label-yes").text(),
				"click" : function() {
					$(this).dialog("close");
					execute_delete(tr, label);
				}
			} ]);
			element.dialog("open");

		}
		function execute_delete(tr, label) {
			var options = {
				"dataType" : "json",
				"error" : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				},
				"success" : function(object, statusText, xhr, $form) {
					if (object.status == "success") {
						dataTable.draw();
						var row = dataTable.row(tr);
						row.remove().draw();
					} else {
						var msg = "Unable to delete [" + label + "].\n"
								+ object.message;
						alert(msg);
					}
				}
			};
			var form = $("#form-delete");
			options["url"] = form.attr("action");
			form.ajaxSubmit(options);
		}
	}

	$("body").on("click", "#table a.link-remove", function(e) {
		e.preventDefault();
		table_delete_row($(this));
		return false;
	});

})