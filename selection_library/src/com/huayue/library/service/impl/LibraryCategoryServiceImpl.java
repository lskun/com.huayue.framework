/**
 * 
 */
package com.huayue.library.service.impl;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.huayue.framework.service.impl.BaseServiceImpl;
import com.huayue.framework.util.CharactorTool;
import com.huayue.framework.util.Page;
import com.huayue.library.common.TreeNode;
import com.huayue.library.dao.LibraryCategoryDao;
import com.huayue.library.domain.Category;
import com.huayue.library.service.LibraryCategoryService;
import com.huayue.library.util.Constants;

/**
 * @author lsk0414
 *
 */
@Service
public class LibraryCategoryServiceImpl extends BaseServiceImpl<Category> implements LibraryCategoryService{
	
	private static Logger log = Logger.getLogger(LibraryCategoryServiceImpl.class);
	
	private LibraryCategoryDao libraryCategoryDao;
	
	@Autowired
	public LibraryCategoryServiceImpl(LibraryCategoryDao libraryCategoryDao)
	{
		this.libraryCategoryDao = libraryCategoryDao;
		setBaseDao(libraryCategoryDao);
	}
	

	@Override
	public void addObj(Category category) throws DataAccessException {
		boolean rst = false;
		String name;
		
		try {
			name = URLDecoder.decode(category.getName(),"UTF-8");
			//	String folder = CharactorTool.getEveryFirstSpell(name);
			//	System.out.println("folder : " + folder);
			//	rst = new File(folder = Constants.BASEPATH + File.separator + folder).mkdir();
			//	if(!rst)  log.error("build directory failed : " + folder);
			//	category.setDirectory(folder);		
			category.setName(name);
			category.setDescription(URLDecoder.decode(category.getDescription(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		libraryCategoryDao.addObj(category);
		if(category.getParentId() != 0) libraryCategoryDao.updateParentNodeStatus(category.getParentId());
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.service.LibraryCategoryService#showCategories()
	 */
	public Page<Category> showCategories(Page<Category> page) {
		return libraryCategoryDao.findByPage(page);
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.service.LibraryCategoryService#findChildrenCategory(int)
	 */
	public List<Category> findChildrenCategory(int categoryId) {
		// TODO Auto-generated method stub
		return libraryCategoryDao.findChildrenCategory(categoryId);
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.service.LibraryCategoryService#getNodeMap()
	 */
	public Map<Integer, String> getNodeMap() {
		// TODO Auto-generated method stub
		List<Map> nodes = libraryCategoryDao.getNodeMap();
		Map<Integer,String> nodeMap = new HashMap<Integer,String>(nodes.size());
		for(Map map : nodes){
			nodeMap.put((Integer)map.get("id"), (String)map.get("name"));
		}	
		return nodeMap;
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.service.LibraryCategoryService#getNodeMapping()
	 */
	public Map<Integer, String> getHierarchicalRelations() {
		// TODO Auto-generated method stub
		List<Map> result = libraryCategoryDao.getNodeHierarchicalRelations();
		Map<Integer,String> mappings = new HashMap<Integer,String>(result.size());
		for(Map map : result){
			mappings.put((Integer)map.get("id"), (String)map.get("hierarchical_relation"));
		}
		return mappings;
	}


	/* 生成节点树结构
	 * @see com.huayue.library.service.LibraryCategoryService#getTreeNodeList()
	 */
	@SuppressWarnings("unchecked")
	public Set<TreeNode> getTreeNodeList() {
		List<Map> list = libraryCategoryDao.getTreeNodeCollection();
		//保证线程同步 以及迭代顺序的treenode Set结构，自定义比较器：以treeNode的parentid作为在集合中的顺序依据
		Set<TreeNode> nodes = Collections.synchronizedSet(new TreeSet(new Comparator<TreeNode>(){
			public int compare(TreeNode o1, TreeNode o2) {
				if(o1.getId() > o2.getId()) return 1;
				if(o1.getId() < o2.getId()) return 2;
				return 0;
			}			
		})			
		);
		for(Map map : list){
			nodes.add(new TreeNode((Integer)map.get("pid"),(String)map.get("name"),(Integer)map.get("id")));
		}
		return nodes;
	}

	/* 批量删除类别
	 * @see com.huayue.library.service.LibraryCategoryService#batchDelete(int[])
	 */
	public void batchDelete(int[] ids) {
		libraryCategoryDao.batchDelete(ids);
	}
	
}
