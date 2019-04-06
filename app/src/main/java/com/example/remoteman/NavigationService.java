package com.example.remoteman;

import org.jetbrains.annotations.Contract;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class NavigationService {

    static int motors = 5;
    static int degrees = 360;

    static int fullSignal = 124;

    static Map<String, Integer> vibrationMapping = new HashMap<String, Integer>() {{
        put("front", 4);
        put("right_front", 3);
        put("right_back", 2);
        put("left_back", 0);
        put("left_front", 1);
    }};

    public static int countSignalPower(double strengthPercentage) {
        int finalPower = (int)strengthPercentage * fullSignal / 100;
        return finalPower;
    }


    public static Byte[] getSignalFromValue(int val, double powerPercentage) {
        Byte[] signal = new Byte[motors+3];

        for(int i=0; i<signal.length; i++) signal[i] = 0;
        int id = 0;
        byte signalStrengthByte = (byte)countSignalPower(powerPercentage);
        if(val > 360-36 || val <= 36) { // front
            id = vibrationMapping.get("front");
            signal[id] = signalStrengthByte;
        } else if(val <= 90){          // right front
            id = vibrationMapping.get("right_front");
            signal[id] = signalStrengthByte;
        } else if(val <= 150){          // right back
            id = vibrationMapping.get("right_back");
            signal[id] = signalStrengthByte;
        } else if(val > 210 && val <= 270) {   // left back
            id = vibrationMapping.get("left_back");
            signal[id] = signalStrengthByte;
        } else if(val > 270 && val <= 344) {     // left front
            id = vibrationMapping.get("left_front");
            signal[id] = signalStrengthByte;
        }


        return signal;
    }
}
