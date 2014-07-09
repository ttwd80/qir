package integration.security;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordITCase {

	@Test
	public void testLengthDefaultInputEmpty() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String encoded = passwordEncoder.encode("");
		assertThat(encoded.length(), equalTo(60));
	}

	@Test
	public void testLengthDefaultInputLong() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String encoded = passwordEncoder.encode("T#1sI5L0n6");
		assertThat(encoded.length(), equalTo(60));
	}

	@Test
	public void testLengthShortInputEmpty() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
		final String encoded = passwordEncoder.encode("");
		assertThat(encoded.length(), equalTo(60));
	}

	@Test
	public void testLengthShortInputLong() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
		final String encoded = passwordEncoder.encode("T#1sI5L0n6");
		assertThat(encoded.length(), equalTo(60));
	}

	@Test
	public void testLengthLongInputEmpty() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		final String encoded = passwordEncoder.encode("");
		assertThat(encoded.length(), equalTo(60));
	}

	@Test
	public void testLengthLongInputLong() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		final String encoded = passwordEncoder.encode("T#1sI5L0n6");
		assertThat(encoded.length(), equalTo(60));
	}

	@Test
	public void testHashHardToCrack() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		final String hashed = "$2a$12$RwnxBcSGBCW3EWNP5RK.q.BHoG/kuh/pyB6BluLp2.9MoNzqwcfFG";
		assertThat(passwordEncoder.matches("m8C5QuSG34534Tje", hashed),
				equalTo(Boolean.TRUE));
	}

	@Test
	public void testHashSuperEasyToCrack() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String hashed = "$2a$10$FPXLVweqprpxU9yGoX2RfuF/xtUVamB4XQByESlaqygK8d4cORgtq";
		assertThat(passwordEncoder.matches("password123!", hashed),
				equalTo(Boolean.TRUE));

	}

	@Test
	public void testHashUserSuperEasyToCrack() {
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String[] name = { "user01", "user02", "user03", "user04",
				"user05", "user06", "user07", "user08", "user09", "user10" };
		final String[] hashed = {
				"$2a$10$DWBqLX6nv6h2UTzg1FcX4uC21PN.MdMKWegsj/tLJvR/iDjzK/jpS",
				"$2a$10$xI8tXn1xzqe.YyeysYL5kuD3WEZLCom7vZ5TJOEQggoFBG.Z4jXhu",
				"$2a$10$CINTGP9y3EH.DdsgHb4BsuiX/cekGwK0A6If5xcZgtamzGt3ZQ06W",
				"$2a$10$bfUBwRcPUJJaibXJUekOWubfdUzAQyv91m/JcJ9ftu2Ryp/7kTVtW",
				"$2a$10$tc2P1gncfAJsKcA.hMRszuN3N1vljXaHaV4nhuHGekcnagEcLxDfu",
				"$2a$10$EQPjEsFVBAIPQCemXtWSwe2OVQt28mUp1jQahuQHep2CUOej0VxjC",
				"$2a$10$88Nu.zsouhH9YZG5kX7td.bm3j6fySQUSeJ7pAo5.ZifT88kTzUvO",
				"$2a$10$pIcoFjNK7iAgpogzOMQteO1wwTTYaXu5iajgFIS7c2JkaxJ4ugp0a",
				"$2a$10$vO4e/TobzfF0tabk2dZ.TewfShZHgIIAhekiHdbLmbnQ5g4MVTFiO",
				"$2a$10$kDuuZOFYrKNpKOcu/MOT8eOGecp..dlRo8m.LrJF4hga.6KKp1ula" };
		assertThat(name.length, equalTo(10));
		assertThat(hashed.length, equalTo(name.length));
		for (int i = 0; i < name.length; i++) {
			assertThat(passwordEncoder.matches(name[i], hashed[i]),
					equalTo(Boolean.TRUE));
		}

	}
}
