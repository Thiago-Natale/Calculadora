package com.example.prjcalculadora;

import android.app.AlertDialog;
import android.content.Intent;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    float vlMemoria = 0;
    float vlResultadoAtual = 0;
    float vlValor1 = 0;
    float vlValor2 = 0;
    ArrayList<String> historicoDia = new ArrayList<>();

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
        TextView vwMemoria = (TextView) findViewById(R.id.vwMemoria);

        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                vlResultadoAtual = vlValor1 + vlValor2;

                vwMemoria.setText(String.valueOf(vlMemoria));
                vwResultado.setText(String.valueOf(vlResultadoAtual));
                historicoDia.add("Soma: " + vlValor1 + " + " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                historicoComp("Soma: " + vlValor1 + " + " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                vlMemoria = vlResultadoAtual;

            }
        });

        Button btnMenos = (Button) findViewById(R.id.btnMenos);

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                vlResultadoAtual = vlValor1 - vlValor2;

                vwMemoria.setText(String.valueOf(vlMemoria));
                vwResultado.setText(String.valueOf(vlResultadoAtual));
                historicoDia.add("Subtração: " + vlValor1 + " - " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                historicoComp("Subtração: " + vlValor1 + " - " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                vlMemoria = vlResultadoAtual;
            }
        });

        Button btnMultiplica = (Button) findViewById(R.id.btnMultiplica);

        btnMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                vlResultadoAtual = vlValor1 * vlValor2;

                vwMemoria.setText(String.valueOf(vlMemoria));
                vwResultado.setText(String.valueOf(vlResultadoAtual));
                historicoDia.add("Multiplicação: " + vlValor1 + " * " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                historicoComp("Multiplicação: " + vlValor1 + " * " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                vlMemoria = vlResultadoAtual;
            }
        });

        Button btnDivide = (Button) findViewById(R.id.btnDivide);

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                vlValor2 = Float.parseFloat(edtValor2.getText().toString());

                if (vlValor2 == 0) {
                    vwResultado.setText("Erro:divisão por zero");
                } else if (vlValor2 == 1) {
                    vlResultadoAtual = vlValor1 * 1;

                    vwMemoria.setText(String.valueOf(vlMemoria));
                    vwResultado.setText(String.valueOf(vlResultadoAtual));
                    historicoDia.add("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                    historicoComp("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                    vlMemoria = vlResultadoAtual;
                } else {
                    vlResultadoAtual = vlValor1 / vlValor2;

                    vwMemoria.setText(String.valueOf(vlMemoria));
                    vwResultado.setText(String.valueOf(vlResultadoAtual));
                    historicoDia.add("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                    historicoComp("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual + "  |mem: " + vlMemoria);
                    vlMemoria = vlResultadoAtual;
                }
            }
        });

        Button btnLimpaVl1 = (Button) findViewById(R.id.btnLimpaVl1);

        btnLimpaVl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = 0;
                edtValor1.setText("");
                vwMemoria.setText(String.valueOf(vlMemoria));
                historicoDia.add("Limpou Valor1" + "  |mem: " + vlMemoria);
                historicoComp("Limpou Valor1" + "  |mem: " + vlMemoria);
            }
        });

        Button btnLimparVl2 = (Button) findViewById(R.id.btnLimpaVl2);

        btnLimparVl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor2 = 0;
                edtValor2.setText("");
                vwMemoria.setText(String.valueOf(vlMemoria));
                historicoDia.add("Limpou Valor2" + "  |mem: " + vlMemoria);
                historicoComp("Limpou Valor2" + "  |mem: " + vlMemoria);
            }
        });

        Button btnLimpaTudo = (Button) findViewById(R.id.btnLimpaTudo);

        btnLimpaTudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = 0;
                vlValor2 = 0;
                vlResultadoAtual = 0;
                edtValor1.setText("");
                edtValor2.setText("");
                vwResultado.setText("");
                vwMemoria.setText(String.valueOf(vlMemoria));
                historicoDia.add("Limpou valores e resultado   |mem: " + vlMemoria);
                historicoComp("Limpou valores e resultado   |mem: " + vlMemoria);
            }
        });

        Button btnResulPVal1 = (Button) findViewById(R.id.btnResulPVal1);

        btnResulPVal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vwMemoria.setText(String.valueOf(vlMemoria));
                vlValor1 = vlResultadoAtual;
                edtValor1.setText(String.valueOf(vlValor1));
                historicoDia.add("Resul para vl1   |memoria = " + vlMemoria);
                historicoComp("Resul para vl1   |memoria = " + vlMemoria);
            }
        });

        Button btnMMais = (Button) findViewById(R.id.btnMMais);

        btnMMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlResultAnt = vlResultadoAtual;
                vlMemoria = Float.parseFloat(vwMemoria.getText().toString());
                vlResultadoAtual += vlMemoria;
                vwResultado.setText(String.valueOf(vlResultadoAtual));
                historicoDia.add("Mem + resul: " + vlMemoria + " + " + vlResultAnt + "resul = " + vlResultadoAtual);
                historicoComp("Mem + resul: " + vlMemoria + " + " + vlResultAnt + "resul = " + vlResultadoAtual);
                vlMemoria = vlResultadoAtual;
            }
        });

        Button btnMMenos = (Button) findViewById(R.id.btnMMenos);

        btnMMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlResultAnt = vlResultadoAtual;
                vlMemoria = Float.parseFloat(vwMemoria.getText().toString());
                vlResultadoAtual = vlMemoria - vlResultadoAtual;
                vwResultado.setText(String.valueOf(vlResultadoAtual));
                historicoDia.add("Mem - resul: " + vlMemoria + " - " + vlResultAnt + "resul = " + vlResultadoAtual);
                historicoComp("Mem - resul: " + vlMemoria + " - " + vlResultAnt + "resul = " + vlResultadoAtual);
                vlMemoria = vlResultadoAtual;
            }
        });

        Button btnMR = (Button) findViewById(R.id.btnMR);

        btnMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vwMemoria.setText(String.valueOf(vlMemoria));
                vlValor1 = vlMemoria;
                edtValor1.setText(String.valueOf(vlValor1));

                historicoDia.add("Memoria para valor 1   |memoria = " + vlMemoria);
                historicoComp("Memoria para valor 1   |memoria = " + vlMemoria);
            }
        });

        Button btnMC = (Button) findViewById(R.id.btnMC);
        btnMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlMemoria = 0;
                vwMemoria.setText("");

                historicoDia.add("Limpou a memoria");
                historicoComp("Limpou a memoria");
            }
        });

        Button btnH = (Button) findViewById(R.id.btnH);

        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
                intent.putStringArrayListExtra("historico", historicoDia);
                startActivity(intent);
            }
        });

        Button btnHC = (Button) findViewById(R.id.btnHC);

        btnHC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historicoDia.clear();

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Historico diario limpo")
                        .show();
            }
        });

        Button btnHF = (Button) findViewById(R.id.btnHF);

        btnHF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricoActivity2.class);
                startActivity(intent);
            }
        });

        Button btnHFC = (Button) findViewById(R.id.btnHFC);

        btnHFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput("historico.txt", MODE_PRIVATE);
                    fos.write("".getBytes()); // Escreve nada (limpa o arquivo)
                    fos.close();
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Historico Completo limpo")
                            .show();
                } catch (IOException e) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Erro")
                            .show();
                }
            }
        });
    }

    public void fecharApp(View view) {
        finishAffinity();
    }
    public void historicoComp(String operacao) {
        try {
            FileOutputStream fos = openFileOutput("historico.txt", MODE_APPEND);
            fos.write((operacao + "\n").getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}