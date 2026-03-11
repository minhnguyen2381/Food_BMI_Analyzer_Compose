package com.nguyennhatminh614.foodbmianalyzercompose.network

import com.nguyennhatminh614.foodbmianalyzercompose.network.model.UserApiModel
import retrofit2.http.GET

interface UsersApi {

    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUsers(): List<UserApiModel>
}