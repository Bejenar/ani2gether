var stompClient = null;

	function setConnected(connected) {
	    $("#connect").prop("disabled", connected);
	    $("#disconnect").prop("disabled", !connected);
	    if (connected) {
	        $("#conversation").show();
	    }
	    else {
	        $("#conversation").hide();
	    }
	    $("#messages").html("");
	}

	function connect() {
	    var socket = new SockJS('/mob');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        setConnected(true);
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/mob/damage', function (message) {
	            showMessage(JSON.parse(message.body));
	        });
	    });
	    
	}

	function disconnect() {
	    if (stompClient !== null) {
	        stompClient.disconnect();
	    }
	    setConnected(false);
	    console.log("Disconnected");
	}

	function sendRequest(message) {

		
	    stompClient.send(
	    		"/rpg/mob/damage", {}, 
	    		message);

	}

	function showMessage(message) {
		console.log(">>>>>>>>>>>I am sending message: " + message);
		var option = message.command;
		
		if (option == 'play'){
			player.seekTo(message.timestamp, true);
			player.playVideo();
			
		}
		
		if (option == 'pause'){
			player.seekTo(message.timestamp, true);
			player.pauseVideo();
		}
		else{
			player.seekTo(message.timestamp, true);
			player.playVideo();
		}
		
	}

	$(function () {
	    $( "#play-button" ).click(function() { 
	    	sendRequest(JSON.stringify(
	    			{	'command': 'play', 
	    				'timestamp': player.getCurrentTime() 
	    			})); 
	    	console.log (player.getCurrentTime()); 
	    	});
	});
	$(function () {
	    $( "#pause-button" ).click(function() {
	    	sendRequest(JSON.stringify(
	    			{	'command': 'pause', 
	    				'timestamp': player.getCurrentTime() 
	    			})); 
	    	console.log (player.getCurrentTime());  
	    	});
	});
	
	function seekTo(time){
		sendRequest(JSON.stringify(
    			{	'command': 'seek', 
    				'timestamp': time 
    			})); 
    	console.log (player.getCurrentTime());  
	}

	connect();