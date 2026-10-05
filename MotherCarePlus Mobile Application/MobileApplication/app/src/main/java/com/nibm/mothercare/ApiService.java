package com.nibm.mothercare;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("api/midwife/dashboard")
    Call<MidwifeDashboardResponse> getMidwifeDashboard(
            @Query("user_id") int userId
    );

    @FormUrlEncoded
    @POST("api/mothers/register")
    Call<PregnantMother> registerMother(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("dateOfBirth") String dateOfBirth
    );

    @POST("api/midwife/visits")
    Call<ResponseBody> recordVisit(
            @Query("assignmentId") int assignmentId,
            @Query("observations") String observations,
            @Query("advice") String advice,
            @Query("pregnancyProgress") String pregnancyProgress
    );

    @PUT("api/midwife/pregnancy/{pregnancyId}")
    Call<ResponseBody> updatePregnancy(
            @Path("pregnancyId") int pregnancyId,
            @Query("currentWeek") Integer currentWeek,
            @Query("status") String status
    );

    @GET("api/midwife/patients")
    Call<List<Map<String, Object>>> getAssignedPatients(
            @Query("user_id") int userId
    );
}