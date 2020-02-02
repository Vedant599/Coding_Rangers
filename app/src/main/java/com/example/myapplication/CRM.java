package com.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
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
    private LineChartView lineChartView;
    private ListView listView;
    String x[]=new String[50];
    float y[]=new float[50];
    int countx=0,county=0;
    List<String> al=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_crm, container, false);
        barChart=root.findViewById(R.id.bar_chart);
        pieChart=root.findViewById(R.id.pie_chart);
        lineChartView=root.findViewById(R.id.line_chart);
        listView=root.findViewById(R.id.list_item);
        for(int i=0;i<DatabaseAct.startList.size();i++)
        {
            x[i]=DatabaseAct.startList.get(i).getCompanyName();
        }for(int i=0;i<DatabaseAct.startList.size();i++)
        {
            y[i]=Float.parseFloat(DatabaseAct.startList.get(i).getNetWorth());
        }

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
                /*if(method.equals("bars"))
                {
                    List<BarEntry>  barEntries=new ArrayList<>();
                    for(int i=0;i<x.length;i++) {
                        barEntries.add(new BarEntry(Integer.parseInt(y[i]),i));
                    }for(int i=0;i<x.length;i++) {
                        al.add(x[i]);
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
                    ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,al);
                    listView.setAdapter(arrayAdapter);
                    listView.setVisibility(View.VISIBLE);

                }*/
                 if(method.equals("pie"))
                {
                    List<PieEntry> pieEntries=new ArrayList<>();
                    for(int i=0;i<x.length;i++) {
                        pieEntries.add(new PieEntry(y[i],x[i]));
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

                }if(method.equals("bars"))
                {
                    String[] axisData = {"AtherEnergy","Bijus","Unacademy","Haptik","InCred","mobikwik","instamojo","OlaCabs"};
                    int[] yAxisData = {4, 8, 16, 3,5,12,4,};
                    List yAxisValues = new ArrayList();
                    List axisValues = new ArrayList();


                    Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

                    for (int i = 0; i < axisData.length; i++) {
                        axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
                    }

                    for (int i = 0; i < yAxisData.length; i++) {
                        yAxisValues.add(new PointValue(i, yAxisData[i]));
                    }

                    List lines = new ArrayList();
                    lines.add(line);

                    LineChartData data = new LineChartData();
                    data.setLines(lines);

                    Axis axis = new Axis();
                    axis.setValues(axisValues);
                    axis.setTextSize(16);
                    axis.setTextColor(Color.parseColor("#03A9F4"));
                    data.setAxisXBottom(axis);

                    Axis yAxis = new Axis();
                    yAxis.setName("Sales in millions");
                    yAxis.setTextColor(Color.parseColor("#03A9F4"));
                    yAxis.setTextSize(16);
                    data.setAxisYLeft(yAxis);

                    lineChartView.setLineChartData(data);
                    Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
                    viewport.top = 110;
                    lineChartView.setMaximumViewport(viewport);
                    lineChartView.setCurrentViewport(viewport);
                    lineChartView.setVisibility(View.VISIBLE);
                }


            }


        });

    }

}
