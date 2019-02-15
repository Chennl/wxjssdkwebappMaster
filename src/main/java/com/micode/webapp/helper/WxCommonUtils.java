package com.micode.webapp.helper;

 
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
 
 
public class WxCommonUtils {
	
	public static String APPID ="wx743b2ddda1bec8b8";
	public static String SECRET="76da573cba7fb93b7f2bb3a4e03540a9";

	public static String getWxAppId() {
		return APPID;
	}
	public static String getAccessToken() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET+"";
 		ResponseEntity<String> response  = restTemplate.getForEntity(url, String.class);
 		if(response.getStatusCode() ==HttpStatus.OK) {
 			return response.getBody();
 		}
		return "";
	}
	  public static String getJsApiTicket(String access_token) throws Exception{
	        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
	        RestTemplate restTemplate = new RestTemplate();
	 		ResponseEntity<String> response  = restTemplate.getForEntity(url, String.class);
	 		if(response.getStatusCode() ==HttpStatus.OK) {
	 			return response.getBody();
	 		}
			return "";

	  }
 
}
