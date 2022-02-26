package com.m.one.mgateway.message

data class RevokeRequest(
    val token: String,
    val userId: Long
)
