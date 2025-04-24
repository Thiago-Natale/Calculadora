package com.example.prjcalculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtListaHistorico = (TextView) findViewById(R.id.txtListaHistoricoDia);

        Intent intent = getIntent();
        ArrayList<String> historico = intent.getStringArrayListExtra("historico");

        if (historico != null && !historico.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String linha : historico) {
                builder.append(linha).append("\n");
            }
            txtListaHistorico.setText(builder.toString());
        } else {
            txtListaHistorico.setText("Nenhuma operação realizada.");
        }
    }

    public void voltar(android.view.View view) {
        finish();
    }
}