package com.dammenhayn.blueprint.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class RandomGenerator {

  private static final String          ALPHABETIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final List<String>    FIRST_NAMES       = Arrays.asList("Daniel", "Owen", "Olivia", "Marc", "Robert", "Christian");
  private static final List<String>    LAST_NAMES        = Arrays.asList("Huntington", "Gray", "Greene", "Wilson", "Brown", "Jackson", "Rhimes");
  private static final List<LocalDate> LOCAL_DATES       = Arrays.asList(LocalDate.of(1970, 1 , 1), LocalDate.of(1980, 7, 4),
          LocalDate.of(1983, 11, 11), LocalDate.of(1990, 9, 30), LocalDate.of(1979,3, 13));

  private static final Random RANDOM = new Random();

  static String randomFirstName() {
    return FIRST_NAMES.get(RANDOM.nextInt(FIRST_NAMES.size()));
  }

  static String randomLastName() {
    return LAST_NAMES.get(RANDOM.nextInt(LAST_NAMES.size()));
  }

  static LocalDate randomLocalDate() {
    return LOCAL_DATES.get(RANDOM.nextInt(LOCAL_DATES.size()));
  }

  static String randomEmail() {
    String firstName = randomFirstName().toLowerCase();
    String lastName  = randomLastName().toLowerCase();

    return firstName + "." + lastName + "@example.com";
  }

  static String randomString(int count) {
    StringBuilder sb = new StringBuilder();
    int upperLimit = ALPHABETIC_STRING.length();
    for (int index = 0; index < count; index++) {
      sb.append(ALPHABETIC_STRING.charAt(RANDOM.nextInt(upperLimit)));
    }

    return sb.toString();
  }

  static boolean randomBoolean() {
    int value = RANDOM.nextInt(10);

    return value > 5;
  }

}

