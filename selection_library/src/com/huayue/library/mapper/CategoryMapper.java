/**
 * 
 */
package com.huayue.library.mapper;

import java.util.*;

import com.huayue.framework.mapper.BaseMapper;
import com.huayue.library.domain.Category;
import org.apache.ibatis.annotations.Select;
/**
 * @author lsk0414
 *
 */
public interface CategoryMapper extends BaseMapper<Category>{
	
	ArrayList<Category> searchAllCategories();
	
	List<Category> findChildrenCategory(int categoryId);
	
	@Select("select id, name from library_category")
	List<Map> getNodeCollection();
	
	void updateParentNodeStatus(int pid);
	
	int addObject(Category category);
	
	@Select("select id, hierarchical_relation from library_category where hierarchical_relation != '' ")
	List<Map> getHierarchicalMapping();
	
	@Select("select id ,name ,pid from library_category ")
	List<Map> getTreeNodeCollection();
	
	void batchDelete(int[] ids);
} 
