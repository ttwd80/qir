QUnit.test("table is a datatable", function(assert) {
	assert.ok($("#table").hasClass("dataTable"), "Passed!");
});
QUnit.test("delete message is initially empty", function(assert) {
	var message = $(".dialog-message", $("#dialog-confirm-delete")).text();
	assert.ok(message === "", "Passed!");
});
QUnit.test("delete message element initially invisible", function(assert) {
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":hidden"), "Passed!");
});

QUnit.test("delete message shown upon click", function(assert) {
	$("#delete-link-1").click();
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":visible"), "Passed!");
	dialog_section.dialog("close");
});

QUnit.test("click no on delete prompt, closes prompt", function(assert) {
	$("#delete-link-1").click();
	var dialog_section = $("#dialog-confirm-delete");
	var message_element = $(".dialog-message", dialog_section);
	assert.ok(message_element.is(":visible"), "Message is visible");
	$("#dialog-confirm-delete-button-no").click();
	assert.ok(message_element.is(":hidden"), "Message is not visible");
});
