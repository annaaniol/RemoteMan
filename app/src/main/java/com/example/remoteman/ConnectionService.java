package com.example.remoteman;

import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import android.os.AsyncTask;


public class ConnectionService extends AsyncTask<Byte, Integer, Boolean> {

    String visualiseIP = "192.168.100.8";
    int visualisePort = 31336;

    String nodeIP = "192.168.100.67";
    int nodePort = 31337;

    public DatagramSocket ds;
    public DatagramSocket dsVisualise;

    ConnectionService(DatagramSocket ds, DatagramSocket dsVisualise) {
        try{
            this.ds = ds;
            this.dsVisualise = dsVisualise;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
        protected Boolean doInBackground(Byte... params) {
            byte[] buf = ArrayUtils.toPrimitive(params);
            try {
                InetAddress ip = InetAddress.getByName(nodeIP);
                DatagramPacket dp = new DatagramPacket(buf, buf.length, ip, nodePort);
                ds.send(dp);

                InetAddress ipVisualise = InetAddress.getByName(visualiseIP);
                DatagramPacket dpVisualise = new DatagramPacket(buf, buf.length, ipVisualise, visualisePort);
                dsVisualise.send(dpVisualise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}
