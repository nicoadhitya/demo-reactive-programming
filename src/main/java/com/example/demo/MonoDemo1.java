package com.example.demo;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoDemo1 {

	public static void main(String[] args) throws Exception{

		Mono<String> alice = createMono("Alice");
		Mono<String> bob = createMono("Bob");
		Mono<String> john = createMono("John");
		Mono<String> delly = createMono("Delly");
		Mono<String> mona = createMono("Mona");

		alice.subscribe(System.out::println);
		bob.subscribe(System.out::println);
		john.subscribe(System.out::println);
		delly.subscribe(System.out::println);
		mona.subscribe(System.out::println);

		Thread.sleep(5000);
	}

	private static Mono<String> createMono(String name) {
		return Mono.just(name)
				//.log()
				.subscribeOn(Schedulers.parallel());
	}
}
