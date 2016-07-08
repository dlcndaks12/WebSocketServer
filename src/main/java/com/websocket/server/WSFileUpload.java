package com.websocket.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint("/fileUpload")
public class WSFileUpload {

	final static String PATH = "D:\\WSFiles\\";
	Session session = null;
	String fileName = null;
	File file = null;
	BufferedOutputStream bos = null;
	
	long progress = 0;
	
	@OnMessage
	public void onMessage(String msg, Session session) {
		if(!msg.equals("end")) {
			fileName = msg;
			System.out.println("fileName : " + PATH + fileName);
			file = new File(PATH + fileName);
			
			if(!file.exists()) {
				new File(PATH).mkdirs();
			}
			
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				bos.flush();
				bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
	
	@OnMessage
	public int processUpload(ByteBuffer msg, boolean last, Session session) {
		
		while(msg.hasRemaining()) {
			try {
				bos.write(msg.get());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return msg.position();
	}
	
	@OnOpen
    public void open(Session session) {
		this.session = session;
        System.out.println("WebSocket File Server Open......");
    }

    @OnError
    public void error(Session session, Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void closedConnection(Session session) {
        System.out.println("연결종료........");
    }
}
