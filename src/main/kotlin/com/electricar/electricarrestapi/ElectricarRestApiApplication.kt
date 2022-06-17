package com.electricar.electricarrestapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan
class ElectricarRestApiApplication

fun main(args: Array<String>) {
    runApplication<ElectricarRestApiApplication>(*args)
}
