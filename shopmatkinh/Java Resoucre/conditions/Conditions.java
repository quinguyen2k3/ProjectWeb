package conditions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Conditions {
	public static Map<String, String> toMapStringString(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> newmap = new HashMap<>();
		for(String name : map.keySet()) {
			if(map.get(name)!=null && !map.get(name)[0].isEmpty())
				newmap.put(name, map.get(name)[0]);
		}
		return newmap;
	}
}
