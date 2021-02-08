package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void botonJugar(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_niveles.class);
        startActivity(intent);
    }

    public void botonConfig(View view) {
        Intent intent = new Intent(getApplicationContext(), Configuracion.class);
        startActivity(intent);
    }

    public void botonSalir(View view) {
        finish();
    }
}