/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */    
/**
 * @author Anatoly F. Bondarenko
 * @version $Revision: 1.2 $
 */

/**
 * Created on 18.10.2005 
 */

package org.apache.harmony.test.stress.jpda.jdwp.scenario.EVENT009;

import org.apache.harmony.test.stress.jpda.jdwp.scenario.share.StressDebuggee;

public class EventDebuggee009 extends StressDebuggee {
    public static final long FREE_MEMORY_LIMIT = EVENT009_FREE_MEMORY_LIMIT; 
    public static final int THREAD_NUMBER_LIMIT = EVENT009_THREAD_NUMBER_LIMIT; 
    public static final String THREAD_NAME_PATTERN = "EventDebuggee009_Thread_"; 

    static EventDebuggee009 eventDebuggee009This;

    static volatile boolean allThreadsToContinue = false;
    static volatile boolean allThreadsToFinish = false;
    static int createdThreadsNumber = 0;
    static volatile int startedThreadsNumber = 0;

    static EventDebuggee009_Thread[] eventDebuggee009Threads = null;

    public void run() {
        
        logWriter.println("--> EventDebuggee009: START...");
        eventDebuggee009This = this;

        logWriter.println("--> EventDebuggee009: Create and start big number of threads...");
        Runtime currentRuntime = Runtime.getRuntime();
        long freeMemory = currentRuntime.freeMemory();
        logWriter.println
        ("--> EventDebuggee009: freeMemory (bytes) BEFORE creating threads = " + freeMemory);

        try {
            eventDebuggee009Threads = new EventDebuggee009_Thread[THREAD_NUMBER_LIMIT]; 
            for (int i=0; i < THREAD_NUMBER_LIMIT; i++) {
                String threadName = THREAD_NAME_PATTERN + i;
                eventDebuggee009Threads[i]= new EventDebuggee009_Thread(threadName);
                eventDebuggee009Threads[i].start();
                createdThreadsNumber++;
                freeMemory = currentRuntime.freeMemory();
                if ( freeMemory < FREE_MEMORY_LIMIT ) {
                    logWriter.println
                    ("--> EventDebuggee009: FREE_MEMORY_LIMIT (" + 
                            FREE_MEMORY_LIMIT + ") is reached!");
                    break;   
                }
            }
        } catch ( Throwable thrown) {
            logWriter.println
            ("--> EventDebuggee009: Exception while creating threads: " + thrown);
        }
        logWriter.println
        ("--> EventDebuggee009: Created threads number = " + createdThreadsNumber);
        
        while ( startedThreadsNumber != createdThreadsNumber ) {
            waitMlsecsTime(10);
        }
        logWriter.println
        ("--> EventDebuggee009: All created threads are started!");
        
        freeMemory = currentRuntime.freeMemory();
        logWriter.println
        ("--> EventDebuggee009: freeMemory (bytes) AFTER creating and starting threads = " + 
                freeMemory);

        printlnForDebug("EventDebuggee009: sendSignalAndWait(SIGNAL_READY_01)");
        sendSignalAndWait(SIGNAL_READY_01);
        printlnForDebug("EventDebuggee009: After sendSignalAndWait(SIGNAL_READY_01)");

        logWriter.println
        ("--> EventDebuggee009: Send signal to all threads to continue and call test method...");
        allThreadsToContinue = true;

        logWriter.println
        ("--> EventDebuggee009: Wait for all started threads to finish...");

        for (int i=0; i < createdThreadsNumber; i++) {
            while ( eventDebuggee009Threads[i].isAlive() ) {
                waitMlsecsTime(10);
            }
        }
        logWriter.println
        ("--> EventDebuggee009: All threads finished!");

        freeMemory = currentRuntime.freeMemory();
        logWriter.println
        ("--> EventDebuggee009: freeMemory (bytes) AFTER all threads finished= " + 
                freeMemory);

        printlnForDebug("EventDebuggee009: sendThreadSignalAndWait(SIGNAL_READY_02)");
        sendThreadSignalAndWait(SIGNAL_READY_02);
        printlnForDebug("EventDebuggee009: After sendThreadSignalAndWait(SIGNAL_READY_02)");

        logWriter.println("--> EventDebuggee009: FINISH...");

    }

    public static void main(String [] args) {
        runDebuggee(EventDebuggee009.class);
    }

}

class EventDebuggee009_Thread extends Thread {
    long[] longArray = null;
    String myName = null;
    public EventDebuggee009_Thread(String name) {
        super(name);
        myName = name;
        longArray = new long[1000];
    }
    
    void testMethod() {
        int intVar = 1;
        long longVar = 2;
    }

    public void run() {
        EventDebuggee009 parent = EventDebuggee009.eventDebuggee009This;
        synchronized (parent) { 
            EventDebuggee009.startedThreadsNumber++;
        }
        while ( ! EventDebuggee009.allThreadsToContinue ) {
            EventDebuggee009.waitMlsecsTime(100);
        }
        
        testMethod();
        
    }
}


