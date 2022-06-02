package dam2.islasfelipinas.ut2_korrichmalik_appnotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

public class NotaDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "notasdb";
    private static final String TABLE_NAME = "notas";
    private static final String COL_ID = "id";
    private static final String COL_TITULO = "titulo";
    private static final String COL_CONTENIDO = "contenido";
    private static final String COL_FECHA  = "fecha_creacion";
    private static final String COL_CATEGORIA = "categoria";

    //constructor
    NotaDatabase (Context context) {
        super(context , DB_NAME ,null , DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Cear query
        String CREAR_TABLE_QUERY = "CREATE TABLE "+TABLE_NAME+ "("+COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COL_TITULO+ " TEXT, "+COL_CONTENIDO+ " TEXT, " +COL_FECHA+ " TEXT, " +COL_CATEGORIA+ " TEXT"+")";

        //Crear la table notas
        sqLiteDatabase.execSQL(CREAR_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " +TABLE_NAME;
       sqLiteDatabase.execSQL(DROP_TABLE_QUERY);

       //llamaos Al metodo Oncreate Para la tabla de nuevo
        onCreate(sqLiteDatabase);
    }

    //metodo para añadir nota
    public void anadirNota(Nota nota){
        ContentValues values = new ContentValues();

        values.put(COL_TITULO,nota.getTitulo());
        values.put(COL_CONTENIDO,nota.getContenido());
        values.put(COL_FECHA,nota.getFecha_creacion());
        values.put(COL_CATEGORIA,nota.getCategoria());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    //metodo para modificar nota
    void modificarNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITULO, nota.getTitulo());
        values.put(COL_CONTENIDO, nota.getContenido());
        values.put(COL_FECHA, nota.getFecha_creacion());
        values.put(COL_CATEGORIA, nota.getCategoria());

       String [] args = {String.valueOf(nota.getId())};
       db.update(TABLE_NAME,values, COL_ID + "=?",args);


    }


    //metodo para eliminar nota
    void eliminarNota(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
    }


    //metodo devolver las notas ordenada por categoria
    ArrayList<Nota> getNotas() {
        ArrayList<Nota> notas = new ArrayList<>();

        String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME +" ORDER BY "+COL_CATEGORIA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String titulo = cursor.getString( 1);
                String contenido = cursor.getString(2);
                String fecha = cursor.getString(3);
                String categoria = cursor.getString(4);

                notas.add(new Nota(id,titulo,contenido,fecha,categoria));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return notas;
    }

}
