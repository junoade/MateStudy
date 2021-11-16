(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    var validationGroup = document.getElementsByClassName('validate-group');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                for (var i = 0; i < validationGroup.length; i++) {
                    validationGroup[i].classList.add('was-validated')
                }
            }, false)
        })
})()