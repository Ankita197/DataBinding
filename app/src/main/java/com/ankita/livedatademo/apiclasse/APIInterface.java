package com.ankita.livedatademo.apiclasse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/v1/create")
    Observable<CreateResponse> CreateEmployee(@Body User user);

    @GET("/api/v1/employees")
    Call<GetEmpoyyes> getAllEmployees();

}
