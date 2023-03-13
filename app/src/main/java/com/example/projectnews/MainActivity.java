package com.example.projectnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.projectnews.adapter.MovieAdapter;
import com.example.projectnews.adapter.MovieCategoryAdapter;

import com.example.projectnews.adapter.AdapterCategory;
import com.example.projectnews.adapter.AdapterInfo;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.Movie;
import com.example.projectnews.model.Account;
import com.example.projectnews.model.Category;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieCategoryAdapter.CategoryClickInterface {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    TextView username1;
    ListView listView;
    ListView listViewNew;
    ListView listViewThongTin;
    TextView listMovieView;
    DrawerLayout drawerLayout;
    DBHelper dbHelper;
    ArrayList<Account> accountArrayList;
    ArrayList<Category> categoryArrayList;
    TextView textName;
    AdapterInfo AdapterInfo;
    AdapterCategory AdapterCategory;
    String email;
    String username;


    RecyclerView newsRV, newCateRV;
    ProgressBar loadingPB;
    ArrayList<Movie> movieArrayList;
    ArrayList<CategoryRvModal> categoryRvModalArrayList;
    MovieCategoryAdapter categoryRVAdapter;
    MovieAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");

        LoadMainView();
        ActionBar();

       ActionViewFlipper();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                        Intent intent = new Intent(MainActivity.this, UpdateProfileActivity.class);
                        intent.putExtra("username",username1);
                        startActivity(intent);
//                    else {
//                        Toast.makeText(MainActivity.this,"Bạn không có quyền ",Toast.LENGTH_SHORT).show();
//                        Log.e("Đăng bài : ","Bạn không có quyền ");
//                    }
                }
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this,WeatherActity.class);
                    startActivity(intent);
                }
                else if(position == 2){
                    Intent intent = new Intent(MainActivity.this,MainActivityNote.class);
                    startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent(MainActivity.this, Map.class);
                    startActivity(intent);
                }
                else if(position == 4){

                        Intent intent = new Intent(MainActivity.this, Account.class);
                        startActivity(intent);

                }
                else if(position == 5){
                    Intent intent = new Intent(MainActivity.this, MovieFavoriteActivity.class);
                    startActivity(intent);

                }
                else{
                    finish();
                }
            }
        });

    }

    private  void ActionViewFlipper(){
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_1__29.jpg");
        mangquangcao.add("https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/c/n/cnp_banner_adapt_980x448-01_1_.jpg");
        mangquangcao.add("https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_78.png");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        Animation animation_side_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_side_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_side_in);
        viewFlipper.setOutAnimation(animation_side_out);

    }
    private void LoadMainView(){

        newsRV = findViewById(R.id.idRvNews);
        newCateRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        movieArrayList = new ArrayList<>();
      //  getNews();
        categoryRvModalArrayList = new ArrayList<>();
        getCategories();
        newsRVAdapter = new MovieAdapter(this, movieArrayList);
        categoryRVAdapter = new MovieCategoryAdapter(categoryRvModalArrayList, this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        newCateRV.setAdapter(categoryRVAdapter);
        newsRVAdapter.notifyDataSetChanged();


       toolbar= findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=findViewById(R.id.viewflipper);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin=findViewById(R.id.listviewThongTin);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);

        //navigation
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");
        if(username1 == null){
            username1 = null;

        }
        if(username1!=null) {
            Cursor cursor1 = dbHelper.getDatausername(username1);
        // String ten = cursor.getString(0);
            String sdt = cursor1.getString(6);
            accountArrayList = new  ArrayList<>();

            accountArrayList.add(new Account(username1,sdt));

            AdapterInfo = new AdapterInfo(this, R.layout.navigation_thongtin, accountArrayList);
            listViewThongTin.setAdapter(AdapterInfo);
        }

        //Navigation 2
        categoryArrayList =new ArrayList<>();
        categoryArrayList.add(new Category("Thông tin",R.drawable.ic_baseline_face_24));
        categoryArrayList.add(new Category("Thời tiết",R.drawable.ic_baseline_cloud_24));
        categoryArrayList.add(new Category("Ghi chú",R.drawable.ic_baseline_event_note_24));
        categoryArrayList.add(new Category("Địa chỉ",R.drawable.ic_baseline_quiz_24));
        categoryArrayList.add(new Category("Tài khoản",R.drawable.ic_baseline_account_circle_24));
        categoryArrayList.add(new Category("Yêu thích",R.drawable.ic_baseline_favorite_24));
        categoryArrayList.add(new Category("Đăng xuất",R.drawable.ic_baseline_login_24));
        AdapterCategory =new AdapterCategory(this, R.layout.category, categoryArrayList);
        listView.setAdapter(AdapterCategory);
    }

    private  void ActionBar(){
        setSupportActionBar(toolbar);
        //set nút của toolbar là true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        //Tạo sự kiện click cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi lại drawerlayout, do toolbar được gọi ra nhờ drawerlayout
                drawerLayout.openDrawer(GravityCompat.START);   //GravityCompat.START làm nó nhảy ra giữa
            }
        });
    }



    private void getCategories(){
        categoryRvModalArrayList.add(new CategoryRvModal(1,"Mới nhất", "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/t/o/tom_jerry_980wx448h.jpg"));
        categoryRvModalArrayList.add(new CategoryRvModal(2,"Khuyến mãi", "https://www.cgv.vn/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_80.png"));
        categoryRvModalArrayList.add(new CategoryRvModal(3,"", "https://www.cgv.vn/media/catalog/product/cache/1/thumbnail/190x260/2e2b8cd282892c71872b9e67d2cb5039/s/u/suzume_vn_teaser_poster.jpg"));

    }

    private void getMovie(){


    }

    @Override
    public void onCategoryClick(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
                break;
            default:break;

        }
        return super.onOptionsItemSelected(item);
    }



}