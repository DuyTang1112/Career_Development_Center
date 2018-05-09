
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Chatroom</title>
    <style>
        
        div, textarea{
            word-wrap: break-word;
        }
        /* dividing page property */
        .column-chat{
            float:left;
            width:68.5%;
            margin-right:0.5%;
        }
        .column-chatter-list{
            float:right;
            width:30%;
        }

        /* chat container properties */
        #chatbox{
            height: 40em;
        }
        /* actual chatbox */
        #chatlog{
            height:30em;
            overflow-x: hidden;
            overflow-y: scroll;
            background-color: turquoise;
        }
       
        /* message division */
        .chat{
            align-items: flex-start;
            display:flex;
            flex-flow: row wrap;
            margin: 1em auto;
            padding: 5px;
        }
        .chat .message{
            width: 80%;
            margin: 0 auto;
            padding: 15px;
            border-radius: 0 10px 10px 0;
            background-color: rgb(202, 238, 247);
        }
        .chat .chatter{
            width: 10em;
            padding: 15px;
            border-radius: 10px 0 0 10px;
            /* backgroundcolor determined by self or other */
        }
         /* switch if message is self */
        .self .chatter{
            order:1;
            background-color:slateblue;
        }
        .other .chatter{
            background-color:salmon;
        }

        /* submitting message (chat-box footer) */
        .submit-message{
            float: bottom;
            margin-top: 20px;
            display: flex;
            align-items: flex-start;
        }
        .submit-message textarea{
            width: 80%;
            height: 5em;
            border: 2px solid rgb(250, 0, 0);
            border-radius: 3px;
            resize: none;
            padding: 10px;
        }
        .submit-message button{
            margin: auto 10px;
            border-radius: 3px;
            box-shadow: 0 3px 0 #0eb2c1;
        }

        #chatter-list{
            height: 40em;
            overflow-y: scroll;
            
        }
        .chatter{
            padding: 5px;
            margin: 5px 0;
        }
    </style>
    <link rel="icon" href="data:;base64,=">
    <!-- prevent those stupid favico request that keeps triggering error -->
</head>
<body class="container">
    <jsp:include page="Navbar.jsp" />
    
    <h3>Chatroom</h3>
    <div class="column-chat">
        <!-- just container -->
        <div id="chatbox" class="panel panel-primary">
            <div class="panel-heading">CHATBOX</div>

            <div id="chatlog"> <!-- append user messages here -->
                <!-- message template
                <div class="chat other">
                    <div class="chatter"> Name</div>
                    <div class="message"> message</div>
                </div> -->
            </div>
            <!-- send message form -->
            <div class="submit-message">
                <textarea id="self-message" class="textarea"></textarea>
                <button id="send-message" class="btn">Send</button>
            </div>
        </div>
    </div>

    <div class="column-chatter-list">
        <!-- list users accessing this page here -->
        <div id="chatter-list" class="panel panel-info">
            <div class="panel-heading">Other users in this room</div>
            <!-- chatter template
            <div class="chatter" id="testGuy">
                Name (Role)
            </div> -->
        </div>
    </div>
    <div id="serverIP" class="invisible">127.0.0.1</div>
</body>
<script>
    $(document).ready(function(){
    	console.log("${pageContext.request.userPrincipal}");
        openSocket();
    });

    var webSocket;
    var clientId;

    function openSocket(){
        if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
            console.log("WebSocket is already opened.");
            return;
        }
        // var ip = $("#serverIP").text(); 
        // webSocket = new WebSocket("ws://"+ ip + ":8080/careerdevelopment/chatserver");
        var ip = $("#serverIP").text(); 
        webSocket = new WebSocket("ws://"+ ip + ":8080/Spring411/chatserver");
        
        webSocket.onopen=function(event){
			console.log(event.data)
        };

        webSocket.onmessage=function(event){
            var msg=event.data.split(":");
            var id, name, role, message;
            console.log(msg);
            switch(msg[0]){
            	case "SETID": //first case
            		//SETID:id
            		clientId = msg[1];
            		webSocket.send("ADDCHATTER:"+clientId+":${pageContext.request.userPrincipal.name}:${role}");
            		console.log("Sent ADDCHATTER for this chatter")
            		break;
                case "ADDCHATTER":
                    //ADDCHATTER:id:name:role
                    addChatter(msg[1], msg[2], msg[3]);
                    break;
                case "REMOVECHATTER":
                    //REMOVECHATTER:id
                    removeChatter(msg[1]);
                    break;
                case "MESSAGE":
                    //MESSAGE:name:text
                    name = msg[1];
                    message = msg[2];
                    appendMessageOther(name, message);
                    break;
            }
        };
        webSocket.onclose=function(event){
			//server will close
			//this is because onclose might bind clientId when it is null
        };
    }

    //SECTION: client

    //if client is sending message
    function appendMessageSelf(message){
        $(
            '<div class="chat self">'+
                '<div class="chatter"> <mark>SELF</mark> </div>' +
                '<div class="message">' + message + '</div>' +
            '</div>'
        ).appendTo("#chatlog")
        
        //do AJAX here if you want
    }

    //when client receives message from server
    function appendMessageOther(userName, message){
        $(
            '<div class="chat other">'+
                '<div class="chatter">' + userName + '</div>' +
                '<div class="message">' + message + '</div>' +
            '</div>'
        ).appendTo("#chatlog")
    }

    //this is for button
    function appendMessageSelfButton(){
        var name = "SELF";
        var message = $("#self-message").val();
        
        $(
            '<div class="chat self">' + 
                '<div class="chatter">' + name + '</div>' +
                '<div class="message">' + message + '</div>' +
            '</div>'
        ).appendTo("#chatlog");

        $("#self-message").val(''); //clear
        console.log("Wrote following msg:" + message);

        webSocket.send("MESSAGE:${pageContext.request.userPrincipal.name}:" + message);
    }
    
    $("#send-message").click(appendMessageSelfButton);

    var chatterDict = {};//keep-track of current participant's names
    function addChatter(id, name, role){
        //keep id as client-global for easier removing
        //should id be server-global? yes, because to prevent name duplicate problem.
        chatterDict[id] = name;
        console.log(chatterDict);
        //concern: what if name duplicates?
        $(
            '<div class="chatter" id="' + id + '">' +
                ' (' + role + ')\t' + name + 
            '</div>'
        ).appendTo("#chatter-list");
    }

    function removeChatter(id){
        var person = $("#chatter-list #" + id);
        console.log("removed: " + person.text());
        person.remove();
    }

    

</script>
</html>