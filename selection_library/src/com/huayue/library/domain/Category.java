/**
 * 
 */
package com.huayue.library.domain;

import java.util.Date;
import java.util.List;

/**
 * @author lsk0414
 * 
 */
public class Category implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 类别名
	private String name;

	// 类别
	private int id;

	// 所在目录
	private String directory;

	// 类别创建时间
	private long createTime;

	// 类别创建人ID
	private int userId;

	// 类别描述
	private String description;

	// 排序ID
	private int order;
	
	//父级节点parentNode
	private int parentId;
	
	//层级关系
	private String hierarchical_relation;
	
	//是否是末级节点 默认0代表末级节点 ,1代表非末级节点
	private int isEndNode;
	
	// 版本(对应学期)
	private List<Version> versions;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 *            the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the versions
	 */
	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * @param versions the versions to set
	 */
	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

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
	 * @return the isEndNode
	 */
	public int getIsEndNode() {
		return isEndNode;
	}

	/**
	 * @param isEndNode the isEndNode to set
	 */
	public void setIsEndNode(int isEndNode) {
		this.isEndNode = isEndNode;
	}
	
	public Date getCreateDateTime(){
		return new Date(createTime);
	}

	/**
	 * @return the hierarchical_relation
	 */
	public String getHierarchical_relation() {
		return hierarchical_relation;
	}

	/**
	 * @param hierarchical_relation the hierarchical_relation to set
	 */
	public void setHierarchical_relation(String hierarchical_relation) {
		this.hierarchical_relation = hierarchical_relation;
	}
	
	
}
