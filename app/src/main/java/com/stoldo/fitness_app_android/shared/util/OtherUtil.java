package com.stoldo.fitness_app_android.shared.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import com.stoldo.fitness_app_android.model.interfaces.Subscriber;
import com.stoldo.fitness_app_android.service.SingletonService;

import java.util.Arrays;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OtherUtil {
    // TODO replace with model
    public static Map<String, Object> getEditMenuEventMap(String actionValue, Boolean editModeValue) {
        return getEventMap(Arrays.asList("action", "editMode"), Arrays.asList(actionValue, editModeValue));
    }

    // TODO replace with model
    public static Map<String, Object> getTimerEventMap(Subscriber subscriberValue, Integer secondsValue) {
        return getEventMap(Arrays.asList("subscriber", "seconds"), Arrays.asList(subscriberValue, secondsValue));
    }

    // TODO replace with model
    public static Map<String, Object> getEventMap(List<String> keys, List<Object> values) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

    public static int convertDpToPixel(int dp, Context context){
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

    // TODO unify
    public static void runSetter(Field field, Object o, Object value) {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        method.invoke(o, value);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        Log.e("OtherUtil", "Could not determine method: " + method.getName());
                    }

                }
            }
        }
    }

    // TODO unify
    public static Object runGetter(Field field, Object o) {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(o);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        Log.e("OtherUtil", "Could not determine method: " + method.getName());
                    }

                }
            }
        }

        return null;
    }

    public static boolean isValidIndex(int index, int listSize) {
        return index >= 0 && index < listSize;
    }

    // TODO implement in savable
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static Object getService(Class serviceClass) {
        return SingletonService.getInstance(null).getSingletonByClass(serviceClass);
    }
}
