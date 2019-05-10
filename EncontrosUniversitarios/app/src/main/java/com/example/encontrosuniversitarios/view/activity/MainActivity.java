package com.example.encontrosuniversitarios.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.view.fragment.FrequenciaFragment;
import com.example.encontrosuniversitarios.view.fragment.ProgramacaoDoDiaFragment;
import com.example.encontrosuniversitarios.view.fragment.ProgramacaoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        int itemId = 1;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_programacao:
                    getSupportActionBar().setTitle(R.string.title_programacao);
                    fragment = new ProgramacaoFragment();
                    itemId = 0;
                    break;
                case R.id.navigation_programacao_do_dia:
                    getSupportActionBar().setTitle(R.string.title_programacao_do_dia);
                    fragment = new ProgramacaoDoDiaFragment();
                    itemId = 1;
                    break;
                case R.id.navigation_frequencia:
                    getSupportActionBar().setTitle(R.string.title_frequencia);
                    fragment = new FrequenciaFragment();
                    itemId = 2;
                    break;
            }
            openFragment(fragment,itemId);
            updateSearchViewFragment();
            return false;
        }
    };

    private void openFragment(Fragment fragment,int itemId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        bottomNavigationView.getMenu().getItem(itemId).setChecked(true);
        getSupportActionBar().setTitle(bottomNavigationView.getMenu().getItem(itemId).getTitle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = findViewById(R.id.message);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragment = new ProgramacaoFragment();
        openFragment(fragment,0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        updateSearchViewFragment();
        return true;
    }

    public void updateSearchViewFragment(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            ProgramacaoListInterface p = (ProgramacaoListInterface) fragment;
            @Override
            public boolean onQueryTextSubmit(String query) {

                p.getProgramacaoAdapter().getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                p.getProgramacaoAdapter().getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}
