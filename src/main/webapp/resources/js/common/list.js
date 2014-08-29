/**
 * 1. create the data table on document ready
 * 
 * 2. show delete prompt when [a.link-remove] is clicked
 */
$(function() {
	init_data_table();
	init_dialog_confirm_remove();
	init_form_remove_action();
	init_handle_table_remove_row_dialog();

	function init_data_table() {
		$("#table").DataTable({});
	}

	function init_dialog_confirm_remove() {
		var options = {
			"resizable" : false,
			"autoOpen" : false,
			"modal" : true
		};
		$("#dialog-confirm-remove").dialog(options);
	}

	function init_form_remove_action() {
		$("#form-remove").attr("action", $("#link-action-remove").attr("href"));
	}

	function init_handle_table_remove_row_dialog() {
		$("body").on("click", "#table > tbody a.link-remove",
				handle_table_remove_row);

		function handle_table_remove_row(event) {
			event.preventDefault();
			table_remove_row($(event.currentTarget));
		}

		function table_remove_row(link) {
			var tr = link.parents("tr");
			var label = $(".list-item-label", tr).text();
			var id = $(".list-item-id", tr).text();
			create_remove_message(label);
			$("#form-remove-id").val(id);
			show_remove_row_dialog(tr, label);
		}

		function create_remove_message(label) {
			var dialog = $("#dialog-confirm-remove");
			var text = $(".dialog-message-template", dialog).text();
			var data = {
				"id" : label
			};
			var template = Hogan.compile(text);
			var rendered = template.render(data);
			$(".dialog-message", dialog).text(rendered);
		}

		function show_remove_row_dialog(tr, label) {
			var element = $("#dialog-confirm-remove");
			var button_no = {
				"id" : "dialog-confirm-remove-button-no",
				"text" : $("#dialog-confirm-remove-label-no").text(),
				"click" : dialog_box_confirm_remove_close
			};
			var button_yes = {
				"id" : "dialog-confirm-remove-button-yes",
				"text" : $("#dialog-confirm-remove-label-yes").text(),
				"click" : dialog_box_confirm_remove_execute
			};
			var buttons = [ button_no, button_yes ];
			element.dialog("option", "buttons", buttons);
			element.dialog("open");
		}

		function dialog_box_confirm_remove_close() {
			var element = $("#dialog-confirm-remove");
			element.dialog("close");
		}

		function dialog_box_confirm_remove_execute() {
			dialog_box_confirm_remove_close();
			execute_remove();
		}

		function execute_remove() {
			var options = {
				"dataType" : "json",
				"error" : show_error_message,
				"success" : execute_remove_success
			};
			var form = $("#form-remove");
			options["url"] = form.attr("action");
			form.ajaxSubmit(options);
		}

		function show_error_message() {
			// TODO error message
		}

		function execute_remove_success(object, statusText, xhr, $form) {
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
			var selector = "#table > tbody span.list-item-id:contains('" + id
					+ "')";
			var span = $(selector).filter(function() {
				return $(this).text() == id;
			});
			var tr = span.parents("tr");
			return tr;
		}
	}

});
