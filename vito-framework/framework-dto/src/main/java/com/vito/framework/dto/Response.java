package com.vito.framework.dto;

/**
 * Response to caller.
 *
 * @author panjin
 */
public class Response extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public static Response success() {
        Response response = new Response();
        response.setCode(DtoConstants.SUCCESS_CODE);
        return response;
    }

    public static Response failure(int code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
