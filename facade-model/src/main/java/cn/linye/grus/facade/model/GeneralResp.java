package cn.linye.grus.facade.model;

/**
 * @Author tianzhonghai
 * @Date 2017/7/19.
 */
public class GeneralResp<T> {
    private int status;
    private String message;
    private T data;

    public GeneralResp(){
        this.status = 200;
        this.message = "";
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
