package com.example.fragment.library.common.aaPackage.http;


import com.example.fragment.library.common.BuildConfig;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManagement {
//
//    private static final long READ_TIMEOUT = 6000;
//
//    private static final long WRITE_TIMEOUT = 6000;
//
//    private static final long CONNECT_TIMEOUT = 6000;
//
//    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
//
//    private RetrofitManagement() {
//
//    }
//
//    public static RetrofitManagement getInstance() {
//        return RetrofitHolder.retrofitManagement;
//    }
//
//    private static class RetrofitHolder {
//        private static final RetrofitManagement retrofitManagement = new RetrofitManagement();
//    }
//
//    private Retrofit createRetrofit(String url) {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
//                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
//                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(new HttpInterceptor())
//                .addInterceptor(new HeaderInterceptor())
//                .addInterceptor(new FilterInterceptor())
//                .retryOnConnectionFailure(true);
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(httpLoggingInterceptor);
//            builder.addInterceptor(new ChuckInterceptor(ContextHolder.getContext()));
//        }
//        OkHttpClient client = builder.build();
//        return new Retrofit.Builder()
//                .client(client)
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }
//
//    <T> ObservableTransformer<BaseResponseBody<T>, T> applySchedulers() {
//        return observable -> observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(result -> {
//                    switch (result.getCode()) {
//                        case HttpCode.CODE_SUCCESS: {
//                            return createData(result.getData());
//                        }
//                        case HttpCode.CODE_TOKEN_INVALID: {
//                            throw new TokenInvalidException();
//                        }
//                        case HttpCode.CODE_ACCOUNT_INVALID: {
//                            throw new AccountInvalidException();
//                        }
//                        default: {
//                            throw new ServerResultException(result.getCode(), result.getMsg());
//                        }
//                    }
//                });
//    }
//
//
//    private <T> Observable<T> createData(T t) {
//        return Observable.create(new ObservableOnSubscribe<T>() {
//            @Override
//            public void subscribe(ObservableEmitter<T> emitter) {
//                try {
//                    emitter.onNext(t);
//                    emitter.onComplete();
//                } catch (Exception e) {
//                    emitter.onError(e);
//                }
//            }
//        });
//    }
//
//    <T> T getService(Class<T> clz) {
//        return getService(clz, HttpConfig.BASE_URL_WEATHER);
//    }
//
//    <T> T getService(Class<T> clz, String host) {
//        T value;
//        if (serviceMap.containsKey(host)) {
//            Object obj = serviceMap.get(host);
//            if (obj == null) {
//                value = createRetrofit(host).create(clz);
//                serviceMap.put(host, value);
//            } else {
//                value = (T) obj;
//            }
//        } else {
//            value = createRetrofit(host).create(clz);
//            serviceMap.put(host, value);
//        }
//        return value;
//    }


}
