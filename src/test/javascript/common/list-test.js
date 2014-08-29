QUnit.module("Test DataTable");
QUnit.test("table is a datatable", function(assert) {
	assert.ok($("#table").hasClass("dataTable"), "Passed!");
});

QUnit.module("Test delete message dialog visibility");
QUnit.test("initially invisible", function(assert) {
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":hidden"), "Passed!");
});

QUnit.test("shown upon click", function(assert) {
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":hidden"), "Initially hidden");
	$("#delete-link-1").click();
	assert.ok(message_element.is(":visible"),
			"Visible when delete link is clicked");
	$("#dialog-confirm-delete-button-no").click();
	assert.ok(message_element.is(":hidden"), "Hidden when no is clicked");
});

QUnit.module("Test delete message dialog message");
QUnit.test("message row #2", function(assert) {
	$("#delete-link-2").click();
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.equal(message_element.text(), "Ok to delete 2?", "Passed!");
	$("#dialog-confirm-delete-button-no").click();
});
QUnit.test("message row #6", function(assert) {
	$("#delete-link-2").click();
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.equal(message_element.text(), "Ok to delete 6?", "Passed!");
	$("#dialog-confirm-delete-button-no").click();
});

QUnit.module("Test delete item");
QUnit.test("will reduces the row count", function(assert) {
	var response = {
		"status" : "success"
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);

	assert.equal($("#table tbody tr").length, 6, "2 rows before delete");

	$("#delete-link-1").trigger("click");
	$("#dialog-confirm-delete-button-yes").trigger("click");
	assert.equal($("#table tbody tr").length, 5, "1 row after delete");
});
QUnit.test("will remove the element", function(assert) {
	var response = {
		"status" : "success"
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);

	assert.equal($("#table tbody tr").length, 6, "2 rows before delete");

	assert.equal($("#delete-link-1").length, 1, "item exists");
	$("#delete-link-1").trigger("click");
	$("#dialog-confirm-delete-button-yes").trigger("click");
	assert.equal($("#delete-link-1").length, 0, "item has been deleted");
});
