package com.example.bookshelfapp.ui.main.data;

import android.app.Application;

import com.example.bookshelfapp.ui.main.data.dao.BookDao;
import com.example.bookshelfapp.ui.main.data.database.Database;
import com.example.bookshelfapp.ui.main.data.entities.BookEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BookRepository {

    private BookDao mBookDao;

    public BookRepository(Application application){
        Database database = Database.getDatabase(application);
        mBookDao = database.bookDao();
    }
    public Single<List<BookEntity>> getAllBooks() {return mBookDao.getBooks(); }

    public Completable addBook(BookEntity bookEntity){
        return mBookDao.insertBook(bookEntity);
    }

}
