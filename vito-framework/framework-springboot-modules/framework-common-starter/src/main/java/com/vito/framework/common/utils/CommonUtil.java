package com.vito.framework.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;

/**
 * @author panjin
 */
public class CommonUtil {

    /** Long类型秒级时间长度 */
    public static final long LENGTH_SECOND_OF_LONG = 10L;
    /** Long类型毫秒级时间长度 */
    public static final long LENGTH_MILLI_SECOND_OF_LONG = 13L;

    private CommonUtil() {
    }

    /**
     * 获取对象中值为null的属性名
     * 一般使用于更新操作，更新时只更新值不为null的属性
     * 比如：BeanUtils.copyProperties(user, existingUser, CommonUtil.getNullPropertyNames(user));
     * User save = userRepository.save(existingUser);则只会更新user中不为null的属性
     * @param source 对象
     * @return 值为null的属性名数组
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        HashSet<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
