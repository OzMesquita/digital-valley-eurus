package com.example.encontrosuniversitarios.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.encontrosuniversitarios.ProgramacaoListInterface;
import com.example.encontrosuniversitarios.R;
import com.example.encontrosuniversitarios.helper.MySharedPreferences;
import com.example.encontrosuniversitarios.view.fragment.AtividadesAlunoFragment;
import com.example.encontrosuniversitarios.view.fragment.AtividadesProfessorFragment;
import com.example.encontrosuniversitarios.view.fragment.AvaliacaoAtividadeFragment;
import com.example.encontrosuniversitarios.view.fragment.CadastroUsuarioFragment;
import com.example.encontrosuniversitarios.view.fragment.CheckInCheckOutListener;
import com.example.encontrosuniversitarios.view.fragment.LoginFragment;
import com.example.encontrosuniversitarios.view.fragment.LogoutListener;
import com.example.encontrosuniversitarios.view.fragment.ProgramacaoDoDiaFragment;
import com.example.encontrosuniversitarios.view.fragment.ProgramacaoFragment;
import com.example.encontrosuniversitarios.view.fragment.RealizarFrequenciaFragment;
import com.example.encontrosuniversitarios.viewmodel.LoginViewModel;
import com.example.encontrosuniversitarios.viewmodel.RealizarFrequenciaViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getBaseContext());
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    break;
                case R.id.navigation_programacao_do_dia:
                    getSupportActionBar().setTitle(R.string.title_programacao_do_dia);
                    fragment = new ProgramacaoDoDiaFragment();
                    itemId = 1;
                    openFragment(fragment, itemId);
                    break;
                case R.id.navigation_frequencia:
                    MySharedPreferences preferences = MySharedPreferences.getInstance(getApplicationContext());
                    getSupportActionBar().setTitle(R.string.title_frequencia);
                    if (preferences.getUserId() != -1) {
                        if (preferences.getUserAccessLevel() == 0) {
                            getSupportActionBar().setTitle("Olá, "+ preferences.getUserName());
                            fragment = new AtividadesAlunoFragment();
                            itemId = 6;
                            openFragment(fragment, itemId - 4);

                        } else if (preferences.getUserAccessLevel() == 1) {
                            getSupportActionBar().setTitle("Olá, "+ preferences.getUserName());
                            fragment = new RealizarFrequenciaFragment();
                            itemId = 2;
                            openFragment(fragment, itemId);

                        } else if (preferences.getUserAccessLevel() == 2) {
                            getSupportActionBar().setTitle("Olá, "+ preferences.getUserName());
                            fragment = new AtividadesProfessorFragment();
                            itemId = 5;
                            openFragment(fragment, itemId -3);
                        }
                    } else {
                        fragment = new LoginFragment();
                        itemId = 3;
                        openFragment(fragment, itemId - 1);
                    }

                    break;
            }
            if (itemId == 0 || itemId == 1 || itemId == 2 || itemId == 5 || itemId == 6) {
                updateSearchViewFragment();
            }
            return true;
        }
    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MySharedPreferences preferences = MySharedPreferences.getInstance(getApplicationContext());
        MenuItem menuItem = menu.size() >= 1 ? menu.getItem(0) : null;
        if (menuItem != null) {
            if (preferences.getUserId() == -1) {
                menu.getItem(1).setVisible(false);
            } else {
                menu.getItem(1).setVisible(true);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        if (fragment instanceof ProgramacaoFragment
                || fragment instanceof ProgramacaoDoDiaFragment
                || fragment instanceof RealizarFrequenciaFragment
                || fragment instanceof AtividadesAlunoFragment
                || fragment instanceof AtividadesProfessorFragment) {
            updateSearchViewFragment();
        }
        return true;
    }

    public void updateSearchViewFragment() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            ProgramacaoListInterface anInterface = (ProgramacaoListInterface) fragment;

            @Override
            public boolean onQueryTextSubmit(String query) {
                anInterface.getProgramacaoAdapter().getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                anInterface.getProgramacaoAdapter().getFilter().filter(newText);
                return true;
            }
        });
    }

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
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.logout) {
            ViewModelProviders.of(this).get(LoginViewModel.class).realizarLogout(this, new LogoutListener() {
                @Override
                public void onSuccessfulLogout() {
                    bottomNavigationView.setSelectedItemId(R.id.navigation_programacao_do_dia);
                }

                @Override
                public void onFailure() {
                    Toast.makeText(getApplicationContext(), "Não foi possível fazer logout", Toast.LENGTH_LONG).show();
                }
            });
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String scannedUserCode = result.getContents();
                RealizarFrequenciaViewModel viewModel = ViewModelProviders.of(this).get(RealizarFrequenciaViewModel.class);
                viewModel.realizarCheckInCheckOut(new CheckInCheckOutListener() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCheckedInOnDifferentRoom(String message) {
                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onInvalidQRCode(String message) {
                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                    }
                }, scannedUserCode, getBaseContext());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
