package com.example.roy.hichartdemo;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roy.hichartdemo.model.BindingViewHolder;
import com.example.roy.hichartdemo.model.DataGraphViewModel;
import com.example.roy.hichartdemo.databinding.ItemChartListBinding;

import java.util.List;

/**
 * Created by Roy on 2018/11/28
 */
public class DataGraphChartAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemChartListBinding>> {
    private List<DataGraphViewModel> data;
    private int size;

    public DataGraphChartAdapter() {
    }

    public void setData(List<DataGraphViewModel> data) {
        this.data = data;
        size = data.size();
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @NonNull
    @Override
    public BindingViewHolder<ItemChartListBinding> onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        ItemChartListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.item_chart_list,null,false);
        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemChartListBinding> holder, int position) {
        ItemChartListBinding binding = holder.getBinding();
        DataGraphViewModel item = data.get(position);
        binding.title.setText(item.getTitle());
        binding.charts.setOptions(item.getHiChartOptions());
        if(!item.isDisplayed()){
            item.setDisplayed(true);
        }else{
            item.getHiChartOptions().getPlotOptions().getSeries().setClip(false);
        }
//        binding.charts.reload();
    }

    @Override
    public int getItemCount() {
        return size;
    }

}
