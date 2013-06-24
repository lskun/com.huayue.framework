/**
 * 
 */
package com.huayue.library.dao;

import com.huayue.framework.dao.BaseDao;
import com.huayue.library.domain.Book;
import com.huayue.library.domain.Mapping;
import com.huayue.library.mapper.BookMapper;

/**
 * @author lsk0414
 *
 */
public interface BookManagerDao extends BaseDao<Book,BookMapper>{
	
	void deleteMappings(int bookId);
	
	void updateBookInfo(Book book);
	
	void updateMappings(java.util.List<Mapping> list);
	
}
