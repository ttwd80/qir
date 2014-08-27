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

	var table_delete_row = function() {
		var tr = $(this).parents("tr");
		var label = $(".item-label", tr).text();
		var id = $(".item-id", tr).text();
		create_delete_message(label);
		$("#form-delete-id").val(id);
		delete_row(tr, label);
		return false;

		function delete_row(tr, label) {
			var element = $("#dialog-confirm-delete");
			element.dialog("option", "buttons", [ {
				text : "No",
				click : function() {
					$(this).dialog("close");
				}
			}, {
				text : "Yes",
				click : function() {
					$(this).dialog("close");
					execute_delete(tr, label);
				}
			} ]);
			element.dialog("open");

		}
		function execute_delete(tr, label) {
			var options = {
				"dataType" : "json",
				"success" : function(object, statusText, xhr, $form) {
					if (object.status == "success") {
						var row = dataTable.row(tr);
						row.remove().draw();
					} else {
						var msg = "Unable to delete [" + label + "].\n"
								+ object.message;
						alert(msg);
					}
				}
			};
			$("#form-delete").ajaxSubmit(options);
		}

	};
	$("#table").on("click", "a.link-remove", table_delete_row);

})