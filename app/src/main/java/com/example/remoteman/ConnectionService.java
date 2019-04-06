package com.example.remoteman;

import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import android.os.AsyncTask;


public class ConnectionService extends AsyncTask<Byte, Integer, Boolean> {

    String testIP = "192.168.100.8";
    int testPort = 8080;
    String nodeIP = "192.168.100.67";
    int nodePort = 31337;

        @Override
        protected Boolean doInBackground(Byte... params) {
            byte[] buf = ArrayUtils.toPrimitive(params);
            try {
                InetAddress ip = InetAddress.getByName(testIP);

                DatagramSocket ds = new DatagramSocket();
                DatagramPacket dp = new DatagramPacket(buf, buf.length, ip, testPort);
                ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}
