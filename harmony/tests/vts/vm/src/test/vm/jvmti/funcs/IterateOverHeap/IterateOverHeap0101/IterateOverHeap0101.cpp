/*
    Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

    See the License for the specific language governing permissions and
    limitations under the License.
*/
/**
 * @author Valentin Al. Sitnick
 * @version $Revision: 1.2 $
 *
 */

/* *********************************************************************** */

#include "events.h"
#include "utils.h"
#include "fake.h"

static bool test = false;
static bool util = false;
static bool flag = false;

static jvmtiIterationControl JNICALL func(jlong, jlong, jlong*, void*);

/* *********************************************************************** */

/**
 * test of function IterateOverHeap0101
 */
void IterateOverHeap0101()
{
    //Fake method for docletting only
}

/* *********************************************************************** */

JNIEXPORT jint JNICALL Agent_OnLoad(prms_AGENT_ONLOAD)
{
    check_AGENT_ONLOAD;

    Callbacks CB;

    cb_exc;
    cb_death;

    AGENT_FOR_EVENTS_TESTS_PART_I; /* events.h */

    jvmtiEvent events[] = { JVMTI_EVENT_EXCEPTION, JVMTI_EVENT_VM_DEATH };

    AGENT_FOR_EVENTS_TESTS_PART_II;

    fprintf(stderr, "\n-------------------------------------------------\n");
    fprintf(stderr, "\ntest IterateOverHeap0101 is started\n{\n");
    fflush(stderr);

    return JNI_OK;
}

/* *********************************************************************** */

void JNICALL callbackException(prms_EXCPT)
{
    check_EXCPT;

    if (flag) return;

    char* name;
    char* signature;
    char* generic;
    jvmtiPhase phase;
    jvmtiError result;

    result = jvmti_env->GetPhase(&phase);

    fprintf(stderr, "\tnative: GetPhase result = %d (must be zero) \n", result);
    fprintf(stderr, "\tnative: current phase is %d (must be 4 (LIVE-phase)) \n", phase);

    if ((result != JVMTI_ERROR_NONE) || (phase != JVMTI_PHASE_LIVE)) return;

    result = jvmti_env->GetMethodName(method, &name, &signature, &generic);
    fprintf(stderr, "\tnative: GetMethodName result = %d (must be zero) \n", result);
    fprintf(stderr, "\tnative: method name is %s \n", name);
    fprintf(stderr, "\tnative: signature name is %s \n", signature);
    fprintf(stderr, "\tnative: generic name is %s \n", generic);
    fflush(stderr);

    if (result != JVMTI_ERROR_NONE) return;

    if (strcmp(name, "special_method")) return;

    flag = true;
    util = true;

    jvmtiHeapObjectFilter object_filter = JVMTI_HEAP_OBJECT_UNTAGGED;
    jvmtiHeapObjectCallback heap_object_callback = func;
    void* user_data = NULL;

    result = jvmti_env->IterateOverHeap( object_filter, heap_object_callback,
            user_data);

    fprintf(stderr, "\tnative: IterateOverHeap result = %d (must be zero) \n",
            result);

    if (result == JVMTI_ERROR_NONE)
        test = true;

}

void JNICALL callbackVMDeath(prms_VMDEATH)
{
    check_VMDEATH;

    fprintf(stderr, "\n\tTest of function IterateOverHeap0101  : ");

    if (test && util)
        fprintf(stderr, " passed \n");
    else
        fprintf(stderr, " failed \n");

    fprintf(stderr, "\n} /* test IterateOverHeap0101 is finished */ \n");
    fflush(stderr);
}

/* *********************************************************************** */

jvmtiIterationControl JNICALL func(jlong, jlong, jlong*, void*)
{
    return (jvmtiIterationControl)NULL;
}

/* *********************************************************************** */

