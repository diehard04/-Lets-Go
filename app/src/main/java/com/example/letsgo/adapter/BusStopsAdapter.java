package com.example.letsgo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgo.R;
import com.example.letsgo.internet.model.BusStopModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dipak Kumar Mehta on 14/06/22.
 */
public class BusStopsAdapter extends RecyclerView.Adapter<BusStopsAdapter.MyViewHolder> {

    private List<BusStopModel> busStopModelList = new ArrayList<>();
    private Context mContext;

    public BusStopsAdapter(Context context, List<BusStopModel> busStopModelList) {
        this.busStopModelList = busStopModelList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bus_stops_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BusStopModel model = busStopModelList.get(position);
        holder.firstStops.setText("A: " + model.getFirststop());
        holder.lastStops.setText("B: " + model.getLaststop());
        holder.busNoTv.setText(model.getBusno());
    }

    @Override
    public int getItemCount() {
        return busStopModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView stopsCount, firstStops, busNoTv, lastStops;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stopsCount = itemView.findViewById(R.id.stopsCount);
            firstStops = itemView.findViewById(R.id.firstStops);
            busNoTv = itemView.findViewById(R.id.busNoTv);
            lastStops = itemView.findViewById(R.id.lastStops);
        }
    }

    public interface BusClickListener {

    }
}
