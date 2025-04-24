package com.example.prjcalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



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

        EditText edtValor1 = (EditText) findViewById(R.id.edtValor1);
        EditText edtValor2 = (EditText) findViewById(R.id.edtValor2);
        Button btnSoma = (Button) findViewById(R.id.btnSoma);
        TextView vwResultado = (TextView) findViewById(R.id.vwResultado);

        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                float vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                float vlResultado = vlValor1 + vlValor2;

                vwResultado.setText(String.valueOf(vlResultado));
            }
        });

        Button btnMenos = (Button) findViewById(R.id.btnMenos);

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                float vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                float vlResultado = vlValor1 - vlValor2;

                vwResultado.setText(String.valueOf(vlResultado));

            }
        });

        Button btnMultiplica = (Button) findViewById(R.id.btnMultiplica);

        btnMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                float vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                float vlResultado = vlValor1 * vlValor2;

                vwResultado.setText(String.valueOf(vlResultado));
            }
        });

        Button btnDivide = (Button) findViewById(R.id.btnDivide);

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    float vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                    float vlValor2 = Float.parseFloat(edtValor2.getText().toString());

                    if (vlValor2 == 0) {
                        vwResultado.setText("Erro:divisão por zero");
                    } else if (vlValor2 == 1) {
                        float vlResultado = vlValor1 * 1;

                        vwResultado.setText(String.valueOf(vlResultado));
                    } else {
                        float vlResultado = vlValor1 / vlValor1;

                        vwResultado.setText(String.valueOf(vlResultado));
                    }
            }
        });

        Button btnLimpaVl1 = (Button) findViewById(R.id.btnLimpaVl1);

        btnLimpaVl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValor1.setText("");
            }
        });

        Button btnLimparVl2 = (Button) findViewById(R.id.btnLimpaVl2);

        btnLimparVl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValor2.setText("");
            }
        });

        Button btnLimpaTudo = (Button) findViewById(R.id.btnLimpaTudo);

        btnLimpaTudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValor1.setText("");
                edtValor2.setText("");
                vwResultado.setText("");
            }
        });

        Button btnResulPVal1 = (Button) findViewById(R.id.btnResulPVal1);

        btnResulPVal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlResulPVal1 = Float.parseFloat(vwResultado.getText().toString());

                edtValor1.setText(String.valueOf(vlResulPVal1));
            }
        });

        TextView vwMemoria = (TextView) findViewById(R.id.vwMemoria);
        float vlMemoria = Float.parseFloat(vwResultado.getText().toString());
        vwMemoria.setText(String.valueOf(vlMemoria));





    }
}