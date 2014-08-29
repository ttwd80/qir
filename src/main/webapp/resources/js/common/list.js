/**
 * 1. create the data table on document ready
 * 
 * 2. show delete prompt when [a.link-remove] is clicked
 */
$(function() {
	init_data_table();
	init_dialog_confirm_delete();
	init_form_delete_action();
	init_handle_table_delete_row_dialog();
	init_handle_table_delete_row_dialog_response();

	function init_data_table() {
		$("#table").DataTable({});
	}

	function init_dialog_confirm_delete() {
		$("#dialog-confirm-delete").dialog({
			"resizable" : false,
			"autoOpen" : false,
			"modal" : true
		});
	}

	function init_form_delete_action() {
		$("#form-delete").attr("action", $("#link-action-delete").attr("href"));
	}

	function init_handle_table_delete_row_dialog() {
		$("body").on("click", "#table a.link-remove", handle_table_delete_row);

		function handle_table_delete_row(event) {
			event.preventDefault();
			table_delete_row($(event.currentTarget));
		}

		function table_delete_row(link) {
			var tr = link.parents("tr");
			var label = $(".list-item-label", tr).text();
			var id = $(".list-item-id", tr).text();
			create_delete_message(label);
			$("#form-delete-id").val(id);
			show_delete_row_dialog(tr, label);
		}

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

		function show_delete_row_dialog(tr, label) {
			var element = $("#dialog-confirm-delete");
			var button_no = {
				"id" : "dialog-confirm-delete-button-no",
				"text" : $("#dialog-confirm-delete-label-no").text(),
			};
			var button_yes = {
				"id" : "dialog-confirm-delete-button-yes",
				"text" : $("#dialog-confirm-delete-label-yes").text(),
			};
			var buttons = [ button_no, button_yes ];
			element.dialog("option", "buttons", buttons);
			element.dialog("open");
		}
	}

	function init_handle_table_delete_row_dialog_response() {
		$("body").on("click", "#dialog-confirm-delete-button-no",
				dialog_box_confirm_delete_close);

		$("body").on("click", "#dialog-confirm-delete-button-yes",
				dialog_box_confirm_delete_execute);

		function dialog_box_confirm_delete_close() {
			$("#dialog-confirm-delete").dialog("close");
		}

		function dialog_box_confirm_delete_execute() {
			dialog_box_confirm_delete_close();
			execute_delete();
		}

		function execute_delete() {
			var options = {
				"dataType" : "json",
				"error" : show_error_message,
				"success" : execute_delete_success
			};
			var form = $("#form-delete");
			options["url"] = form.attr("action");
			form.ajaxSubmit(options);
		}

		function show_error_message() {
			// TODO error message
		}

		function execute_delete_success(object, statusText, xhr, $form) {
			if (object.status == "success") {
				remove_table_row(object.id);
			} else {
				show_error_message();
			}
		}

		function remove_table_row(item_id) {
			var tr = find_row_by_item_id(item_id);
			if (tr != null) {
				var dataTable = $("#table").DataTable();
				var row = dataTable.row(tr);
				row.remove().draw();
			} else {
				show_error_message();
			}
		}

		function find_row_by_item_id(id) {
			var selector = "span.list-item-id:contains('" + id + "')";
			var span = $(selector).filter(function() {
				return $(this).text() == id;
			})
			return span.parents("tr");
		}
	}

});
