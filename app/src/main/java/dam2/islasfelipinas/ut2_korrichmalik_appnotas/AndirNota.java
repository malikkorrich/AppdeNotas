package dam2.islasfelipinas.ut2_korrichmalik_appnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AndirNota extends AppCompatActivity {
private EditText etContenido,etTitulo;
private Spinner sp;
private NotaDatabase notaDatabase;
    //get Date Time
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    String fecha = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andir_notas);

        //insitanciamos un objeto de clase Notadatabase
        notaDatabase = new NotaDatabase(this);

        //inflate de los views
        sp = findViewById(R.id.SP_Categoria);
        etContenido = findViewById(R.id.ET_Contenido);
        etTitulo = findViewById(R.id.ET_Titulo);

        //Categoria Array of String
        String[]  categorias = {"Viajes","Compras","Estudios"};

        ArrayAdapter spAdapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,categorias);
        sp.setAdapter(spAdapter);

    }

    // aqui hacemos un inflate del menu anadir nota
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.anadir_menu,menu);
        return true;
    }

    // aqui manejamos evento click del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.addMark:
                Nota nota = new Nota(etTitulo.getText().toString(),etContenido.getText().toString(),fecha,sp.getSelectedItem().toString());
                notaDatabase.anadirNota(nota);
                 Toast.makeText(getApplicationContext(),"Nota creada",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }


        return false;
    }
}