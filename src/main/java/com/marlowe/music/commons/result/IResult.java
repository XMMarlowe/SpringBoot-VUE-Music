package com.marlowe.music.commons.result;

/**
 * @program: SpringBoot-VUE-Music
 * @description: 返回结果接口
 * @author: Marlowe
 * @create: 2021-05-29 22:25
 **/
public interface IResult<T> {

    /**
     * 获得信息
     *
     * @return
     */
    String getMsg();

    /**
     * 获得状态码
     *
     * @return
     */
    String getCode();

    /**
     * 获得数据
     *
     * @return
     */
    T getData();
}
