package com.cosmos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/loginFallback")
    public Mono<String> loginFallback(){
        return Mono.just("Login Service taking too long to respond. Please try again later..");
    }
    @RequestMapping("/cartFallback")
    public Mono<String> cartFallback(){
        return Mono.just("Cart Service taking too long to respond. Please try again later..");
    }
    @RequestMapping("/contactFallback")
    public Mono<String> contactFallback(){
        return Mono.just("Contact Service taking too long to respond. Please try again later..");
    }
}
