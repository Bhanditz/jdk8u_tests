;    Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
;
;    Licensed under the Apache License, Version 2.0 (the "License");
;    you may not use this file except in compliance with the License.
;    You may obtain a copy of the License at
;
;       http://www.apache.org/licenses/LICENSE-2.0
;
;    Unless required by applicable law or agreed to in writing, software
;    distributed under the License is distributed on an "AS IS" BASIS,
;    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;
;    See the License for the specific language governing permissions and
;    limitations under the License.

;
; Author: Ilia A. Leviev
; Version: $Revision: 1.3 $
;
.class public org/apache/harmony/vts/test/vm/jvms/instructions/stack/dup2/dup205/dup20501/dup20501p
.super java/lang/Object

; standard initializer
.method public <init>()V
   aload_0
   invokespecial java/lang/Object/<init>()V
   return
.end method
; test method

.method public test([Ljava/lang/String;)I
   .limit stack 5
   .limit locals 5
 

 sipush 10
 ldc 2147483647
 ldc 2147483647
 dup2
 istore 1
 istore 2
 istore 3
 istore 4
 iload 1
 ldc 2147483647
 if_icmpne L3
 iload 2
 ldc 2147483647
 if_icmpne L3
 iload 3
 ldc 2147483647
 if_icmpne L3
 iload 4
 ldc 2147483647
 if_icmpne L3
 
 sipush  10
 if_icmpeq L4
 sipush  105
 ireturn
   
 
L4:
 sipush  104
 ireturn
  
L3:
 sipush  105
 ireturn  
  
.end method



;
;standard main function
.method public static main([Ljava/lang/String;)V
  .limit stack 2
  .limit locals 1

  new org/apache/harmony/vts/test/vm/jvms/instructions/stack/dup2/dup205/dup20501/dup20501p
  dup
  invokespecial org/apache/harmony/vts/test/vm/jvms/instructions/stack/dup2/dup205/dup20501/dup20501p/<init>()V
  aload_0
  invokevirtual org/apache/harmony/vts/test/vm/jvms/instructions/stack/dup2/dup205/dup20501/dup20501p/test([Ljava/lang/String;)I
  invokestatic java/lang/System/exit(I)V
  return
.end method


















