package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoDemo2 {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private static class Student {
		private String name;
		private Integer score;
		private String gender;
	}

	public static void main(String[] args) throws Exception{

		Mono<Student> alice = Mono.just(new Student("Alice", 65, "F"));
		Mono<Student> bob = Mono.just(new Student("Bob", 55, "M"));
		Mono<Student> john = Mono.just(new Student("John", 35, "M"));

		//giveBonusScore(alice).subscribe(System.out::println);
		//giveBonusScore(bob).subscribe(System.out::println);
		//giveBonusScore(john).subscribe(System.out::println);

		/*applyScholarship(alice)
				.subscribe(student -> System.out.println(student.getName() + " gets a scholarship"));*/
		/*applyScholarship(alice)
				.subscribe(
						student -> System.out.println(student.getName() + " gets a scholarship"),
						System.err::println);*/
		/*applyScholarship(giveBonusScore(alice))
				.subscribe(
						student -> System.out.println(student.getName() + " gets a scholarship"),
						System.err::println);*/

		Thread.sleep(5000);
	}

	private static Mono<Student> giveBonusScore(Mono<Student> studentMono) {
		return studentMono.filter(student -> "F".equals(student.getGender()))
				.doOnNext(student -> System.out.println(student.getName() + " get a bonus"))
				.doOnNext(student -> student.setScore(student.getScore() + 20))
				.doOnSuccess(student -> System.out.println("Success giving/not giving bonus"))
				.switchIfEmpty(studentMono)
				//.defaultIfEmpty(new Student())
				;
	}

	private static Mono<Student> applyScholarship(Mono<Student> studentMono) {
		return studentMono.filter(student -> student.getScore() > 80)
				.switchIfEmpty(Mono.error(new RuntimeException("Score tidak mencukupi")))
				.doOnError(throwable -> System.out.println("Terjadi kesalahan"))
				;
	}
}
