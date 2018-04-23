package br.usjt.deswebmob.aula05;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ListaPaisesActivity extends Activity {

    public static final String PAIS = "br.usjt.desmob.geodata.pais";
    Activity atividade;
    ArrayList<Pais> paises;
    ArrayList<String> nomes;

    /**
     * Autor: Lucas dos Reis Pereira
     * RA: 816110978
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paises);
        atividade = this;
        Intent intent = getIntent();
        String continente = intent.getStringExtra(MainActivity.CHAVE);
        new ConsomeWSPaises();

        nomes = Data.listarNomes(paises);

        ListView listView = (ListView) findViewById(R.id.lista_paises);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nomes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalhePaisActivity.class);
                intent.putExtra(PAIS, paises.get(position));

                startActivity(intent);

            }

        });
    }

    public void setPaises(ArrayList<Pais> p){
        this.paises = p;
    }

    private class ConsomeWSPaises extends AsyncTask<String, Void, ArrayList<Pais>> {

        @Override
        protected ArrayList<Pais> doInBackground(String... url) {
            OkHttpClient client = new OkHttpClient();
            String url2 = "https://restcountries.eu/rest/v2/all";
            JSONArray obejctJSON;
            ArrayList<Pais> paises = new ArrayList<>();

            try{

                Request request = new Request.Builder().url(url2).build();
                Response response = client.newCall(request).execute();
                String json = response.body().toString();

                obejctJSON = new JSONArray(json);

                for(int i = 0; i < obejctJSON.length(); i++) {

                    JSONObject paisJson = obejctJSON.getJSONObject(i);
                    Pais pais;
                    ArrayList<String> idiomas;
                    ArrayList<String> moedas;
                    ArrayList<String> dominios;
                    ArrayList<String> fronteiras;
                    ArrayList<String> fusos;
                    pais = new Pais();
                    pais.setNome(paisJson.getString("name"));
                    pais.setCodigo3(paisJson.getString("alpha3Code"));
                    pais.setCapital(paisJson.getString("capital"));
                    pais.setRegiao(paisJson.getString("region"));
                    pais.setSubRegiao(paisJson.getString("subregion"));
                    pais.setDemonimo(paisJson.getString("demonym"));
                    pais.setPopulacao(paisJson.getInt("population"));
                    pais.setArea(paisJson.getInt("area"));
                    pais.setBandeira(paisJson.getString("flag"));
                    pais.setGini(27.80);
                    idiomas = new ArrayList<>();
                    idiomas.add("Pashto");
                    idiomas.add("Uzbek");
                    idiomas.add("Turkmen");
                    pais.setIdiomas(idiomas);
                    moedas = new ArrayList<>();
                    moedas.add("Afghan afghani");
                    pais.setMoedas(moedas);
                    dominios = new ArrayList<>();
                    dominios.add(".af");
                    pais.setDominios(dominios);
                    fusos = new ArrayList<>();
                    fusos.add("UTC+04:30");
                    pais.setFusos(fusos);
                    fronteiras = new ArrayList<>();
                    fronteiras.add("IRN");
                    fronteiras.add("PAK");
                    fronteiras.add("TKM");
                    fronteiras.add("UZB");
                    fronteiras.add("TJK");
                    fronteiras.add("CHN");
                    pais.setFronteiras(fronteiras);
                    pais.setLatitude(33.000000);
                    pais.setLongitude(65.000000);
                    paises.add(pais);
                }

            }catch (IOException | JSONException e){
                e.printStackTrace();
            }
            setPaises(paises);
            return paises;

        }

        @Override
        protected void onPostExecute(ArrayList<Pais> paises) {

        }

    }

}