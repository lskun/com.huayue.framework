/**
 * 
 */
package com.huayue.library.dao;

import java.util.*;

import com.huayue.framework.dao.BaseDao;
import com.huayue.library.domain.Category;
import com.huayue.library.domain.Version;
import com.huayue.library.mapper.CategoryMapper;

/**
 * @author lsk0414
 *
 */
public interface LibraryCategoryDao extends BaseDao<Category,CategoryMapper>{
				
	List<Category> findChildrenCategory(int categoryId);
		
	List<Map> getNodeMap();
	
	void updateParentNodeStatus(int pid);
	
	List<Map> getNodeHierarchicalRelations();
	
	List<Map> getTreeNodeCollection();
	
	void batchDelete(int[] ids);
}
