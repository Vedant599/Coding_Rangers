package com.example.myapplication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CRM extends Fragment {


    public CRM() {
        // Required empty public constructor
    }

    private BarChart barChart;
    private PieChart pieChart;
    int x[]={2010,2011,2012,2013,2014,2015,2016,2017};
    int y[]={13,16,20,26,31,35,41,55};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_crm, container, false);
        barChart=root.findViewById(R.id.bar_chart);
        pieChart=root.findViewById(R.id.pie_chart);

        GetGrowthChart(getArguments().getString("method"));
        return root;
    }
    private void GetGrowthChart(final String method)
    {
        Call<List<Growth>> call= ApiClient.getApiClient().create(ApiInterface.class).getstartupgrowthinfo();
        call.enqueue(new Callback<List<Growth>>() {
            @Override
            public void onResponse(Call<List<Growth>> call, Response<List<Growth>> response) {

            }


            @Override
            public void onFailure(Call<List<Growth>> call, Throwable t) {
                if(method.equals("bars"))
                {
                    List<BarEntry>  barEntries=new ArrayList<>();
                    for(int i=0;i<x.length;i++) {
                        barEntries.add(new BarEntry(x[i],y[i]));
                    }
                    BarDataSet barDataSet=  new BarDataSet(barEntries,"Growth");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    BarData barData=new BarData(barDataSet);
                    barData.setBarWidth(0.9f);
                    barChart.setVisibility(View.VISIBLE);
                    barChart.animateY(5000);
                    barChart.setData(barData);
                    barChart.setFitBars(true);

                    Description description=new Description();
                    description.setText("Growth Rate Per Year");
                    barChart.setDescription(description);
                    barChart.invalidate();

                }
                else if(method.equals("pie"))
                {
                    List<PieEntry> pieEntries=new ArrayList<>();
                    for(int i=0;i<x.length;i++) {
                        pieEntries.add(new PieEntry(y[i], Integer.toString(x[i])));
                    }
                    pieChart.setVisibility(View.VISIBLE);
                    pieChart.animateXY(5000,5000);
                    PieDataSet pieDataSet =new PieDataSet(pieEntries,"Growth per Year");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);
                    Description description=new Description();
                    description.setText("Growth Year per Year");
                    pieChart.setDescription(description);
                     pieChart.invalidate();

                }

            }
        });
    }

}
