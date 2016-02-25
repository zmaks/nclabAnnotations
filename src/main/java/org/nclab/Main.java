package org.nclab;

import org.apache.log4j.Logger;
import org.nclab.annotations.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Максим on 25.02.2016.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            ObjectsMap objectsMap = new ObjectsMap(Component.class);

            objectsMap.getObject("Test1");
            objectsMap.getObject("Test2");
            objectsMap.getObject("Test3");
            objectsMap.getObject("Test4");
            objectsMap.getObject("Test2");
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
        }


    }
}
