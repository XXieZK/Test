package com.xiezekai.test;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.xiezekai.test.util.NumberMapperUtil;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() throws Exception {
		List<Integer> paramList = Lists.newArrayList(2, 3);
		List<String> list = NumberMapperUtil.numberLetterMapper(paramList);
		list.forEach(e -> System.out.print(e));
	}

}
