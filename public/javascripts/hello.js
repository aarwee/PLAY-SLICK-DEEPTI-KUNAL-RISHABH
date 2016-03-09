if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

$(document).ready(function(){


    $("#addaward").click(function(){
       var data = {"id":parseInt($("#id").val()),"userId":$("#userId").val(),"name":$("#name").val(),"year":$("#year"),"description":$("#description").val()};
               $.ajax({
                           type : 'POST',
                           url : "add",
                           dataType : "text/plain",
                           data : data
                       });
                $("#myModal").modal('hide');
});
});

$('#awardForm').on('submit',function(e){
    e.preventDefault();
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(this).attr('action'),
        data     : $(this).serialize(),
        success  : function(data) {
            alert(data);
        }
    });

});

