/**
 * 
 */
package com.huayue.library.service;

import com.huayue.framework.service.BaseService;
import com.huayue.framework.util.Page;
import com.huayue.library.domain.Acticle;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * @author lsk0414
 *
 */
public interface ActicleManagerService extends BaseService<Acticle>{
	String getDirectory(int id);
}
