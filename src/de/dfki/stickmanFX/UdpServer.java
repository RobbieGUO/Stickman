/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.stickmanFX;

import static de.dfki.stickmanSwing.StickmanSwing.mLogger;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robbie
 */
public class UdpServer extends Thread {

    private Boolean UDPrunning = false;
    private DatagramSocket serverSocket;
    private String feedback = null;
    public static boolean mDone = false;
    private StickmanStageController controler;
    
    public UdpServer(StickmanStageController scontroler) throws SocketException {
        controler = scontroler;
    }

    @Override
    public final void start() {
        if (!UDPrunning) {
            try {
                serverSocket = new DatagramSocket(9777);
            } catch (SocketException ex) {
                Logger.getLogger(UdpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            UDPrunning = true;
            super.start();
        }
        
        }

    @Override
    public final void run() {
        // Print some information
        try {
            while (!mDone) {
                System.out.println("Server Started!");
                // Receive a new message
                wairingUpdMessage();
                if (feedback != null) {
                    feedback = feedback.trim();
                    feedback = feedback.replace("\\n", "\n");
                    // Handle the message
                    controler.modifyBullyTextArea(feedback);
                    System.out.println(feedback + ": Changed!");
                }
            }
            
//        mDone = true;
        // Close the socket now
        if ((serverSocket != null) && !serverSocket.isClosed()) {
            System.out.println("Server closed!");
            serverSocket.close();
        }
        } catch (final Exception exc) {

        }
    }

    public void wairingUpdMessage() throws SocketException {

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            serverSocket.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(UdpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        feedback = new String(receivePacket.getData());
        System.out.println("RECEIVED FROM VSM: " + feedback);
    }

    public final void abort() {
        // Set termination flag
        mDone = true;
        // Close the socket now
        if ((serverSocket != null) && !serverSocket.isClosed()) {
            serverSocket.close();
            System.out.println("Server closed!!!!!!!!!!");
        }
    }

}
