package com.vito.framework.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Response with batch record to return,
 * usually use in conditional query
 * <p/>
 * @author panjin
 */
public class MultiResponse<T> extends Response {
    
    private static final long serialVersionUID = 1L;
    
    private Collection<T> data;

    public Collection<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        if (data instanceof List) {
            return (List<T>) data;
        }
        return new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }
    
    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }
    
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static MultiResponse success() {
        MultiResponse response = new MultiResponse();
        response.setCode(DtoConstants.SUCCESS_CODE);
        return response;
    }

    public static MultiResponse failure(int code, String message) {
        MultiResponse response = new MultiResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> MultiResponse<T> of (Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setCode(DtoConstants.SUCCESS_CODE);
        response.setData(data);
        return response;
    }
}
