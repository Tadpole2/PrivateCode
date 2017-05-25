package com.yd.dby.a.sys.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YdSysControllerMainsiteError implements ErrorController  {
	
	private static final String ERROR_PATH = "/error";  
	   
	 @RequestMapping(value=ERROR_PATH)  
	    public String handleError(){  
	        return "pages/404";  
	    }  
	   
	 @Override  
	 public String getErrorPath() {  
		 return ERROR_PATH;  
	 }
}
