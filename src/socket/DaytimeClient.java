/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.*;
import java.net.*;

/**
 *
 * @author USER
 */
public class DaytimeClient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int ServerSocket = 9000;
        Socket socket = null;

        try {
            socket = new Socket(hostname, ServerSocket);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}