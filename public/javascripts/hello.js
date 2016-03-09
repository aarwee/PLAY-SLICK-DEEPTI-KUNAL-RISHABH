if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

$(document).ready(function(){

$('#awardForm').on('submit',function(e){
    e.preventDefault();
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(this).attr('action'),
        data     : $(this).serialize(),

                $("#myModal").modal('hide');

    });

});

$('#assignmentForm').on('submit',function(e){
    e.preventDefault();
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(this).attr('action'),
        data     : $(this).serialize(),


                $("#myModal").modal('hide');

    });

});
$('#languageForm').on('submit',function(e){
    e.preventDefault();
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(this).attr('action'),
        data     : $(this).serialize(),

                $("#myModal").modal('hide');

    });

});

});



