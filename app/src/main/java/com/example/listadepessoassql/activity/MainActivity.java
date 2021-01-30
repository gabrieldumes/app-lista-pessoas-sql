package com.example.listadepessoassql.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listadepessoassql.ArmazenamentoBancoDeDados;
import com.example.listadepessoassql.R;
import com.example.listadepessoassql.RecyclerItemClickListener;
import com.example.listadepessoassql.adapter.Adapter;
import com.example.listadepessoassql.model.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabInserirPessoa;

    private List<Pessoa> listaPessoas = new ArrayList<>();
    private ArmazenamentoBancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fabInserirPessoa = findViewById(R.id.fabInserirPessoa);

        bancoDeDados = new ArmazenamentoBancoDeDados(this);

        fabInserirPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InserirPessoaActivity.class));
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        MainActivity.this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Pessoa pessoa = listaPessoas.get(position);
                                Toast.makeText(MainActivity.this, pessoa.getNome(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(MainActivity.this, String.valueOf(listaPessoas.get(position).getAno()), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

        listaPessoas.clear();
        popularLista();

        Adapter adapter = new Adapter(listaPessoas);
        recyclerView.setAdapter(adapter);
    }

    public void popularLista() {
        for (int i = 0; i < bancoDeDados.recuperarQuantidadeDeLinhas(); i++) {
            Pessoa pessoa = new Pessoa(bancoDeDados.recuperarNomePessoa(i), bancoDeDados.recuperarAnoNascimento(i));
            listaPessoas.add(pessoa);
        }
    }
}