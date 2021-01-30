package com.example.listadepessoassql.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadepessoassql.R;
import com.example.listadepessoassql.model.Pessoa;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Pessoa> listaPessoas;

    public Adapter(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_pessoas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pessoa pessoa = listaPessoas.get(position);
        holder.textNome.setText(pessoa.getNome());
        holder.textAno.setText(String.valueOf(pessoa.getAno()));
    }

    @Override
    public int getItemCount() {
        return listaPessoas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textNome, textAno;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            textAno = itemView.findViewById(R.id.textAno);
        }
    }
}
