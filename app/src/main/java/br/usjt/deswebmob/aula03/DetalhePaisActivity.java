package br.usjt.deswebmob.aula03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhePaisActivity extends Activity {

    /**
     * Autor: Lucas dos Reis Pereira
     * RA: 816110978
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pais);
        TextView txtPais = (TextView)findViewById(R.id.txtPais);
        Intent intent = getIntent();
        Pais pais = (Pais)intent.getSerializableExtra(ListaPaisesActivity.PAIS);
        txtPais.setText(pais.toString());
    }
}