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

QUnit.module("Test delete item removal Ok", {
	setup : function() {
		var response = {
			"status" : "success",
			"id" : 1
		};

		sinon.stub(jQuery, "ajax").yieldsTo("success", response);
	},
	teardown : function() {
		jQuery.ajax.restore();
	}
});
QUnit.test("will reduces the row count", function(assert) {

	assert.equal($("#table tbody tr").length, 6, "6 rows before delete");

	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	assert.equal($("#table tbody tr").length, 5, "5 row after delete");
});

QUnit.test("will remove the element", function(assert) {
	assert.equal($("#table tbody tr").length, 6, "6 rows before delete");
	assert.equal($("#delete-link-1").length, 1, "item exists");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	assert.equal($("#delete-link-1").length, 0, "item has been deleted");
});

QUnit.module("dialog error");
QUnit.test("initially empty", function(assert) {
	assert.ok($("#dialog-confirm-remove").is(":hidden"), "Passed!");
});
QUnit.test("shown on non-json", function(assert) {
	var server = this.sandbox.useFakeServer();
	server.respondWith("POST", "/delete-action", [ 200, {
		"Content-Type" : "text/html"
	}, 'please login' ]);
	// dialog-error-remove
	assert.ok($("#dialog-error-remove").is(":hidden"), "initially hidden");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	server.respond();
	assert.ok($("#dialog-error-remove").is(":visible"), "then shown");
	$("#dialog-error-remove").dialog("close");
	assert.ok($("#dialog-error-remove").is(":hidden"), "then hidden again");
});
QUnit.test("shown on valid error", function(assert) {
	var server = this.sandbox.useFakeServer();
	var response = {
		"status" : "error"
	};
	server.respondWith("POST", "/delete-action", [ 200, {
		"Content-Type" : "application/json"
	}, JSON.stringify(response) ]);
	// dialog-error-remove
	assert.ok($("#dialog-error-remove").is(":hidden"), "initially hidden");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	server.respond();
	assert.ok($("#dialog-error-remove").is(":visible"), "then shown");
	$("#dialog-error-remove").dialog("close");
	assert.ok($("#dialog-error-remove").is(":hidden"), "then hidden again");
});
QUnit.test("shown on server return invalid id", function(assert) {
	var server = this.sandbox.useFakeServer();
	var response = {
		"status" : "success",
		"id" : 1000
	};
	server.respondWith("POST", "/delete-action", [ 200, {
		"Content-Type" : "application/json"
	}, JSON.stringify(response) ]);
	// dialog-error-remove
	assert.ok($("#dialog-error-remove").is(":hidden"), "initially hidden");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	server.respond();
	assert.ok($("#dialog-error-remove").is(":visible"), "then shown");
	$("#dialog-error-remove").dialog("close");
	assert.ok($("#dialog-error-remove").is(":hidden"), "then hidden again");
});
QUnit.test("shown then dismissed on button click", function(assert) {
	var server = this.sandbox.useFakeServer();
	server.respondWith("POST", "/delete-action", [ 200, {
		"Content-Type" : "text/html"
	}, 'please login' ]);
	// dialog-error-remove
	assert.ok($("#dialog-confirm-remove").is(":hidden"), "initially hidden");
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	server.respond();
	assert.ok($("#dialog-error-remove").is(":visible"), "then shown");
	$("#dialog-error-remove-button-ok").trigger(jQuery.Event("click"));
	assert.ok($("#dialog-error-remove").is(":hidden"), "then hidden again");
});

QUnit.module("post data");
QUnit.test("delete item 1 will post id=1", function(assert) {
	var response = {
		"status" : "success",
		"id" : 1
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);
	$("#delete-link-1").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	assert.equal(jQuery.ajax.getCall(0).args[0].data, "id=1", "Passed!");
	jQuery.ajax.restore();
});
QUnit.test("delete item 3 will post id=3", function(assert) {
	var response = {
		"status" : "success",
		"id" : 3
	};

	sinon.stub(jQuery, "ajax").yieldsTo("success", response);
	$("#delete-link-3").trigger(jQuery.Event("click"));
	$("#dialog-confirm-remove-button-yes").trigger(jQuery.Event("click"));
	assert.equal(jQuery.ajax.getCall(0).args[0].data, "id=3", "Passed!");
	jQuery.ajax.restore();
});
