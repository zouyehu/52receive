package com.winnerlook.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/mis"})
public class ReceiveService
{
  @RequestMapping({"/receiveRecordPush"})
  @ResponseBody
  public void receiveRecordPush(HttpServletRequest request, HttpServletResponse response)
  {
//    String logninfo = request.getParameter("user");
//    JSONObject data = JSONObject.fromObject(logninfo);
//    
//    System.out.println(data);
	  System.out.println("你好，虎哥！");
  }
}