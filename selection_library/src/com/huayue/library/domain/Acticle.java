/**
 * 
 */
package com.huayue.library.domain;

import java.util.Date;
import java.util.List;

import com.huayue.framework.util.Format;

/**
 * @author lsk0414
 * 
 */
public class Acticle implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 文章ID
	private int id;

	// 篇名
	private String name;
	
	// 作者
	private String author;

	// 国别
	private int countryId;

	// 体裁
	private String genre;

	// 表达方式
	private String presentation;

	// 来源
	private String origin;

	// 一级内容ID
	private int firConId;

	// 二级内容ID
	private int secConId;

	// 描述
	private String comment;

	// 所在目录
	private String directory;

	//后缀
	private String suffix;
	
	//大小
	private long size;
	
	//String size
	private String sizeText;
	
	//名家名篇
	private String famousMasterpiece;
	
	//时代
	private String times;
	
	//版本
	private String version;
	
	// 添加时间
	private long createTime;
	
	//引用此文章对应的书籍集合
	private List<Book> books;
	
	// 添加人
	private int userId;

	// 更新时间
	private long updateTime;

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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the presentation
	 */
	public String getPresentation() {
		return presentation;
	}

	/**
	 * @param presentation
	 *            the presentation to set
	 */
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the firConId
	 */
	public int getFirConId() {
		return firConId;
	}

	/**
	 * @param firConId
	 *            the firConId to set
	 */
	public void setFirConId(int firConId) {
		this.firConId = firConId;
	}

	/**
	 * @return the secConId
	 */
	public int getSecConId() {
		return secConId;
	}

	/**
	 * @param secConId
	 *            the secConId to set
	 */
	public void setSecConId(int secConId) {
		this.secConId = secConId;
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
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the famousMasterpiece
	 */
	public String getFamousMasterpiece() {
		return famousMasterpiece;
	}

	/**
	 * @param famousMasterpiece the famousMasterpiece to set
	 */
	public void setFamousMasterpiece(String famousMasterpiece) {
		this.famousMasterpiece = famousMasterpiece;
	}

	/**
	 * @return the times
	 */
	public String getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(String times) {
		this.times = times;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @return the sizeText
	 */
	public String getSizeText() {
		return Format.convertSize((int)size);
	}
	
	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return new Date(createTime);
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString(){
		return "id :" + id + " name : " + name + " author : " + author + " countryId : " + countryId + " genre : " + genre + " presentation : " + presentation 
				+ " origin : " + origin + " firConId : " + firConId + " secConId : " + secConId + " comment : " + comment + " suffix : " + suffix + " directory : " +
				directory + " size : " + size + " famousMasterpiece : " + famousMasterpiece + " times : " + times + " versions : " + version +
				" createTime : " + createTime + " userId : " + userId;
	}
}
