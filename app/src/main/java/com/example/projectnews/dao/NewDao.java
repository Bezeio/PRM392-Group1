package com.example.projectnews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.Movie;

import java.util.ArrayList;

public class NewDao extends DBHelper implements INewDao {

    public NewDao(Context context) {
        super(context);
    }

    /**
     * @author minhbd
     * Feature News Query
     **/

    //NEW ENTITY
    @Override
    public Movie getMovieById(int Id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEW_TABLE_NAME, null, NEW_ID_COLUMN + " = ?", new String[]{String.valueOf(Id)}, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() == 0) return null;
            cursor.moveToFirst();
        }

        Movie mewObj = new Movie(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return mewObj;
    }


    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        String query = "SELECT * FROM " + NEW_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Movie movieObj = new Movie(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            movieList.add(movieObj);
            cursor.moveToNext();
        }
        return movieList;
    }

    @Override
    public ArrayList<Movie> getMoviesByCategory(String category) {
        if (category == "") category = "Mới nhất";
        ArrayList<Movie> movieList = new ArrayList<>();
        String query = "SELECT * FROM " + NEW_TABLE_NAME + " WHERE " + NEW_CATEGORY_NAME_COLUMN + " = '" + category + "';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Movie movieObj = new Movie(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            movieList.add(movieObj);
            cursor.moveToNext();
        }
        return movieList;
    }

    @Override
    public ArrayList<Movie> getMoviesBySearch(String text) {
        ArrayList<Movie> movieList = new ArrayList<>();
        String query = "SELECT * FROM " + NEW_TABLE_NAME + " WHERE " + NEW_TITLE_COLUMN + " like '%" + text + "%';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Movie movieObj = new Movie(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            movieList.add(movieObj);
            cursor.moveToNext();
        }
        return movieList;
    }

    //NEW CATEGORY ENTITY
    @Override
    public ArrayList<CategoryRvModal> getAllMoviesCategory() {
        ArrayList<CategoryRvModal> newCateList = new ArrayList<>();
        String query = "SELECT * FROM " + NEW_CATEGORY_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            CategoryRvModal newCate = new CategoryRvModal(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            newCateList.add(newCate);
            cursor.moveToNext();
        }
        return newCateList;
    }

    @Override
    public ArrayList<Integer> getListMovieFavor(String username) {
        return null;
    }

    //NEW FAVOR ENTITY
    @Override
    public ArrayList<Integer> getListNewFavor(String username) {
        ArrayList<Integer> newListId = new ArrayList<>();

        String whereCondition = username != null ? " WHERE " + NEW_FAVOR_USER_COLUMN + "= '" + username + "';" : " WHERE " + NEW_FAVOR_USER_COLUMN + "= null;";
        String query = "SELECT * FROM " + NEW_FAVOR_TABLE_NAME + whereCondition;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() == 0) return new ArrayList<>();
            cursor.moveToFirst();
        } else return new ArrayList<>();

        while (cursor.isAfterLast() == false) {
            newListId.add(cursor.getInt(1));
            cursor.moveToNext();
        }
        return newListId;
    }

    @Override
    public boolean AddMovieFavor(int newId, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        if(checkNewExistFavor(newId, username)) return false;

        boolean check;
        ContentValues values = new ContentValues();
        values.put(NEW_FAVOR_NEW_ID_COLUMN, newId);
        values.put(NEW_FAVOR_USER_COLUMN, username);

        check = db.insert(NEW_FAVOR_TABLE_NAME, null, values)==1;
        db.close();

        return check;
    }

    @Override
    public void RemoveMovieFavor(int newId, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(username != null){
            db.delete(NEW_FAVOR_TABLE_NAME, NEW_FAVOR_NEW_ID_COLUMN + " = ?  AND " + NEW_FAVOR_USER_COLUMN + " = ? ", new String[]{String.valueOf(newId), username});
        }else {
            db.delete(NEW_FAVOR_TABLE_NAME, NEW_FAVOR_NEW_ID_COLUMN + " = ? " , new String[]{String.valueOf(newId)});
        }
        db.close();
    }

    @Override
    public boolean checkNewExistFavor(int newId, String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor;
        if (username != null)
            cursor = MyDB.rawQuery("Select * from " + NEW_FAVOR_TABLE_NAME + " where " + NEW_FAVOR_NEW_ID_COLUMN + " = ? and " + NEW_FAVOR_USER_COLUMN + " = ?", new String[]{Integer.toString(newId), username});
        else
            cursor = MyDB.rawQuery("Select * from " + NEW_FAVOR_TABLE_NAME + " where " + NEW_FAVOR_NEW_ID_COLUMN + " = ? and " + NEW_FAVOR_USER_COLUMN + " = null ", new String[]{Integer.toString(newId)});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
