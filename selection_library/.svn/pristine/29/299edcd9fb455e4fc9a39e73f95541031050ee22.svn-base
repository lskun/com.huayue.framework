/**
 * 
 */
package com.huayue.library.common;

/**
 * @author lsk0414
 *
 */
public class TreeNode {
	
	private int parentId;
	
	private int id;
	
	private String nodeName;
	
	private int deep;
	
	public TreeNode(int pid,String name,int id){
		this.parentId = pid;
		this.id = id;
		this.nodeName = name;
	}
	
	public TreeNode(){}
	
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @return the deep
	 */
	public int getDeep() {
		return deep;
	}

	/**
	 * @param deep the deep to set
	 */
	public void setDeep(int deep) {
		this.deep = deep;
	}
	
	public String getDeepStr(){
		StringBuilder builder = new StringBuilder();
		while(deep-- > 1){
			builder.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return builder.toString();
	}
}
