package com.example.roy.hichartdemo.domain;

import android.content.Context;

import com.example.roy.hichartdemo.HiChartBuilder;
import com.example.roy.hichartdemo.Repository;
import com.example.roy.hichartdemo.model.DataGraph;
import com.example.roy.hichartdemo.model.DataGraphCategoryDetailModel;
import com.example.roy.hichartdemo.model.DataGraphRelations;
import com.example.roy.hichartdemo.model.DataGraphViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetDataGraphCategoryDetail{
    private Repository repository;
    private Context context;

    public GetDataGraphCategoryDetail(Repository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }

    protected Single<List<DataGraphViewModel>> buildUseCaseForResult(String param) {
        return repository.getDataGraphCategory(param)
                .map(categoryModelNetResponse -> {
                    DataGraphCategoryDetailModel response = categoryModelNetResponse.getBody();
                    List<DataGraphViewModel> graphList = new ArrayList<>();
                    for(DataGraphRelations relations:response.getGraphThemeRelations()){
                        DataGraphViewModel graph = new DataGraphViewModel();
                        graph.setId(relations.getGraphId());
                        DataGraph rawGraph = relations.getGraph();
                        graph.setTitle(rawGraph.getTitle());
                        graph.setDescription(rawGraph.getDescription());
                        graph.setStar(rawGraph.getStar());
                        //chart
                        graph.setChart(rawGraph.getGeneratedChart());
                        graph.setHiChartOptions(new HiChartBuilder().setData(graph.getChart()).build(context));
                        graphList.add(graph);
                    }
                    return graphList;
                });
    }

    public Disposable execute(ResourceSingleObserver<List<DataGraphViewModel>> observer) {
        return buildUseCaseForResult(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}
