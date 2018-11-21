package com.xiyifen.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fs.z
 * Date 2018/10/25 13:33
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product msg";
    }

}
