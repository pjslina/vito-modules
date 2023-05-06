package com.vito.framework.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 移动端的分页对象
 * @author panjin
 */
public class MobilePageResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数，包含本次返回的记录数
     * 比如：这次返回了10条，总共有100条，那么这个值就是100
     */
    private int totalCount = 0;

    private int pageSize = 1;

    private String nextPageToken;

    private Collection<T> data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<T> getData() {
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

    public int getTotalPages() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount
                / this.pageSize : (this.totalCount / this.pageSize) + 1;
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static MobilePageResponse buildSuccess() {
        MobilePageResponse response = new MobilePageResponse();
        response.setSuccess(true);
        return response;
    }

    public static MobilePageResponse buildFailure(String errCode, String errMessage) {
        MobilePageResponse response = new MobilePageResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> MobilePageResponse<T> of(int pageSize) {
        MobilePageResponse<T> response = new MobilePageResponse<>();
        response.setSuccess(true);
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setNextPageToken("");
        return response;
    }

    public static <T> MobilePageResponse<T> of(Collection<T> data, int totalCount, int pageSize, String nextPageToken) {
        MobilePageResponse<T> response = new MobilePageResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setNextPageToken(nextPageToken);
        return response;
    }
}
