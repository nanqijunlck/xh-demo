package com.fqyc.demo.util;//package com.fqyc.quality.util;
//
//import org.apache.commons.lang3.StringUtils;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ValidateUtils {
//
//    public static boolean  isMobile(String mobile) {
//        if (StringUtils.isEmpty(mobile)) {
//            return false;
//        }
//        Pattern pattern = Pattern.compile("^[1]([3-9])[0-9]{9}$");
//        Matcher matcher = pattern.matcher(mobile);
//        return matcher.matches();
//    }
//
//    public static <T> String validateByGroup(T t, Class<?>... groups) {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<T>> errorSet = validator.validate(t, groups);
//        return validate(errorSet);
//    }
//
//    private static <T> String validate(Set<ConstraintViolation<T>> errorSet) {
//        StringBuilder errorMsg = new StringBuilder();
//        Iterator iterator = errorSet.iterator();
//
//        while(iterator.hasNext()) {
//            ConstraintViolation<?> violation = (ConstraintViolation) iterator.next();
//            errorMsg.append(violation.getMessage()).append(",");
//        }
//
//        if (!StringUtils.isEmpty(errorMsg.toString())) {
//            errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
//            return errorMsg.toString();
//        } else {
//            return "";
//        }
//    }
//
//}
