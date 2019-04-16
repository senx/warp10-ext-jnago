package io.warp10.script.ext.jnago;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class JNAGo {
  // GoSlice class maps to:
  // C type struct { void *data; GoInt len; GoInt cap; }
  public static class GoSlice extends Structure {
    public static class ByValue extends GoSlice implements Structure.ByValue {}
    public Pointer data;
    public long len;
    public long cap;
    protected List getFieldOrder(){
      return Arrays.asList(new String[] { "data", "len", "cap" });
    }
    
    public static GoSlice fromArray(long[] longs) {
      GoSlice.ByValue slice = new GoSlice.ByValue();
      slice.data = new Memory(longs.length * Native.getNativeSize(Long.TYPE));
      slice.data.write(0, longs, 0, longs.length);
      slice.len = longs.length;
      slice.cap = longs.length;
      return slice;
    }
    
    public static GoSlice fromArray(double[] doubles) {
      GoSlice.ByValue slice = new GoSlice.ByValue();
      slice.data = new Memory(doubles.length * Native.getNativeSize(Double.TYPE));
      slice.data.write(0, doubles, 0, doubles.length);
      slice.len = doubles.length;
      slice.cap = doubles.length;
      return slice;
    }
    
    public static GoSlice fromArray(int[] ints) {
      GoSlice.ByValue slice = new GoSlice.ByValue();
      slice.data = new Memory(ints.length * Native.getNativeSize(Integer.TYPE));
      slice.data.write(0, ints, 0, ints.length);
      slice.len = ints.length;
      slice.cap = ints.length;
      return slice;
    }

    public static GoSlice fromArray(float[] floats) {
      GoSlice.ByValue slice = new GoSlice.ByValue();
      slice.data = new Memory(floats.length * Native.getNativeSize(Float.TYPE));
      slice.data.write(0, floats, 0, floats.length);
      slice.len = floats.length;
      slice.cap = floats.length;
      return slice;
    }

    public static GoSlice fromArray(short[] shorts) {
      GoSlice.ByValue slice = new GoSlice.ByValue();
      slice.data = new Memory(shorts.length * Native.getNativeSize(Short.TYPE));
      slice.data.write(0, shorts, 0, shorts.length);
      slice.len = shorts.length;
      slice.cap = shorts.length;
      return slice;
    }

    public static GoSlice fromArray(byte[] bytes) {
      GoSlice.ByValue slice = new GoSlice.ByValue();
      slice.data = new Memory(bytes.length * Native.getNativeSize(Byte.TYPE));
      slice.data.write(0, bytes, 0, bytes.length);
      slice.len = bytes.length;
      slice.cap = bytes.length;
      return slice;
    }
    
    public static GoSlice fromArray(Object[] objects) {
      long[] nativePointers = new long[objects.length];
      for (int i = 0; i < objects.length; i++) {
        if (objects[i] instanceof Pointer) {
          nativePointers[i] = Pointer.nativeValue((Pointer) objects[i]);
        } else if (objects[i] instanceof Structure) {
          nativePointers[i] = Pointer.nativeValue(((Structure) objects[i]).getPointer()); 
        } else {
          throw new RuntimeException("Invalid object, can only convert Pointer and Structures.");
        }
      }
      return fromArray(nativePointers);
    }
  }

  // GoString class maps to:
  // C type struct { const char *p; GoInt n; }
  public static class GoString extends Structure implements Structure.ByValue {
    public static class ByValue extends GoString implements Structure.ByValue {}
    public String p;
    public long n;
    protected List getFieldOrder(){
      return Arrays.asList(new String[] { "p", "n" });
    }
    public static GoString fromString(String s) {
      GoString.ByValue gs = new GoString.ByValue();
      gs.p = s;
      gs.n = s.length();
      return gs;
    }
  }

  public static class TwoStringsResult extends Structure implements Structure.ByValue {
    public String r0;
    public String r1;

    protected List<String> getFieldOrder() {
        return Arrays.asList("r0", "r1");
    }
  }

  public static class ArraysAndString extends Structure implements Structure.ByValue {
    public Pointer r0;
    public Pointer r1;
    public String r2;

    protected List<String> getFieldOrder() {
        return Arrays.asList("r0", "r1", "r2");
    }
  }
}
