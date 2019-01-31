package com.example.roy.hichartdemo.model;

import java.util.List;

/**
 * Created by Roy on 2019/1/17
 */
public class DataGraphCategoryDetailModel extends DataGraphCategoryModel{
    private List<DataGraphRelations> graph_theme_relations;

    public List<DataGraphRelations> getGraphThemeRelations() {
        return graph_theme_relations;
    }

    public void setGraph_theme_relations(List<DataGraphRelations> graph_theme_relations) {
        this.graph_theme_relations = graph_theme_relations;
    }
}
