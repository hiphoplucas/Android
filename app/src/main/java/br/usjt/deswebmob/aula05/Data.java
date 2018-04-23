package br.usjt.deswebmob.aula05;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.*;

/**
 * Autor Lucas dos Reis
 * RA: 816110978
 */

public class Data {

    public static ArrayList<String> listarNomes(ArrayList<Pais> paises){
        ArrayList<String> nomes = new ArrayList<>();
        for (Pais pais : paises) {
            nomes.add(pais.getNome());
        }
        return nomes;
    }
    public static ArrayList<Pais> listarPaises(String continente) {
        Pais[] lista;
        ArrayList<Pais> paises = new ArrayList<>();
        ArrayList<Pais> temp = new ArrayList<>();
        //carrega somente os paises da regiao escolhida


        for (Pais pais : todosPaises()) {
            if (pais.getRegiao().equals(continente) || continente.equals("Todos")) {
                paises.add(pais);
            }
        }
        //converte para vetor para poder ordenar usando o sort da classe Arrays
        lista = paises.toArray(new Pais[0]);
        //ordena baseado no compareTo sobrescrito na classe Pais
        //para funcionar, a classe pais precisa implementar a interface Comparable
        Arrays.sort(lista);
        //transforma de novo em ArrayList
        paises = new ArrayList<>();
        for(int i = 0; i < lista.length; i++) {
            paises.add(lista[i]);
        }
        return paises;
    }

    private static ArrayList<Pais> todosPaises() {

        OkHttpClient client = new OkHttpClient();
        String url = "https://restcountries.eu/rest/v2/all";
        JSONArray obejctJSON;
        ArrayList<Pais> paises = new ArrayList<>();

        try{

            Request request = new Request.Builder().url(url).build();
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

        return paises;
    }
}
