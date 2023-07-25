/**
 * 
 */
 $('#loaders').hide();
 $('#buttons').show();
  console.log("ready ....");
 $('#myform').on('submit',function(event){
 
 $('#loaders').show();
  $('#buttons').hide();
  
 event.preventDefault();
 let form=new FormData(this);
 
 $.ajax({
    url:"/contact/contactprocess",
    type:'post',
    data:form,
   success: function (data, textStatus, jqXHR) {

                            console.log(data);
                           swal("Good Job","Contact Added Successfuly","success");
                            $('#loaders').hide();
                            $('#buttons').show();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
 
                         $('#loaders').hide();
                         $('#buttons').show();
                          console.log(jqXHR);
                          swal("OOPs","Something went wrong","error");
                        },
                        processData: false,
                        contentType: false
 });
 });