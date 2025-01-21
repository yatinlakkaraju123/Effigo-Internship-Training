package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SayHelloController {
//"say-hello" => "hello what are you learning today"
	@RequestMapping("/say-hello")
	@ResponseBody
	public String sayHello()
	{
		return "hello what are you learning today";
	}
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String sayHelloHTML()
	{	StringBuffer sb = new StringBuffer();
	sb.append("<html>");
	sb.append("<head>");
	sb.append("<title>hello page</title>");
	sb.append("</head>");
	sb.append("<body>");
	sb.append("<p>this is a hello page</p>");
	sb.append("</body>");
	sb.append("</html>");
	
		return sb.toString();
	}
	@RequestMapping("/say-hello-jsp")
	//@ResponseBody
	public String sayHelloJSP()
	{
		return "sayHello";
	}
	
}
