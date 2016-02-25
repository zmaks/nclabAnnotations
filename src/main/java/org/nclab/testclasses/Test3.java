package org.nclab.testclasses;

import org.apache.log4j.Logger;
import org.nclab.annotations.Component;
import org.nclab.annotations.Initialize;

public class Test3 {
    private static final Logger logger = Logger.getLogger(Test3.class.getName());
    @Initialize(lazy = true)
    public void init(){
        logger.info("Running init() in "+this.getClass().getSimpleName());
    }
}

