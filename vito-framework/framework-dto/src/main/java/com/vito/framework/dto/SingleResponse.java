package com.vito.framework.dto;

/**
 * Response with single record to return
 * <p/>
 *
 * @author panjin
 */
public class SingleResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse success() {
        SingleResponse response = new SingleResponse();
        response.setCode(DtoConstants.SUCCESS_CODE);
        return response;
    }

    public static SingleResponse failure(int code, String message) {
        SingleResponse response = new SingleResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setCode(DtoConstants.SUCCESS_CODE);
        response.setData(data);
        return response;
    }
}
