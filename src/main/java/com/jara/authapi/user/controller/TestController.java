package com.jara.authapi.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola usuario autenticado";
    }

}