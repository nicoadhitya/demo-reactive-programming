package com.example.demo.service.impl;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.AddGradeRequest;
import com.example.demo.request.InsertStudentRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.service.api.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository repository;

  @Override
  public Mono<Void> deleteByNim(String nim) {
    return Mono.just(nim)
        .flatMap(repository::findByNim)
        .flatMap(repository::delete)
        .switchIfEmpty(Mono.error(new RuntimeException("NIM tidak ditemukan")));
  }

  @Override
  public Mono<Student> findByNim(String nim) {
    return repository.findByNim(nim);
  }

  @Override
  public Mono<Student> insert(InsertStudentRequest request) {
    return Mono.just(request.getGender())
        // validate gender
        .map(gender -> request.getNim())
        .flatMap(repository::findByNim)
        .doOnNext(student -> {
          throw new RuntimeException("NIM sudah terpakai");
        })
        .switchIfEmpty(repository.save(
            Student.builder()
                .nim(request.getNim())
                .name(request.getName())
                .gender(request.getGender())
                .grades(new ArrayList<>())
                .build())
        );
  }

  @Override
  public Mono<Student> updateByNim(String nim, UpdateStudentRequest request) {
    return Mono.just(request)
        // validate gender
        .map(gender -> nim)
        .flatMap(repository::findByNim)
        .doOnNext(student -> student.setGender(request.getGender()))
        .doOnNext(student -> student.setName(request.getName()))
        .switchIfEmpty(Mono.error(new RuntimeException("NIM tidak ditemukan")));
  }

  /*
    TASK 1 : validate student gender upon update/insert
  */
  private Mono<String> validateGender(String gender) {
    return Mono.just(gender)
        // validate if gender is either F/M
        // return some error if gender is invalid
        ;
  }

  /*
    TASK 2 : add logic to add grade(s) for student by nim
    - semester must be > 0, otherwise send error "Semester harus >= 1"
    - score must be >= 0.0 and <= 100.0, otherwise send error "Score harus bernilai 0 - 100"
  */
  @Override
  public Mono<Student> addGradeByNim(String nim, AddGradeRequest request) {
    return Mono.just(request)
        // validate score
        // validate semester
        .map(grade -> nim)
        .flatMap(repository::findByNim)
        // add grade to student's grade list
        .switchIfEmpty(Mono.error(new RuntimeException("NIM tidak ditemukan")));
  }

  private Mono<Integer> validateSemester(Integer semester) {
    return Mono.just(semester)
        // validate score here
        // send error if semester is invalid
        ;
  }

  private Mono<Double> validateScore(Double score) {
    return Mono.just(score)
        // validate score here
        // send error if score is invalid
        ;
  }

  /*
    TASK 3 : add logic to get grades of a student, which score > standard
  */
  @Override
  public Flux<Grade> findGoodGradeByNim(String nim, Double standard) {
    return Mono.just(nim)
        .flatMap(repository::findByNim)
        .map(Student::getGrades)
        .flatMapMany(Flux::fromIterable)
        // get grades with score > standard
        ;
  }
}
