package com.example.listadepessoassql.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadepessoassql.ArmazenamentoPreferencias;
import com.example.listadepessoassql.R;

public class FiltrarActivity extends AppCompatActivity {

    private EditText editFiltro;
    private Button buttonAplicarFiltro;

    private ArmazenamentoPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar);

        editFiltro = findViewById(R.id.editFiltro);
        buttonAplicarFiltro = findViewById(R.id.buttonAplicarFiltro);

        preferencias = new ArmazenamentoPreferencias(this);

        buttonAplicarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.salvarFiltro(editFiltro.getText().toString());
                Toast.makeText(FiltrarActivity.this, "Filtro aplicado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        editFiltro.setText(preferencias.recuperarFiltro());
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fechar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}