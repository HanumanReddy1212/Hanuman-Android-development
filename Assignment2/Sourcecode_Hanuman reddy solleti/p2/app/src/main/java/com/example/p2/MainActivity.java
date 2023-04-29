package com.example.p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerList;
    public Button proceed_click_to_action;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar_home);
//        setSupportActionBar(toolbar);

        spinnerList = findViewById(R.id.gestures_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gesture_list, R.layout.spinner_override);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerList.setAdapter(adapter);

        proceed_click_to_action=(Button)findViewById(R.id.apphome_proceed);
        proceed_click_to_action.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.apphome_proceed:
                proceedToExpertGesture();
                break;
        }

    }

    public void proceedToExpertGesture(){

        spinnerList = findViewById(R.id.gestures_spinner);

        Intent intent = new Intent(this, Gestures.class);
        intent.putExtra("user_chosen_gesture",spinnerList.getSelectedItem().toString());
        startActivity(intent);

    }
}