package com.haoyanbing.algorithm.sublist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 计算子集
 */
public class SubListTest {

    public static void main(String[] args) {
        List<Integer> list = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        System.out.println(pickItemByNum0(list));
        System.out.println(pickItemByNum1(list));
        System.out.println(pickItemByNum2(list));
        System.out.println(pickItemByNum3(list));
        System.out.println(pickItemByNum4(list));
    }

    /**
     * 原始的二进制写法
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> pickItemByNum0(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        int size = nums.size();
        int count = 1 << size;
        for (int i = 0; i < count; i++) {
            List<Integer> subList = new ArrayList<>();
            String binaryString = fillZero(Integer.toBinaryString(i), size);

            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == '1') {
                    subList.add(nums.get(j));
                }
            }
            result.add(subList);
        }
        return result;
    }

    /**
     * 在给定字符串前面添 "0" 至指定位数
     *
     * @param source 原始字符串
     * @param num    指定位数
     */
    private static String fillZero(String source, Integer num) {
        if (source == null || source.length() >= num) {
            return source;
        }
        int length = source.length();
        StringBuilder sourceBuilder = new StringBuilder(source);
        for (int i = 0; i < (num - length); i++) {
            sourceBuilder.insert(0, "0");
        }
        return sourceBuilder.toString();
    }


    /**
     * 二进制
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> pickItemByNum1(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 集合数量
        int size = nums.size();

        // 所有可能的子集的数量
        int count = 1 << size;

        for (int i = 0; i < count; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) != 0) {
                    subList.add(nums.get(j));
                }
            }
            result.add(subList);
        }
        return result;
    }

    /**
     * 逐个枚举
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> pickItemByNum2(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (Integer key : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> presentSubList = result.get(i);
                ArrayList<Integer> copySubList = new ArrayList<>(presentSubList);
                copySubList.add(key);
                result.add(copySubList);
            }
        }
        return result;
    }

    /**
     * DFS
     * 例如集合：[1,2,3]
     * root
     * <p>
     * 1出现                     1不出现
     * 2出现        2不出现       2出现        2不出现
     * 3出现 3不出现 3出现 3不出现 3出现 3不出现 3出现 3不出现
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> pickItemByNum3(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        dfs(nums, 0, result, subList);
        return result;
    }

    private static void dfs(List<Integer> nums, int i, List<List<Integer>> result, List<Integer> subList) {
        // 递归到最后，把当前sublist添加到结果中
        if (i == nums.size()) {
            result.add(new ArrayList<>(subList));
            return;
        }

        // 包含当前节点
        subList.add(nums.get(i));
        dfs(nums, i + 1, result, subList);

        // 不包含当前节点
        subList.remove(subList.size() - 1);
        dfs(nums, i + 1, result, subList);
    }

    private static List<List<Integer>> pickItemByNum4(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        preOrder(nums, -1, result, subList, true);
        return result;
    }

    /**
     * 递归回溯
     *
     * @param nums    源集合
     * @param i       遍历的源集合的下标
     * @param result  解集
     * @param subList 子集
     * @param status  状态，true表示出现在子集，false表示不出现在子集
     */
    private static void preOrder(List<Integer> nums, int i, List<List<Integer>> result, List<Integer> subList, boolean status) {
        if (i == nums.size()) {
            return;
        }

        // 判断当前元素是否需要添加到子集中
        if (status && i >= 0) {
            subList.add(nums.get(i));
        }

        // 继续遍历左右节点
        preOrder(nums, i + 1, result, subList, true);
        preOrder(nums, i + 1, result, subList, false);

        // 判断是否已经是子节点
        if (i == nums.size() - 1) {
            result.add(new ArrayList<>(subList));
        }

        // 重置状态
        if (status && i >= 0) {
            subList.remove(subList.size() - 1);
        }
    }

}
