package com.example.tasks.committee10_2018;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasks.committee10_2018.app.App;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.syscod;
import com.example.tasks.committee10_2018.presenter.CasesPresenter;
import com.example.tasks.committee10_2018.repositories.imp.CasesRepositoryImp;
import com.example.tasks.committee10_2018.utils.PrefUtils;
import com.example.tasks.committee10_2018.utils.Utils;
import com.example.tasks.committee10_2018.view.CaseInterface;
import com.example.tasks.committee10_2018.view.CaseTabs;
import com.example.tasks.committee10_2018.view.CasesAdapter;
import com.example.tasks.committee10_2018.view.LoginActivity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CaseInterface{


    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.search_filter)
    CardView searchFilter;
    @BindView(R.id.logout)
    ImageButton logout;
    @BindView(R.id.action_filter)
    ImageButton filter;
    @BindView(R.id.search)
    ImageButton search;
    @BindView(R.id.searchTV)
    EditText searchET;
    @BindView(R.id.bar_text)
    TextView bar_text;
    @BindView(R.id.CaseRecyclerView)
    RecyclerView casesRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.emptyCaseList)
    TextView emptyCaseList;
    @BindView(R.id.issue_state)
    Spinner statusSpinner;
    @BindView(R.id.checkBox)
    CheckBox finish;
    @BindView(R.id.search_button)
    Button searchButton;
    CasesPresenter presenter;
    CaseInterface caseInterface;
    stpv_case[] cases;
    String[] statuesValue;
    Integer[] statuesID;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        App.setContext(this);
        ButterKnife.bind(this);

        bar_text.setText("قائمة القضايا");

        progressDialog= new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("يتم تحميل القضايا...");
        progressDialog.show();

        caseInterface = this;
        presenter = new CasesPresenter(new CasesRepositoryImp(), caseInterface);
        presenter.casesRequest();
        presenter.StatusRequest();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.casesRequest();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.clear();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchET.getVisibility() != View.VISIBLE) {
                    searchET.setVisibility(View.VISIBLE);
                    bar_text.setVisibility(View.GONE);
                }else {
                    String searchText= searchET.getText().toString();
                    searchET.setVisibility(View.GONE);
                    bar_text.setVisibility(View.VISIBLE);
                    if(searchText != null){
                        presenter.caseRequest(searchText);
                    }
                }
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchFilter.getVisibility() != View.VISIBLE) {
                    searchFilter.setVisibility(View.VISIBLE);
                }else {
                    searchFilter.setVisibility(View.GONE);
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int statuesSearch;
                String finished;
                int selected_position = statusSpinner.getSelectedItemPosition();
                if(selected_position == 0){
                    statuesSearch = -1;
                }else{
                    statuesSearch = statuesID[selected_position];
                }

                if (finish.isChecked()) {
                    finished = "1";
                } else {
                    finished  = "0";
                }
                presenter.getCasesRequest(finished, String.valueOf(statuesSearch));
            }
        });


    }

    @Override
    public void getCases(stpv_case[] cases) {
        progressDialog.dismiss();
        this.cases = cases;
        emptyCaseList.setVisibility(View.GONE);
        casesRecyclerView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        casesRecyclerView.setLayoutManager(layoutManager);
        CasesAdapter adapter = new CasesAdapter(cases);
        casesRecyclerView.setAdapter(adapter);
        onItemsLoadedComplete();
    }

    @Override
    public void noCases(String message) {
        progressDialog.dismiss();

        casesRecyclerView.setVisibility(View.GONE);
        emptyCaseList.setVisibility(View.VISIBLE);
        if (!Utils.isOnline(this)){
            emptyCaseList.setText("الجهاز غير متصل بالانترنت لن يتم تحميل المعاملات برجاء الاتصال بالانترنت واعادة تحميل الصفحة...");
        }else {
            emptyCaseList.setText(message);
        }
        onItemsLoadedComplete();
    }

    @Override
    public void getCase(stpv_case cases) {
        progressDialog.dismiss();
        if(cases == null){
            casesRecyclerView.setVisibility(View.GONE);
            emptyCaseList.setVisibility(View.VISIBLE);
            emptyCaseList.setText("لا يوجد قضية متاحه الان بهذا الرقم...");
        }
        Intent intent = new Intent(MainActivity.this, CaseTabs.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("case", cases);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void noCase(String message) {
        progressDialog.dismiss();
        casesRecyclerView.setVisibility(View.GONE);
        emptyCaseList.setVisibility(View.VISIBLE);
        if (!Utils.isOnline(this)){
            emptyCaseList.setText("الجهاز غير متصل بالانترنت لن يتم تحميل المعاله برجاء الاتصال بالانترنت واعادة تحميل الصفحة...");
        }else {
            emptyCaseList.setText(message);
        }
        onItemsLoadedComplete();
    }

    @Override
    public void status(syscod[] status){
        statuesValue = new String[status.length + 1];
        statuesID = new Integer[status.length + 1];
        statuesValue[0] = "";
        statuesID[0] = 0;
        for(int i=0 ; i<status.length ; i++){
            statuesValue[i+1] = status[i].LOCDESC;
            statuesID[i+1] = status[i].SUBCOD;
        }

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(App.getContext(), android.R.layout.simple_spinner_item, statuesValue);
        statusSpinner.setAdapter(spinnerArrayAdapter);
    }


    private void onItemsLoadedComplete() {
        swipeRefresh.setRefreshing(false);
    }

}
