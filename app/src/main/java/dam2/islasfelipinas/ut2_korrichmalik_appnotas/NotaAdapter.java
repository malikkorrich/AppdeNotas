package dam2.islasfelipinas.ut2_korrichmalik_appnotas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotaAdapter extends BaseAdapter {
    ArrayList<Nota> notas;
    Context context;

    public NotaAdapter(Context context ,ArrayList<Nota> notas) {
        this.notas = notas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null){
            v =LayoutInflater.from(context).inflate(R.layout.lista_notas,null,false);
        }
             TextView tvCategoria = v.findViewById(R.id.tvCategoria);
            TextView tvContenido = v.findViewById(R.id.tvContenido);
            Nota selectedNota = notas.get(position);
            tvCategoria.setText(selectedNota.getCategoria());
            tvContenido.setText(selectedNota.getContenido());
        return v ;
    }
}
