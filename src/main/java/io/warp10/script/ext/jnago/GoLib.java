package io.warp10.script.ext.jnago;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.Pointer;

import io.warp10.script.ext.jnago.JNAGo.GoSlice;
import io.warp10.script.ext.jnago.JNAGo.GoString;

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
