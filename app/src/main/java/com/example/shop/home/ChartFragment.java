package com.example.shop.home;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shop.R;
import com.example.shop.adapter.MyProductAdapter;
import com.example.shop.apiserver.ProductApiService;
import com.example.shop.enrty.Commodity;
import com.example.shop.enrty.Time;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends SupportFragment {

    LineChart lineChart;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        lineChart = view.findViewById(R.id.line_chart);
        queryAllCommodity();
        return view;
    }


    public void lineChart() {
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);  //图例窗体的形状  目前是一条线 ，还有 圆圈 方块等形状
        legend.setTextSize(20f);  //图例文字的大小
        legend.setTextColor(Color.BLUE); //设置图例文字颜色
        legend.setFormSize(10);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //图例说明文字在图表的上方
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); //图例左居中
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例的方向为水平
        legend.setDrawInside(false);//绘制在chart的外侧
        //XY轴的设置
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴设置显示位置在底部
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签
        xAxis.setTextColor(Color.BLUE);//设置X轴字体颜色
        xAxis.setTextSize(10f);  //设置X轴字体大小
        xAxis.setDrawGridLines(false); //不绘制X轴网格，默认为绘制。

        //y轴设置
        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        leftAxis.setAxisMinimum(0f); //保证Y轴从0开始，不然会上移一点
        rightAxis.setAxisMinimum(0f);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ((int) value) + "￥";
            }
        });
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ((int) value) + "￥";
            }
        });
        /*LimitLine limitLine = new LimitLine(1000,"超过平均"); //得到限制线
        LimitLine line = new LimitLine(500,"低于平均");
        line.setLineWidth(4f); //宽度
        line.setTextSize(10f);
        line.setTextColor(Color.BLUE);  //颜色
        line.setLineColor(Color.RED);
        leftAxis.addLimitLine(line);
        limitLine.setLineWidth(4f); //宽度
        limitLine.setTextSize(10f);
        limitLine.setTextColor(Color.BLUE);  //颜色
        limitLine.setLineColor(Color.RED);
        leftAxis.addLimitLine(limitLine);*/
        lineChart.animateY(8000);


    }


    public void queryAllCommodity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //商品
        ProductApiService productApiService = retrofit.create(ProductApiService.class);

        Call<Commodity> itemCall = productApiService.queryAllCommodity();

        itemCall.enqueue(new Callback<Commodity>() {
            @Override
            public void onResponse(Call<Commodity> call, Response<Commodity> response) {

                //商品
                Commodity i = response.body();
                List<Entry> entries = new ArrayList<>();
                    lineChart();
                    for (int j = 0; j < 20; j++) {
                        entries.add(new Entry(j, i.getCommodity().get(j).getPrice()));
                    }
                        LineDataSet lineDataSet = new LineDataSet(entries, "商品ID价格表");
                        LineData data = new LineData(lineDataSet);
                        lineDataSet.setValueTextSize(10);
                        lineDataSet.setColor(Color.parseColor("#1E90FF"));//线条颜色
                        lineDataSet.setCircleColor(Color.parseColor("#1E90FF"));//圆点颜色
                        lineDataSet.setLineWidth(2f);//线条宽度
                        lineChart.setData(data);
                        lineChart.invalidate();
                        lineDataSet.setDrawFilled(true);
                        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
                        lineDataSet.setValueTextSize(10f);
                        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);


            }

            @Override
            public void onFailure(Call<Commodity> call, Throwable t) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

