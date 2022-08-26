package cn.caofanqi.apiserver.dto;

import lombok.Data;

/**
 * @author BobbyCao
 * @date 2022/8/20 23:30
 */
@Data
public class Result<T> {

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MSG = "success";

    private int code;

    private String msg;

    private T data;

    /**
     * 成功返回
     * @return result
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    /**
     * 成功返回
     * @param data data
     * @return result
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        result.setData(data);
        return result;
    }



}
