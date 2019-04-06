package com.example.remoteman;

public class ConnectionManager {

    // Provide angle in range 0 to 360, powerPercentage in range 0 to 100
    public void sendAngleAndPower(int angle, int powerPercentage) {
        Byte[] signal = NavigationService.getSignalFromAngleAndPower(angle, powerPercentage);
        new ConnectionService().execute(signal);
    }
}
