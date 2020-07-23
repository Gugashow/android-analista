package com.example.analista_nota10.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analista_nota10.R;

public class PerformanceController extends AppCompatActivity {

    TextView porcentagemPerformance, acertosPerformance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        porcentagemPerformance = findViewById(R.id.porcentagemPerformance);
        acertosPerformance = findViewById(R.id.acertosPerformance);
    }
    public void buttonPrincipal (View view){
        Intent Menu = new Intent(this, MenuController.class);
        startActivity(Menu);
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop()", Toast.LENGTH_SHORT).show();
    }
}