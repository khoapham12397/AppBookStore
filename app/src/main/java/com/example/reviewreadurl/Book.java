package com.example.reviewreadurl;


import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    int id;
    String name;
    String author;
    int price;

    protected Book(Parcel in) {
        id = in.readInt();
        name = in.readString();
        author = in.readString();
        price = in.readInt();
        pathImg = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPathImg() {
        return pathImg;
    }

    public Book(int id, String name, String author, int price, String pathImg) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.pathImg = pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    String pathImg;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeInt(price);
        dest.writeString(pathImg);
    }
}

