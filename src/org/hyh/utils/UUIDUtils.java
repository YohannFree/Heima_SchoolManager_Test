package org.hyh.utils;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtils {//32位的uuid(无短横线)

	public static String UUIDutils() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Test
	public void test() {
		System.out.println(UUIDutils());
		;
	}
}
