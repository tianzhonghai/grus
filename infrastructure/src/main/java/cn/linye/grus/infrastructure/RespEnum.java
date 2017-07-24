package cn.linye.grus.infrastructure;

/**
 * @Author tianzhonghai
 * @Date 2017/7/24.
 */
public enum RespEnum {
    ILLEGAL_ARGUMENT(100,"请求参数非法"),
    SUCCESS(200,"ok"),
    FAIL(500,"请求处理失败"),
            ;
    private int value = 200;
    private String msg;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    RespEnum(int value,String msg){
        this.value = value;
        this.msg = msg;
    }

    public static RespEnum getByValue(int val) {
        for (RespEnum errorCode : RespEnum.values()) {
            if (errorCode.getValue() == val) {
                return errorCode;
            }
        }
        return null;
    }
}
