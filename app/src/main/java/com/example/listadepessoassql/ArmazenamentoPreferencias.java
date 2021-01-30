package com.example.listadepessoassql;

import android.content.Context;
import android.content.SharedPreferences;

public class ArmazenamentoPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String ARQUIVO_PREFERENCIAS = "arquivo.preferencias";
    private final String CHAVE_FILTRO = "chave-filtro";

    public ArmazenamentoPreferencias(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        editor = preferences.edit();
    }

    public void salvarFiltro(String filtro) {
        editor.putString(CHAVE_FILTRO, filtro).commit();
    }

    public String recuperarFiltro() {
        return preferences.getString(CHAVE_FILTRO, "");
    }
}
