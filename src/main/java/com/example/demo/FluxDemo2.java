package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxDemo2 {

	public static void main(String[] args) throws InterruptedException {
		Flux<Integer> numbers1 = createFluxWithRange(1);
		Flux<Integer> numbers2 = createFluxWithRange(4);
		Flux<Integer> numbers3 = createFluxWithRange(7);
		Flux<Integer> numbers4 = createFluxWithRange(10);

		numbers1.subscribe(System.out::println);
		numbers2.subscribe(System.out::println);
		numbers3.subscribe(System.out::println);
		numbers4.subscribe(System.out::println);

		Thread.sleep(5000);
	}

	private static Flux<Integer> createFluxWithRange(int start) {
		return Flux.range(start, 3)
				/*.flatMap(value ->
						Mono.just(value)
								.publishOn(Schedulers.elastic())
								.map(v -> v * 2)
				)*/
				//.map(value -> value * 2)
				//.subscribeOn(Schedulers.parallel())
				;
	}
}
