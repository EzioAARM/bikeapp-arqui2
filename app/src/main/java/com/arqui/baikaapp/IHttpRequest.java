package com.arqui.baikaapp;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IHttpRequest {

    @GET("android-app")
    Call<ObjetoRetorno> ObtenerDatos();

}
