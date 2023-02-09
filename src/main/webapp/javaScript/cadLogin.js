function validarLogin(){
    validarUser()
    validarPassword()
    let validar = $('.to-check').hasClass('is-invalid');
    if (validar) {
        return false;
    } else {
        return true;
    }
}

$('.to-check').on('input', function(){
    $(this).removeClass("is-invalid");    
});

function validarUser(){
    let user = $('#user');
    if (user.val().length < 1) 
        user.addClass("is-invalid");
}

function validarPassword(){
    var password = $('#password');
    if (password.val().length < 1) 
        password.addClass("is-invalid"); 
}
