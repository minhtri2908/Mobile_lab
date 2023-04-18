package com.example.exercise4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        editText = findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_DPAD_CENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    String message = editText.getText().toString();
                    builder.setMessage(message);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return true;
                }
                return false;
            }
        });
    }
}