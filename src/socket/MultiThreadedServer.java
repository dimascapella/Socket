/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

/**
 *
 * @author USER
 */
import java.net.*;
import java.io.*;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;
    protected Socket clientSocket = null;
    protected int countClient;
    protected String msg = "";

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped()) {
            try {
                clientSocket = this.serverSocket.accept();
                if (clientSocket != null) {
                    msg += "Client Connected \n";
                    countClient++;
                }
            } catch (IOException e) {
                if (isStopped()) {
                    msg += "Server Stopped";
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
        }
        msg += "Server Stopped";
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}
