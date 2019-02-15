package com.micode.webapp.service;

 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micode.webapp.entity.AccessToken;
import com.micode.webapp.entity.JssdkTicket;
import com.micode.webapp.helper.Sign;
import com.micode.webapp.helper.WxCommonUtils;
import com.micode.webapp.repository.AccessTokenRepository;
import com.micode.webapp.repository.JssdkTicketRepository;

@Service
@Transactional
public class WxJssdkServiceImpl implements WxJssdkService {

	@Autowired
	AccessTokenRepository accessTokenRepository;
	@Autowired
	JssdkTicketRepository jssdkTicketRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	private String flushToken() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String jsonToken =WxCommonUtils.getAccessToken();
	//	String jsonToken="{\"access_token\":\"18_4DF0Z-Zg0ie4LJCuSFFK0gzto9_MtPB6jzg0x4JnYwkZ94HNEW2lpUGzmz9-3rXIV5RA0P2CUy2QcGMelTgeQYYn0CHjWelaBibaQYTkOJjvw5IMViBs4wCc9S1fyiiCmbUEwXdCL14K84-fGCVaAFAQMF\",\"expires_in\":7200}";
		AccessToken accessToken = objectMapper.readValue(jsonToken, AccessToken.class);		 
		accessToken.setCreateDate(format.format(new Date()));
		accessTokenRepository.deleteAll();
		accessTokenRepository.saveAndFlush(accessToken);
		return accessToken.getAccessToken();
	}
	@Override
	public String getAccessToken() throws Exception {

			List<AccessToken> list = accessTokenRepository.findAll();
			if(list.size()>0) {
				AccessToken token = list.get(0);
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");			
				Date createDate = format.parse(token.getCreateDate());
				Date now = new Date();
				long between =  now.getTime() -  createDate.getTime() -  7260*1000;
				if(between>0) {
					return flushToken();
				}else
				{
					return token.getAccessToken();
				}
			}else {
				return flushToken();
			}
	 
	}
	
	private String flushTicket(String accessToken) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String jsonTicket =WxCommonUtils.getJsApiTicket(accessToken);
		JssdkTicket jssdkTicket = objectMapper.readValue(jsonTicket, JssdkTicket.class);
		jssdkTicket.setCreateDate(format.format(new Date()));
		jssdkTicket.setTicket(jssdkTicket.getTicket().replaceAll("\"", ""));
		jssdkTicketRepository.deleteAll();
		jssdkTicketRepository.saveAndFlush(jssdkTicket);
		return jssdkTicket.getTicket();
	}

	@Override
	public String getJsApiTicket(String accessToken) throws Exception{			
  
		List<JssdkTicket> list = jssdkTicketRepository.findAll();
		if(list.size()>0) {
			JssdkTicket ticket = list.get(0);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date createDate = format.parse(ticket.getCreateDate());
			Date now = new Date();
			long between =  now.getTime() -  createDate.getTime() - 7260*1000;
			if(between>0) {
				return flushTicket(accessToken);
			}else
			{	return ticket.getTicket();
				
			}
		}else {
			return flushTicket(accessToken);
		}

	}
	
	@Override
	public  Map<String, String> getSign(String url) {
		try {
		String accessToken = getAccessToken();
		if(accessToken.length()==0) {
			System.out.println("fail to get access token form wechat online.");
			return null;
		}else {
			String jsapi_ticket = getJsApiTicket(accessToken);
			Map<String, String> map = Sign.sign(jsapi_ticket, url);
			map.put("appId", WxCommonUtils.getWxAppId());
			return map;
		}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	* 将该方法配置成定时任务，程序启动后五秒自动调用，
	* 之后每隔一小时刷新一次
	*/
	public void flush(){
	 
		System.out.println("===================我被调用了=================");
	 
//		try {
//			//获取access_token
//			String accessToken = WxCommonUtils.getAccessToken();
//			this.access_token = accessToken;
//		 
//			//获取jsapi_ticket
//			String jsApiTicket = WxCommonUtils.getJsApiTicket(accessToken);
//			this.jsapi_ticket = jsApiTicket;
//	 
//	 
//		} catch (Exception e) {
//		System.out.println("刷新access_token失败");
//		e.printStackTrace();
//		}
	 
	}
	@Override
	public Map<String, String> getSign(String jsapi_ticket, String url) {
		Map<String, String> map = Sign.sign(jsapi_ticket, url);
		map.put("appId", WxCommonUtils.getWxAppId());
		return map;
	}



	 

}
