package com.winnerlook.huzou.test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.winnerlook.huzou.pojo.StatusObj;

/**
 * 利用dom4j和XStream解析xml
 * @author Hu
 *
 */
public class Dom4jTest {

	public static void main(String[] args) {
		
		String xml = "<?xml version='1.0' encoding='utf-8'?><NewDataSet nextID='663724895'>  <result>  "
				+ "  <ID>663320752</ID>    <mobile>13682494194</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706088426575870485101</MessageID>    <statustext>4300</statustext>    <org_messageid />    <inserttime>2017-06-11T10:46:00.7+08:00</inserttime>    <token>70485101</token>    <IP>183.12.64.145</IP>  </result>  "
				+ "<result>    <ID>663331596</ID>    <mobile>13682494194</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706088460780170485101</MessageID>    <statustext>4300</statustext>    <org_messageid />    <inserttime>2017-06-11T14:44:00.45+08:00</inserttime>    <token>70485101</token>    <IP>183.12.64.145</IP>  </result>  <result>    <ID>663435744</ID>    <mobile>18368805153</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128645088370485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T10:22:45.583+08:00</inserttime>    <token>70485101</token>    <IP>180.168.192.126</IP>  </result>  <result>    <ID>663437489</ID>    <mobile>18368805153</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128645097070485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T10:33:01.237+08:00</inserttime>    <token>70485101</token>    <IP>180.168.192.126</IP>  </result>"
				+ "  <result>    <ID>663444637</ID>    <mobile>18368805153</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128645524770485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T11:10:15.433+08:00</inserttime>    <token>70485101</token>    <IP>180.168.192.126</IP>  </result>  <result>    <ID>663444775</ID>    <mobile>18368805153</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128645589270485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T11:11:13.76+08:00</inserttime>    <token>70485101</token>    <IP>180.168.192.126</IP>  </result>  <result>    <ID>663444820</ID>    <mobile>18368805153</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128645589870485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T11:11:50.71+08:00</inserttime>    <token>70485101</token>    <IP>180.168.192.126</IP>  </result>  "
				+ "<result>    <ID>663445064</ID>    <mobile>18368805153</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128645590770485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T11:13:25.457+08:00</inserttime>    <token>70485101</token>    <IP>180.168.192.126</IP>  </result>  <result>    <ID>663499761</ID>    <mobile>17603075873</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706108627417970485101</MessageID>    <statustext>4448</statustext>    <org_messageid />    <inserttime>2017-06-12T16:44:20.66+08:00</inserttime>    <token>70485101</token>    <IP>121.35.209.158</IP>  </result>  <result>    <ID>663522702</ID>    <mobile>18930513756</mobile>    <SrcNumber>106904850485101</SrcNumber>    <MessageID>201706128653827570485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T17:44:11.91+08:00</inserttime>    <token>70485101</token>    <IP>114.80.241.146</IP>  </result>  "
				+ "<result>    <ID>663526672</ID>    <mobile>13068711689</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128654060170485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T18:05:34.76+08:00</inserttime>    <token>70485101</token>    <IP>113.116.63.182</IP>  </result>  <result>    <ID>663527755</ID>    <mobile>13717677901</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128654288470485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T18:07:52.56+08:00</inserttime>    <token>70485101</token>    <IP>124.65.124.178</IP>  </result>  <result>    <ID>663530203</ID>    <mobile>13068711689</mobile>    <SrcNumber>106900290485101</SrcNumber>    <MessageID>201706128654370270485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-12T18:10:32.86+08:00</inserttime>    <token>70485101</token>    <IP>113.116.63.182</IP>  </result>  "
				+ "<result>    <ID>663662858</ID>    <mobile>18930513756</mobile>    <SrcNumber>106904850485101</SrcNumber>    <MessageID>201706138679170870485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-13T10:51:03.733+08:00</inserttime>    <token>70485101</token>    <IP>114.80.241.146</IP>  </result>  <result>    <ID>663721510</ID>    <mobile>18930513756</mobile>    <SrcNumber>106904850485101</SrcNumber>    <MessageID>201706138685959870485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-13T14:10:22.233+08:00</inserttime>    <token>70485101</token>    <IP>114.80.241.146</IP>  </result>  <result>    <ID>663722528</ID>    <mobile>18930513756</mobile>    <SrcNumber>106904850485101</SrcNumber>    <MessageID>201706138686097570485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-13T14:17:14.883+08:00</inserttime>    <token>70485101</token>    <IP>114.80.241.146</IP>  </result> "
				+ " <result>    <ID>663724895</ID>    <mobile>18930513756</mobile>    <SrcNumber>106904850485101</SrcNumber>    <MessageID>201706138686309270485101</MessageID>    <statustext>1000</statustext>    <org_messageid />    <inserttime>2017-06-13T14:53:39.91+08:00</inserttime>    <token>70485101</token>    <IP>114.80.241.146</IP>  </result></NewDataSet>";
		//解析开始
				try {
					ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(xml.getBytes());
					SAXReader sax=new SAXReader();//创建一个SAXReader对象  
//				    File xmlFile=new File("d:\\123.xml");//根据指定的路径创建file对象  
				    Document document=sax.read(tInputStringStream);//获取document对象,如果文档无节点，则会抛出Exception提前结束  
				    Element root=document.getRootElement();//获取根节点  
				    Attribute attr = root.attribute("nextID");
				    String id = attr.getText();
				    
				    List<Element> list = root.elements();
				    //取得某个节点的子节点.  
				      Element element=root.element("result");  
				    //取得节点的文字  
				    //取得某节点下所有名为“result”的子节点，并进行遍历
				      String str = "";
				      for(int i=0;i<list.size();i++){
				    	  Map<String,String> map = new HashMap<String, String>();
				    	  if("result".equals(list.get(i).getName())){
				    		  Element ele=root.element("mobile");
				    		  str = ele.getTextTrim();
//				    		  for (Iterator it = element.elementIterator(); it.hasNext();) {
//						    	   	Element elm = (Element) it.next();
//						    	   	str = elm.getTextTrim();
//						       }
					      } 
				      }
				      
				      
				    	  
				      
				       
				    
//				    Node node = root.selectSingleNode("result");
//				    List<Element> relist = node.n
//				    getNodes(root);//从根节点开始遍历所有节点  
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}

			public static void getNodes(Element node){  
			    System.out.println("--------------------");  
			      
			    //当前节点的名称、文本内容和属性  
//			    System.out.println("当前节点名称："+node.getName());//当前节点名称  
//			    System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称  
			   
				List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
			    	for(Attribute attr:listAttr){//遍历当前节点的所有属性  
				        String name=attr.getName();//属性名称  
				        String value=attr.getValue();//属性的值  
				        System.out.println("属性名称："+name+"属性值："+value);  
				    } 
			     
			      
			    //递归遍历当前节点所有的子节点  
			    List<Element> listElement=node.elements();//所有一级子节点的list  
			    for(Element e:listElement){//遍历所有一级子节点  
			        getNodes(e);//递归  
			    }  
			}  
			
			
			//比较不错的解析方法
			public static void Xstr(){
				try {
					
					  XStream xstream = new XStream(new DomDriver());
					  xstream.alias("NewDataSet", List.class);
					  xstream.alias("result", StatusObj.class);
					 //转换为list
					  @SuppressWarnings("unchecked")
					  List<StatusObj> list = (List<StatusObj>) xstream.fromXML("要解析的xml");
					  for(int i=0;i<list.size();i++){
					   System.out.println(list.get(i).getID()+"------"+list.get(i).getMobile()+"----"+list.get(i).getMessageID());
					  }
					
				} catch (Exception e) {
				}
			}
			
	}

