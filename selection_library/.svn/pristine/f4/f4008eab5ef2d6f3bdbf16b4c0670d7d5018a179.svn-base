/**
 * 
 */
package com.huayue.library.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huayue.library.dao.BookManagerDao;
import com.huayue.library.domain.Book;
import com.huayue.library.domain.Mapping;
import com.huayue.library.mapper.BookMapper;
import com.huayue.framework.dao.impl.BaseDaoImpl;
/**
 * @author lsk0414
 *
 */
@Repository
public class BookManagerDaoImpl extends BaseDaoImpl<Book ,BookMapper> implements BookManagerDao{

	/* 删除关联表中对应bookId的映射记录，标记is_deleted = 1
	 * @see com.huayue.library.dao.BookManagerDao#deleteMappings(int)
	 */
	public void deleteMappings(int bookId) {
		getMapper().deleteMappings(bookId);
	}

	/* update book information
	 * @see com.huayue.library.dao.BookManagerDao#updateBookInfo(com.huayue.library.domain.Book)
	 */
	public void updateBookInfo(Book book) {
		getMapper().updateBookInfo(book);
	}

	/* update library_relation_mapping datas
	 * @see com.huayue.library.dao.BookManagerDao#updateMappings(java.util.List)
	 */
	public void updateMappings(List<Mapping> list) {
		getMapper().updateMappings(list);
	}

}
