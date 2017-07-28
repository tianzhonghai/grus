package cn.linye.grus.infrastructure.exception;

import cn.linye.grus.infrastructure.RespEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tim on 2017/7/16.
 */
public class BizException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(BizException.class);

    private static final long serialVersionUID = 4418564609919928814L;
    private RespEnum errorCode;

    public BizException(RespEnum errorCode, String msg) {

        this(errorCode, msg, null);
    }

    public BizException(String msg) {
        this(RespEnum.FAIL, msg);
    }

    public BizException(RespEnum errorCode) {
        this(errorCode,errorCode.getMsg(),null);
    }

    public BizException(String msg, Throwable cause ) {
        this(RespEnum.FAIL, msg, cause );
    }

    public BizException(RespEnum errorCode, String msg, Throwable cause) {
        super(msg, cause);
        if (errorCode == null) {
            throw new IllegalArgumentException("errorCode is null");
        }
        this.errorCode = errorCode;
        logger.error(msg,cause);
    }

    public RespEnum getErrorCode() {
        return errorCode;
    }

    public static void throwBizException(RespEnum errorCode,String message,Throwable cause){
        throw new BizException(errorCode,message,cause);
    }

    public static void throwBizException(RespEnum errorCode,String message){
        throwBizException(errorCode,message,null);
    }

    public static void throwIllegalArgument(String message, Throwable cause){
        throwBizException(RespEnum.ILLEGAL_ARGUMENT,message,cause);
    }
    public static void throwIllegalArgument(String message){
        throwBizException(RespEnum.ILLEGAL_ARGUMENT,message,null);
    }

    public static void throwFail(String message, Throwable cause){
        throwBizException(RespEnum.FAIL,message,cause);
    }
    public static void throwFail(String message){
        throwBizException(RespEnum.FAIL,message,null);
    }
}
