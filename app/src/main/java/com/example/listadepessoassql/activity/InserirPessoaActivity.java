package com.example.listadepessoassql.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadepessoassql.ArmazenamentoBancoDeDados;
import com.example.listadepessoassql.R;
import com.example.listadepessoassql.model.Pessoa;

public class InserirPessoaActivity extends AppCompatActivity {

    private EditText editNomePessoa, editAnoNascimentoPessoa;
    private Button buttonCadastrarPessoa;

    private ArmazenamentoBancoDeDados bancoDeDados;

    private int idPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_pessoa);

        editNomePessoa = findViewById(R.id.editNomePessoa);
        editAnoNascimentoPessoa = findViewById(R.id.editAnoNascimentoPessoa);
        buttonCadastrarPessoa = findViewById(R.id.buttonCadastrarPessoa);

        bancoDeDados = new ArmazenamentoBancoDeDados(getApplicationContext());
    }

    @Override
    protected void onStart() {
        try {
            Bundle bundleDados = getIntent().getExtras();
            idPessoa = bundleDados.getInt("id-pessoa");
        } catch (Exception e) {
            idPessoa = -1;
            Log.i("INSETO", e.getMessage());
        }
        if (idPessoa == -1) {
            buttonCadastrarPessoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean verificacao = verificarEdits(editNomePessoa.getText().toString(),
                            editAnoNascimentoPessoa.getText().toString());
                    if (verificacao) {
                        bancoDeDados.cadastrarPessoa(
                                editNomePessoa.getText().toString(),
                                Integer.parseInt(editAnoNascimentoPessoa.getText().toString())
                        );
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
        } else {
            buttonCadastrarPessoa.setText("Salvar");
            Pessoa pessoa = bancoDeDados.recuperarPessoaPorId(idPessoa);
            editNomePessoa.setText(pessoa.getNome());
            editAnoNascimentoPessoa.setText(String.valueOf(pessoa.getAno()));
            buttonCadastrarPessoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean verificacao = verificarEdits(editNomePessoa.getText().toString(),
                            editAnoNascimentoPessoa.getText().toString());
                    if (verificacao) {
                        bancoDeDados.alterarPessoa(
                                idPessoa,
                                editNomePessoa.getText().toString(),
                                Integer.parseInt(editAnoNascimentoPessoa.getText().toString())
                        );
                        Toast.makeText(
                                InserirPessoaActivity.this,
                                "Pessoa alterada com sucesso!",
                                Toast.LENGTH_SHORT
                        ).show();
                        finish();
                    }
                }
            });
        }
        super.onStart();
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

    public boolean verificarEdits(String nome, String  ano) {
        if (nome.equals("")) {
            Toast.makeText(
                    InserirPessoaActivity.this,
                    "Campo nome VAZIO!",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        } else if (ano.equals("")) {
            Toast.makeText(
                    InserirPessoaActivity.this,
                    "Campo ano de nascimento VAZIO!",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        } else if (ano.length() < 4) {
            Toast.makeText(
                    InserirPessoaActivity.this,
                    "O ano tem que ter 4 dígitos!",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        } else {
            return true;
        }
    }
}