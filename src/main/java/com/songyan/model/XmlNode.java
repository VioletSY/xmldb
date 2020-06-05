package com.songyan.model;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.songyan.constant.CODE;
import com.songyan.util.XmlUtils;

/**
 * @author songyan
 * @date 2020年4月28日 上午9:41:37
 * @desc xml节点操作类
 */
public class XmlNode {

	private String rootNodeName;
	private String key;
	private XmlUtils xmlUtils = XmlUtils.getInstance();
	private String path;
	private String begin;
	
	/**
	 * 创建初始化
	 * @param path
	 * @param rootNodeName
	 * @param key
	 * @param begin
	 */
	public XmlNode(String path, String rootNodeName, String key,String begin) {
		super();
		this.path = path;
		this.rootNodeName = rootNodeName;
		this.key = key;
		this.begin = begin;
	}

	/**
	 * 获取文档列表
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getList(Class<T> clazz) {
		T msr = null;
		try {
			msr = (T) (xmlUtils.xmlStrToOject(path, clazz));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msr == null ? null : msr;
	}
	
	/**
	 * 根据id获取节点
	 * @param id
	 * @return
	 */
	public Node getNode(Document doc, String begin,String id) {
		try { 
			String express = new SelectNodeExpress(begin,rootNodeName, key, id).getExpress();
			Node element = xmlUtils.selectSingleNode(express,doc.getDocumentElement());
			return element;
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据指定属性查找
	 * @param doc
	 * @param begin
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public Node getNode(Document doc, String begin,String attrName,String attrValue) {
		try { 
			String express = new SelectNodeExpress(begin,rootNodeName, attrName, attrValue).getExpress();
			Node element = xmlUtils.selectSingleNode(express,doc.getDocumentElement());
			return element;
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据指定属性查找
	 * @param doc
	 * @param begin
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public NodeList getNodeList(Document doc, String begin,String attrName,String attrValue) {
		String express = new SelectNodeExpress(begin,rootNodeName, attrName, attrValue).getExpress();
		NodeList element = xmlUtils.selectNodes(express,doc.getDocumentElement());
		return element;
	}
	
	/**
	 * 根据id获取节点
	 * @param id
	 * @return
	 */
	public Node getNode(String id) {
		Document doc = getDocument();
		return getNode(doc,begin,id);
	}
	
	/**
	 * 根据id获取节点
	 * @param id
	 * @return
	 */
	public NodeList getNodeByAttr(String attrName,String attrValue) {
		Document doc = getDocument();
		return getNodeList(doc,begin,attrName,attrValue);
	}


	/**
	 * 根据id获取节点
	 * @param id
	 * @return
	 */
	public Element getElement(Document doc, String id) {
		return (Element) getNode(doc,begin, id);
	}

	/**
	 * 添加节点
	 * 
	 * @return
	 */
	public int insert(Attributes attributes) {
		try {
			Document doc = getDocument();

			Element rootNode = getRootNode(doc);

			// 创建新节点
			Element mainNode = getMainNode(doc);

			// 节点属性赋值
			setAttributes(mainNode, attributes);

			// 追加到根节点
			rootNode.appendChild(mainNode);

			// 保存
			save(doc);

			return CODE.OPERATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CODE.OPERATE_ERROR;
		}
	}
	
	/**
	 * 删除节点
	 * @param node
	 * @return
	 */
	public int delete(String id) {
		try {
			Document doc = getDocument();

			// 获取
			Node node = getNode(doc,begin, id);

			// 删除
			node.getParentNode().removeChild(node);
			
			// 保存
			save(doc);
			return CODE.OPERATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CODE.OPERATE_ERROR;
		}
	}

	/**
	 * 修改
	 * @param id 主键
	 * @param attributes 属性列表
	 * @param doc 文档对象
	 * @return
	 */
	public int update(String id, Attributes attributes) {
		Document doc = getDocument();

		Element element = getElement(doc, id);
		if (attributes != null) {
			for (Attribute attribute : attributes.getList()) {
				element.setAttribute(attribute.getKey(), attribute.getValue());
			}
		}

		return save(doc);
	}
	
	/**
	 * 保存xml
	 * 
	 * @param rootNode
	 * @return
	 */
	public int save(Document doc) {
		try {
			xmlUtils.saveXml(path, doc);
			return CODE.OPERATE_SUCCESS;
		} catch (Exception e) {
			return CODE.OPERATE_ERROR;
		}
	}

	/**
	 * 节点属性赋值
	 * @param element
	 * @param attributes
	 */
	private void setAttributes(Element element, Attributes attributes) {
		if (element != null && attributes != null) {
			for (Attribute attribute : attributes.getList()) {
				element.setAttribute(attribute.getKey(), attribute.getValue());
			}
		}
	}

	/**
	 * 获取文档对象
	 * @return
	 */
	private Document getDocument() {
		return xmlUtils.getXml(path);
	}

	/**
	 * 获取文档根节点
	 * @return
	 */
	private Element getRootNode(Document doc) {
		return doc.getDocumentElement();
	}

	/**
	 * 获取文档主节点
	 * @return
	 */
	private Element getMainNode(Document doc) {
		return doc.createElement(rootNodeName);
	}

	
}
