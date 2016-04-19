package com.levent_j.meizhi.net;

import com.google.gson.Gson;
import com.levent_j.meizhi.bean.GalleryListResult;
import com.levent_j.meizhi.bean.GalleryResult;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by levent_j on 16-4-18.
 */
public class Api {

    private static final String URL = "http://www.tngou.net/tnfs/api/";

    private static volatile Api instance;

    private final Gson gson = new Gson();

    private final ApiService apiService;

    public Api(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static Api getInstance(){
        if (instance==null){
            synchronized (Api.class){
                instance = new Api();
            }
        }
        return instance;
    }

    //下面加入方法就行了
    public void getGalleryList(int page,int rows,int id, Subscriber<GalleryListResult> subscriber){
        apiService.getGalleryList(page, rows, id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getGalleryDetail(int id,Subscriber<GalleryResult> subscriber){
        apiService.getGalleryDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public interface ApiService{
        //http://www.tngou.net/tnfs/api/list
        @GET("list")
        Observable<GalleryListResult> getGalleryList(@Query("page") int page,
                                              @Query("rows") int rows,
                                              @Query("id") int id);

        //http://www.tngou.net/tnfs/api/show?id=18
        @GET("show")
        Observable<GalleryResult> getGalleryDetail(@Query("id") int id);
    }
}
