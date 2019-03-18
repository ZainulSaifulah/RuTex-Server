/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loop.rutexserver;

import com.loop.rutexserver.controller.WebSocketService;

/**
 *
 * @author shiroe-13
 */
public class RuTexApplication {

    public static void main(String[] args) {
        WebSocketService.getInstance().run();
    }
}
