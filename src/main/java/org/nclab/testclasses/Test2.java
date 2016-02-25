package org.nclab.testclasses;

import org.apache.log4j.Logger;
import org.nclab.annotations.Component;
import org.nclab.annotations.Initialize;

@Component
public class Test2 {
    private static final Logger logger = Logger.getLogger(Test2.class.getName());
    @Initialize(lazy = true)
    public void init(){
        logger.info("Running init() in "+this.getClass().getSimpleName());
    }
}

