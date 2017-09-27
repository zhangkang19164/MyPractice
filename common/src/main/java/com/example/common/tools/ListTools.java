package com.example.common.tools;

import java.util.List;

/**
 * create time : 2017/09/26
 * desc        : List 工具类
 */

public class ListTools {
    /**
     * 判断list是不空的
     *
     * @param list 需要判断的list
     * @return 如果为null或者为空 输出true
     */
    public static boolean isEmpty(List list) {
        return null == list || list.isEmpty();
    }

}
