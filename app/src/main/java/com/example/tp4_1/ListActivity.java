package com.example.tp4_1;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp4_1.adapter.StarAdapter;
import com.example.tp4_1.beans.Star;
import com.example.tp4_1.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarService service;
    private StarAdapter starAdapter = null;
    private Toolbar myToolbar;

    public ListActivity() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        //this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("STAR RATE");


        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        stars = service.findAll();
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void filterList(String s) {
        List<Star> filtredList = new ArrayList<>();
        for (Star star : stars) {
            if (star.getName().toLowerCase().startsWith(s.toLowerCase().trim())) {
                filtredList.add(star);
            }

            starAdapter.setFiltredList(filtredList);
        }
    }

    public void init() {
        service.create(new Star("Tour Eiffel", "https://media.tacdn.com/media/attractions-splice-spp-674x446/12/2e/16/f8.jpg", 0.8f));
        service.create(new Star("Arc de Triomphe", "https://www.cuddlynest.com/blog/wp-content/uploads/2024/03/arc-de-triomphe.jpg", 1.7f));
        service.create(new Star("Musée d'Orsay", "https://s5.ezgif.com/tmp/ezgif-5-a2ddc8163a.jpg", 3.9f));
        service.create(new Star("Musée du Louvre", "https://s5.ezgif.com/tmp/ezgif-5-d3fc991e5d.jpg", 2.5f));
        service.create(new Star("Cathédral Notre Dames de Paris", "https://s5.ezgif.com/tmp/ezgif-5-6f4feb942b.jpg", 1.6f));
        service.create(new Star("Palais de Versailles", "https://s5.ezgif.com/tmp/ezgif-5-147666b613.jpg", 1.9f));
        service.create(new Star("Palais Garnier", "https://s5.ezgif.com/tmp/ezgif-5-bd238e577f.jpg", 1.7f));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}