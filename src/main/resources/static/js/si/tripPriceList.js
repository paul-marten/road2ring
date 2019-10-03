$(document).ready( function () {
   var tripId = window.location.pathname.split('/')
//	 console.log(tripId[2])
	 var table = $('#rsp-tbl').DataTable({
	 "dom": '<"row"<"col-sm-2"<"newRecord">><"col-sm-10"<"toolbar">>><"row"<"col-sm-12"tr>><"row"<"col-sm-6"i><"col-sm-6"p>>',
			"sAjaxSource": "/api/trip/"+tripId[2]+"/price-list/data",
			"sAjaxDataProp": "",
			"aoColumns": [
			    {"mData": "id",
            "width": "5%",
            "searchable": false,
            "orderable": false,
            "createdCell": function(td, cellData, rowData, row, col) {
                $(td).attr('data-th', 'No.');
            }
          },
          { "mData": "startTrip"},
			    { "mData": "finishTrip"},
			    { "mData": "price"},
			    { "mData": "status"},
			    { "mData": "personPaid",
              "width": "12%",
              "orderable": false,
              "createdCell": function(td, cellData, rowData, row, col) {
                  $(td).attr('data-th', "Person Paid");
              }
          },
			    { "mData": "discount"},
			    { "mData": "id",
            "width": "10%",
            "searchable": false,
            "orderable": false,
            "createdCell": function(td, cellData, rowData, row, col) {
               var image = "icon-icon_action";
               var text = "Action ";

               if (rowData.isPublished == "PUBLISHED" || rowData.isPublished == "EDITED") {
                   image = "icon-icon_published";
                   text = "Published";
               }
               var buttonIcon = $('<span>', {'class':'buttonIcon'}).append($('<i>', {'class':image}));
               var buttonText = $('<span>', {'class':'buttonText'}).append($('<a>', {'text': text}));
               var button = $('<button>', {'class':'btn btn-default dropdown-toggle', 'type':'button', 'data-toggle':'dropdown'}).append(buttonIcon).append(buttonText).append($('<span>', {'class':'caret'}));

               var hiddenId = $('<input>', {
                   'type': 'hidden',
                   'value': cellData
               });

               var list = $('<ul>', {'class':'dropdown-menu'}).append(drawListAction(rowData, cellData));
               var element = $('<div>', {'class':'dropdown'}).append(button).append(list).add(hiddenId);

               $(td).html(element);
               $(td).attr('data-th', 'Action');

           }
         },
			],
			"columnDefs": [ {
        "searchable": false,
        "orderable": true,
        "targets": 0
      } ],
      "columnDefs": [{
          "targets": 3,
          "render": function(data, type, row) {
              return data != null && data != '' ? data : '' ;
          }
      },{
          "targets":1,
          "render": function(data, type, row) {
            var parseTs = moment(data, 'x');
            return data != null && data != '' ? moment(parseTs).format('DD/MM/YYYY') : '-'
          }
      },{
          "targets":2,
          "render": function(data, type, row) {
            var parseTs = moment(data, 'x');
            return data != null && data != '' ? moment(parseTs).format('DD/MM/YYYY') : '-'
          }
      }],
      "order": [[ 0, "asc" ]],

	 });

	 table.columns(4).search("waiting").draw();

	 table.on( 'draw.dt', function () {
    var PageInfo = $('#rsp-tbl').DataTable().page.info();
    table.column(0, { page: 'current' }).nodes().each( function (cell, i) {
       cell.innerHTML = i + 1 + PageInfo.start;
    } );
  });

  var btnNew = '<a href="'+window.location.pathname+'/add" class="btn btn-default btn-sm"><span class="fa fa-plus-circle fa-lg"></span> Add New Record</a>';
  var filterStatus = 'Filter by : <select class="form-control tripStatus"><option value="">--- All Status ---</option><option value="WAITING">Waiting</option><option value="EXPIRED">Expired</option><option value="COMPLETE">Complete</option><option value="CANCEL">Cancel</option></select>';
  var filterCaptain = '&nbsp;<input class="form-control findCaptain" size="24" type="text" name="findCaptain" placeholder="Find Specific Captain">';
  var filterTitle = '&nbsp;<input class="form-control findTitle" size="47" type="text" name="findTitle" placeholder="Find Specific Title">';
  var filter = filterStatus;
  $("div.newRecord").html(btnNew);
  $("div.toolbar").html(filter);

  $('.tripStatus').on('change', function() {
          table.columns(4).search(this.value).draw();
      });


  $('.findTitle').on('keyup', function(event) {
      if ($(this).val().length > 2)
          table.columns(1).search(this.value).draw();
      else
          table.columns(1).search('').draw();
  });
  $('.findCaptain').on('keyup', function(event) {
      if ($(this).val().length > 2)
          table.columns(3).search(this.value).draw();
      else
          table.columns(3).search('').draw();
  });
});

 function drawListAction(rowData, cellData) {
  //Draw button Edit
  var iconEdit = $('<span>').append($('<i>', {'class':'icon-icon_edit'}));
  var textEdit =$('<span>').append( $('<a>', {
                              'text':'Edit ',
                              'href': window.location.pathname+'/edit?id=' + cellData,
                          }));
  var btnEdit = $('<li>').append(iconEdit).append(textEdit);

  var iconAddMotor = $('<span>').append($('<i>', {'class':'icon-icon_motor'}));
  var textAddMotor =$('<span>').append( $('<a>', {
                              'text':'List Motor ',
                              'href': window.location.pathname+'/' + cellData + '/bike',
                          }));
  var btnAddMotor = $('<li>').append(iconAddMotor).append(textAddMotor);

  var list = btnEdit.add(btnAddMotor);

  if (rowData.isPublished == "PUBLISHED" || rowData.isPublished == "EDITED") {
                  btnScheduled = $('<li>', {'style':'display: none;'});
                  list = btnEdit.add(btnUnpublish);
  }
  if (rowData.isPublished == "SCHEDULED") {
      list = btnEdit.add(btnPublish).add(btnUnpublish).add(btnScheduled);
  }
  return list;
}