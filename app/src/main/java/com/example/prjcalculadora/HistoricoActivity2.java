package com.example.prjcalculadora;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HistoricoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historico2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtListarHistComp = (TextView) findViewById(R.id.txtListarHistComp);

        try {
            FileInputStream fis = openFileInput("historico.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder historico = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                historico.append(linha).append("\n");
            }

            br.close();
            isr.close();
            fis.close();

            txtListarHistComp.setText(historico.toString());

        } catch (Exception e) {
            txtListarHistComp.setText("Nenhum histórico salvo.");
            e.printStackTrace();
        }
    }
    public void voltar(android.view.View view) {
        finish();
    }
}