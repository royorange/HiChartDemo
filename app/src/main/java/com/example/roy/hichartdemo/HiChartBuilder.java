package com.example.roy.hichartdemo;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import com.example.roy.hichartdemo.model.Axis;
import com.example.roy.hichartdemo.model.DataChartCurve;
import com.example.roy.hichartdemo.model.DataGraphChart;
import com.example.roy.hichartdemo.model.DataGraphSeriesData;
import com.highsoft.highcharts.common.HIColor;
import com.highsoft.highcharts.common.hichartsclasses.HICSSObject;
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HICredits;
import com.highsoft.highcharts.common.hichartsclasses.HIDataLabels;
import com.highsoft.highcharts.common.hichartsclasses.HIDateTimeLabelFormats;
import com.highsoft.highcharts.common.hichartsclasses.HIExporting;
import com.highsoft.highcharts.common.hichartsclasses.HILabels;
import com.highsoft.highcharts.common.hichartsclasses.HILegend;
import com.highsoft.highcharts.common.hichartsclasses.HIMonth;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotOptions;
import com.highsoft.highcharts.common.hichartsclasses.HISeries;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HITooltip;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roy on 2019/1/22
 */
public class HiChartBuilder {
    private DataGraphChart data;

    public HiChartBuilder setData(DataGraphChart data){
        this.data = data;
        return this;
    }

    /**
     * 创建图表参数
     * @param context
     * @return
     */
    public HIOptions build(Context context){
        return build(context,true);
    }

    public HIOptions build(Context context , boolean isBuildSeries){
        HIOptions options = new HIOptions();
        options.setChart(buildChart());
        options.setYAxis(buildYAxis());
        options.setXAxis(buildXAxis());
        if(isBuildSeries){
            options.setSeries(buildDataSeries());
        }
        buildOptions(options,context);
        data = null;
        return options;
    }

    /**
     * 设置x轴
     * @return
     */
    private ArrayList<HIXAxis> buildXAxis(){
        ArrayList<HIXAxis> list = new ArrayList<>();
        HIXAxis axis = new HIXAxis();
        //固定时间类型
        axis.setType("datetime");
        HIDateTimeLabelFormats formats = new HIDateTimeLabelFormats();
        HIMonth month = new HIMonth();
        month.setMain("%Y/%m");
        formats.setMonth(month);
        axis.setDateTimeLabelFormats(formats);
        list.add(axis);
        return list;
    }

    /**
     * 设置y轴
     * @return
     */
    private ArrayList<HIYAxis> buildYAxis(){
        ArrayList<HIYAxis> list = new ArrayList<>();
        for (Axis y:data.getYAxises()){
            HIYAxis yAxis = new HIYAxis();
            //title
            HITitle title = new HITitle();
            title.setText("");
            title.setVerticalAlign("top");
            title.setAlign("high");
            title.setRotation(0);
            title.setTextAlign(y.isOpposite()?"right":"left");
            yAxis.setTitle(title);
            //label
            HILabels label = new HILabels();
            HICSSObject hicssObject = new HICSSObject();
            hicssObject.setColor(y.getColor());
            label.setStyle(hicssObject);
            yAxis.setLabels(label);
            yAxis.setOpposite(y.isOpposite());
            list.add(yAxis);
        }
        return list;
    }

    /**
     * 设置图表信息
     * @return
     */
    private HIChart buildChart(){
        HIChart chart = new HIChart();
        chart.setSpacingTop(data.getCurves().size()*18+(data.getCurves().size()-2)*2);
        chart.setZoomType("xy");
        return chart;
    }

    /**
     * 设置数据
     * @return
     */
    private ArrayList<HISeries> buildDataSeries(){
        ArrayList<HISeries> list = new ArrayList<>();
        for (DataChartCurve curve:data.getCurves()){
            HISeries series = new HISeries();
            series.setType(curve.getDisplayType());
            series.setName(curve.getTitle() + "(" + curve.getUnits()+")");
            series.setYAxis(curve.getYAxis());
            series.setColor(HIColor.initWithName(curve.getColor()));
            //当使用datetime时，数据为二维数组
            Number[][] seriesData = new Number[curve.getDataRows().size()][2];
            for(int index=0;index<curve.getDataRows().size();index++){
                DataGraphSeriesData data = curve.getDataRows().get(index);
                seriesData[index][0] = data.getTime()*1000;
                seriesData[index][1] = data.getValue();
            }
            series.setData(new ArrayList<>(Arrays.asList(seriesData)));
            list.add(series);
        }
        return list;
    }

