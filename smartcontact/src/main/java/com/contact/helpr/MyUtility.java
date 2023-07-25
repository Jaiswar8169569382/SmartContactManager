package com.contact.helpr;

import javax.servlet.http.HttpServletRequest;

public class MyUtility {

	public static String generateUrl(HttpServletRequest request)
	{
		String requestUrl=request.getRequestURL().toString();
		
		return requestUrl.replace(request.getServletPath(), "");
	}
}
