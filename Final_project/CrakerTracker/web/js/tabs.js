$(function(){
    $('li a').click(function () {
        // remove existing active class inside li elements
        $('li').removeClass('active');
        // add class to current clicked element
        $(this).closest('li').addClass('active');
    });
});