    public ArrayList<HISeries> buildDataSeries(List<DataChartCurve> curves){
        ArrayList<HISeries> list = new ArrayList<>();
        for (DataChartCurve curve:curves){
            HISeries series = new HISeries();
            series.setType(curve.getDisplayType());
            series.setName(curve.getTitle() + "(" + curve.getUnits()+")");
            series.setYAxis(curve.getYAxis());
            series.setColor(HIColor.initWithName(curve.getColor()));
            HIDataLabels hiDataLabels = new HIDataLabels();
            hiDataLabels.setEnabled(false);
            series.setDataLabels(hiDataLabels);
            series.setEnableMouseTracking(true);
            //当使用datetime时，数据为二维数组
            Number[][] seriesData = new Number[curve.getDataRows().size()][2];
            for(int index=0;index<curve.getDataRows().size();index++){
                DataGraphSeriesData data = curve.getDataRows().get(index);
                seriesData[index][0] = data.getTime()*1000;
                seriesData[index][1] = data.getValue();
            }
            series.setData(new ArrayList<>(Arrays.asList(seriesData)));
            list.add(series);
        }
        return list;
    }

    /**
     * 设置option内其他参数
     * @param options
     * @param context
     * @return
     */
    private HIOptions buildOptions(HIOptions options, Context context){
        //标题不直接在chart上显示
        HITitle title = new HITitle();
        title.setText("  ");
        options.setTitle(title);

        //设置可单指滚动
        //https://github.com/highcharts/highcharts-android/issues/47
        HITooltip tooltip = new HITooltip();
        tooltip.setShared(true);
        tooltip.setFollowTouchMove(true);
        //%A:星期几，%b:月份
        tooltip.setHeaderFormat("{point.key:%Y/%m/%d}<br/>");
        options.setTooltip(tooltip);
        //设置右下角水印
        HIExporting exporting = new HIExporting();
        exporting.setEnabled(false);
        options.setExporting(exporting);
        HICredits credits = new HICredits();
        credits.setHref("");
        credits.setText(context.getString(R.string.app_name));
        options.setCredits(credits);

        //设置图例
        HILegend legend = new HILegend();
        legend.setLayout("vertical");
        legend.setAlign("left");
        legend.setVerticalAlign("top");
        legend.setX(dpToPx(8));
        //修正图例位置，基于曲线的个数
        legend.setY(-data.getCurves().size()*18+(data.getCurves().size()-2)*2-3);
        legend.setFloating(true);
        options.setLegend(legend);

        //设置图表属性
        HIPlotOptions plotOptions = new HIPlotOptions();
        HISeries hiSeries = new HISeries();
        HIDataLabels hiDataLabels = new HIDataLabels();
        hiDataLabels.setEnabled(false);
        hiSeries.setDataLabels(hiDataLabels);
        hiSeries.setEnableMouseTracking(true);
        plotOptions.setSeries(hiSeries);
        options.setPlotOptions(plotOptions);
        return options;
    }

    public HIOptions buildDefaultOption(Context context){
        HIOptions options = new HIOptions();
        HITitle title = new HITitle();
        title.setText("  ");
        options.setTitle(title);
        //设置右下角水印
        HIExporting exporting = new HIExporting();
        exporting.setEnabled(false);
        options.setExporting(exporting);
        HICredits credits = new HICredits();
        credits.setHref("");
        credits.setText(context.getString(R.string.app_name));
        options.setCredits(credits);
        return options;
    }

    /**
     *
     * @param title
     * @param options
     * @param adjustLegend 是否调整图例位置
     * @return
     */
    public HIOptions updateTitle(String title, HIOptions options, boolean adjustLegend){
        HITitle hiTitle = new HITitle();
        hiTitle.setText(title);
        HICSSObject hicssObject = new HICSSObject();
        hicssObject.setColor("#E6B977");
        hiTitle.setStyle(hicssObject);
        options.setTitle(hiTitle);
        if(adjustLegend){
            if(TextUtils.isEmpty(title.trim())){
                HIChart chart = new HIChart();
                chart.setSpacingTop(data.getCurves().size()*18+(data.getCurves().size()-2)*2);
                chart.setZoomType("xy");
                options.setChart(chart);
                HILegend legend = new HILegend();
                legend.setLayout("vertical");
                legend.setAlign("left");
                legend.setVerticalAlign("top");
                legend.setX(dpToPx(10));
                legend.setY(-data.getCurves().size()*18+(data.getCurves().size()-2)*2);
                legend.setFloating(true);
                options.setLegend(legend);
            }else {
                HIChart chart = new HIChart();
                chart.setSpacingTop(0);
                chart.setZoomType("xy");
                options.setChart(chart);
                HILegend legend = new HILegend();
                legend.setLayout("vertical");
                legend.setAlign("left");
                legend.setVerticalAlign("top");
                legend.setX(dpToPx(10));
                legend.setY(-5);
                legend.setFloating(true);
                options.setLegend(legend);
            }
        }
        return options;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
