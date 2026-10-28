package com.example.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();

        TextView tvSummary = findViewById(R.id.tvSummary);

        String size = intent.getStringExtra("size");
        String crust = intent.getStringExtra("crust");

        tvSummary.setText("ההזמנה שלך: פיצה " + size + " בצק: " + crust);

        int price = intent.getIntExtra("price", 0);
        TextView priceTx = findViewById(R.id.priceTx);
        priceTx.setText("price: " + price);

        Button btnPayment = findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SummaryActivity.this, PaymentActivity.class);
                intent.putExtra("pizzaSize", size);
                intent.putExtra("pizzaCrust", crust);
                intent.putExtra("pizzaPrice", price);
                startActivity(intent);
            }
        });
    }
}