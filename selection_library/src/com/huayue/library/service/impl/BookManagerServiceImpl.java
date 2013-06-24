/**
 * 
 */
package com.huayue.library.service.impl;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.huayue.library.dao.BookManagerDao;
import com.huayue.library.domain.Book;
import com.huayue.library.domain.Mapping;
import com.huayue.library.service.BookManagerService;
import com.huayue.framework.service.impl.BaseServiceImpl;

/**
 * @author lsk0414
 *
 */
@Service
public class BookManagerServiceImpl extends BaseServiceImpl<Book> implements BookManagerService{
	
	private BookManagerDao bookManagerDao;
	
	@Autowired
	public BookManagerServiceImpl(BookManagerDao bookManagerDao){
		setBaseDao(bookManagerDao);
		this.bookManagerDao = bookManagerDao;
	}

	/* update bookinfo and update mappings of library_relation_mapping
	 * @see com.huayue.library.service.BookManagerService#updateMappings(com.huayue.library.domain.Book, java.lang.String[])
	 */
	public void updateMappings(Book book, String[] acticleIds) {
		if(acticleIds != null){
			book.setActicleCount(acticleIds.length);
		}
		bookManagerDao.updateBookInfo(book);		
		bookManagerDao.deleteMappings(book.getId());
		if(acticleIds != null){
			List<Mapping> list = new ArrayList<Mapping>(acticleIds.length);
			for(String str : acticleIds){
				list.add(new Mapping(book.getId() ,Integer.parseInt(str)));
			}
			bookManagerDao.updateMappings(list);
		}
	}
	
	@Override
	public void deleteByID(Serializable primaryKey) throws DataAccessException {
		bookManagerDao.deleteByID(primaryKey);
		bookManagerDao.deleteMappings((Integer) primaryKey);
	}

	public void addObject(Book book, String[] acticleIds) {
		
		if(acticleIds != null){
			book.setActicleCount(acticleIds.length);
			bookManagerDao.addObj(book);
			List<Mapping> list = new ArrayList<Mapping>(acticleIds.length);
			for(String str : acticleIds){
				list.add(new Mapping(book.getId(),Integer.parseInt(str)));
			}
			bookManagerDao.updateMappings(list);
		}else{
			bookManagerDao.addObj(book);
		}
		
	}
}
