class MyUploadAdapter {
    constructor( loader ) {
        // The file loader instance to use during the upload.
        this.loader = loader;
    }

    // Starts the upload process.
    upload() {
        // Update the loader's progress.
        server.onUploadProgress( data => {
            loader.uploadTotal = data.total;
            loader.uploaded = data.uploaded;
        } );

        // Return a promise that will be resolved when the file is uploaded.
        return loader.file
            .then( file => server.upload( file ) );
    }

    // Aborts the upload process.
    abort() {
        // Reject the promise returned from the upload() method.
        server.abortUpload();
    }
}

var index = 1;
var curIndex = 0;
var contDel;

$(document).on("click", "#add-article", function(event) {
    console.log("click")
    clickAddTextArea()
    var child = $(this).parent().children().length
    console.log(child)
    $(this).remove()
    index++;
    curIndex++;
});

function clickAddTextArea(){
  var formGroup = $('.article-list')

  var textAreaContainer = $('<div>',{
      'class': 'article-list-'+index
  })

  textAreaContainer.append(drawTextArea())
  formGroup.append(textAreaContainer)
  formGroup.append(drawButton())
  formGroup.append("<br />")

  ClassicEditor
      .create(document.querySelector( '#textarea-'+index ), {
//        toolbar: [ 'MyCustomUploadAdapterPlugin' ],
        extraPlugins: [ MyCustomUploadAdapterPlugin ],
//        heading: {
//            options: [
//                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
//                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
//                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' }
//            ]
//        }
      })
      .then(editor => {
        myEditor = editor;
      })
      .catch(error => {});
}

function MyCustomUploadAdapterPlugin( editor ) {
    editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
        // Configure the URL to the upload script in your back-end here!
        console.log(loader)

        return new MyUploadAdapter( loader );
    };
}

function drawTextArea(){
  var input = $('<textarea>', {
      'class': 'form-control',
      'placeholder': 'Enter Description',
      'id': 'textarea-'+index,
    }).text('')

  return input
}

function drawButton(){
  var divBtn = $('<div>', {
    'class' : 'button-plus-minus',
    'style' : 'margin-top: 10px;',
  })

  var buttonMin = $('<button>',{
    'id' : 'delete-body',
    'type' : 'button',
    'class' : 'btn btn-default btn-sm m-bottom-sm btn-del-body',
  });

  var iconMinus = $('<i>',{
    'class' : 'fa fa-minus fa-lg',
    'style' : 'color: #D81E36;',
  });

  var buttonPlus = $('<button>',{
    'id' : 'add-article',
    'style' : 'margin-right:3px;',
    'type' : 'button',
    'class' : 'btn btn-default btn-sm m-bottom-sm btn-add-body',
  });

  var iconPlus = $('<i>',{
    'class' : 'fa fa-plus fa-lg',
    'style' : 'color: #3DD091;',
  });

  var iconMin = buttonMin.append(iconMinus);
  buttonMin.append("&nbsp;");
  buttonMin.append('Delete Page');
  var iconPls = buttonPlus.append(iconPlus);
  buttonPlus.append("&nbsp;");
  buttonPlus.append('Add Page');
  divBtn.append(iconPls)
  divBtn.append(iconMin)

  return divBtn;
}

$(document).on("click", '#delete-body', function(event){
  $('#publishConfirm').popup('show');
  //assign the element to global variable for futher use
  contDel = $(this)
});

function goDelete(){
  var container = contDel.parent().parent().parent().parent();
  container.remove();
  addButtonWhenDelete();
  changeAllLabel();
  setToHidden();
}

$(document).on("click", '.delete-it', function() {
  $('#publishConfirm').popup('hide');
  goDelete();
});

$(document).on("click", '.custom-popup .dont-delete', function() {
  $('.custom-popup').popup('hide');
});