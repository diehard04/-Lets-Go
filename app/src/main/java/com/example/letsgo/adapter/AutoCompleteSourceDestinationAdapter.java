package com.example.letsgo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.letsgo.R;
import com.example.letsgo.internet.model.SourceDestinationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dipak Kumar Mehta on 6/13/2022.
 */
public class AutoCompleteSourceDestinationAdapter extends ArrayAdapter<SourceDestinationModel> {
    private List<SourceDestinationModel> stopListFull;

    public AutoCompleteSourceDestinationAdapter(@NonNull Context context, @NonNull List<SourceDestinationModel> stopsList) {
        super(context, 0, stopsList);
        stopListFull = new ArrayList<>(stopsList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return stopsListFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.auto_complete_row, parent, false);
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        SourceDestinationModel countryItem = getItem(position);

        if (countryItem != null) {
            textViewName.setText(countryItem.getStopname());
        }
        return convertView;
    }

    private Filter stopsListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<SourceDestinationModel> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(stopListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SourceDestinationModel item : stopListFull) {
                    if (item.getStopname().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((SourceDestinationModel) resultValue).getStopname();
        }
    };
}