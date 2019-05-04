package com.wagner.blueprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBlueprintApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootBlueprintApplication.class, args);
  }

}

// ToDo DanielW: Erweiterung der REST-API um folgende Use cases:
//  - Employee nach lastName suchen
//  - ReadMe anpassen
//  - Employee nach Team suchen