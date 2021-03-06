package com.example.listadepessoassql.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listadepessoassql.ArmazenamentoBancoDeDados;
import com.example.listadepessoassql.ArmazenamentoPreferencias;
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

    private ArmazenamentoPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fabInserirPessoa = findViewById(R.id.fabInserirPessoa);

        bancoDeDados = new ArmazenamentoBancoDeDados(this);
        preferencias = new ArmazenamentoPreferencias(this);

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
                                Intent intent = new Intent(MainActivity.this, InserirPessoaActivity.class);
                                Pessoa pessoa = listaPessoas.get(position);
                                intent.putExtra("id-pessoa", pessoa.getId());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                                dialog.setTitle("Quer mesmo excluir este item?");
                                dialog.setMessage("Não é possível desfazer esta operação");
                                dialog.setCancelable(false);
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Pessoa pessoa = listaPessoas.get(position);
                                        bancoDeDados.removerPessoa(pessoa.getId());
                                        Toast.makeText(MainActivity.this, "Item excluído com sucesso", Toast.LENGTH_SHORT).show();
                                        onStart();
                                    }
                                });
                                dialog.setNegativeButton("Não", null);
                                dialog.create().show();
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
        for (int i = 0; i < bancoDeDados.recuperarQuantidadeDeLinhas(preferencias.recuperarFiltro()); i++) {
            Pessoa pessoa = bancoDeDados.recuperarPessoa(i, preferencias.recuperarFiltro());
            listaPessoas.add(pessoa);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.munu_filtrar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(), FiltrarActivity.class));
        return super.onOptionsItemSelected(item);
    }
}