package br.usjt.deswebmob.aula04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.usjt.deswebmob.aula04_2.R;

public class MeuAdapter extends BaseAdapter {

    Context context;
    ArrayList<Pais> T;

    public MeuAdapter(Context context, ArrayList<Pais> T){
        this.context = context;
        this.T = T;
    }

    @Override
    public int getCount() {
        return this.T.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.minha_linha, null);
        }

        ImageView img = (ImageView) v.findViewById(R.id.listaImg);
        TextView titulo = (TextView) v.findViewById(R.id.listaTitulo);
        TextView subTitulo = (TextView) v.findViewById(R.id.listaSubTitulo);

        //img.set
        titulo.setText(this.T.get(position).getNome());
        subTitulo.setText(this.T.get(position).getSubRegiao());

        return null;

    }
}
