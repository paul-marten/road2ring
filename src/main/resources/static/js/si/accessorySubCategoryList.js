$(document).ready( function () {
	 if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
	 $("div.mobile-tbl").addClass("mbl-tbl");
	     			}
	 var table = $('#rsp-tbl').DataTable({
	 "dom": '<"row"<"col-sm-2"<"newRecord">><"col-sm-10"<"toolbar">>><"row"<"col-sm-12"tr>><"row"<"col-sm-6"i><"col-sm-6"p>>',
			"sAjaxSource": "/api/accessory_category/sub-category/data",
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
          { "mData": "title",
            "createdCell": function(td, cellData, rowData, row, col) {
                $(td).attr('data-th', 'Title');
            }},
          { "mData": "status",
            "createdCell": function(td, cellData, rowData, row, col) {
                $(td).attr('data-th', 'Status');
            }
          },
          { "mData": "accessoryCategory.title",
            "createdCell": function(td, cellData, rowData, row, col) {
                $(td).attr('data-th', 'Category');
            }
          },
			    { "mData": "id",
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
        "targets": 2,
        "visible": false
      } ],
      "order": [[ 2, "asc" ]],

	 });
	 table.on( 'draw.dt', function () {
    var PageInfo = $('#rsp-tbl').DataTable().page.info();
    table.column(0, { page: 'current' }).nodes().each( function (cell, i) {
       cell.innerHTML = i + 1 + PageInfo.start;
    } );
  });

  var btnNew = '<a href="'+window.location.pathname+'/add" class="btn btn-default btn-sm"><span class="fa fa-plus-circle fa-lg"></span> Add New Record</a>';
  var filterStatus = 'Filter by : <select class="form-control isIncluded"><option value="">--- All Status ---</option><option value="true">Include</option><option value="false">Not Include</option></select>';
  var filterTitle = '&nbsp;<input class="form-control findTitle" size="27" type="text" name="findTitle" placeholder="Find By Name">';
  var filterByCategory = '&nbsp;<input class="form-control findCategory" size="27" type="text" name="findTitle" placeholder="Find By Category">';


  var filter = filterTitle + filterByCategory;
  $("div.newRecord").html(btnNew);
  $("div.toolbar").html(filter);

  $('.isIncluded').on('change', function(event){
    if ($(this).val() != "")
        table.columns(2).search(this.value).draw();
    else
        table.columns(2).search('').draw();
  })

  $('.findTitle').on('keyup', function(event) {
      if ($(this).val().length > 2)
          table.columns(1).search(this.value).draw();
      else
          table.columns(1).search('').draw();
  });

  $('.findCategory').on('keyup', function(event) {
        if ($(this).val().length > 2)
            table.columns(3).search(this.value).draw();
        else
            table.columns(3).search('').draw();
    });

$('.findCapacity').on('keyup', function(event) {
    table.draw();
//      if ($(this).val().length > 0)
//          table.columns(2).search(this.value).draw();
//      else
//          table.columns(2).search('').draw();
  });

$.fn.dataTable.ext.search.push(
  function(settings, data, dataIndex) {
      var inputPrice = parseInt($('.findPrice').val());
      var inputCapacity = parseInt($('.findCapacity').val());
      if (isNaN(inputPrice) && isNaN(inputCapacity))  {
        return true;
      }
      var price = parseFloat( data[4] ) || 0;
      var capacities = parseFloat( data[2] ) || 0;
      if ((inputPrice >= price) || (inputCapacity >= capacities)) {
          return true;
      }

      return false;
  }
);

  $('.findPrice').on('keyup', function(event) {
      table.draw();
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
      $.post( "/api/accessory_category/sub-category/change-status/"+ dataId + "/PUBLISHED").done(function(data) {
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
      $.post( "/api/accessory_category/sub-category/change-status/"+ dataId + "/UNPUBLISHED").done(function(data) {
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
                              'href': '/accessory-category/sub-category/edit?id=' + cellData,
                          }));
  var btnEdit = $('<li>').append(iconEdit).append(textEdit);

//  var iconEdit = $('<span>').append($('<i>', {'class':'icon-icon_edit'}));
//  var textFacility =$('<span>').append( $('<a>', {
//                                'text':'Facility ',
//                                'href': '/trip/'+cellData+'/facility',
//                            }));
//  var btnFacility= $('<li>').append(textFacility);
//

//  var iconEdit = $('<span>').append($('<i>', {'class':'icon-icon_edit'}));
//  var textIternary =$('<span>').append( $('<a>', {
//                                'text':'Iternary ',
//                                'href': '/trip/'+cellData+'/itinerary',
//                            }));
//  var btnIternary= $('<li>').append(textIternary);

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

  if (rowData.status == "PUBLISHED") {
                  btnScheduled = $('<li>', {'style':'display: none;'});
                  list = btnEdit.add(btnUnpublish);
  }
  if (rowData.status == "SCHEDULED") {
      list = btnEdit.add(btnPublish).add(btnUnpublish).add(btnScheduled);
  }
  return list;
}