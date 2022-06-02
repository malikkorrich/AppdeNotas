package dam2.islasfelipinas.ut2_korrichmalik_appnotas;


public class Nota {
    private int id;
    private String titulo;
    private String contenido;
    private String fecha_creacion;
    private String categoria;

    public Nota(int id, String titulo, String contenido, String fecha_creacion, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha_creacion = fecha_creacion;
        this.categoria = categoria;
    }


    public Nota(String titulo, String contenido, String fecha_creacion, String categoria) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha_creacion = fecha_creacion;
        this.categoria = categoria;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
