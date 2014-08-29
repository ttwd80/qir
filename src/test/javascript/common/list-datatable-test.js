QUnit.test("table is a datatable", function(assert) {
	assert.ok($("#table").hasClass("dataTable"), "Passed!");
});
QUnit.test("table contains 6 rows", function(assert) {
	assert.equal(6, $("#table tbody tr").length, "Passed!");
});
QUnit.test("table contains 5 rows after delete", function(assert) {
	var tr = $("#delete-link-4").parents("tr");
	$("#table").DataTable().row(tr).remove().draw();
	assert.equal(5, $("#table tbody tr").length, "Passed!");
});
QUnit.test("table contains 6 rows", function(assert) {
	assert.equal(6, $("#table tbody tr").length, "Passed!");
});
QUnit.test("table contains 5 rows after click delete", function(assert) {
	$("#delete-link-4").click();
	alert("clicked");
	assert.equal(5, $("#table tbody tr").length, "Passed!");
});
