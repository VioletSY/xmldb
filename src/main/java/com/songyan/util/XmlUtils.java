package com.songyan.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**  
* @param <T>
 * @Description: xml文件读写工具类
*   TODO:是否读写分离  读文件时不使用单例模式?
* @Author: 守得莲开结伴游  
* @Date: 2019年6月10日  下午1:16:42 
*/  
public class XmlUtils {
	private volatile static XmlUtils util = null;
	private final static Object lock_qutil = new Object();
	private XmlUtils(){}
	
	/**
	 * 双重锁定单例模式
	 * */
	public static XmlUtils getInstance(){
		if(util == null){
			synchronized (lock_qutil) {
				util = new XmlUtils();
			}
		}
		return util;
	}
	
	/**
	 * 查找文件,并返回Document
	 */
	public Document getXml(String filePath) {
		File file = null;
		Document doc = null;
		try {
			file = ResourceUtils.getFile(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	 /**
     * 查找节点，并返回第一个符合条件节点
     */
    public Node selectSingleNode(String express, Object source) throws XPathExpressionException {
        Node result = null;
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        result = (Node) xpath.evaluate(express, source, XPathConstants.NODE);
        return result;
    }
    
    /**
     * 查找节点，返回符合条件的节点集。
     * @param express
     * @param source
     * @return
     */
    public NodeList selectNodes(String express, Object source) {
        NodeList result = null;
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            result = (NodeList) xpath.evaluate(express, source,XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/**
	 * 根据文件名称 和Document 保存文件
	 * @throws FileNotFoundException 
	 */
	public void saveXml(String filePath, Document doc) throws FileNotFoundException{
		File file =ResourceUtils.getFile(filePath);
		try(FileOutputStream fos = new FileOutputStream(file);) {
			try(OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8"); ) {
				TransformerFactory transFactory = TransformerFactory.newInstance();
				Transformer transformer = transFactory.newTransformer();
				transformer.setOutputProperty("indent", "yes");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
				DOMSource source = new DOMSource();
				source.setNode(doc);
				StreamResult result = new StreamResult();
				result.setWriter(osw);
				transformer.transform(source, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}
	
	/**
	 * jaxb 实现 xml转pojo对象
	 * 2019年6月10日  下午1:44:03 @_守得莲开结伴游_  
	 * @throws FileNotFoundException  
	 */
	public Object xmlStrToOject(String filePath,Class<?> clazz) throws FileNotFoundException {
		Object xmlObject  = null;
		File file =ResourceUtils.getFile(filePath);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));) {
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			try(Reader reader = new StringReader(buffer.toString());) {
				xmlObject = unmarshaller.unmarshal(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return xmlObject;
	}
	
	 /**
     * XML转换为javabean
     * 2020/04/28 songyan添加此方法
     * @param <T>
     * @param xml
     * @param t
     * @return
	 * @throws FileNotFoundException 
     * @throws JAXBException
	 * @throws IOException 
     */
    @SuppressWarnings("unchecked")
	public <T> T xmlToBean(String filePath, Class<?> clazz) throws FileNotFoundException{
    	File file = null;
		file = ResourceUtils.getFile(filePath);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));) {
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			JAXBContext context = JAXBContext.newInstance(clazz);
	        Unmarshaller um = context.createUnmarshaller();
	        
			try(StringReader sr = new StringReader(buffer.toString());) {
				T result = (T) um.unmarshal(sr);
				return result;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	       
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		
        return null;
    }
	
	/**
	 * jaxb 实现pojo对象转xml格式的字符串
	 * 2019年6月10日  下午1:50:06 @_守得莲开结伴游_  
	 */
	public String convertToXml(Object obj) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(obj, sw);
		return sw.toString();
	}
	
	/**
	 * w3c.Node 转字符串
	 * 2019年6月22日  下午2:44:02 @_守得莲开结伴游_  
	 */
	public String xmlToString(Node node) throws TransformerException {
		TransformerFactory ft = TransformerFactory.newInstance();
		Transformer ff = ft.newTransformer();
		ff.setOutputProperty("encoding", "UTF-8");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ff.transform(new DOMSource(node), new StreamResult(bos));
		return bos.toString();
	}
	
	public String getNodeAttributeValue(Node node,String attributeName) {
		if(node!=null){
			NamedNodeMap namedNodeMap = node.getAttributes();
			Node attributeNode = namedNodeMap.getNamedItem(attributeName);
			if(attributeNode!=null){
				return attributeNode.getNodeValue();
			}
		}
		return null;
	}
	
	public <T> T getNodeAttributeValue(Node node,String attributeName,Class<T> clazz) {
		String value= getNodeAttributeValue(node, attributeName);
		return DataUtil.parseToObject(value, clazz);
	}
}
