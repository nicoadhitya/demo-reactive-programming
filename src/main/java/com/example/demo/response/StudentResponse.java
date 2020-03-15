package com.example.demo.response;

import com.example.demo.entity.Grade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentResponse {

  private String id;
  private String nim;
  private String name;
  private String gender;
  private List<Grade> grades;
}
