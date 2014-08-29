QUnit.module("Test DataTable");
QUnit.test("table is a datatable", function(assert) {
	assert.ok($("#table").hasClass("dataTable"), "Passed!");
});

QUnit.module("Test delete message");
QUnit.test("initially invisible", function(assert) {
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":hidden"), "Passed!");
});
QUnit.test("shown upon click", function(assert) {
	$("#delete-link-1").click();
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.equal(message_element.text(), "Ok to delete 1?", "delete item 1");
	dialog_section.dialog("close");

	$("#delete-link-2").click();
	assert.equal(message_element.text(), "Ok to delete 2?", "delete item 2");
	dialog_section.dialog("close");

});

function create_row(value) {
	var row = $("<tr></tr>");
	var cell1 = $("<td></td>");
	var span = $("<span></span>");
	span.addClass("list-item-id");
	span.addClass("list-item-label");
	span.text(value);
	cell1.append(span);
	row.append(cell1);

	var cell2 = $("<td></td>");
	var link = $("<a></a>");
	link.text("Delete");
	link.attr("id", "delete-link-" + value);
	link.addClass("link-remove")
	cell2.append(link);
	row.append(cell2);
	return row;
}

QUnit.module("Test delete prompt", {

	setup : function(assert) {
		$('#table').DataTable({
			"bDestroy" : true
		});
		var table = $('#table');
		var tbody = $("tbody", table);
		tbody.empty();
		tbody.append(create_row("1"));
		tbody.append(create_row("2"));
	},
	teardown : function(assert) {

	}
});
QUnit.test("click no will close the dialog", function(assert) {
	$("#delete-link-1").click();
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":visible"), "Message is visible");
	$("#dialog-confirm-delete-button-no").trigger("click");
	assert.ok(message_element.is(":hidden"), "Message is not visible");
});

QUnit.module("F");
QUnit.test("click yes will delete the row", function(assert) {
	var response = {
		"status" : "success"
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);

	assert.equal($("#table tbody tr").length, 2, "2 rows before delete");

	$("#delete-link-1").trigger("click");
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":visible"), "Message is visible");
	$("#dialog-confirm-delete-button-yes").trigger("click");
	assert.ok(message_element.is(":hidden"), "Message is not visible");
	assert.equal($("#table tbody tr").length, 1, "1 row after delete");

});

QUnit.module("X");
QUnit.test("fixture on data table", function(assert) {
	assert.ok(!$.fn.dataTable.isDataTable("#fixed-table"),
			"initially not data table");
	$("#fixed-table").DataTable({});
	assert.ok($.fn.dataTable.isDataTable("#fixed-table"), "data table");
});
QUnit.test("fixture on data table 2", function(assert) {
	assert.ok(!$.fn.dataTable.isDataTable("#fixed-table"),
			"initially not data table");
	$("#fixed-table").DataTable({});
	
	assert.ok($.fn.dataTable.isDataTable("#fixed-table"), "data table");
});