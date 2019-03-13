$(document).ready( function () {
	 var table = $('#rsp-tbl').DataTable({
			"sAjaxSource": "/api/trip/datatable",
			"sAjaxDataProp": "",
			"aoColumns": [
			    {"mData": "id"},
			    { "mData": "description"},
			    { "mData": "iconCover"},
			    { "mData": "iconPublisher"},
			    { "mData": "duration"},
			    { "mData": "tag"},
			    { "mData": "detailDescription"},
			    { "mData": "roadCaptain"},
			],
			"columnDefs": [ {
        "searchable": false,
        "orderable": true,
        "targets": 0
      } ],
      "order": [[ 0, "asc" ]],

	 });
	 table.on( 'draw.dt', function () {
    var PageInfo = $('#rsp-tbl').DataTable().page.info();
    table.column(0, { page: 'current' }).nodes().each( function (cell, i) {
       cell.innerHTML = i + 1 + PageInfo.start;
    } );
   } );
});