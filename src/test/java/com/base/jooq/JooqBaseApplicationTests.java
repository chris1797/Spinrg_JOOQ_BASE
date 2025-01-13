package com.base.jooq;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


class JooqBaseApplicationTests {

	@Test
	void contextLoads() {

		int l = 10;
		int r = 20;

		int[] arr = IntStream.rangeClosed(l, r)
				.mapToObj(String::valueOf)
				.map(String::toCharArray)
				.filter(chars -> isOnlyFiveAndZero(chars))
				.map(String::new)
				.mapToInt(Integer::parseInt)
				.toArray();



		int [] answer = {-1};
	}

	// char 배열이 '5'와 '0'으로만 이루어졌는지 확인하는 메서드
	private static boolean isOnlyFiveAndZero(char[] arr) {
		for (char c : arr) {
			if (c != '5' && c != '0') {
				return false;
			}
		}
		return true;
	}
}
