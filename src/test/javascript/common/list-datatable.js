$(function() {
	var dataTable = $("#table").DataTable({});

	$("body").on("click", "#table tbody tr td a.link-remove", function() {
		var tr = $(this).parents("tr");
		tr.remove();
		dataTable.row(tr).remove().draw();
	});
});