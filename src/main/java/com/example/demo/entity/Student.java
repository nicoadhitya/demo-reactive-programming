package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@Document(collection = "Student")
public class Student {

  @Id
  @Field(value = "id")
  private String id;

  @Field(value = "nim")
  private String nim;

  @Field(value = "name")
  private String name;

  @Field(value = "gender")
  private String gender;

  @Field(value = "grades")
  private List<Grade> grades;
}
