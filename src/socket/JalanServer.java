/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.net.SocketException;

/**
 *
 * @author USER
 */
public class JalanServer {

    public static void main(String[] args) throws SocketException {
        MultiThreadedServer server = new MultiThreadedServer(9000);
        new Thread(server).start();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (server.clientSocket != null) {
            server.clientSocket.setSoTimeout(10000);
        } else {
            System.out.println("Stopping Server");
            server.stop();
        }
    }
}
