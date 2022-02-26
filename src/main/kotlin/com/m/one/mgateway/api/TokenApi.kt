package com.m.one.mgateway.api

import com.m.one.mgateway.message.RevokeRequest
import com.m.one.mgateway.message.TokenDetailMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {

    @POST("/internal/token/revoke")
    fun revoke(
        @Body revokeRequest: RevokeRequest,
    ): Call<TokenDetailMessage>

}