package org.nclab.testclasses;

import org.apache.log4j.Logger;
import org.nclab.annotations.Component;
import org.nclab.annotations.Initialize;

@Component
public class Test4 {
    private static final Logger logger = Logger.getLogger(Test4.class.getName());

    public void init(){
        logger.info("Running init() in "+this.getClass().getSimpleName());
    }
}

