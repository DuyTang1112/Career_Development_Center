package com.springlab.chat;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/chatserver")
public class ChatServer
{	
	private static ConcurrentHashMap<Session, String> chatterHashMap = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session session) throws IOException
	{
		//SETID:id
		System.out.println(session.getId() + " has opened a connection!");
		session.getBasicRemote().sendText("SETID:"+session.getId());
		//tell new user of current chatters
		try{
			if(!chatterHashMap.isEmpty()) {
				Basic remote = session.getBasicRemote();
				for (String entry :  chatterHashMap.values()){
					//send a chatter
					//sending the remote might be calling it too fast
					remote.sendText("ADDCHATTER:" + entry);//id:name:role
					System.out.println("Sending to "+ session.getId() + " about current chatters");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//updating other of new chatter happens onMessage:ADDCHATTER
	}

	@OnMessage
	public void onMessage(String message, Session session){
		//Protocol is following
		//MESSAGE:id:name:text
		//ADDCHATTER:id:name:role
		//REMOVECHATTER:id
		String[] decodeMessage = message.split(":");
		switch(decodeMessage[0]){
		case "ADDCHATTER":
			//ADDCHATTER:id:name:role
			for( Session otherSession: chatterHashMap.keySet()){
				try{
					//pass message
					System.out.println(session.getId() + "'s info has been sent to "+ otherSession.getId());
					otherSession.getBasicRemote().sendText(message);
					System.out.println("Sending to "+ otherSession.getId() + " from server "+ this);
				}catch(Exception ioe){
					System.err.println("Removing Dead Session");
					try{
						chatterHashMap.remove(otherSession);
					}catch(Exception e) { 
						System.err.println("Exception on Remove Dead Session"+ e);
					}
				}
			}
			chatterHashMap.put(session, decodeMessage[1] + ":" + decodeMessage[2] + ":" + decodeMessage[3]);
			break;
		case "REMOVECHATTER": // client might never send this to server
			//REMOVECHATTER:id
			for( Session otherSession: chatterHashMap.keySet()){
				try{
					//pass message
					otherSession.getBasicRemote().sendText(message);

				}catch(Exception ioe){
					System.err.println("Removing Dead Session");
					try{
						chatterHashMap.remove(otherSession);
					}catch(Exception e) { 
						System.err.println("Exception on Remove Dead Session"+ e);
					}
				}
			}
			chatterHashMap.remove(session);
			System.out.println("Removed user "+ session.getId() + " from server "+ this);

			break;
		case "MESSAGE":
			for( Session otherSession: chatterHashMap.keySet()){
				if(otherSession != session) {
					try{
						//pass message
						System.out.println("Passing "+ message + " from " + session.getId());
						otherSession.getBasicRemote().sendText(message);
					}catch(Exception ioe){
						System.err.println("Removing Dead Session");
						try{
							chatterHashMap.remove(otherSession);
						}catch(Exception e) { 
							System.err.println("Exception on Remove Dead Session"+ e);
						}
					}
				}
				
			}
			break;
		}
	}
	
	@OnClose
	public void onClose(Session session)
	{
		chatterHashMap.remove(session);
		for( Session otherSession: chatterHashMap.keySet()){
			try{
				//pass message
				otherSession.getBasicRemote().sendText("REMOVECHATTER:"+session.getId());
				System.out.println("Sending to "+ otherSession.getId() + " from server "+ this);
			}catch(Exception ioe){
				System.err.println("Removing Dead Session");
				try{
					chatterHashMap.remove(otherSession);
				}catch(Exception e) { 
					System.err.println("Exception on Remove Dead Session"+ e);
				}
			}
		}
		System.out.println(session.getId() + " has left the room.");
		
	}

//	@OnError
//	public void onBigError(Session session, Throwable thr)
//	{
//		System.err.println("Session "+ session.getId() + " closed suddenly");
//	}
}
