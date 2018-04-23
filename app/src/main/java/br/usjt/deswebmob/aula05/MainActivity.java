package br.usjt.deswebmob.aula05;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText txtContinente;
    public static final String CONTINENTE = "br.usjt.deswebmob.servicedesk.nome";
    Spinner spinnerContinente;
    public static final String CHAVE = "br.usjt.desmob.geodata.txtContinente";
    String continente = "Todos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerContinente = (Spinner)findViewById(R.id.spinnerContinente);
        spinnerContinente.setOnItemSelectedListener(new PaisSelecionado());

    }

    /**
     * Autor: Lucas dos Reis Pereira
     * RA: 816110978
     */
    public void buscarPaises(View view){
        Intent intent = new Intent(this, ListaPaisesActivity.class);

        intent.putExtra(CHAVE, continente);
        startActivity(intent);
    }

    private class PaisSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            continente = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}