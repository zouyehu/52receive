package com.winnerlook.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("receive")
public class ReceiveApis {

	@Path("test")
	@POST
	@Produces({ "text/xml", "application/json" })
	@Consumes({ "text/xml", "application/json",
			"application/x-www-form-urlencoded" })
	public String sendMsgTest(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		String result = "";
		JSONObject result_json = null;

		BufferedReader reder = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String str = "";
		while ((str = reder.readLine()) != null) {
			result = result + str;
		}

		if(!"".equals(result)){
			// JSON解析开始
			JSONObject Data = JSONObject.fromObject(result);
			System.out.println(Data.toString());
//			Boolean success = Data.getBoolean("success");
//			if (success) {
				Object obj = Data.get("logninfo");
			
				if (obj instanceof JSONArray) {
					JSONArray errorSmsArr = (JSONArray) obj;
					JSONObject errorSmsObj = null;
					String name = null;
					String phone = null;

					int len = errorSmsArr.size();
					int index = 0;

					while (index < len) {
						errorSmsObj = errorSmsArr.getJSONObject(index);
						name = errorSmsObj.getString("name");
						phone = errorSmsObj.getString("phone");
						String qwlogn = errorSmsObj.getString("qwLogn");
						String car = errorSmsObj.getString("car");
						System.out.println("姓名：" + name);
						System.out.println("手机号：" + phone);
						System.out.println("期望额度:" + qwlogn);
						System.out.println("车辆信息:" + car);
						index++;
					}

				}
//			}
			result_json = new JSONObject();
			result_json.put("status", "0");
		}

		return result_json.toString();
	}
	
	//房速贷第三方推送测试
	@Path("room")
	@POST
	@Produces({ "text/xml", "application/json" })
	@Consumes({ "text/xml", "application/json",
			"application/x-www-form-urlencoded" })
	public String receiveTest(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException {

		String result = "";
		JSONObject result_json = null;

		BufferedReader reder = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String str = "";
		while ((str = reder.readLine()) != null) {
			result = result + str;
		}

		if(!"".equals(result)){
			
			// JSON解析开始
			JSONObject Data = JSONObject.fromObject(result);
			System.out.println(Data.toString());
						
			String name = Data.getString("name");
			String phone = Data.getString("mobile");
			String qwlogn = Data.getString("creditAmount");
			String city = Data.getString("city");
			System.out.println("姓名：" + name);
			System.out.println("手机号：" + phone);
			System.out.println("期望额度:" + qwlogn);
			System.out.println("车辆信息:" + city);
				
			result_json = new JSONObject();
			result_json.put("code", "0");
			result_json.put("showMsg", "成功");
			return result_json.toString();
		}

		return "";
	}
}
