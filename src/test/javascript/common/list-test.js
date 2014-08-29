QUnit.module("Test DataTable");
QUnit.test("table is a datatable", function(assert) {
	assert.ok($("#table").hasClass("dataTable"), "Passed!");
});

QUnit.module("Test delete message dialog visibility");
QUnit.test("initially invisible", function(assert) {
	var dialog_section = $("#dialog-confirm-remove");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":hidden"), "Passed!");
});

QUnit.test("shown upon click", function(assert) {
	var dialog_section = $("#dialog-confirm-remove");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":hidden"), "Initially hidden");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	assert.ok(message_element.is(":visible"),
			"Visible when delete link is clicked");
	$("#dialog-confirm-remove-button-no").trigger(jQuery.Event("click"));
	assert.ok(message_element.is(":hidden"), "Hidden when no is clicked");
});

QUnit.module("Test delete message dialog message");
QUnit.test("message row #2", function(assert) {
	$("#delete-link-2").trigger(jQuery.Event("click"));
	var dialog_section = $("#dialog-confirm-remove");
	var message_element = $(".dialog-message", dialog_section);
	assert.equal(message_element.text(), "Ok to delete 2?", "Passed!");
	$("#dialog-confirm-remove-button-no").trigger(jQuery.Event("click"));
});
QUnit.test("message row #6", function(assert) {
	$("#delete-link-6").trigger(jQuery.Event("click"));
	var dialog_section = $("#dialog-confirm-remove");
	var message_element = $(".dialog-message", dialog_section);
	assert.equal(message_element.text(), "Ok to delete 6?", "Passed!");
	$("#dialog-confirm-remove-button-no").trigger(jQuery.Event("click"));
});

QUnit.module("Test delete item");
QUnit.test("will reduces the row count", function(assert) {
	var response = {
		"status" : "success",
		"id" : 1
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);

	assert.equal($("#table tbody tr").length, 6, "6 rows before delete");

	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	assert.equal($("#table tbody tr").length, 5, "5 row after delete");
	jQuery.ajax.restore();
});

QUnit.test("will remove the element", function(assert) {
	var response = {
		"status" : "success",
		"id" : 1
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);

	assert.equal($("#table tbody tr").length, 6, "6 rows before delete");

	assert.equal($("#delete-link-1").length, 1, "item exists");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	assert.equal($("#delete-link-1").length, 0, "item has been deleted");
	jQuery.ajax.restore();
});
