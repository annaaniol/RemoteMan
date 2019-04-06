package com.example.remoteman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.agilie.circularpicker.ui.view.CircularPickerView;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

public class MainActivity extends AppCompatActivity {

    int powerPercentage = 100;
    ConnectionManager connectionManager = new ConnectionManager();

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
                    connectionManager.sendAngleAndPower(val, powerPercentage);
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

