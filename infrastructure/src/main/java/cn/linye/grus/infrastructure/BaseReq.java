package cn.linye.grus.infrastructure;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author tianzhonghai
 * @Date 2017/7/18.
 */
public class BaseReq {
    private static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public void doValidate(){
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(this);

        if(constraintViolations != null && constraintViolations.size() > 0){
            StringBuffer sb = new StringBuffer();
            for (ConstraintViolation item:constraintViolations) {
                sb.append(item.getMessage()).append(";");
            }

            throw new IllegalArgumentException(sb.toString());
        }
    }
}
