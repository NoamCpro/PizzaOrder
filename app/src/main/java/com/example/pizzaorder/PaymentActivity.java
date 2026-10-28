package com.example.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvPizza = findViewById(R.id.tvPizza);
        TextView tvPrice = findViewById(R.id.tvPrice);

        Intent intent = getIntent();

        String size = intent.getStringExtra("pizzaSize");
        String crust = intent.getStringExtra("pizzaCrust");
        int price = intent.getIntExtra("pizzaPrice", 0);

        tvPizza.setText("פיצה " + size + "עם בצק " + crust);
        tvPrice.setText("מחיר: " + price);

        RadioGroup rgPaymentM = findViewById(R.id.paymentM);

        Button pay = findViewById(R.id.payment);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int paymentMId = rgPaymentM.getCheckedRadioButtonId();

                String paymentM;
                if (paymentMId == R.id.cash) {
                    paymentM = "cash";
                } else {
                   paymentM = "card";
                }

                Toast.makeText(PaymentActivity.this, "payed using " + paymentM + ". Thank You!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}