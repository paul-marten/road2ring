$(document).ready( function () {
	 var tripId = window.location.pathname.split('/')
	 console.log(tripId[2])
	 var table = $('#rsp-tbl').DataTable({
	 "dom": '<"row"<"col-sm-2"<"newRecord">><"col-sm-10"<"toolbar">>><"row"<"col-sm-12"tr>><"row"<"col-sm-6"i><"col-sm-6"p>>',
			"sAjaxSource": "/api/trip/"+tripId[2]+"/itinerary/data",
//			"sAjaxSource": "/api/trip/test",
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
          { "mData": "groupTitleEvent"},
			    { "mData": "countEvent"},
			    { "mData": "status"},
//			    { "mData": "groupTitle"},
			    { "mData": "groupEvent",
            "width": "10%",
            "searchable": false,
            "orderable": false,
            "createdCell": function(td, cellData, rowData, row, col) {
               var image = "icon-icon_action";
               var text = "Action ";

               if (rowData.status == "PUBLISHED") {
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
      },{
      "targets": 3,
      "visible": false
      } ],
      "order": [[ 3, "asc" ]],

	 });
	 table.on( 'draw.dt', function () {
    var PageInfo = $('#rsp-tbl').DataTable().page.info();
    table.column(0, { page: 'current' }).nodes().each( function (cell, i) {
       cell.innerHTML = i + 1 + PageInfo.start;
    } );
  });

  var btnNew = '<a href="'+window.location.pathname+'/add" class="btn btn-default btn-sm"><span class="fa fa-plus-circle fa-lg"></span> Add New Record</a>';
//  var filterStatus = 'Filter by : <select class="form-control isPublished"><option value="">--- All Status ---</option><option value="0">Unpublish Content</option><option value="1">Published Content</option><option value="3">Scheduled</option></select>';
//  var filterCaptain = '&nbsp;<input class="form-control findCaptain" size="24" type="text" name="findCaptain" placeholder="Find Specific Captain">';
  var filterTitle = '&nbsp;<input class="form-control findTitle" size="47" type="text" name="findTitle" placeholder="Find Specific Title">';
  var filter = filterTitle;
  $("div.newRecord").html(btnNew);
  $("div.toolbar").html(filter);

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

  $(document).on('click', '#publishContent', function() {
      /* Act on the event */
      var data = table.row( $(this).parents('tr') ).data()
      console.log(data)
//        if(data.tripPrices == 0){
//          alert("Trip Price atau Itinerary Kosong, harap input data terlebih dahulu sebelum publish")
//        }else{
        var hide_id = $(this).parent().parent().parent().find('input').val();
        $('#publishConfirm').popup('show');
        $('#publishConfirm input[name=api_id]').val(data.id);
//        }
      return false;
  });

  $(document).on("click", '#publishConfirm .do-it', function() {
      var dataId = $('#publishConfirm input[name=api_id]').val()
      $.post( "/api/trip/itinerary/change-status/"+ dataId + "/PUBLISHED").done(function(data) {
        window.location.reload()
      })
  });

  $(document).on('click', '#publishConfirm .cancel', function(event) {
      /* Act on the event */
      $('#publishConfirm').popup('hide');
  });

  $(document).on('click', '#unpublishContent', function() {
      /* Act on the event */
      var data = table.row( $(this).parents('tr') ).data()
      $('#takeoutConfirm').popup('show');
      $('#takeoutConfirm input[name=api_id]').val(data.id);
      return false;
  });

  $(document).on("click", '#takeoutConfirm .do-it', function() {
      var dataId = $('#takeoutConfirm input[name=api_id]').val()
      $.post( "/api/trip/itinerary/change-status/"+ dataId + "/UNPUBLISHED").done(function(data) {
        window.location.reload()
      })
  });

  $(document).on('click', '#takeoutConfirm .cancel', function(event) {
      /* Act on the event */
      $('#takeoutConfirm').popup('hide');
  });

});

 function drawListAction(rowData, cellData) {
  //Draw button Edit
  var iconEdit = $('<span>').append($('<i>', {'class':'icon-icon_edit'}));
  var textEdit =$('<span>').append( $('<a>', {
                              'text':'Edit ',
                              'href': window.location.pathname + '/edit?id=' + cellData,
                          }));
  var btnEdit = $('<li>').append(iconEdit).append(textEdit);

//  var iconEdit = $('<span>').append($('<i>', {'class':'icon-icon_edit'}));
  var textFacility =$('<span>').append( $('<a>', {
                                'text':'Facility ',
                                'href': '/trip/'+cellData+'/facility',
                            }));
  var btnFacility= $('<li>').append(textFacility);


//  var iconEdit = $('<span>').append($('<i>', {'class':'icon-icon_edit'}));
  var textIternary =$('<span>').append( $('<a>', {
                                'text':'Iternary ',
                                'href': '/trip/'+cellData+'/itinerary',
                            }));
  var btnIternary= $('<li>').append(textIternary);

  //Draw buttom Publish
  var iconPublish =$('<span>').append($('<i>', {'class':'icon-icon_publish'}));
  var textPublish =$('<span>').append( $('<a>', {
                                  'text':'Publish ',
                                  'href': '',
                              }));
  var btnPublish = $('<li>', {'id':'publishContent'}).append(iconPublish).append(textPublish);

  //Draw buttom Publish
  var iconUnpublish =$('<span>').append($('<i>', {'class':'icon-icon_unpublish'}));
  var textUnpublish =$('<span>').append( $('<a>', {
                                  'text':'Unpublish',
                                  'href': '',
                              }));
  var btnUnpublish = $('<li>', {'id':'unpublishContent'}).append(iconUnpublish).append(textUnpublish);

  //Draw button Scheduled
//  var iconScheduled = $('<span>').append($('<i>', {'class':'icon-icon_schedule_post'}));
//  var textScheduled =$('<span>').append( $('<a>', {
//                                      'text':'Schedule Publish',
//                                      'href': '',
//                                      }));
//  var btnScheduled = $('<li>', {'id':'schedule'}).append(iconScheduled).append(textScheduled);
  var list = btnEdit.add(btnPublish);
//  .add(btnFacility).add(btnIternary).add(btnPublish);

  if (rowData.status == "PUBLISHED") {
                  btnScheduled = $('<li>', {'style':'display: none;'});
                  list = btnEdit.add(btnUnpublish);
  }
  if (rowData.status == "SCHEDULED") {
      list = btnEdit.add(btnPublish).add(btnUnpublish).add(btnScheduled);
  }
  return list;
}