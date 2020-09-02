package com.example.appintenttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_name = findViewById(R.id.txt_name);
    }

    public void sendName(View view){

        String name = txt_name.getText().toString();
        String error = "";

        if(name.isEmpty()){
            error = "Verifica el Nombre";
        }

        if(error.isEmpty()){
            Intent intent = new Intent(this, MainActivityTwo.class);
            intent.putExtra("name", name);
            startActivity(intent);
        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }

    }

}