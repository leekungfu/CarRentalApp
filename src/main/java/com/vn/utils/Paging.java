package com.vn.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Paging {
    public static List<Integer> genPageList(Integer totalPages, Integer currentPage){
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) {
                    start = end - 4;
                } else {
                    if (start == 1) {
                        end = start + 4;
                    }
                }
            }
            List<Integer> pageList = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            return pageList;
    }
}
