package com.micode.webapp.service;

import java.util.Map;

public interface WxJssdkService  {
	 

	String getAccessToken() throws Exception;
	String getJsApiTicket(String accessToken) throws Exception;
	Map<String, String> getSign(String url);
	Map<String, String> getSign(String ticket,String url);
	 
}
