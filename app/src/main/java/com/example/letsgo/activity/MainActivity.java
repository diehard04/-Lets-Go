package com.example.letsgo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgo.R;
import com.example.letsgo.adapter.AutoCompleteSourceDestinationAdapter;
import com.example.letsgo.adapter.BusStopsAdapter;
import com.example.letsgo.internet.model.BusStopModel;
import com.example.letsgo.internet.model.SourceDestinationModel;
import com.example.letsgo.internet.model.BusStopsResult;
import com.example.letsgo.internet.model.SourceDestinationResult;
import com.example.letsgo.internet.model.SourceDestPostModel;
import com.example.letsgo.internet.presenter.DataManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataManager.StopList, DataManager.ResultCallBack {

    private AutoCompleteTextView sourceEt;
    private AutoCompleteTextView destinationEt;
    private Button searchBtn;
    private List<SourceDestinationModel> sourceDestinationModelList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getName();
    private String mSourceId;
    private String mDestinationId;
    private ProgressBar loadingPB;
    private List<BusStopModel> busStopModelList = new ArrayList<>();
    private RecyclerView busStopListRv;
    private BusStopsAdapter mBusStopsAdapter;
    private TextView searchResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadingPB.setVisibility(View.VISIBLE);
        DataManager.getSourceDestinationStopsData(MainActivity.this);
    }

    private void initView() {
        sourceEt = findViewById(R.id.sourceEt);
        destinationEt = findViewById(R.id.destinationEt);
        searchBtn = findViewById(R.id.searchBtn);
        loadingPB = findViewById(R.id.idLoadingPB);
        searchResultTv = findViewById(R.id.searchResultTv);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSourceId == null) {
                    Toast.makeText(getApplicationContext(), "Please select valid Source point", Toast.LENGTH_SHORT).show();
                } else if (mDestinationId == null) {
                    Toast.makeText(getApplicationContext(), "Please select valid Destination point", Toast.LENGTH_SHORT).show();
                } else {
                    callSearch();
                }
            }
        });
        busStopListRv = findViewById(R.id.busStopListRv);
        busStopListRv.setHasFixedSize(true);
        busStopListRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void callSearch() {
        SourceDestPostModel sourceDestPostModel = new SourceDestPostModel();
        sourceDestPostModel.setSource(mSourceId);
        sourceDestPostModel.setDest(mDestinationId);
        loadingPB.setVisibility(View.VISIBLE);
        DataManager.getBusList(MainActivity.this, sourceDestPostModel);
    }

    private void updateDestinationAdapter() {
        AutoCompleteSourceDestinationAdapter destinationAdapter = new AutoCompleteSourceDestinationAdapter(MainActivity.this, sourceDestinationModelList);
        destinationEt.setAdapter(destinationAdapter);
        destinationEt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDestinationId = sourceDestinationModelList.get(position).getIdstop();
                Toast.makeText(MainActivity.this, "destinationEt " + sourceDestinationModelList.get(position).getIdstop(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSourceAdapter() {
        AutoCompleteSourceDestinationAdapter destinationAdapter = new AutoCompleteSourceDestinationAdapter(MainActivity.this, sourceDestinationModelList);
        sourceEt.setAdapter(destinationAdapter);
        sourceEt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSourceId = sourceDestinationModelList.get(position).getIdstop();
                Toast.makeText(MainActivity.this, "sourceEt " + sourceDestinationModelList.get(position).getIdstop(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccess(SourceDestinationResult sourceDestinationResult) {
        loadingPB.setVisibility(View.GONE);
        Log.d(TAG, " results " + sourceDestinationResult);
        sourceDestinationModelList.clear();
        sourceDestinationModelList = sourceDestinationResult.getBusStopsModel();
        Log.d(TAG, " bodies " + sourceDestinationModelList.size());
        updateSourceAdapter();
        updateDestinationAdapter();
    }

    @Override
    public void onSuccess(BusStopsResult busStopsResult) {
        loadingPB.setVisibility(View.GONE);
        busStopModelList = busStopsResult.getData();
        if (busStopModelList != null && busStopModelList.size() !=0) {
            Log.d(TAG, " onSuccess " + busStopModelList);
            searchResultTv.setVisibility(View.VISIBLE);
            mBusStopsAdapter = new BusStopsAdapter(MainActivity.this, busStopModelList);
            busStopListRv.setAdapter(mBusStopsAdapter);
        } else {
            Toast.makeText(MainActivity.this, " No data available ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(String onFailure) {
        searchResultTv.setVisibility(View.GONE);
        loadingPB.setVisibility(View.GONE);
        Log.d(TAG, " onFailure " + sourceDestinationModelList);
        Toast.makeText(MainActivity.this, "stop list API error ", Toast.LENGTH_SHORT).show();
    }
}