//
//   Copyright 2019  SenX S.A.S.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

package io.warp10.script.ext.jnago;

import java.util.Arrays;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import io.warp10.script.ext.jnago.JNAGo.ArraysAndString;
import io.warp10.script.ext.jnago.JNAGo.TwoStringsResult;

public interface GoLib extends Library {

  String ReturnString(String s);

  TwoStringsResult TwoStrings();

  Pointer ReturnByteArray();

  ArraysAndString ReturnStruct();

  public static void main(String[] args) {
    GoLib golib = (GoLib) Native.load("golib", GoLib.class);
    
    System.out.println("ReturnString('foo')=" + golib.ReturnString("foo"));
    TwoStringsResult ret = golib.TwoStrings();
    System.out.println(ret.r0 + " / " + ret.r1);
    System.out.println(Arrays.toString(golib.ReturnByteArray().getByteArray(0,3)));
    ArraysAndString aas = golib.ReturnStruct();
    System.out.println(Arrays.toString(aas.r0.getByteArray(0,3)));
    System.out.println(Arrays.toString(aas.r1.getByteArray(0,3)));
    System.out.println(aas.r2);
  }
}
