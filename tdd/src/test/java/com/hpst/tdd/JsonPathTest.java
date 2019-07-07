package com.hpst.tdd;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;

class JsonPathTest {

	@Test
	void test() throws IOException {
		List<String> authors = JsonPath.read(new File("C:\\Users\\hsin65\\git\\tdd\\tdd\\src\\test\\resources\\test.json"),
				"$.store.book[*].author");
		System.out.println(authors);
	}

}
