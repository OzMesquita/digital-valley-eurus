package ufc.russas.encontrosuniversitarios.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ufc.russas.encontrosuniversitarios.ScheduleListInterface;
import ufc.russas.encontrosuniversitarios.R;
import ufc.russas.encontrosuniversitarios.helper.MySharedPreferences;
import ufc.russas.encontrosuniversitarios.helper.QRCodeHelper;
import ufc.russas.encontrosuniversitarios.model.Activity;
import ufc.russas.encontrosuniversitarios.view.adapter.TodaysScheduleAdapter;
import ufc.russas.encontrosuniversitarios.viewmodel.StudentAcivityViewModel;

import java.util.List;

<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesAlunoFragment.java
public class AtividadesAlunoFragment extends Fragment  implements ProgramacaoListInterface {
=======
public class StudentActivitiesFragment extends Fragment implements ScheduleListInterface {
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/StudentActivitiesFragment.java

    private TodaysScheduleAdapter todaysScheduleAdapter;
    private StudentAcivityViewModel studentAcivityViewModel;
    private RecyclerView recyclerView;
    private TextView txtUlmimaSalaCheckin;
    private ImageView info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividades_aluno, container, false);
        Button btnGenerateQRCode = view.findViewById(R.id.btnGenerateQRCode);
        studentAcivityViewModel = ViewModelProviders.of(this).get(StudentAcivityViewModel.class);
        recyclerView = view.findViewById(R.id.atividades_aluno);
        txtUlmimaSalaCheckin = view.findViewById(R.id.salaUltimoCheckin);
        info = view.findViewById(R.id.activity_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        final ProgressBar progressBar = view.findViewById(R.id.aluno_progress);

        btnGenerateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreferences p = MySharedPreferences.getInstance(getContext());
                QRCodeHelper qrCodeHelper = new QRCodeHelper(500,500);
                qrCodeHelper.generateUserQRCodeAlertDialog(getContext(),"EURUSSAS-"+p.getUserId()+"-"+p.getUserName());
            }
        });
        studentAcivityViewModel.getAtividades().observe(this, new Observer<List<Activity>>() {
            @Override
<<<<<<< Updated upstream:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/AtividadesAlunoFragment.java
            public void onChanged(List<Atividade> atividades) {
                programacaoDoDiaAdapter = new ProgramacaoDoDiaAdapter(atividades, null,false);
                recyclerView.setAdapter(programacaoDoDiaAdapter);
=======
            public void onChanged(List<Activity> activities) {
                todaysScheduleAdapter = new TodaysScheduleAdapter(activities, null, false);
                recyclerView.setAdapter(todaysScheduleAdapter);
>>>>>>> Stashed changes:EncontrosUniversitarios/app/src/main/java/ufc/russas/encontrosuniversitarios/view/fragment/StudentActivitiesFragment.java
            }
        });
        studentAcivityViewModel.carregarAtividades(getContext(), new ActivitiesListener() {
            @Override
            public void onLoading() {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onDone() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);

        return view;
    }

    private void showInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("As atividades listadas abaixo corresponderão apenas às que você assistiu por completo, porém todo seu tempo de permanência nas salas será contabilizado!");
        builder.setPositiveButton("Ok", null);
        builder.show();
    }


    @Override
    public Filterable getScheduleAdapter() {
        return todaysScheduleAdapter;
    }

}
