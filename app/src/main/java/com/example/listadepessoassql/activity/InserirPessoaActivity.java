package com.example.listadepessoassql.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadepessoassql.ArmazenamentoBancoDeDados;
import com.example.listadepessoassql.R;

public class InserirPessoaActivity extends AppCompatActivity {

    private EditText editNomePessoa, editAnoNascimentoPessoa;
    private Button buttonCadastrarPessoa;

    private ArmazenamentoBancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_pessoa);

        editNomePessoa = findViewById(R.id.editNomePessoa);
        editAnoNascimentoPessoa = findViewById(R.id.editAnoNascimentoPessoa);
        buttonCadastrarPessoa = findViewById(R.id.buttonCadastrarPessoa);

        bancoDeDados = new ArmazenamentoBancoDeDados(getApplicationContext());

        buttonCadastrarPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editNomePessoa.getText().toString().equals("")) {
                    Toast.makeText(
                            InserirPessoaActivity.this,
                            "Campo nome VAZIO!",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (editAnoNascimentoPessoa.getText().toString().equals("")) {
                    Toast.makeText(
                            InserirPessoaActivity.this,
                            "Campo ano de nascimento VAZIO!",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (editAnoNascimentoPessoa.getText().length() < 4) {
                    Toast.makeText(
                            InserirPessoaActivity.this,
                            "O ano tem que ter 4 dígitos!",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    String nome = editNomePessoa.getText().toString();
                    int ano = Integer.parseInt(editAnoNascimentoPessoa.getText().toString());
                    bancoDeDados.cadastrarPessoa(nome, ano);
                    Toast.makeText(
                            InserirPessoaActivity.this,
                            "Pessoa cadastrada com sucesso!",
                            Toast.LENGTH_SHORT
                    ).show();
                    editNomePessoa.setText("");
                    editAnoNascimentoPessoa.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fechar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_fechar) finish();
        return super.onOptionsItemSelected(item);
    }
}