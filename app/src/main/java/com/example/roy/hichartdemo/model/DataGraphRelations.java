package com.example.roy.hichartdemo.model;

/**
 * Created by Roy on 2019/1/20
 */
public class DataGraphRelations {
    private String graph_id;
    private String graph_theme_id;
    private DataGraph graph;

    public String getGraphId() {
        return graph_id;
    }

    public void setGraph_id(String graph_id) {
        this.graph_id = graph_id;
    }

    public String getGraph_theme_id() {
        return graph_theme_id;
    }

    public void setGraph_theme_id(String graph_theme_id) {
        this.graph_theme_id = graph_theme_id;
    }

    public DataGraph getGraph() {
        return graph;
    }

    public void setGraph(DataGraph graph) {
        this.graph = graph;
    }
}
