package com.nikolanedeljkovic.flightadvisor;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ExampleTest {
	
	@Test
	public void test_1() {
		String encodedPass = new BCryptPasswordEncoder().encode("password123");
		System.out.println(encodedPass);
	}
}
