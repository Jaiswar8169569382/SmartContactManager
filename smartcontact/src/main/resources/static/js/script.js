
const register=()=>
{
	alert("Hello")
}

const dataToggle=()=>
{
if($('.sidebar').is(":visible"))
{
$('.sidebar').css("display","none");
$('.content').css("margin-left","5px");
}
else
{
$('.sidebar').css("display","block");
$('.content').css("margin-left","20%");
}
}



function deleteContact(cid)
{
swal({
  title: "Are you sure?",
  text: "Do you want to delete your contact!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
    window.location="/contact/delete/"+cid;
  } else {
    swal("Your contact is safe!");
  }
});
	
}

const search=()=>
{
  console.log("searching");

  let query=$("#search-input").val();
  
  if(query=='')
  {
    $('.search-result').hide();
  }
  else{

    

    //let url=`http://3.6.55.142:8080/search/${query}`;
    let url=`http://localhost:7070/search/${query}`;

    fetch(url).then(response=>{

      return response.json();
    })
    .then((data)=>
    {

let text=`<div class='list-group'>`;
data.forEach((element) => {
  text+=`<a href='/contact/singlecontact/+${element.cid}' class='list-group-item list-group-action'>${element.name} </a>`
});
text+=`</div>`
$(".search-result").html(text);
$(".search-result").show();
    });
    console.log(query)
  }
}

const payementStart=()=>
{
  console.log("payemnt start");
  let amount=$('#amount').val();

  console.log(amount);
  if(amount=='' || amount==null)
  {
    swal("Failed", "Amount is Required", "error");
   return;
  }
  else{


  }

  //send to server

  $.ajax({

    url:'/contact/createpayement',
    data:JSON.stringify({amount:amount,info:'order_request'}),
    contentType:'application/json',
    type:'post',
    dataType:'json',
    success:function(response)
    {
      console.log(response);

      if(response.status=="created")
      {
        // open payement form
        let option=
        {
          key:"rzp_test_6yThDVUmEiuIA9",
          amount:response.amount,
          currency:'INR',
          name:'Smart Contact Manager',
          description:'Donation',
          image:'https://www.google.com/imgres?imgurl=https%3A%2F%2Fw7.pngwing.com%2Fpngs%2F338%2F851%2Fpng-transparent-smart-contract-blockchain-ethereum-cryptocurrency-brochure-computer-logo-bitcoin.png&imgrefurl=https%3A%2F%2Fwww.pngwing.com%2Fen%2Ffree-png-zfufp&tbnid=uZ6vkYsnMaitgM&vet=12ahUKEwiAkc-ExpT5AhUwKbcAHQIFCgwQMygGegUIARDfAQ..i&docid=YVzcJpuPIoYFcM&w=920&h=920&q=smart%20contract%20logo&ved=2ahUKEwiAkc-ExpT5AhUwKbcAHQIFCgwQMygGegUIARDfAQ',
         order_id:response.id,

         handler:function(response)
         {
           console.log(response.razorpay_payment_id)
           console.log(response.razorpay_order_id)
           console.log(response.razorpay_signature)
           console.log("payement successfull");

           updatePaymentOnServer(response.razorpay_payment_id,response.razorpay_order_id,'paid');
        
         },
         prefill:
         {
           name:'',
           contact:'',
           emailL:''
         },
        notes:
         {
           address:"Mj Javatech tutorial",
         },

         theme:
         {
          color: "#3399cc"
         }
        };

        let rzp=new Razorpay(option);
        rzp.on('payment.failed', function (response){    
          // Todo - store this information in the server
          console.log(response.error.code);    
          console.log(response.error.description);    
          console.log(response.error.source);    
          console.log(response.error.step);    
          console.log(response.error.reason);    
          console.log(response.error.metadata.order_id);    
          console.log(response.error.metadata.payment_id);
          
      }
      );
        rzp.open();
      }
    },
    error:function(error)
    {
       alert("something went wrong !!");
    }
  })
}

function updatePaymentOnServer(payment_id,order_id,status)
{
  $.ajax({

    url:'/contact/updatepayement',
    data:JSON.stringify({payment_id:payment_id,order_id:order_id,status:status}),
    contentType:'application/json',
    type:'post',
    dataType:'json',
   
    success:function(response)
    {
      console.log(response)
      swal("Good job!", "Payement Successful !!!", "success");
    },
    error:function(error)
    {
      console.log(error);
      swal("Failed", "You Payement Is successfull , but we did not get on server,We will contact as soon as possible", "error");
    }
})

}


