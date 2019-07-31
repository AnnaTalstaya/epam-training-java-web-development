$(function(){
    $.validator.setDefaults({
        highlight: function(element){
            $(element)
                .closest('.form-group')
                .addClass('has-error')
        },
        unhighlight: function(element){
            $(element)
                .closest('.form-group')
                .removeClass('has-error')
        }
    });

    $.validate({
        rules:
            {
                password: "required",
                dateOfBirth: "required",
                weight: {
                    required:true,
                    number:true
                },
                height:  {
                    required:true,
                    number:true
                },
                email: {
                    required: true,
                    email: true
                }
            },
        messages:{
            email: {
                required: true,
                email: true
            }
        },
        password: {
            required: " Please enter password"
        },
        dateOfBirth: {
            required: " Please enter dateOfBirth"
        },
        email: {
            required: ' Please enter email',
            email: ' Please enter valid email'
        },
        weight: {
            required: " Please enter your weight",
            number: " Only numbers allowed"
        },
        height: {
            required: " Please enter your height",
            number: " Only numbers allowed"
        }
    });

});
