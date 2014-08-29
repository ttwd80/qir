$(function() {
	var dataTable = $("#table").DataTable({});

	$("a.link-remove").click(function() {
		var tr = $(this).parents(tr);
		dataTable.row(tr).remove().draw();
	});
});