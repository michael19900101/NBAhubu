package com.aotuman.nbahubu.ui.player;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.aotuman.nbahubu.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class RadarChartActivity extends DemoBase {

    private RadarChart chart;
    float[] yValues = new float[]{3.8f, 1.3f, 0.5f, 1.0f, 3.0f};
    float[] yMaxValues = new float[]{35.7f, 11.8f, 3.0f, 2.8f, 13.7f};
    float[] yChartValues = new float[5];

    private void calActualYValues() {
        for (int i = 0; i < 5; i++) {
            float actualValue = yValues[i] / yMaxValues[i];
            BigDecimal bd = new BigDecimal(actualValue).setScale(1, RoundingMode.HALF_UP);
            yChartValues[i] = bd.floatValue();
//            yChartValues[i] = Math.round(yValues[i]/yMaxValues[i]*10)/10;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calActualYValues();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radarchart);

        setTitle("RadarChartActivity");

        chart = findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.parseColor("#E6F2FE")); // 五条内边线颜色
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.parseColor("#C6E1FC"));
        chart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart

        setData();

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(14f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            private final String[] mActivities = new String[]{"得分", "助攻", "封盖", "抢断", "篮板"};

            @Override
            public String getFormattedValue(float value) {
                return mActivities[(int) value % mActivities.length];
            }

        });
        xAxis.setTextColor(Color.parseColor("#11202B"));

        YAxis yAxis = chart.getYAxis();
        yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(14f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(1f);
        yAxis.setDrawLabels(false);
        yAxis.setTextColor(Color.parseColor("#11202B"));

        // 隐藏图例
        chart.getLegend().setEnabled(false);
    }

    private void setData() {
        int cnt = 5;
        ArrayList<RadarEntry> entries1 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            entries1.add(new RadarEntry(yChartValues[i], yValues[i]));

        }

        RadarDataSet set1 = new RadarDataSet(entries1, "各项指标场均水平");
        set1.setColor(Color.parseColor("#DBEAFE"));
        set1.setFillColor(Color.parseColor("#5AA7F9"));
//        int startColor = Color.parseColor("#E6F2FE");
//        int endColor = Color.parseColor("#A6D0Fa");
//        set1.setGradientColor(startColor, endColor);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(1f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);
        set1.setValueTextColor(Color.BLACK);
        set1.setValueFormatter(new ValueFormatter() {

            @Override
            public String getRadarLabel(RadarEntry radarEntry) {
                return String.valueOf(radarEntry.getData());
            }
        });

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTypeface(tfLight);
        data.setValueTextSize(10f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.parseColor("#11202B"));

        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/RadarChartActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : chart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                chart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (chart.getData() != null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionToggleRotate: {
                if (chart.isRotationEnabled())
                    chart.setRotationEnabled(false);
                else
                    chart.setRotationEnabled(true);
                chart.invalidate();
                break;
            }
            case R.id.actionToggleFilled: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) chart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleHighlightCircle: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) chart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    set.setDrawHighlightCircleEnabled(!set.isDrawHighlightCircleEnabled());
                }
                chart.invalidate();
                break;
            }
            case R.id.actionToggleXLabels: {
                chart.getXAxis().setEnabled(!chart.getXAxis().isEnabled());
                chart.notifyDataSetChanged();
                chart.invalidate();
                break;
            }
            case R.id.actionToggleYLabels: {

                chart.getYAxis().setEnabled(!chart.getYAxis().isEnabled());
                chart.invalidate();
                break;
            }
            case R.id.animateX: {
                chart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                chart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                chart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                chart.spin(2000, chart.getRotationAngle(), chart.getRotationAngle() + 360, Easing.EaseInOutCubic);
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "RadarChartActivity");
    }
}