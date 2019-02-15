package com.micode.webapp.controller;

  
import java.util.HashMap;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micode.webapp.service.WxJssdkService;

        
@Controller
public class JssdkController {
  
	@Autowired 
	WxJssdkService wxJssdkService;
	@PostMapping("/jssdkconfig")
	@ResponseBody  
	public Map<String, Object> getconf(HttpServletRequest request,String url){
	 	String jsapi_ticket="kgt8ON7yVITDhtdwci0qeYmSUL3e15xxgEdh1ZV-phOKsfHqn8qwJFIHsxjrsBLGd-rRuNlkqGVB-BOYSfvMbA";
	 	Map<String, String> map =wxJssdkService.getSign(jsapi_ticket, url);
	//	Map<String, String> map =wxJssdkService.getSign( url);
		Map<String,Object> sign= new HashMap<String, Object>();
		sign.put("appId",map.get("appId"));
		sign.put("nonceStr",map.get("nonceStr"));
		sign.put("signature",map.get("signature"));
		sign.put("timestamp",Long.valueOf(map.get("timestamp")));
		return sign;
	}
	@GetMapping("/demo")
	public String getLocation(HttpServletRequest request,Model model) {
//		String url = "http://www.ewushi.cn/jssdk.jsp";
//		url=request.getRequestURL().toString();
//		Map<String, String> map =wxJssdkService.getSign( url);
//		model.addAttribute("map", map);
//		model.addAttribute("timestamp",Integer.valueOf(map.get("timestamp")));
		return "jssdk";
	} 
	@GetMapping("/test")
	public String test(Model model) {
	
		model.addAttribute("str","qweerrrrr");
		model.addAttribute("timestamp",123456);
		return "test";
	}
}

 
