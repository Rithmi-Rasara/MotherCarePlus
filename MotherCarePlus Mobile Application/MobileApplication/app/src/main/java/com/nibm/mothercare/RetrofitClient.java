package com.nibm.mothercare;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // Use http://10.0.2.2:8080/ for Android Emulator, or http://<YOUR_LOCAL_IP>:8080/ for physical device
    private static final String BASE_URL =
            "http://10.30.32.186:8080/";

    private static Retrofit retrofit;

    private RetrofitClient() {
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    )
                    .build();
        }

        return retrofit;
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}