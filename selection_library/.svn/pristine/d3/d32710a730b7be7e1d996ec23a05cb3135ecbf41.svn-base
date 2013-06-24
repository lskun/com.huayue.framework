/**
 * 
 */
package com.huayue.library.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huayue.library.domain.Category;
import com.huayue.library.service.BookManagerService;
import com.huayue.library.service.LibraryCategoryService;

/**
 * @author lsk0414
 *
 */
@Component
public class NodeHelper {
	
	public static final Logger log = Logger.getLogger(NodeHelper.class);
	
	@Autowired
	LibraryCategoryService libraryCategoryService;
	
	// ========================= 产生jquery.treeview的jsonstring=================//
	/**
	 * 类别json生成入口
	 * @param root 是否为根节点
	 * @return
	 */
	public String generateJTVJsonString(String root){
		boolean isRoot;
		String output = "";
		// jquery treeview的根请求为root=source
		if(StringUtils.isBlank(root) || "source".equals(root)){
			isRoot = true;
		}else{
			isRoot = false;
		}
		if(isRoot) output = generateInitTreeString();
		else output = generateTreeChildNodeString(Integer.parseInt(root));		
		return output;
	}
	
	/**
	 * book json generatePoint
	 * @param root
	 * @return
	 */
	public String generateJTVJsonStringForBook(String root){
		String output = "";
		boolean isRoot;
		if(StringUtils.isBlank(root) || "source".equals(root))
			isRoot = true;
		else
			isRoot = false;
		if(isRoot) output = generateInitBookTreeString();
		else output = generateBookTreeChildNodeString(Integer.parseInt(root));
		return output;
	}
	
	private String generateInitBookTreeString(){
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("[");
		List<Category> categories = libraryCategoryService.findChildrenCategory(0);
		if(categories.isEmpty()) return "";
		jsonString.append("{");
		jsonString.append("\"text\": \"<a href='/book/b_list.do' target='rightFrame'>根目录</a>\"");
		jsonString.append(", \"classes\": \"folder\"");
		jsonString.append(", \"expanded\":true");
		jsonString.append(", \"children\":");
		jsonString.append("[");
		int i = 0;
		for(Category category : categories){
			if(i > 0) jsonString.append(",");
			jsonString.append(toBookJSONString(category));
			i++;
		}
		jsonString.append("]}]");
		return jsonString.toString();
	}
	
	//根节点
	private String generateInitTreeString(){
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("[");
		List<Category> categories = libraryCategoryService.findChildrenCategory(0);
		if(categories.isEmpty()) return "";
		jsonString.append("{");
		jsonString.append("\"text\": \"<a href='/category/v_list.do' target='rightFrame'>根目录</a>\"");
		jsonString.append(", \"classes\": \"folder\"");
		jsonString.append(", \"expanded\":true");
		jsonString.append(", \"children\":");
		jsonString.append("[");
		int i = 0;
		for(Category category : categories){
			if(i > 0) jsonString.append(",");
			jsonString.append(toJSONString(category));
			i++;
		}
		jsonString.append("]}]");
		return jsonString.toString();
	}
	
	private String generateTreeChildNodeString(int nodeId){
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("[");
		List<Category> categories = libraryCategoryService.findChildrenCategory(nodeId);
		
		if(categories.isEmpty()) return "";
		
		int i = 0;
		for(Category category : categories){
			if(i > 0) jsonString.append(",");
			jsonString.append(toJSONString(category));
			
			i++;
		}
		jsonString.append("]");
		return jsonString.toString();
	}
	private String generateBookTreeChildNodeString(int nodeId){
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("[");
		List<Category> categories = libraryCategoryService.findChildrenCategory(nodeId);
		
		if(categories.isEmpty()) return "";
		
		int i = 0;
		for(Category category : categories){
			if(i > 0) jsonString.append(",");
			jsonString.append(toBookJSONString(category));
			
			i++;
		}
		jsonString.append("]");
		return jsonString.toString();
	}
	private String toJSONString(Category category){
		StringBuilder sb = new StringBuilder();
		sb.append(" {");
		sb.append(" \"text\": \""+  generateLinkString(category) + "\"");
		//if this category has childNode ,take action...
		if(category.getIsEndNode() != 0){
			sb.append(", \"id\": \"" + category.getId() + "\"");
			sb.append(", \"hasChildren\":true");
			sb.append(", \"classes\":\"folder\"");
		}else{
			sb.append(", \"classes\":\"file\"");
			sb.append(",\"id\": \"" + category.getId() + "\"");
			sb.append(", \"hasChildren\":false");
		}
		sb.append("}");
		return sb.toString();
	}
	
	private String toBookJSONString(Category category){
		StringBuilder sb = new StringBuilder();
		sb.append(" {");
		sb.append(" \"text\": \""+  generateBookLinkString(category) + "\"");
		//if this category has childNode ,take action...
		if(category.getIsEndNode() != 0){
			sb.append(", \"id\": \"" + category.getId() + "\"");
			sb.append(", \"hasChildren\":true");
			sb.append(", \"classes\":\"folder\"");
		}else{
			sb.append(", \"classes\":\"file\"");
			sb.append(",\"id\": \"" + category.getId() + "\"");
			sb.append(", \"hasChildren\":false");
		}
		sb.append("}");
		return sb.toString();
	}
	
	private String generateBookLinkString(Category category){
		String link = "<a href='/book/b_list.do?root=" + category.getId() + "' target='rightFrame' >" + category.getName() + "</a>";
		return link;
	}
	
	private String generateLinkString(Category category){
		String link = "<a href='/category/v_list.do?root=" + category.getId() + "' target='rightFrame' >" + category.getName() + "</a>";
		return link;
	}
}
