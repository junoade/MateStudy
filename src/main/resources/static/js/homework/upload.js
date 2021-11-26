$('input[type="file"]').change(function(e){
    const fileName = e.target.files[0].name;
    $('.custom-file-label').html(fileName);
});
