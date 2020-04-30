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
	        
	        stompClient.subscribe('/topic/mob/add', function (message) {
	            addNoteSync(JSON.parse(message.body));
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
	
	function sendAddRequest(note, isFast, isImport) {
		stompClient.send(
				"/rpg/mob/add", {},
				JSON.stringify(
		    			{	'note': note, 
		    				'isFast': isFast,
		    				'isImport' : isImport
		    			}));
	}

	function sendRequest(message) {

		
	    stompClient.send(
	    		"/rpg/mob/damage", {}, 
	    		message);

	}
	
	function addNoteSync(note){
		
		
		
		var notes;
		if(note.isFast)
			notes = document.getElementById("fast-note-list");
		else
			notes = document.getElementById("note-list");
		
		if(!note.isImport){
			
		var node = document.createElement("LI");
	    var span = document.createElement("SPAN");
	  
	    span.className += "glyphicon glyphicon-play-circle play-icon play-icon-played";
	  
	    span.setAttribute("onclick", "seekTo(" + cues[currentCue].startTime + "," + currentCue +")");
	    //node.setAttribute("id", "note-" + i);
	  
	    var textnode = document.createTextNode(" " + note.note); 
	  
	    node.appendChild(span);
	  
	    node.appendChild(textnode);                           
	  
	    notes.appendChild(node);
		}
		else{
			notes.innerHTML = note.note;
		}
	}

	function showMessage(message) {
		console.log(">>>>>>>>>>>I am sending message: " + message);
		var option = message.command;
		
		if (option == 'play'){
			player.currentTime(message.timestamp);
			player.play();
			
			var tracks = player.textTracks();
			var track = tracks[0];
			  
			  console.log(">>>>>track<<<<<<<<<<<<<" + track.cues[0].text);
			
		}
		
		if (option == 'pause'){
			player.currentTime(message.timestamp);
			player.pause();
		}
		else{
			player.currentTime(message.timestamp);
			player.play();
			
			currentCue = parseInt(message.command);

	    	delcss();
	    	sexcss("li-" + currentCue);
		}
		
	}

	$(function () {
	    $( "#play-button" ).click(function() { 
	    	sendRequest(JSON.stringify(
	    			{	'command': 'play', 
	    				'timestamp': player.currentTime() 
	    			})); 
	    	console.log (player.currentTime()); 
	    	});
	});
	$(function () {
	    $( "#pause-button" ).click(function() {
	    	sendRequest(JSON.stringify(
	    			{	'command': 'pause', 
	    				'timestamp': player.currentTime() 
	    			})); 
	    	console.log (player.currentTime());  
	    	});
	});
	

	var currentCue = 1, tracks, track, cues; 
	
	function seekTo(time, pos){
		sendRequest(JSON.stringify(
    			{	'command': pos, 
    				'timestamp': time 
    			})); 
    	console.log (player.currentTime()); 
    	
    	
	}
	
	function seks(){
		tracks = player.textTracks();
		track = tracks[0];
		cues = track.cues;

		for(var i = 0; i < cues.length; i++){
			console.log(cues[i].text);
			var subs = document.getElementById("list")
			var node = document.createElement("LI");
		    var span = document.createElement("SPAN");
		  
		    span.className += "glyphicon glyphicon-play-circle play-icon play-icon-played";
		  
		    span.setAttribute("onclick", "seekTo(" + cues[i].startTime + "," + i +")");
		    node.setAttribute("id", "li-" + i);
		  
		    var textnode = document.createTextNode(" " + cues[i].text); 
		  
		    node.appendChild(span);
		  
		    node.appendChild(textnode);                           
		  
		    subs.appendChild(node);   
		}
		
		player.on('timeupdate', function () {
		      
		      var currTime = this.currentTime();

		      console.log(currTime);
		      
		      if (currTime > cues[currentCue].endTime && currTime > cues[currentCue+1].startTime){
		    	  currentCue++;
		    	  delcss();
		    	  sexcss("li-" + currentCue);
		      }
		      
		      if (currTime < cues[currentCue].startTime && currentCue > 0){
		    	  currentCue--;
		    	  delcss();
		    	  sexcss("li-" + currentCue);
		      } 
		    });
		
	}
	
	function sexcss(li_id){
		var style = document.createElement('style');
		style.type = 'text/css';
		style.innerHTML = '#'+ li_id + ' { background-color: #454343; }';
		style.id = "highlight-style";
		document.getElementsByTagName('head')[0].appendChild(style);
		
	}
	
	function delcss(){
		if (document.contains(document.getElementById("highlight-style"))) {
            document.getElementById("highlight-style").remove();
}
	}
	
	function makeNote(event){
		if (event.keyCode == 13){
			var note = document.getElementById("note");
			
			sendAddRequest(note.value, document.getElementById('fast-note').checked, false);
		
		    note.value = "";
		}
	}
	
	function displayCue(){
		document.getElementById("cue-wrapper").style.display = "block";
		document.getElementById("note-wrapper").style.display = "none";
		document.getElementById("fast-note-wrapper").style.display = "none";
		
	}
	function displayNote(){
		document.getElementById("cue-wrapper").style.display = "none";
		document.getElementById("note-wrapper").style.display = "block";
		document.getElementById("fast-note-wrapper").style.display = "none";
		
	}
	function displayFastNote(){
		document.getElementById("cue-wrapper").style.display = "none";
		document.getElementById("note-wrapper").style.display = "none";
		document.getElementById("fast-note-wrapper").style.display = "block";
		
	}
	
	function importNotes(){
		 var copyText = document.getElementById("myInput");

		
		sendAddRequest(copyText.value, document.getElementById('fast-note').checked, true);		
	}
	
	function copyToClipboard(){
		var str;
		
		if(document.getElementById('fast-note').checked)
			str = document.getElementById('fast-note-list').innerHTML;
		else
			str = document.getElementById('note-list').innerHTML;
		
		  const el = document.createElement('textarea');
		  el.value = str;
		  document.body.appendChild(el);
		  el.select();
		  document.execCommand('copy');
		  document.body.removeChild(el);
		};

	connect();