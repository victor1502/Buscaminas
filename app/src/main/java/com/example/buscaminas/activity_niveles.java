package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_niveles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);
    }

    public void buttonVolver(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void buttonDificil(View view) {
        Intent intent = new Intent(getApplicationContext(), Jugar.class);
        intent.putExtra("casillas",16);
        intent.putExtra("bombas", 40);
        startActivity(intent);
    }

    public void buttonNormal(View view) {
        Intent intent = new Intent(getApplicationContext(), Jugar.class);
        intent.putExtra("casillas",12);
        intent.putExtra("bombas", 22);
        startActivity(intent);
    }

    public void buttonFacil(View view) {
        Intent intent = new Intent(getApplicationContext(), Jugar.class);
        intent.putExtra("casillas",8);
        intent.putExtra("bombas", 10);
        startActivity(intent);
    }
}