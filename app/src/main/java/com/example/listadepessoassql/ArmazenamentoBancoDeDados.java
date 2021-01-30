package com.example.listadepessoassql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadepessoassql.model.Pessoa;

public class ArmazenamentoBancoDeDados {

    private Context context;
    private SQLiteDatabase database;

    public ArmazenamentoBancoDeDados(Context context) {
        this.context = context;
        try {
            database = context.openOrCreateDatabase("app_db", Context.MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS pessoa(nome VARCHAR, ano INT(4))");
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
        }
    }

    public void cadastrarPessoa(String nome, int ano) {
        try {
            database.execSQL("INSERT INTO pessoa(nome, ano) VALUES ('" + nome + "', " + ano + ")");
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
        }
    }

    /*public String recuperarNomePessoa(int position) {
        try {
            Cursor cursor = database.rawQuery("SELECT nome FROM pessoa", null);
            int indiceColunaNome = cursor.getColumnIndex("nome");
            cursor.moveToPosition(position);
            return cursor.getString(indiceColunaNome);
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return "Erro";
        }
    }

    public int recuperarAnoNascimento(int position) {
        try {
            Cursor cursor = database.rawQuery("SELECT ano FROM pessoa", null);
            int indiceColunaAno = cursor.getColumnIndex("ano");
            cursor.moveToPosition(position);
            return cursor.getInt(indiceColunaAno);
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return 1111;
        }
    }*/

    public Pessoa recuperarPessoa(int position, String filtro) {

        try {
            if (filtro == "") {
                Cursor cursor = database.rawQuery("SELECT nome, ano FROM pessoa", null);
                int indiceColunaNome = cursor.getColumnIndex("nome");
                int indiceColunaAno = cursor.getColumnIndex("ano");
                cursor.moveToPosition(position);
                return new Pessoa(cursor.getString(indiceColunaNome), cursor.getInt(indiceColunaAno));
            } else {
                String sql = "SELECT nome, ano FROM pessoa WHERE nome LIKE '%" + filtro + "%' " +
                        "OR ano LIKE '%" + filtro + "%'";
                Cursor cursor = database.rawQuery(sql, null);
                int indiceColunaNome = cursor.getColumnIndex("nome");
                int indiceColunaAno = cursor.getColumnIndex("ano");
                cursor.moveToPosition(position);
                return new Pessoa(cursor.getString(indiceColunaNome), cursor.getInt(indiceColunaAno));
            }
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return new Pessoa("Erro", 1111);
        }
    }

    public int recuperarQuantidadeDeLinhas(String filtro) {
        try {
            if (filtro == "") {
                Cursor cursor = database.rawQuery("SELECT nome FROM pessoa", null);
                return cursor.getCount();
            } else {
                String sql = "SELECT nome, ano FROM pessoa WHERE nome LIKE '%" + filtro + "%' " +
                        "OR ano LIKE '%" + filtro + "%'";
                Cursor cursor = database.rawQuery(sql, null);
                return cursor.getCount();
            }
        } catch (Exception e) {
            Log.i("INSETO ", e.getMessage());
            return 0;
        }
    }
}
