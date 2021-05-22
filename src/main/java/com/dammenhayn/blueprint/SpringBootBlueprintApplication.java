package com.dammenhayn.blueprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBlueprintApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootBlueprintApplication.class, args);
  }

}

// ToDo DanielW: Wenn Job und Level null sind, wird das auch so in der UI gezeigt
// ToDo DanielW: Unit/Integrationtests
// ToDo DanielW: General InitBinder fürs Trimming -> ist das wirklich für jeden Controller notwendig? Was ist mit Rest?
