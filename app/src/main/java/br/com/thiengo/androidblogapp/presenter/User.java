package br.com.thiengo.androidblogapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    public static final String KEY = "user";

    private String id;
    private String email;
    private String nome;
    private String profissao;
    private String uriImagem;
    private boolean logged;


    public User( String id, String email ){
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public void setUriImagem(String uriImagem) {
        this.uriImagem = uriImagem;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.email);
        dest.writeString(this.nome);
        dest.writeString(this.profissao);
        dest.writeString(this.uriImagem);
        dest.writeByte(this.logged ? (byte) 1 : (byte) 0);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.email = in.readString();
        this.nome = in.readString();
        this.profissao = in.readString();
        this.uriImagem = in.readString();
        this.logged = in.readByte() != 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
