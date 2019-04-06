package com.example.remoteman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agilie.circularpicker.presenter.CircularPickerContract;
import com.agilie.circularpicker.ui.view.CircularPickerView;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class MainActivity extends AppCompatActivity {

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
                    Byte[] signal = NavigationService.getSignalFromValue(val, 100);
                    new ConnectionService().execute(signal);
                }
        );
    }
}

