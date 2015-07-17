package com.example.knsi.bugbook.app.test;
import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Bug Book on 03-06-2015.
 */
public class fullTestSuite extends TestSuite{
    public static Test Suite(){

        return new TestSuiteBuilder(fullTestSuite.class).includeAllPackagesUnderHere().build();

    }


}
