package dam2.islasfelipinas.ut2_korrichmalik_appnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ModificarBorrarNota extends AppCompatActivity {
private final int RES_CODE =1;
    private EditText etContenido,etTitulo;
    private Spinner sp;
    private NotaDatabase notaDatabase;
    private int id;
    //get Date Time
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    String fecha = sdf.format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_borrar_nota);

        //insitanciamos un objeto de clase Notadatabase
        notaDatabase = new NotaDatabase(this);

        //inflate de los views
        etTitulo = findViewById(R.id.ET_Titulo);
        etContenido = findViewById(R.id.ET_Contenido);
        sp = findViewById(R.id.SP_Categoria);

        //Categoria Array of String

        // he utilizado adapter para el spinner porq tiene el metodo spAdapter.getPosition(categoria)
        //para obtener selected categoria from list view
        String[]  categorias = {"Viajes","Compras","Estudios"};

        ArrayAdapter spAdapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,categorias);
          sp.setAdapter(spAdapter);


        //aqui recuperamos los datos enviados desde mainactivity
        Intent it = getIntent();
         id = it.getIntExtra("id",0);
        String titulo = it.getStringExtra("titulo");
        String categoria = it.getStringExtra("categoria");
        String contenido = it.getStringExtra("contenido");

        //anadimos los datos a los views
        etTitulo.setText(titulo);
        etContenido.setText(contenido);
        sp.setSelection(spAdapter.getPosition(categoria));

    }



    //inflate our menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.modif_menu,menu);
        return true;
    }


    //Menu Click event
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.modfiNota:
                String titulo =etTitulo.getText().toString();
                String contenido =etContenido.getText().toString();
                String categoria = sp.getSelectedItem().toString();

                //aqui creamos un objeto de nota  y llamaos el metodo modificarnota  y cerreamos actividad mostrando un toast
                Nota nota = new Nota(id,titulo,contenido,fecha,categoria);
                notaDatabase.modificarNota(nota);
                Toast.makeText(getApplicationContext(),"Nota actualizada",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.elimNota:
                //aqui usado alterdialog en borrar notas
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Eliminar Nota");
                alert.setMessage("estas seguro que deseas eliminar esta nota ?");
                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Nota nota2 = new Nota(id,etTitulo.getText().toString(),etContenido.getText().toString(),fecha,sp.getSelectedItem().toString());
                        notaDatabase.eliminarNota(nota2.getId());
                        Toast.makeText(getApplicationContext(),"Nota eliminada",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    }
                });
                alert.create().show();

                break;

        }


        return false;
    }
}