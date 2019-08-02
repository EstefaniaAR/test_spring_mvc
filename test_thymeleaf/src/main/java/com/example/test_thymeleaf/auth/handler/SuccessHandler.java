package com.example.test_thymeleaf.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException 
	{
		SessionFlashMapManager flashManager = new SessionFlashMapManager();
		
		FlashMap flashMap = new FlashMap();
		flashMap.put("success","Welcome "+authentication.getName());
		flashManager.saveOutputFlashMap(flashMap, request, response);
		if(authentication!=null)
		{
			logger.info("success: usuaro has been logged sucessfuly: '"+authentication.getName()+"'");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
	

}
