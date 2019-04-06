package com.example.remoteman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.agilie.circularpicker.presenter.CircularPickerContract;
import com.agilie.circularpicker.ui.view.CircularPickerView;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class MainActivity extends AppCompatActivity {

    long powerPercentage = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circular_picker);

        CircularPickerView circularPickerView = (CircularPickerView)findViewById(R.id.circular_picker);
        circularPickerView.setMaxValue(360);
        circularPickerView.setMaxLapCount(1);

        circularPickerView.setValueChangedListener(
                (val) -> {
                    System.out.println("new value: " + val);
                    Byte[] signal = NavigationService.getSignalFromValue(val, powerPercentage);
                    new ConnectionService().execute(signal);
                }
        );

        CrystalSeekbar seekbar = (CrystalSeekbar)findViewById(R.id.rangeSeekbar);
        seekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number val) {
                System.out.println(val);
                TextView powerText = (TextView)findViewById(R.id.powerText);
                powerText.setText(String.valueOf(val));
                powerPercentage = val.intValue();
            }
        });
    }
}

