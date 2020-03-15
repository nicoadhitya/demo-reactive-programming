package com.example.demo.service.api;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.request.AddGradeRequest;
import com.example.demo.request.InsertStudentRequest;
import com.example.demo.request.UpdateStudentRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

  Mono<Student> addGradeByNim(String nim, AddGradeRequest request);

  Mono<Void> deleteByNim(String nim);

  Mono<Student> findByNim(String nim);

  Flux<Grade> findGoodGradeByNim(String nim, Double standard);

  Mono<Student> insert(InsertStudentRequest request);

  Mono<Student> updateByNim(String nim, UpdateStudentRequest request);
}
