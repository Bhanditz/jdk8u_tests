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

package org.apache.harmony.test.func.api.java.io.ObjectOutputStream.writeObject0004;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.harmony.test.func.api.java.io.ObjectOutputStream.share.SerializationOnlyTest;

class NotSerializable {
    int i;

    long j;
}

class C implements Serializable {
    int i;

    long j;

    NotSerializable ns = new NotSerializable();

    void fake() {
    };
}

public class writeObject0004 extends SerializationOnlyTest {
    public static void main(String[] args) {
        System.exit(new writeObject0004().test(args));
    }

    protected int testOut(ObjectOutputStream oos) throws IOException {
        try {
            oos.writeObject(new C());
        } catch (NotSerializableException e) {
            return pass();
        }
        return fail("object refers to not serializable class");
    }
}