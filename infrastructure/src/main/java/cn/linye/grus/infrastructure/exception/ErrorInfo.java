package cn.linye.grus.infrastructure.exception;

/**
 * Created by Tim on 2017/7/16.
 */
public class ErrorInfo<T> {
    public static final Integer OK = 200;
    public static final Integer ERROR = 500;

    private Integer status;
    private String message;
    private String url;
    private T data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}