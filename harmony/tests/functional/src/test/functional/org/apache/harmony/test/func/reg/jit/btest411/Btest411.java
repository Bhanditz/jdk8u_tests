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

/*
 * Compiled TestParent class should be deleted before the test running!
*/

package org.apache.harmony.test.func.reg.jit.btest411;

public class Btest411 {
    public static void main(String[] args) {
        System.err.println("Case1: ");
        try {
            new TestParent();
        } catch (Throwable t) {
            System.err.println("Expected exception: " + t);
        }

        System.err.println("Case2: ");
        try {
            new TestParent();
        } catch (Throwable t) {
            System.err.println("Expected exception: " + t);
        }

        System.err.println("Case3: ");
        try {
            new TestChild();
        } catch (Throwable t) {
            System.err.println("Expected exception: " + t);
        }

        System.err.println("Case4: ");
        try {
            new TestChild();
        } catch (Throwable t) {
            System.err.println("Expected Exception: " + t 
                    + "\nTest passed successfully!");
            }
    }
}

