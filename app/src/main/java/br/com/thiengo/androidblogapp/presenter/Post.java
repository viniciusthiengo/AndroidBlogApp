package br.com.thiengo.androidblogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;


public class Post implements Parcelable {
    public static final String POSTS_KEY = "posts";

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.uriImagem);
        dest.writeString(this.sumario);
    }

    public Post() {}

    protected Post(Parcel in) {
        this.id = in.readLong();
        this.titulo = in.readString();
        this.uriImagem = in.readString();
        this.sumario = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
