package http;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Run {
	public static void main(String[] args) throws Exception{
		String json="["
				+ "{\"Province\":\"gz\",\"Username\":\"532530199508040233\", \"Password\":\"Pppp0000\"},"
				+ "{\"Province\":\"gz\",\"Username\":\"530102198509171119\", \"Password\":\"Pppp0000\"},"
				+ "{\"Province\":\"gz\",\"Username\":\"532128197509180518\", \"Password\":\"Pppp0000\"}"
				+ "]";
		List<Map<String,Object>> accounts=new ObjectMapper().readValue(json, List.class);
		Map<String,Object> account=accounts.get(1);
		
		Map map=new HashMap<String,Object>();
		map.put("Province", account.get("Province"));
		map.put("Username", account.get("Username"));
		map.put("Password", account.get("Password"));
		
		Map map1=Step1_Home.home(map);
		
		
		map1.put("Province", map.get("Province"));
		Map map2=Step2_Captcha.captcha(map1);
		
		
		map2.put("Province", map.get("Province"));
		map2.put("Username", map.get("Username"));
		map2.put("Password", map.get("Password"));
		map2.put("Set-Cookie",map1.get("Set-Cookie"));
		Map map3=Step3_Login.login(map2);
		
		
		map3.put("Province", map.get("Province"));
		map3.put("Set-Cookie",map1.get("Set-Cookie"));
		Map map4=Step4_UserCtrl.userctrl(map3);
		
		
	}
}
