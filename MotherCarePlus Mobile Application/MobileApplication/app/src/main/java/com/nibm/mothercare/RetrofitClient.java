package com.nibm.mothercare;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL =
            "http://10.38.141.186/MotherCarePlus/MotherCareAPI/";

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

        return getRetrofitInstance()
                .create(ApiService.class);
    }

}
