QUnit.module("Form count");
QUnit.test("total 2 forms", function(assert) {
	assert.equal($("form").length, 2, "Passed!");
});
QUnit.test("total 1 form will be validated", function(assert) {
	assert.equal($("form.bv-form").length, 1, "Passed!");
});

QUnit.module("check validator state");
QUnit.test("initial state for all is NOT_VALIDATED", function(assert) {
	assert.equal($("small[data-bv-result='NOT_VALIDATED']").length, 6,
			"All not validated");
});

QUnit.module("check validation");
QUnit.test("check username", function(assert) {
	var div = $("#username").parent();
	assert.equal($("small[data-bv-validator]", div).length, 1,
			"username has 1 validator");
	assert.equal($("small[data-bv-validator='notEmpty']", div).length, 1,
			"username has 1 notEmpty validator");

});
QUnit.test("check fullname", function(assert) {
	var div = $("#fullname").parent();
	assert.equal($("small[data-bv-validator]", div).length, 1,
			"fullname has 1 validator");
	assert.equal($("small[data-bv-validator='notEmpty']", div).length, 1,
			"fullname has 1 notEmpty validator");

});
QUnit.test("check password", function(assert) {
	var div = $("#password").parent();
	assert.equal($("small[data-bv-validator]", div).length, 2,
			"password has 2 validators");
	assert.equal($("small[data-bv-validator='notEmpty']", div).length, 1,
			"password has 1 notEmpty validator");
	assert.equal($("small[data-bv-validator='identical']", div).length, 1,
			"password has 1 notEmpty validator");

});
QUnit.test("check confirmPassword", function(assert) {
	var div = $("#confirmPassword").parent();
	assert.equal($("small[data-bv-validator]", div).length, 2,
			"confirmPassword has 2 validators");
	assert.equal($("small[data-bv-validator='notEmpty']", div).length, 1,
			"confirmPassword has 1 notEmpty validator");
	assert.equal($("small[data-bv-validator='identical']", div).length, 1,
			"confirmPassword has 1 notEmpty validator");

});
