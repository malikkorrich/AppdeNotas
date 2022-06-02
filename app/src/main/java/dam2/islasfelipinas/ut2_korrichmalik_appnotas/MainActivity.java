package dam2.islasfelipinas.ut2_korrichmalik_appnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 private ListView lv;
 private NotaDatabase notaDatabase;
 private ArrayList<Nota>listaNotas;
 private NotaAdapter adapter;
 private ArrayList<Nota> notas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);

      //insitanciamos un objeto de clase Notadatabase
          notaDatabase = new NotaDatabase(this);
            listaNotas = notaDatabase.getNotas();


          //aqui mostramos la lista de notas en el lista view
       if (listaNotas.size() > 0) {
           notas = notaDatabase.getNotas();
           adapter = new NotaAdapter(this,notas);

            lv.setAdapter(adapter);



           //Manejamos El click de los elementos  del ListView
           lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String contenido = adapter.notas.get(i).getContenido();
                   String categoria = adapter.notas.get(i).getCategoria();
                   String titulo = adapter.notas.get(i).getTitulo();

                int id = adapter.notas.get(i).getId();

                Intent it = new Intent(MainActivity.this,ModificarBorrarNota.class);
                it.putExtra("id",id);
                it.putExtra("titulo",titulo);
                it.putExtra("categoria",categoria);
                it.putExtra("contenido",contenido);
                startActivity(it);

               }
           });
        }
        else {

            Toast.makeText(this, "Todavia no Hay notas .", Toast.LENGTH_LONG).show();
        }





    }


    // aqui he sobreescrito el metodo on resume para actualizar el listview
    @Override
    protected void onResume() {
        super.onResume();
        if (listaNotas.size() > 0) {
            notas = notaDatabase.getNotas();
            adapter = new NotaAdapter(this, notas);
            lv.setAdapter(adapter);
        }
    }

    // aqui hacemos un inflate del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    // aqui manejamos evento click del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.addMark:
              startActivity(new Intent(MainActivity.this, AndirNota.class));

                break;
        }


        return false;
    }
}