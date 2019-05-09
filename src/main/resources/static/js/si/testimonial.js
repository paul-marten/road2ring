$(document).on("click", "#add-article", function(event) {
    console.log("click")
    clickAddTextArea()
});

function clickAddTextArea(){
  var formGroup = $('.article-list')

  var textAreaContainer = $('<div>',{
      'class': 'article-list-1'
  })

  textAreaContainer.append(drawTextArea)
  formGroup.append("<br />")
  formGroup.append(textAreaContainer)

  ClassicEditor
      .create(document.querySelector( '#textarea-1' ))
      .then(editor => {
        myEditor = editor;
        console.log(myEditor.getData())
      })
      .catch(error => {});
}

function drawTextArea(label){
  var input = $('<textarea>', {
      'class': 'form-control',
      'placeholder': 'Enter Description',
      'id': 'textarea-1',
    }).text('')

  return input
}