/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loop.rutexserver.controller;

import com.google.gson.Gson;
import com.loop.rutexserver.model.Task;
import com.loop.rutexserver.view.MainView;
import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 *
 * @author Zainul Saifulah Aziz
 */
public class WebSocketService extends WebSocketServer {

    private static final String HOST = "10.0.0.34";
    private static final int PORT = 8887;
    private static final Object LOCK = new Object();
    private static volatile WebSocketService sInstance;

    public static WebSocketService getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new WebSocketService();
                }
            }
        }
        return sInstance;
    }



    public WebSocketService() {
        super(new InetSocketAddress(WebSocketService.HOST, WebSocketService.PORT));
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {
        System.out.println("new connection to " + ws.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket ws, int i, String string, boolean bln) {
        System.out.println("closed " + ws.getRemoteSocketAddress() + " with exit code " + i + " additional info: " + string);
    }

    @Override
    public void onMessage(WebSocket ws, String string) {
       String task =  TaskController.getInstance().getMessage(parseJson(string));
        broadcast(task);
    }

    @Override
    public void onError(WebSocket ws, Exception excptn) {
        System.err.println("an error occured on connection " + ws.getRemoteSocketAddress() + ":" + excptn);
    }

    @Override
    public void onStart() {
        SqliteHelper.getInstance().createTable();
        new MainView().setVisible(true);
    }
    
    private Task parseJson(String message){
        return new Gson().fromJson(message, Task.class);
    }

}
