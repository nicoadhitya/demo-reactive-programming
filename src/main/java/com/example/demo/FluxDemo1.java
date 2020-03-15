package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxDemo1 {

	public static void main(String[] args) throws InterruptedException {
		Flux<String> names = Flux.just("Alice", "Bob", "John", "Delly", "Mona")
				//.log()
				;

		//syncDemo(names).subscribe(System.out::println);
		//asyncDemo(names).subscribe(System.out::println);
		//asyncDemo(names).collectList().subscribe(System.out::println);

		Thread.sleep(5000);
	}

	private static Flux<String> syncDemo(Flux<String> input) {
		return input.map(String::toLowerCase)
				//.log
				.filter(value -> value.length() > 4)
				//.log()
				;
	}

	private static Flux<String> asyncDemo(Flux<String> input) {
		return input.flatMap(value -> {
					return Mono.just(value).map(String::toLowerCase).subscribeOn(Schedulers.parallel());
				})
				//.log
				;
	}
}
