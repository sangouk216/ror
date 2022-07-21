package kr.ror.common.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class ConvUtil {
	
	public String make(BufferedReader br) throws Exception {
		StringBuilder sb = new StringBuilder();
        char[] charBuffer = new char[128];
        int bytesRead = -1;
        while ((bytesRead = br.read(charBuffer)) > 0) {
        	sb.append(charBuffer, 0, bytesRead);
        }		
        br.close();
        return new String(sb.toString().getBytes(), "UTF-8");
	}

	public Map<String, Object> convert(JSONObject jsonData) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<?> keys = jsonData.keys();
		String key = "";
        while(keys.hasNext()) map.put((key=(String)keys.next()),jsonData.get(key));
        
        
        return map;
	}
	
	public JSONObject convert(Map<String, Object> map) throws Exception {
		JSONObject jsonData = new JSONObject();
		Iterator<?> keys = map.keySet().iterator();
		String key = "";
		while(keys.hasNext()) jsonData.put((key=(String)keys.next()), map.get(key));
		return jsonData;
	}
	
	public JSONObject convert(List<Object> child, String name) throws Exception {
		JSONObject jsonData = new JSONObject();
		jsonData.put(name, child);
		return jsonData;
	}
	
	public JSONObject convert(JSONObject child, String name) throws Exception {
		JSONObject jsonData = new JSONObject();
		return jsonData.put(name, child);
	}		
}
