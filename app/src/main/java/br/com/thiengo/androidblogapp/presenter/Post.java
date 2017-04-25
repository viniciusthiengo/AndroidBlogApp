package br.com.thiengo.androidblogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;


public class Post {
    public static final String POST_KEY = "post";
    public static final String CATEGORIA_KEY = "categoria";

    private long id;
    private String titulo;
    private String uriImagem;
    private String sumario;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public void setUriImagem(String uriImagem) {
        this.uriImagem = uriImagem;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }
}
