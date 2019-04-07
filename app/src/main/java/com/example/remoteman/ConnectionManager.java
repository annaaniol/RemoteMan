package com.example.remoteman;

import java.net.DatagramSocket;

public class ConnectionManager {

    DatagramSocket ds;
    DatagramSocket dsVisualise;

    ConnectionManager() {
        try{
            ds = new DatagramSocket();
            dsVisualise = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Provide angle in range 0 to 360, powerPercentage in range 0 to 100
    public void sendAngleAndPower(int angle, int powerPercentage) {
        Byte[] signal = NavigationService.getSignalFromAngleAndPower(angle, powerPercentage);
        new ConnectionService(ds,dsVisualise).execute(signal);
    }
}
