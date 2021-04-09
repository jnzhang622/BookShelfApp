package com.example.bookshelfapp.ui.main.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "books")
public class BookEntity {
    @ColumnInfo
    @PrimaryKey
    @NonNull
    private String title;

    @ColumnInfo
    private String author;

    @ColumnInfo
    private String genre;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        BookEntity that = (BookEntity) o;
        return getTitle().equals(that.getTitle()) &&
                getAuthor().equals(that.getAuthor()) &&
                getGenre().equals(that.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getGenre());
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
