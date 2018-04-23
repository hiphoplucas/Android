package br.usjt.deswebmob.aula05;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        String json = intent.getStringExtra("JSONRETURN");
        //System.out.println(json);

        try {

            JSONArray obejctJSON = new JSONArray(json);
            paises = new ArrayList<Pais>();

            for(int i = 0; i < obejctJSON.length(); i++) {

                JSONObject paisJson = obejctJSON.getJSONObject(i);

                if(!paisJson.getString("region").equals(continente)){
                    continue;
                }

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
                if(paisJson.getString("area") != "null") pais.setArea(Double.parseDouble(paisJson.getString("area")));
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
                System.out.println(paisJson.getString("name"));
                this.paises.add(pais);
            }

            nomes = this.listarNomes(this.paises);

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

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> listarNomes(ArrayList<Pais> paises){
        ArrayList<String> nomes = new ArrayList<>();
        for (Pais pais : paises) {
            nomes.add(pais.getNome());
        }
        return nomes;
    }

}