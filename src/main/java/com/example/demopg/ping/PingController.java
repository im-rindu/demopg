package com.example.demopg.ping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/ping")
public class PingController {
  @GetMapping("path")
  public String pingPong() {
    return "PONK!";
  }
}