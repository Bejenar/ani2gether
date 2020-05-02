function banUser(id){
	console.log("dsd");
	banById(id);
	
}

function banById(id){
	var settings = {
  		  "url": "http://89.42.181.23:8080/api/users/ban/" + id,
  		  "method": "POST",
  		  "timeout": 0,
  		};

  		$.ajax(settings).done(function (response) {
  		  console.log(response);
  		  removeUser(id, "ban");
  		});
  		
}