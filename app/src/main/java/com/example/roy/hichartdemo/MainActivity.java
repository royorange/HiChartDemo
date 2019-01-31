package com.example.roy.hichartdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.roy.hichartdemo.databinding.ActivityMainBinding;
import com.example.roy.hichartdemo.model.DataGraph;
import com.example.roy.hichartdemo.model.DataGraphCategoryDetailModel;
import com.example.roy.hichartdemo.model.DataGraphRelations;
import com.example.roy.hichartdemo.model.DataGraphViewModel;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    DataGraphChartAdapter adapter;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView(binding);
        initNetwork();
        fetchData(binding);
    }

    private void initView(ActivityMainBinding binding){
        adapter = new DataGraphChartAdapter();
        binding.list.setAdapter(adapter);
        binding.refresh.setRefreshing(true);
        binding.refresh.setOnRefreshListener((() -> fetchData(binding)));
    }

    private void fetchData(ActivityMainBinding binding){
       repository.getDataGraphCategory("3")
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
                        graph.setHiChartOptions(new HiChartBuilder().setData(graph.getChart()).build(this));
                        graphList.add(graph);
                    }
                    return graphList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataGraphViewModels, throwable) -> {
                    adapter.setData(dataGraphViewModels);
                    binding.refresh.setRefreshing(false);
                });
    }

    private void initNetwork(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .baseUrl("https://api-test.wisburg.com");
        repository = builder.build().create(Repository.class);
    }

    private OkHttpClient createClient(){
        X509TrustManager easyTrustManager = new X509TrustManager() {
            public void checkClientTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
                // Oh, I am easy!
            }

            public void checkServerTrusted(
                    X509Certificate[] chain,
                    String authType) throws CertificateException {
                // Oh, I am easy!
            }

            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };
        SSLContext sc = null;
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {easyTrustManager};
        // Install the all-trusting trust manager
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(100000, TimeUnit.SECONDS)
                .readTimeout(100000,TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        if(sc != null){
            builder.sslSocketFactory(sc.getSocketFactory(),easyTrustManager);
        }
        return builder.build();
    }
}
