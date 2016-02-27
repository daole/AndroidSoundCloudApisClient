package com.dreamdigitizers.androidsoundcloudapi.core;

import android.text.TextUtils;

import com.dreamdigitizers.androidsoundcloudapi.models.v2.Charts;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

class ApiV2 implements IApiV2 {
    private static final String ERROR_MESSAGE__NOT_YET_INITIALIZED = "ApiV2.initialize() has not yet called.";

    private static ApiV2 instance;

    private IApiV2 mApi;

    public static void initialize(final String pClientId) {
        Api.initialize(pClientId, null);
    }

    public static void initialize(final String pClientId, final String pOauthToken) {
        if (ApiV2.instance == null) {
            ApiV2.instance = new ApiV2(pClientId, pOauthToken);
        }
    }

    public static ApiV2 getInstance() {
        if(ApiV2.instance == null) {
            throw new IllegalStateException(ApiV2.ERROR_MESSAGE__NOT_YET_INITIALIZED);
        }
        return ApiV2.instance;
    }

    private ApiV2(final String pClientId, final String pOauthToken) {
        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain pChain) throws IOException {
                Request request = pChain.request();
                HttpUrl.Builder builder = request.httpUrl().newBuilder();
                builder.addQueryParameter("client_id", pClientId);
                if(!TextUtils.isEmpty(pOauthToken)) {
                    builder.addQueryParameter("oauth_token", pOauthToken);
                }
                HttpUrl httpUrl = builder.build();
                request = request.newBuilder().url(httpUrl).build();
                return pChain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApiV2.API_URL__BASE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.mApi = retrofit.create(IApiV2.class);
    }

    @Override
    public Call<Charts> charts(String pKind, String pGenre) {
        return this.mApi.charts(pKind, pGenre);
    }

    @Override
    public Call<Charts> charts(
            int pLinkedPartitioning,
            int pLimit,
            int pOffset,
            String pKind,
            String pGenre) {
        return this.mApi.charts(
                pLinkedPartitioning,
                pLimit,
                pOffset,
                pKind,
                pGenre);
    }

    @Override
    public Observable<Charts> chartsRx(String pKind, String pGenre) {
        return this.chartsRx(pKind, pGenre);
    }

    @Override
    public Observable<Charts> chartsRx(
            int pLinkedPartitioning,
            int pLimit,
            int pOffset,
            String pKind,
            String pGenre) {
        return this.mApi.chartsRx(
                pLinkedPartitioning,
                pLimit,
                pOffset,
                pKind,
                pGenre);
    }

    /*
    @Override
    public Call<List<Track>> userFavorites(int pId) {
        return this.mApi.userFavorites(pId);
    }

    @Override
    public Call<Collection> userFavorites(int pId, int pLinkedPartitioning, int pLimit, String pOffset) {
        return this.mApi.userFavorites(pId, pLinkedPartitioning, pLimit, pOffset);
    }

    @Override
    public Observable<List<Track>> userFavoritesRx(int pId) {
        return this.mApi.userFavoritesRx(pId);
    }

    @Override
    public Observable<Collection> userFavoritesRx(int pId, int pLinkedPartitioning, int pLimit, String pOffset) {
        return this.mApi.userFavoritesRx(pId, pLinkedPartitioning, pLimit, pOffset);
    }
    */
}