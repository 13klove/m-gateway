package com.m.one.mgateway.message

data class TokenDetailMessage(
    var tokenType: String,
    var token: String,
    var email: String,
    var iat: Int,
    var exp: Int
)
