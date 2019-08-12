package com.example.encontrosuniversitarios.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
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
    private boolean noReplaceFragment;
    private static final String FRAGMENT_HOJE = "FRAGMENT_HOJE";
    private static final String FRAGMENT_PROGRAMACAO = "FRAGMENT_PROGRAMACAO";
    private static final String FRAGMENT_FREQUENCIA = "FRAGMENT_FREQUENCIA";
    private FrameLayout flContainerForFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);
        mTextMessage = findViewById(R.id.message);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        flContainerForFragment = (FrameLayout) findViewById(R.id.fragment_container);
        fragment = new ProgramacaoDoDiaFragment();
        //managerFragment(fragment, FRAGMENT_HOJE);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(false);
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
                    if (!noReplaceFragment) {//Frag será true apenas caso o usuario aperte o botao volta do device. Serve Apenas para mudar a cor do icon
                        getSupportActionBar().setTitle(R.string.title_programacao);
                        ProgramacaoFragment fragment = new ProgramacaoFragment();
                        itemId = 0;
                        openFragment(fragment, itemId);
                        //managerFragment(fragment, FRAGMENT_PROGRAMACAO);
                    }
                    return true;
                case R.id.navigation_programacao_do_dia:
                    if (!noReplaceFragment) {
                        getSupportActionBar().setTitle(R.string.title_programacao_do_dia);
                        ProgramacaoDoDiaFragment fragment = new ProgramacaoDoDiaFragment();
                        itemId = 1;
                        //managerFragment(fragment, FRAGMENT_HOJE);
                        openFragment(fragment, itemId);
                    }
                    return true;
                case R.id.navigation_frequencia:
                    if (!noReplaceFragment) {
//                   getSupportActionBar().hide();
//                        fragment = new RealizarFrequenciaFragment();
                    fragment = new LoginFragment();
//                        CadastroUsuarioFragment fragment = new CadastroUsuarioFragment();
                        itemId = 2;
                        //managerFragment(fragment, FRAGMENT_FREQUENCIA);

                        openFragment(fragment, itemId);
                    }
                    return true;
            }

//            openFragment(fragment, itemId);
            if (itemId == 0 || itemId == 1) {
                updateSearchViewFragment();
            }
            return false;
        }
    };

    private void openFragment(Fragment fragment, int itemId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        bottomNavigationView.getMenu().getItem(itemId).setChecked(true);

        //getSupportActionBar().setTitle(bottomNavigationView.getMenu().getItem(itemId).getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
//
//    @Override
//    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment mfragmentA = fragmentManager.findFragmentById(0);
//        Fragment mfragmentB = fragmentManager.findFragmentById(1);
//        Fragment mfragmentC = fragmentManager.findFragmentById(2);
//        if (mfragmentA != null || mfragmentB != null || mfragmentC != null) {
//            if (mfragmentA.isVisible()) {
//                finish();
//                Log.i("TAG", "tathiane" );
////                return;
//            }
//            if (mfragmentB.isVisible()) {
//                finish();
//                return;
//            }
//            if (mfragmentC.isVisible()) {
//                finish();
//                return;
//            }
//        }
//        super.onBackPressed();
////        managerIconsOfBottomNavigation(fragmentManager);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
//        switch (item.getItemId()) {
//            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
//                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
//                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
//                onBackPressed();
//                break;
//            default:break;
//        }
//        return true;
//    }

    @Override
    public void onBackPressed() { //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem

        return;
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

    private void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
