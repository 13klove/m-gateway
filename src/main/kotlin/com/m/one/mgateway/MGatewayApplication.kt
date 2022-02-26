package com.m.one.mgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class MGatewayApplication

fun main(args: Array<String>) {
    runApplication<MGatewayApplication>(*args)
}
