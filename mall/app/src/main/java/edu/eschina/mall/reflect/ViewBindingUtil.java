package edu.eschina.mall.reflect;

import android.view.LayoutInflater;

import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * viewBinding反射工具类
 */
public class ViewBindingUtil {
    public static <T extends ViewBinding> T getViewBinding(LayoutInflater layoutInflater, Class<?> clazz) {

        try {
            ParameterizedType type;
            try {
                type = (ParameterizedType) clazz.getGenericSuperclass();

            } catch (Exception e) {
                type = (ParameterizedType) clazz.getSuperclass().getGenericSuperclass();
            }

            Class<T> tClass = (Class<T>) type.getActualTypeArguments()[0];
            Method method = tClass.getMethod("inflate", LayoutInflater.class);

            return (T) method.invoke(null, layoutInflater);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
