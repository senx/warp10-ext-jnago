package io.warp10.script.ext.jnago;

import java.util.HashMap;
import java.util.Map;

import io.warp10.warp.sdk.WarpScriptExtension;

public class JNAGoWarpScriptExtension extends WarpScriptExtension {
  
  private static final Map<String,Object> functions;
  
  static {
    functions = new HashMap<String, Object>();
    
  }
  
  @Override
  public Map<String, Object> getFunctions() {
    return functions;
  }

}
