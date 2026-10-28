package com.example.pizzaorder;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgSize;
    RadioGroup rgCrust;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgSize = findViewById(R.id.rgSize);
        rgCrust = findViewById(R.id.rgCrust);

        Button btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = rgSize.getCheckedRadioButtonId();
                int checkedIdC = rgCrust.getCheckedRadioButtonId();

                String size;
                if (checkedId == R.id.rbSmall) {
                    size = "קטנה";
                    price += 12;
                } else if (checkedId == R.id.rbMedium) {
                    size = "בינונית";
                    price += 17;
                } else {
                    size = "משפחתית";
                    price += 20;
                }

                if (checkedIdC == -1) {
                    Toast.makeText(MainActivity.this, "בחרו סוג בצק לפני ההזמנה", Toast.LENGTH_SHORT).show();
                    return;
                }

                String crust;
                if(checkedIdC == R.id.rbThick) {
                    crust = "עבה";
                    price += 6;
                }
                else
                    crust = "דק";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("אישור הזמנה");
                builder.setMessage("להזמין פיצה " + size + " עם בצק " + crust + "?");

                builder.setPositiveButton("אשר", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "ההזמנה התקבלה!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                        intent.putExtra("size", size);
                        intent.putExtra("crust", crust);
                        intent.putExtra("price", price);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("ביטול", null);

                builder.show();
            }
        });
    }
}