package com.example.demo.controller;

import com.example.demo.request.AddGradeRequest;
import com.example.demo.request.InsertStudentRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.BaseResponse;
import com.example.demo.response.GoodGradeResponse;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.api.StudentService;
import com.example.demo.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class StudentController {

  @Autowired
  private StudentService service;

  @GetMapping("/api/student/{nim}")
  public Mono<StudentResponse> findByNim(@PathVariable String nim) {
    return service.findByNim(nim)
        .map(ResponseHelper::convertToStudentResponse);
  }

  @PostMapping("/api/student")
  public Mono<BaseResponse> insert(@RequestBody InsertStudentRequest request) {
    return service.insert(request)
        .map(data -> BaseResponse.builder().success(true).build())
        .onErrorResume(error ->
            Mono.just(BaseResponse.builder()
                .success(false)
                .errorMessage(error.getMessage())
                .build())
        );
  }

  @PutMapping("/api/student/{nim}")
  public Mono<BaseResponse> update(@PathVariable String nim,
      @RequestBody UpdateStudentRequest request) {
    return service.updateByNim(nim, request)
        .map(data -> BaseResponse.builder().success(true).build())
        .onErrorResume(error ->
            Mono.just(BaseResponse.builder()
                .success(false)
                .errorMessage(error.getMessage())
                .build())
        );
  }

  @DeleteMapping("/api/student/{nim}")
  public Mono<BaseResponse> deleteByNim(@PathVariable String nim) {
    return service.deleteByNim(nim)
        .map(data -> BaseResponse.builder().success(true).build())
        .onErrorResume(error ->
            Mono.just(BaseResponse.builder()
                .success(false)
                .errorMessage(error.getMessage())
                .build())
        );
  }

  @PostMapping("/api/student/{nim}/_addGrade")
  public Mono<BaseResponse> addGradeByNim(@PathVariable String nim,
      @RequestBody AddGradeRequest request) {
    return service.addGradeByNim(nim, request)
        .map(data -> BaseResponse.builder().success(true).build())
        .onErrorResume(error ->
            Mono.just(BaseResponse.builder()
                .success(false)
                .errorMessage(error.getMessage())
                .build())
        );
  }

  @GetMapping("/api/student/{nim}/goodGrade")
  public Mono<List<GoodGradeResponse>> findGoodGradeByNim(@PathVariable String nim,
      @RequestParam double standard) {
    return service.findGoodGradeByNim(nim, standard)
        .map(ResponseHelper::convertToGradeResponse)
        .collectList();

  }
}
