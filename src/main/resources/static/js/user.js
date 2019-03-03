//Surveyer Table Js for UserList in SI 
$(document)
		.ready(
				function() {
					var table = $('#surveyerTable')
							.DataTable(
									{
										"ajax" : "/surveyers",
										"destroy" : true,
										"deferRender" : true,
										"processing" : true,
										'serverSide' : true,
										"responsive" : true,
										"paging" : true,
										"pageLength" : 10,
										"ordering" : true,

										"order" : [ [ 1, 'asc' ] ],
										columns : [
												{
													"data" : null,
													"orderable" : false,
													"sClass" : "buts",

												},
												{
													data : 'name'
												},
												{
													data : 'phone'
												},
												{
													data : 'username'
												},
												{
													data : 'totalField'
												},
												{
													data : 'latestUpdate'
												},
												{
													"data" : 'id',
													"sClass" : "drop",
													searchable : false,
													"orderable" : false,
													"createdCell" : function(
															td, cellData,
															rowData, row, col) {
														var name = rowData.name;
														var btngroup = $(
																'<div>',
																{
																	'class' : 'btn-group'
																});
														var dropdowntoggle = $('<a>');
														var icon = $(
																"<i>",
																{
																	"class" : "icon-edit_icon",
																	"onclick" : "editUserForm('"
																			+ rowData.name
																			+ "','"
																			+ rowData.username
																			+ "','"
																			+ rowData.id
																			+ "','"
																			+ rowData.phone
																			+ "')"
																});
														var edit = $(btngroup)
																.append(
																		$(
																				dropdowntoggle)
																				.append(
																						icon));
														$(td).html(edit);
													}

												} ]
									})

					table.on('draw.dt', function() {
						table.column(0).nodes().each(function(cell, i) {
							cell.innerHTML = table.page.info().start + (i + 1);
						});
					}).draw();

					$('#searchName').on('keyup', function() {
						table.columns(1).search(this.value).draw();
					});
				});

// function editUserForm(iduser, name, email, phone){
function editUserForm(name, email, id, phone) {
	$('#editForm input[name=name]').val(name);
	$('#editForm input[name=username]').val(email);
	$('#editForm input[name=id]').val(id);
	$('#editForm input[name=phone]').val(phone);
	$('#editForm').show();
}

// $(document).on("click", '.icon-edit_icon', function() {
// console.log("cek");
// $('#editForm').show();
// });

$(document).on("click", '#cancel, .overlay', function() {
	console.log("cek");
	$('#editForm').hide();
});

$(document).ready(function() {
	$('.form-create input').keyup(function() {

		var empty = false;
		$('.form-create input').each(function() {
			if ($(this).val().length == 0) {
				empty = true;
			}
		});

		if (!empty) {
			$('.actions[type="submit"]').removeAttr('disabled');
		} else {
			$('.actions[type="submit"]').attr('disabled', 'disabled');
		}
	});
});