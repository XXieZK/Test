package com.xiezekai.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

import com.google.common.collect.Lists;

/**
 * @Title 数字字母转换
 * @Description 数字字母转换
 * @Date 2022/2/16
 * @Author xieZeKai
 */
public class NumberMapperUtil {

	private static HashMap<Integer, List<String>> mapperMap;

	public static HashMap mapperMap() {
		mapperMap = new HashMap<>();
		mapperMap.put(0, Lists.newArrayList());
		mapperMap.put(1, Lists.newArrayList());
		mapperMap.put(2, Lists.newArrayList("A", "B", "C"));
		mapperMap.put(3, Lists.newArrayList("D", "E", "F"));
		mapperMap.put(4, Lists.newArrayList("G", "H", "I"));
		mapperMap.put(5, Lists.newArrayList("J", "K", "L"));
		mapperMap.put(6, Lists.newArrayList("M", "N", "O"));
		mapperMap.put(7, Lists.newArrayList("P", "Q", "R", "S"));
		mapperMap.put(8, Lists.newArrayList("T", "U", "V"));
		mapperMap.put(9, Lists.newArrayList("W", "X", "Y", "Z"));
		return mapperMap;
	}

	public static List<String> numberLetterMapper(List<Integer> numberList) throws Exception {
		mapperMap();
		ArrayList<String> letterList = Lists.newArrayList();
		if (ObjectUtils.isEmpty(numberList)) {
			return letterList;
		}

		List<List<String>> dataList = Lists.newArrayList();

		for (Integer number : numberList) {
			if (number < 0 || number > 9) {
				throw new Exception("参数错误");
			}

			List<String> list = mapperMap.get(number);
			if (ObjectUtils.isEmpty(list)) {
				continue;
			}
			dataList.add(list);
		}

		if (ObjectUtils.isEmpty(dataList)) {
			return letterList;
		}

		List<List<String>> mapperDataList = recursionMethod(dataList, 0, null);

		mapperDataList.forEach(e -> {
			letterList.add(e.stream().collect(Collectors.joining("")));
			letterList.add("  ");
		});

		return letterList;
	}

	private static List<List<String>> recursionMethod(List<List<String>> dataList, int index,
			List<List<String>> resultList) {

		if (index == dataList.size()) {
			return resultList;
		}

		List<List<String>> temporaryResultList = Lists.newArrayList();

		if (index == 0) {
			List<String> list = dataList.get(0);
			list.stream().forEach(e -> {
				temporaryResultList.add(Lists.newArrayList(e));
			});
		} else {
			List<String> list = dataList.get(index);

			for (List<String> data : resultList) {
				for (String s : list) {
					// 复制数组并扩充新元素
					ArrayList<String> arrayList = Lists.newArrayList(data);
					arrayList.addAll(data);
					arrayList.set(arrayList.size() - 1, s);
					temporaryResultList.add(arrayList);
				}
			}
		}
		index = index + 1;
		return recursionMethod(dataList, index, temporaryResultList);
	}
}
