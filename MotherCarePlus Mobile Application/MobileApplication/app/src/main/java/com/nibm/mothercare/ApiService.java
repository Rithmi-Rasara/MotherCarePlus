package com.nibm.mothercare;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login.php")
    Call<LoginResponse> login(@Body LoginRequest request);
}
