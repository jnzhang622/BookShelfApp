package com.example.bookshelfapp.ui.main.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bookshelfapp.ui.main.data.entities.BookEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class BookDao {
    @Query("SELECT * FROM books")
    public abstract Single<List<BookEntity>> getBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Completable insertBook(BookEntity book);

    @Query("DELETE FROM books")
    public abstract Completable deleteBooks();
}
