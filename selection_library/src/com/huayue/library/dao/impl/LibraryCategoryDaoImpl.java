/**
 * 
 */
package com.huayue.library.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.huayue.framework.dao.impl.BaseDaoImpl;
import com.huayue.framework.util.Page;
import com.huayue.library.dao.LibraryCategoryDao;
import com.huayue.library.domain.Category;
import com.huayue.library.domain.Version;
import com.huayue.library.mapper.CategoryMapper;
/**
 * @author lsk0414
 *
 */
@Repository
public class LibraryCategoryDaoImpl extends BaseDaoImpl<Category,CategoryMapper> implements LibraryCategoryDao {

	public List<Category> findChildrenCategory(int categoryId) {
		return getMapper().findChildrenCategory(categoryId);
	}

	public List<Map> getNodeMap() {
		return getMapper().getNodeCollection();
	}

	public void updateParentNodeStatus(int pid) {
		getMapper().updateParentNodeStatus(pid);
	}

	public List<Map> getNodeHierarchicalRelations() {
		return getMapper().getHierarchicalMapping();
	}

	public List<Map> getTreeNodeCollection() {
		return getMapper().getTreeNodeCollection();
	}

	public void batchDelete(int[] ids) {
		getMapper().batchDelete(ids);
	}
	
}
