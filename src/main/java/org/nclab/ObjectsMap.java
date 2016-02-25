package org.nclab;


import org.apache.log4j.Logger;
import org.nclab.annotations.Initialize;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectsMap {
    private static final Logger logger = Logger.getLogger(ObjectsMap.class.getName());

    private Map<String,Object> map;

    public ObjectsMap(Class<? extends Annotation> anntotaionClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        map = new HashMap<String, Object>();
        loadMap(anntotaionClass);
    }

    private void loadMap(Class<? extends Annotation> anntotaionClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        logger.info("Loading map...");
        Set<Class<?>> annotatedClasses = new Reflections().getTypesAnnotatedWith(anntotaionClass);
        for (Class c : annotatedClasses) {
            logger.info("Found a class "+c.getSimpleName()+" with annotation Component");
            Object object = c.newInstance();
            map.put(c.getSimpleName(),object);
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Initialize.class)) {
                    if (!m.getAnnotation(Initialize.class).lazy()) {
                        logger.info("Found a method with annotation Initialize in class "+c.getSimpleName()+". Lazy == false, invoke now...");
                        m.invoke(object);
                    }
                    else {
                        logger.info("Found a method with annotation Initialize in class "+c.getSimpleName()+". Lazy == true, invoke will be later");
                    }
                }
            }
        }
        logger.info("End of loading map");
    }

    public Object getObject(String simpleClassName) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        logger.info("getObject of "+simpleClassName);
        Object object = null;
        if (map.containsKey(simpleClassName)){
            Class<?> c =  map.get(simpleClassName).getClass();
            object = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Initialize.class)) {
                    if (m.getAnnotation(Initialize.class).lazy()) {
                        logger.info("[getObject] Found a method with annotation Initialize in class "+c.getSimpleName()+". Lazy == true, invoke now...");
                        m.invoke(object);
                    }
                    else {
                        logger.info("[getObject] Found a method with annotation Initialize in class "+c.getSimpleName()+". This object have already initialized.");
                    }
                }
            }
        }
        else {
            logger.info("Cannot found "+simpleClassName);
        }
        return object;
    }
}
