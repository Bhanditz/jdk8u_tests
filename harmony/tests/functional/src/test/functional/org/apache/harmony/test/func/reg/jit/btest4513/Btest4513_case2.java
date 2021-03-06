/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
*/
 
package org.apache.harmony.test.func.reg.jit.btest4513;

/*
 * This is a testcase form 
 * "Additional Comment #2 From stepan.m.mishura 2005-09-13 11:30"  
 */

import java.security.Provider;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateFactorySpi;
import java.util.List;

public class Btest4513_case2 {   
    
    public static void main(String[] args) throws Exception  {    
        System.err.println("Start Btest4513_case2 ...");

        System.err.println("foo ...");
        boolean ret1 = foo();

        System.err.println("(new Btest4513_case2()).test() ...");
        boolean ret2 = (new Btest4513_case2()).test();
        
        if (ret1 && ret2) {
            System.err.println("PASSED!");
        } else {
            System.err.println("FAILED!");
        }
    }

    public static boolean foo() throws Exception {
        boolean ret = true;

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        try {
            cf.generateCertPath((List)null);
            System.err.println("NullPointerException was not thrown!");
        } catch (NullPointerException e) {
            System.err.println("NullPointerException was thrown as expected");
        }
        return ret;
    }

    public boolean test() throws Exception {
        return foo();
    }
}
