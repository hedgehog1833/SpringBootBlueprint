package com.wagner.blueprint.web.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequestDto {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  @NotEmpty
  private String email;

  @NotBlank
  private String teamName;

  private boolean active;
  private LocalDate birthday;
  private String job;
  private String careerLevel;

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("firstName", firstName);
    map.put("lastName", lastName);
    map.put("email", email);
    map.put("teamName", teamName);
    map.put("active", active);
    map.put("birthday", birthday);
    map.put("job", job);
    map.put("careerLevel", careerLevel);

    return map;
  }

  public void setBirthday(String birthdayStr) {
    birthday = LocalDate.parse(birthdayStr, dateTimeFormatter);
  }

}
