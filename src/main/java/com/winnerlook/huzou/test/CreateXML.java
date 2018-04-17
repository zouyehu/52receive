package com.winnerlook.huzou.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * 使用org.w3c.dom自动创建xml
 * @author Hu
 *
 */
public class CreateXML {
	
private static void createBZXML(List<Map<String, String>> createSmilList, String mmsFile, List<String> textList) {
		
		try {
			
			org.w3c.dom.Element page = null;
			org.w3c.dom.Element img = null;
			org.w3c.dom.Element textElement = null;
			org.w3c.dom.Text context = null;
			org.w3c.dom.Text textValue = null;
			String resultString = null;
			
			javax.xml.parsers.DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.newDocument();
			//构建XML中的节点
			org.w3c.dom.Element mms = doc.createElement("mms");
			org.w3c.dom.Element subject = doc.createElement("subject");
			org.w3c.dom.Text nameValue = doc.createTextNode(createSmilList.get(0).get("mmsName"));
			org.w3c.dom.Element pages = doc.createElement("pages");
			if (createSmilList != null && createSmilList.size() > 0) {
				for (Map<String, String> smilMap : createSmilList) {
					
					page = doc.createElement("page");
					page.setAttribute("dur", smilMap.get("playTime"));
					img = doc.createElement("img");
					
					String imgPath = mmsFile + File.separator + smilMap.get("img");
					String filenameString = imgPath.substring(imgPath.lastIndexOf('/') + 1);
					if (filenameString.toLowerCase().indexOf(".jpg") != -1) {
						img.setAttribute("type", "image/jpeg");
					} else {
						img.setAttribute("type", "image/gif");
					}
					
					if (StringUtils.isNotBlank(smilMap.get("img"))) {
						
						InputStream in = null;
						byte[] data = null;
						try {
							in = new FileInputStream(imgPath);
							data = new byte[in.available()];
							in.read(data);
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("GetImageStr22 文件" + imgPath + " 长度:" + data.length);
						//图片文件 转换字节数组进行Base64编码
						 BASE64Encoder encoder = new BASE64Encoder();
						 resultString = encoder.encode(data);

						 System.out.println("base64后值值:" + resultString);
						 System.out.println("base64后值长度:" + resultString.length());
						 
					}
					 context = doc.createTextNode(resultString);
					 textElement = doc.createElement("text");
					 textValue = doc.createTextNode(textList.get(0));
					 textList.remove(0);
					 
					 //根节点pages下有多个page节点，循环生成
					 pages.appendChild(page);
					 page.appendChild(img);
					 page.appendChild(textElement);
					 subject.appendChild(nameValue);
					 img.appendChild(context);
					 textElement.appendChild(textValue);
				}

			}
			
	       //按顺序添加各个节点
			doc.appendChild(mms);
			mms.appendChild(subject);
			mms.appendChild(pages);
//			pages.appendChild(page);
//			page.appendChild(img);
//			page.appendChild(textElement);
//			subject.appendChild(nameValue);
//			img.appendChild(context);
//			textElement.appendChild(textValue);
			
			Transformer t=TransformerFactory.newInstance().newTransformer();
			//设置换行和缩进
			t.setOutputProperty(OutputKeys.INDENT,"yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(new File(mmsFile + File.separator + "mms.xml"))));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
