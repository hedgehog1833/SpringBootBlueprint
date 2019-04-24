package com.wagner.blueprint.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class EmployeeRequestDto {

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  private String email;

  private boolean isActive;
  private LocalDate birthday;
  private String job;
  private String careerLevel;

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("firstName", firstName);
    map.put("lastName", lastName);
    map.put("email", email);
    map.put("isActive", isActive);
    map.put("birthday", birthday);
    map.put("job", job);
    map.put("careerLevel", careerLevel);

    return map;
  }

}
