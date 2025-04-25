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
                try {
                    vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                    vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                    vlResultadoAtual = vlValor1 + vlValor2;

                    vwResultado.setText(String.valueOf(vlResultadoAtual));
                    historicoDia.add("Soma: " + vlValor1 + " + " + vlValor2 + " = " + vlResultadoAtual);
                    historicoComp("Soma: " + vlValor1 + " + " + vlValor2 + " = " + vlResultadoAtual);
                } catch (Exception e) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Valor não pode ser nulo")
                            .show();
                }
            }
        });

        Button btnMenos = (Button) findViewById(R.id.btnMenos);

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                    vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                    vlResultadoAtual = vlValor1 - vlValor2;

                    vwResultado.setText(String.valueOf(vlResultadoAtual));
                    historicoDia.add("Subtração: " + vlValor1 + " - " + vlValor2 + " = " + vlResultadoAtual);
                    historicoComp("Subtração: " + vlValor1 + " - " + vlValor2 + " = " + vlResultadoAtual);
                } catch (Exception e) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Valor não pode ser nulo")
                            .show();
                }
            }
        });

        Button btnMultiplica = (Button) findViewById(R.id.btnMultiplica);

        btnMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                vlValor2 = Float.parseFloat(edtValor2.getText().toString());
                vlResultadoAtual = vlValor1 * vlValor2;

                vwResultado.setText(String.valueOf(vlResultadoAtual));
                historicoDia.add("Multiplicação: " + vlValor1 + " * " + vlValor2 + " = " + vlResultadoAtual);
                historicoComp("Multiplicação: " + vlValor1 + " * " + vlValor2 + " = " + vlResultadoAtual);
                } catch (Exception e) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Valor não pode ser nulo")
                            .show();
                }
            }
        });

        Button btnDivide = (Button) findViewById(R.id.btnDivide);

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    vlValor1 = Float.parseFloat(edtValor1.getText().toString());
                    vlValor2 = Float.parseFloat(edtValor2.getText().toString());

                    if (vlValor2 == 0) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("Erro, divisão por zero")
                                .show();
                    } else if (vlValor2 == 1) {
                        vlResultadoAtual = vlValor1 * 1;

                        vwResultado.setText(String.valueOf(vlResultadoAtual));
                        historicoDia.add("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual);
                        historicoComp("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual);
                    } else {
                        vlResultadoAtual = vlValor1 / vlValor2;

                        vwResultado.setText(String.valueOf(vlResultadoAtual));
                        historicoDia.add("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual);
                        historicoComp("Divisão: " + vlValor1 + " / " + vlValor2 + " = " + vlResultadoAtual);
                    }
                } catch (Exception e) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Valor não pode ser nulo")
                            .show();
                }
            }
        });

        Button btnLimpaVl1 = (Button) findViewById(R.id.btnLimpaVl1);

        btnLimpaVl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = 0;
                edtValor1.setText("");
                historicoDia.add("Limpou Valor1");
                historicoComp("Limpou Valor1");
            }
        });

        Button btnLimparVl2 = (Button) findViewById(R.id.btnLimpaVl2);

        btnLimparVl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor2 = 0;
                edtValor2.setText("");
                historicoDia.add("Limpou Valor2");
                historicoComp("Limpou Valor2");
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
                historicoDia.add("Limpou valores e resultado");
                historicoComp("Limpou valores e resultado");
            }
        });

        Button btnResulPVal1 = (Button) findViewById(R.id.btnResulPVal1);

        btnResulPVal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = vlResultadoAtual;
                edtValor1.setText(String.valueOf(vlValor1));
                historicoDia.add("Resul para vl1");
                historicoComp("Resul para vl1");
            }
        });

        Button btnMMais = (Button) findViewById(R.id.btnMMais);

        btnMMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlMemoriaAnt = 0;
                vlMemoriaAnt = vlMemoria;
                vlMemoria += vlResultadoAtual;
                vwMemoria.setText(String.valueOf(vlMemoria));
                historicoDia.add("Mem + resul: " + vlMemoriaAnt + " + " + vlResultadoAtual + "resul = " + vlMemoria);
                historicoComp("Mem + resul: " + vlMemoriaAnt + " + " + vlResultadoAtual + "resul = " + vlMemoria);
            }
        });

        Button btnMMenos = (Button) findViewById(R.id.btnMMenos);

        btnMMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vlMemoriaAnt = 0;
                vlMemoriaAnt = vlMemoria;
                vlMemoria -= vlResultadoAtual;
                vwMemoria.setText(String.valueOf(vlMemoria));
                historicoDia.add("Mem + resul: " + vlMemoriaAnt + " - " + vlResultadoAtual + "resul = " + vlMemoria);
                historicoComp("Mem + resul: " + vlMemoriaAnt + " - " + vlResultadoAtual + "resul = " + vlMemoria);
            }
        });

        Button btnMR = (Button) findViewById(R.id.btnMR);

        btnMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vlValor1 = vlMemoria;
                edtValor1.setText(String.valueOf(vlValor1));

                historicoDia.add("Memoria para valor 1");
                historicoComp("Memoria para valor 1");
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