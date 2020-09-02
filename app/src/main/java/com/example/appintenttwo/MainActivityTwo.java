package com.example.appintenttwo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityTwo extends AppCompatActivity {

    private CheckBox chb_whiped_cream, chb_chocolate;
    private TextView tv_name, tv_quantity;
    private String NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        NAME = getIntent().getStringExtra("name");
        String text = "Pedido de " + NAME;

        tv_name = findViewById(R.id.text_name);
        chb_whiped_cream = findViewById(R.id.chb_whiped_cream);
        chb_chocolate = findViewById(R.id.chb_chocolate);
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_name.setText(text);
    }

    public void lees(View view) {
        int quantity = Integer.parseInt(tv_quantity.getText().toString());

        if(quantity > 1) {
            quantity -= 1;
            String result = String.valueOf(quantity);
            tv_quantity.setText(result);
        }
    }

    public void more(View view) {
        int quantity = Integer.parseInt(tv_quantity.getText().toString());

        if(quantity < 10) {
            quantity += 1;
            String result = String.valueOf(quantity);
            tv_quantity.setText(result);
        }
    }

    public void toBuy(View view) {

        String error = "";
        int CHOCOLATE = 2;
        int WHIPED_CREAM = 1;
        int total = 0;
        int quantity = Integer.parseInt(tv_quantity.getText().toString());

        if(!chb_chocolate.isChecked() && !chb_whiped_cream.isChecked()) {
            error = "Debes Seleccionar al Menos Un Producto";
        } else {
            if(chb_chocolate.isChecked()) {
                total += CHOCOLATE;
            }

            if(chb_whiped_cream.isChecked()) {
                total += WHIPED_CREAM;
            }
        }

        if(error.isEmpty()) {
            total = total * quantity;

            Uri uri = Uri.parse("mailto: contact@coffee.com");

            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra(Intent.EXTRA_TEXT,
                    "Cantidad: " + quantity + "\n" +
                           "Total a Pagar: $" + total);
            intent.putExtra(
                    Intent.EXTRA_SUBJECT, "Pedido de " + NAME
            );

            if(intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }

    public void toCall(View view) {
        Uri uri = Uri.parse("tel: 133");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

}