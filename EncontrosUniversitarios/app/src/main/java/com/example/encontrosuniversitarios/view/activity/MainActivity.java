package com.example.encontrosuniversitarios.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.model.Usuario;
import com.example.encontrosuniversitarios.view.fragment.CadastroUsuarioFragment;
import com.example.encontrosuniversitarios.view.fragment.LoginFragment;
import com.example.encontrosuniversitarios.view.fragment.ProgramacaoDoDiaFragment;
import com.example.encontrosuniversitarios.view.fragment.ProgramacaoFragment;
import com.example.encontrosuniversitarios.view.fragment.RealizarFrequenciaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private static final String FRAGMENT_HOJE = "FRAGMENT_HOJE";
    private static final String FRAGMENT_PROGRAMACAO = "FRAGMENT_PROGRAMACAO";
    private static final String FRAGMENT_FREQUENCIA = "FRAGMENT_FREQUENCIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);
        mTextMessage = findViewById(R.id.message);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        MySharedPreferences.getInstance(getApplicationContext()).setUserData(new Usuario(-1,null,-1));
        fragment = new ProgramacaoDoDiaFragment();

        getSupportActionBar().setTitle(R.string.title_programacao_do_dia);
        openFragment(fragment, 1);
    }

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
                        openFragment(fragment, itemId);
                    return true;
                case R.id.navigation_programacao_do_dia:
                        getSupportActionBar().setTitle(R.string.title_programacao_do_dia);
                        fragment = new ProgramacaoDoDiaFragment();
                        itemId = 1;
                        openFragment(fragment, itemId);
                    return true;
                case R.id.navigation_frequencia:
                        MySharedPreferences preferences = MySharedPreferences.getInstance(getApplicationContext());
                        if(preferences.getUserId()!= -1){
                            fragment = new RealizarFrequenciaFragment();
                        }else{
                            fragment = new LoginFragment();
                        }
                        itemId = 2;

                        openFragment(fragment, itemId);
                    return true;
            }

            if (itemId == 0 || itemId == 1) {
                updateSearchViewFragment();
            }
            return false;
        }
    };

    private void openFragment(Fragment fragment, int itemId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        bottomNavigationView.getMenu().getItem(itemId).setChecked(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        updateSearchViewFragment();
        return true;
    }

    public void updateSearchViewFragment() {
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


}
