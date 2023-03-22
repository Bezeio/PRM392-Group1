package com.example.projectnews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.projectnews.model.Account;
import com.example.projectnews.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Appdocbao.db";
    Context context;

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }

    //SESSION TABLE
    public static final String SESSION_TABLE_NAME = "session";
    public static final String SESSION_KEY_COLUMN = "key";
    public static final String SESSION_VALUE_COLUMN = "value";

    //NEW_CATEGORY TABLE
    public static final String NEW_CATEGORY_TABLE_NAME = "newcategory";
    public static final String NEW_CAT_ID_COLUMN = "id";
    public static final String NEW_CAT_NAME_COLUMN = "name";
    public static final String NEW_CAT_IMAGE_LINK_COLUMN = "imagelink";

    //NEW TABLE
    public static final String NEW_TABLE_NAME = "new";
    public static final String NEW_ID_COLUMN = "id";
    public static final String NEW_CATEGORY_NAME_COLUMN = "cate_name";
    public static final String NEW_TITLE_COLUMN = "title";
    public static final String NEW_CONTENT_COLUMN = "content";
    public static final String NEW_IMAGE_COLUMN = "image";
    public static final String NEW_AUTHOR_COLUMN = "author";
    public static final String NEW_CREATE_DATE_COLUMN = "create_date";

    //ROOM MOVIE TABLE
    public static final String NEW_ROOM_TABLE_NAME = "room";
    public static final String NEW_ID_ROOM_COLUMN = "id";
    public static final String NEW_ROOM_ID_MOVIE_COLUMN = "movie";
    public static final String NEW_TIME_LINE_COLUMN = "timeline";
    public static final String NEW_DATE_TIME_COLUMN = "datetime";
    public static final String NEW_ROOM_NAME_COLUMN = "room_name";
    public static final String NEW_VENUE_COLUMN = "venue"; //dia diem chieu

    //CHAIR IN ROOM MOVIE TABLE
    public static final String NEW_CHAIR_ROOM_TABLE_NAME = "chai_room";
    public static final String NEW_ID_CHAIR_COLUMN = "id";
    public static final String NEW_CHAIR_NAME_COLUMN = "chair_name";
    public static final String NEW_ID_ROOM_TABLE = "room";
    public static final String NEW_STATUS_CHAIR_TABLE = "status";

    //CART TABLE
    public static final String NEW_CARD_NAME = "card";
    public static final String NEW_ID_CARD_ITEM = "id";
    public static final String NEW_ID_TABLE = "movie";

    //NEW FAVOR TABLE
    public static final String NEW_FAVOR_TABLE_NAME = "new_favor";
    public static final String NEW_FAVOR_ID_COLUMN = "id";
    public static final String NEW_FAVOR_NEW_ID_COLUMN = "new_id";
    public static final String NEW_FAVOR_USER_COLUMN = "username";

    //NOTE TABLE
    private static final String tableName = "mynotes";
    private static final String columnId = "id";
    private static final String columnTitle = "title";
    private static final String columnDescription = "description";

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        // MyDB.execSQL(SQLQuery1);
        String create_session_table = String.format("CREATE TABLE %s(%s TEXT, %s TEXT)",
                SESSION_TABLE_NAME, SESSION_KEY_COLUMN, SESSION_VALUE_COLUMN);
        String create_new_cat_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)",
                NEW_CATEGORY_TABLE_NAME, NEW_CAT_ID_COLUMN, NEW_CAT_NAME_COLUMN, NEW_CAT_IMAGE_LINK_COLUMN);
        String create_new_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                NEW_TABLE_NAME, NEW_ID_COLUMN, NEW_CATEGORY_NAME_COLUMN, NEW_TITLE_COLUMN, NEW_CONTENT_COLUMN, NEW_IMAGE_COLUMN, NEW_AUTHOR_COLUMN, NEW_CREATE_DATE_COLUMN);

        String create_new_room_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                NEW_ROOM_TABLE_NAME, NEW_ID_ROOM_COLUMN, NEW_ROOM_ID_MOVIE_COLUMN, NEW_TIME_LINE_COLUMN, NEW_DATE_TIME_COLUMN, NEW_ROOM_NAME_COLUMN, NEW_VENUE_COLUMN);

        String create_new_chair_room_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT, %s INTEGER)",
                NEW_CHAIR_ROOM_TABLE_NAME, NEW_ID_CHAIR_COLUMN, NEW_ID_ROOM_TABLE, NEW_CHAIR_NAME_COLUMN, NEW_STATUS_CHAIR_TABLE);

        String create_new_card = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER)",
                NEW_CARD_NAME, NEW_ID_CARD_ITEM, NEW_ID_TABLE);

        String create_new_favor_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT)",
                NEW_FAVOR_TABLE_NAME, NEW_FAVOR_ID_COLUMN, NEW_FAVOR_NEW_ID_COLUMN, NEW_FAVOR_USER_COLUMN);

        String query = "CREATE TABLE " + tableName +
                " (" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                columnTitle + " TEXT, " +
                columnDescription + " Text);";

        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, status text, role text,avatar blob,showname text)");
        MyDB.execSQL(query);
        MyDB.execSQL(create_session_table);
        MyDB.execSQL(create_new_cat_table);
        MyDB.execSQL(create_new_table);
        MyDB.execSQL(create_new_chair_room_table);
        MyDB.execSQL(create_new_card);
        MyDB.execSQL(create_new_room_table);
        MyDB.execSQL(create_new_favor_table);

        //User
        MyDB.execSQL(SQLQuery2);

        //New
        MyDB.execSQL(insertTableNewCategoryquery);
        MyDB.execSQL(insertTableNewquery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists truyen");
        MyDB.execSQL("DROP TABLE IF EXISTS " + tableName);
        MyDB.execSQL("DROP TABLE IF EXISTS " + SESSION_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + NEW_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + NEW_FAVOR_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + NEW_CATEGORY_TABLE_NAME);
        onCreate(MyDB);
    }

    /**
     * Query xử lý session cho app
     **/
    public void addSession(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SESSION_KEY_COLUMN, key);
        values.put(SESSION_VALUE_COLUMN, value);

        db.insert(SESSION_TABLE_NAME, null, values);
        db.close();
    }

    public boolean addToCart(int movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (isMovieInCart(movie + "")) return false;
        db.execSQL("INSERT INTO card (movie) VALUES('" + movie + "');");
        db.close();
        return true;
    }

    private boolean isMovieInCart(String movie){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String sql = "select * from card where movie =?";
        Cursor cursor = MyDB.rawQuery(sql, new String[]{movie});
        if (cursor.moveToNext()) return true;
        return false;
    }

    private Movie getMovieById(String id) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String sql = "select * from new where id =?";
        Cursor cursor = MyDB.rawQuery(sql, new String[]{id});
        Movie movie = null;
        if (cursor.moveToFirst()) {
            do {
                int id1 = cursor.getInt(0);
                String cate = cursor.getString(1);
                String title = cursor.getString(2);
                String content = cursor.getString(3);
                String image = cursor.getString(4);
                String author = cursor.getString(5);
                String create = cursor.getString(6);
                movie = new Movie(id1, cate, title, content, image, author, create);
            } while (cursor.moveToNext());
        }
        return movie;
    }

    public List<Movie> getMovieFromCart() {
        List<Movie> movies = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String sql = "select * from card";
        Cursor cursor = MyDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(1);
                Movie movie = getMovieById(id + "");
                if (movie != null) movies.add(movie);
            } while (cursor.moveToNext());
        }
        return movies;
    }

    public String getSession(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(SESSION_TABLE_NAME, null, SESSION_KEY_COLUMN + " = ?", new String[]{String.valueOf(key)}, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() == 0) return null;
            cursor.moveToFirst();
        }

        String value = cursor.getString(1);
        return value;
    }

    public void removeSession(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SESSION_TABLE_NAME, SESSION_KEY_COLUMN + " = ?", new String[]{String.valueOf(key)});
        db.close();
    }

    /*
        @author longhn
        Feature Public Query
    */
    public Boolean insertData(String username, String password, String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("status", 0);
        contentValues.put("role", 0);
        contentValues.put("avatar", "");
        contentValues.put("showname", "");
        long result = MyDB.insert("user", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updatepass(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);

        long result = MyDB.update("user", contentValues, "username = ?", new String[]{username});
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updateprofile(String username, String showname, byte[] avatar) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("showname", showname);
        //       contentValues.put("email", email);
        contentValues.put("avatar", avatar);
        long result = MyDB.update("user", contentValues, "username = ?", new String[]{username});
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getDatausername(String a) {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        String sql = "select * from user where username =?";
        Cursor cursor = MyDB.rawQuery(sql, new String[]{a});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;


    }

    public void deleteEmployee(String id) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "DELETE FROM user where username =?";
        Cursor cursor = MyDB.rawQuery(query, new String[]{id});

    }

    public List<Account> getEmployeeList() {
        String sql = "select * from user where role = 0";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        List<Account> storeEmployee = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(0);
                String email = cursor.getString(2);
                String status = cursor.getString(3);
                String role = cursor.getString(4);
                byte[] test = cursor.getBlob(5);
                storeEmployee.add(new Account(username, email, status, role, test));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }

    //Notes
    public void addNotes(String title, String desc) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(columnTitle, title);
        cv.put(columnDescription, desc);

        long resultValue = db.insert(tableName, null, cv);

        if (resultValue == -1) {
            Toast.makeText(context, "Dữ liệu không được add ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

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

    public Cursor readNotes() {
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteAllNotes() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + tableName;
        database.execSQL(query);

    }

    public void updateNotes(String title, String desc, String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnTitle, title);
        contentValues.put(columnDescription, desc);

        long resut = database.update(tableName, contentValues, "id=?", new String[]{id});
        if (resut == -1) {
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Hoàn thành!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteSingleItem(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(tableName, "id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Dữ liệu chưa được xóa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
        }
    }

    //DATA USER
    private String SQLQuery2 = "INSERT INTO user VAlUES ('admin','admin','admin@gmail.com',0,1,'','admin')";

    //DATA NEW CATEGORY
    private String insertTableNewCategoryquery = "INSERT INTO " + NEW_CATEGORY_TABLE_NAME + " (" + NEW_CAT_NAME_COLUMN + "," + NEW_CAT_IMAGE_LINK_COLUMN + " ) VALUES \n" +
            "   ('Mới nhất', 'https://play-lh.googleusercontent.com/P8D-vfnCmeaP3b3pbS_JmWlDkGGYaPg1xE4rOXMWPiTsL8fKlpsTxgVOkWj7w1ryx0pC'),\n" +
            "   ('Hành động', 'https://i.chungta.vn/2020/01/24/AI-tech-620x389-2485-1579853809.jpg'),\n" +
            "   ('Thế giới', 'http://nghiencuuquocte.org/wp-content/uploads/2021/01/globe_hand.jpg'),\n" +
            "   ('Kinh dị', 'https://irace.vn/wp-content/uploads/2019/10/silhouete-action-sport-outdoors-group-kids-having-fun-playing-soccer-football.jpg'),\n" +
            "   ('Sức khỏe', 'https://ichef.bbci.co.uk/news/640/cpsprodpb/D897/production/_101174455_whatsubject.jpg'),\n" +
            "   ('Thời trang', 'https://media.vneconomy.vn/w800/images/upload/2022/09/15/avaa.png');";

    private String insertTableNewquery = "INSERT INTO " + NEW_TABLE_NAME + " (" + NEW_CATEGORY_NAME_COLUMN + "," + NEW_TITLE_COLUMN + "," + NEW_CONTENT_COLUMN + "," + NEW_IMAGE_COLUMN + "," + NEW_AUTHOR_COLUMN + "," + NEW_CREATE_DATE_COLUMN + " ) VALUES \n" +
            "   ( 'Kinh dị', 'Everything everywhere all at once', '94%', 'https://image.tmdb.org/t/p/original/w3LxiVYdWWRvEVdn5RYq6jIqkb1.jpg', 'Thanh Phong', '29/10/2022'),\n" +

            "   ( 'Hành động', 'Shazam! Fury of the Gods', '86%', 'https://assets-prd.ignimgs.com/2023/01/24/shzam2-vert-main-2764x4096-dom-1674589983500.jpg', 'Hoàng Quyết', '30/10/2022'),\n" +
            "   ( 'Hành động', 'Black Panther: Wakanda forever', '84%', 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/A1PaCX4oXjL.jpg', 'Thanh Phong', '30/10/2022'),\n" +
            "   ( 'Hành động', 'Top gun: Maverick 2022', '97%', 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71BokibfVUL.jpg', 'Thanh Phong', '30/10/2022'),\n" +
            "   ( 'Hành động', 'Avatar: The way of Water', '78%', 'https://m.media-amazon.com/images/M/MV5BYjhiNjBlODctY2ZiOC00YjVlLWFlNzAtNTVhNzM1YjI1NzMxXkEyXkFqcGdeQXVyMjQxNTE1MDA@._V1_FMjpg_UX1000_.jpg', 'Thanh Phong', '2/11/2022');";
    private String insertTableNewRoom = "INSERT INTO " + NEW_ROOM_TABLE_NAME + " (" + NEW_ID_ROOM_COLUMN + "," + NEW_ROOM_ID_MOVIE_COLUMN + " ," + NEW_TIME_LINE_COLUMN + "," + NEW_DATE_TIME_COLUMN + "," + NEW_ROOM_NAME_COLUMN + "," + NEW_VENUE_COLUMN + ") VALUES \n" +
            "   ( '1', '1', '9:00 AM', '22/3/2023', 'AC1', 'Mỹ Đình'),\n" +
            "   ( '2', '1', '12:00 AM', '22/3/2023', 'AC1', 'Mỹ Đình'),\n" +
            "   ( '3', '2', '14:00 AM', '22/3/2023', 'AC1', 'Mỹ Đình'),\n" +
            "   ( '4', '3', '16:00 AM', '22/3/2023', 'AC1', 'Mỹ Đình'),\n" +
            "   ( '5', '1', '18:00 AM', '22/3/2023', 'AC1', 'Mỹ Đình');";

}