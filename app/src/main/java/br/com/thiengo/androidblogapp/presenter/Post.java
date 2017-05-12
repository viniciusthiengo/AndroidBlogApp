package br.com.thiengo.androidblogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import br.com.thiengo.androidblogapp.R;

public class Post implements Parcelable {
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

    public int getIcon( int categoria ){
        switch( categoria ){
            case 1:
                return R.drawable.ic_categoria_1;
            case 2:
                return R.drawable.ic_categoria_2;
            case 3:
                return R.drawable.ic_categoria_3;
            case 4:
                return R.drawable.ic_categoria_4;
            default:
                return R.drawable.ic_categoria_5;
        }
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

    public Post() {
    }

    protected Post(Parcel in) {
        this.id = in.readLong();
        this.titulo = in.readString();
        this.uriImagem = in.readString();
        this.sumario = in.readString();
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
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
