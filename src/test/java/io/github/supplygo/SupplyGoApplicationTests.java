package io.github.supplygo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
class SupplyGoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void passwordEncoderTest() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "admin123";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println("加密后的密码: " + encodedPassword);
		assertTrue(passwordEncoder.matches(password, encodedPassword));
	}

}
