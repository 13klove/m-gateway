package com.m.one.mgateway.filter

import com.m.one.mgateway.api.TokenApi
import com.m.one.mgateway.message.RevokeRequest
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import retrofit2.Retrofit

@Component
class TokenFilter(
    mAuthService: Retrofit
): AbstractGatewayFilterFactory<Any>() {

    companion object {
        const val AUTHORIZATION_KEY = "Authorization"
        const val USER_ID_KEY = "X-USER-ID"
        const val HEADER_USER = "H-USER-ID"
    }

    private val mAuthService = mAuthService.create(TokenApi::class.java)

    override fun apply(config: Any?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            val headers = request.headers
            if (!headers.containsKey(AUTHORIZATION_KEY)) {
                checkException(response, "token error")
            }

            if (!headers.containsKey(USER_ID_KEY)) {
                checkException(response, "userId error")
            }

            val token = headers[AUTHORIZATION_KEY]!![0]
            val userId = headers[USER_ID_KEY]!![0]

            mAuthService.revoke(RevokeRequest(token, userId.toLong())).execute().body()?: kotlin.run {
                checkException(response, "token error")
            }

            println(headers.keys)
            request.mutate().header(HEADER_USER, userId)
            println(headers.keys)

            chain.filter(exchange)
        }
    }

    private fun checkException(response: ServerHttpResponse, message: String) {
        response.statusCode = HttpStatus.UNAUTHORIZED
        throw RuntimeException(message)
    }

}