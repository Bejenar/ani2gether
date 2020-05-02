

function joinRoom(){
	
	var name = document.getElementById("user-name").innerHTML;
	var id;
	
	var settings = {
			  "url": "http://89.42.181.23:8080/api/users",
			  "method": "POST",
			  "timeout": 0,
			  "headers": {
			    "Content-Type": "application/json"
			  },
			  "data": JSON.stringify({"id":999,"name":name}),
			};

			$.ajax(settings).done(function (response) {
				user = response;
				id = response.id;
				console.log(user);
				appendAllUsers(user);
			});

	
	
	
}

function appendAllUsers(response){
	
	console.log("Append all users function - " + response.user.name);
	

	
	if (response.command == 'ban'){
		console.log("BAN -" + response.user.ip);
		$('.user-id-'+response.user.id).remove();
		
		if (user.ip == response.user.ip)
			location.reload();
	}
	
	var u = document.getElementById("user-list");
	u.innerHTML = "";
	
	var settings = {
			  "url": "http://89.42.181.23:8080/api/users",
			  "method": "GET",
			  "timeout": 0,
			};

			$.ajax(settings).done(function (response) {
			  console.log(response);
			  response.forEach(function (item){
				 appendUser(item.id, item.name); 
			  });
			  
			  if (response.command == 'left'){
					if (document.contains(document.getElementById("user-" + response.user.id))) {
				        document.getElementById("user-" + response.user.id).remove();
				     
					}
				}
			});
			
	
}


/*ЗДЕСЬ ДОБАВЛЯЮТСЯ ЭЛЕМЕНТЫ НА СТРАНИЦУ*/
function appendUser(id, name){
	var u = document.getElementById("user-list");
	var li = document.createElement("LI");
	li.id = "user-" + id;
	
	var span = document.createElement("SPAN");
	span.className += "glyphicon glyphicon-remove ";
	  
    span.setAttribute("onclick", "banUser(" + id +")");
    
	var textnode = document.createTextNode(name); 
	  
    li.appendChild(textnode);  
    li.appendChild(span);
    u.appendChild(li);
}

function removeUser(id, reason){
	console.log('removing: ' + id);
	
	 var settings = {
   		  "url": "http://89.42.181.23:8080/api/users/delete/" + id,
   		  "method": "DELETE",
   		  "timeout": 0,
   		};

   		$.ajax(settings).done(function (response) {
   		  console.log("removeUser() - " + response);
   		  sendUserRequest(response, reason);
   		});
	
	
	/*if (document.contains(document.getElementById("user-" + id))) {
        document.getElementById("user-" + id).remove();
     
	}*/
	
	
	
}



window.addEventListener('beforeunload', function(e){
	
	sendUserRequest(user, "leave");
	removeUser(user.id, "left");
	
	return 'Если ты это видишь, скажи админу';
});



	