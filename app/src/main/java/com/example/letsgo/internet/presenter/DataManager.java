package com.example.letsgo.internet.presenter;

import android.content.Context;
import android.util.Log;

import com.example.letsgo.internet.RetrofitClient;
import com.example.letsgo.internet.model.BusStopsResult;
import com.example.letsgo.internet.model.SourceDestinationResult;
import com.example.letsgo.internet.model.SourceDestPostModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dipak Kumar Mehta on 6/13/2022.
 */
public class DataManager {

    private static final String TAG = DataManager.class.getName();

    //https://run.mocky.io/v3/dad8803e-3ac3-4354-ba0b-d4114f800d0d
    public static void getSourceDestinationStopsData(Context context) {
        StopList stopList = (StopList) context;
        Call<SourceDestinationResult> call = RetrofitClient.getInstance().getMyApi().getSourceDestination();
        call.enqueue(new Callback<SourceDestinationResult>() {
            @Override
            public void onResponse(Call<SourceDestinationResult> call, Response<SourceDestinationResult> response) {
                SourceDestinationResult sourceDestinationResult = response.body();
               stopList.onSuccess(sourceDestinationResult);
            }

            @Override
            public void onFailure(Call<SourceDestinationResult> call, Throwable t) {
                Log.d(TAG, " onFailure " + t.getMessage());
                if (t != null) {
                    stopList.onFailure(t.getMessage());
                }
            }
        });
    }

    public interface StopList {
        void onSuccess(SourceDestinationResult sourceDestinationResult);
        void onFailure(String onFailure);
    }

    public static void getBusList(Context context, SourceDestPostModel sourceDestPostModel) {
        ResultCallBack resultCallBack = (ResultCallBack) context;
        Call<BusStopsResult> call = RetrofitClient.getInstance().getMyApi().getBusList(sourceDestPostModel);
        call.enqueue(new Callback<BusStopsResult>() {
            @Override
            public void onResponse(Call<BusStopsResult> call, Response<BusStopsResult> response) {
                Log.d(TAG,  " onResponse " + response.toString());
                BusStopsResult re = response.body();
                resultCallBack.onSuccess(re);
            }

            @Override
            public void onFailure(Call<BusStopsResult> call, Throwable t) {
                Log.d(TAG, " onFailure " + t.getMessage());
                resultCallBack.onFailure(t.getMessage());
            }
        });
    }

    public interface ResultCallBack {
        void onSuccess(BusStopsResult busStopsResult);
        void onFailure(String onFailure);
    }
}
