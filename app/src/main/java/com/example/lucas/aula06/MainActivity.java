package com.example.lucas.aula06;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.deswebmob.aula05.R;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends Activity {

        Spinner spinnerContinente;
        public static final String CHAVE = "br.usjt.desmob.geodata.txtContinente";
        public static String JSONCONTENT;
        private ArrayList<Pais> paises;
        String continente = "Todos";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            spinnerContinente = (Spinner)findViewById(R.id.spinnerContinente);
            spinnerContinente.setOnItemSelectedListener(new PaisSelecionado());
            new ConsomeWSPaises().execute("https://restcountries.eu/rest/v2/all");
        }

        /**
         * Autor: Lucas dos Reis Pereira
         * RA: 816110978
         */
        public void buscarPaises(View view){
            Intent intent = new Intent(this, ListaPaisesActivity.class);

            intent.putExtra(CHAVE, continente);
            intent.putExtra("JSONRETURN", JSONCONTENT);

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

        private class ConsomeWSPaises extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... url) {
                OkHttpClient client = new OkHttpClient();

                try{

                    Request request = new Request.Builder().url(url[0]).build();
                    Response response = client.newCall(request).execute();
                    String json = response.body().string();

                    //System.out.println(json);
                    return json;

                }catch (IOException e){
                    e.printStackTrace();
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String json) {
                JSONCONTENT = json;
            }

        }
    }
