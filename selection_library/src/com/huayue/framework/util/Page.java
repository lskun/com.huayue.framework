/**
 * 
 */
package com.huayue.framework.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lsk0414
 *
 */
public class Page<T> {
	
	private int pageIndex = 1;    //当前页
	
    private int pageCount = 0;    //总页数
    
    private int recordCount = 0;  //记录总数
    
    private int pageSize = 15;    //每页显示的记录数
    
    private int prevPage;
    
    private int nextPage;
    
    @SuppressWarnings("unchecked")
	private List<T> items = Collections.EMPTY_LIST; //对应的当前页记录
    
    private Map<String, Object> params = new HashMap<String,Object>(); //其他的参数我们把它分装成一个Map对象  
    
    public Page(){}
    
    public Page(int page, int total, int pSize)
    {
        pageIndex = page;
        recordCount = total;
        pageSize = pSize;

        pageIndex = pageIndex < 1 ? 1 : pageIndex;
        if (( recordCount > 0) && (pageSize > 0))
        {
            pageCount = (int)Math.ceil((float)recordCount / (float)pageSize);
        }
        pageIndex = pageIndex > pageCount ? pageCount : pageIndex;
    }

	public void add(T obj)
    {
        items.add(obj);
    }

    public Object get(int i)
    {
    	if (i < items.size()) return items.get(i);
        else return null;
    }

    public int getRecordCount()
    {
        return recordCount;
    }
    
    public void setRecordCount(int recordCount){
    	this.recordCount = recordCount;
    	if (( recordCount > 0) && (pageSize > 0))
        {
            pageCount = (int)Math.ceil((float)recordCount / (float)pageSize);
        }
        pageIndex = pageIndex > pageCount ? pageCount : pageIndex;
        prevPage = (pageIndex > 1 ? pageIndex - 1 : 1);
        nextPage = (pageIndex >= pageCount ? pageCount : pageIndex + 1);
    }
    
    public int getPageIndex()
    {
        return pageIndex;
    }
    
    public int size()
    {
        return items.size();
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize){
    	this.pageSize = pageSize;
    }
    
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int prevPage()
    {   	   	
        return (pageIndex > 1 ? pageIndex - 1 : 1);   
    }

    public int nextPage()
    {   	
        return (pageIndex >= pageCount ? pageCount : pageIndex + 1);
    }

    protected void finalize() throws Throwable
    {
    	items.clear();
    }

    /**
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}	

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String toString()
    {
        return null == items ? "" : items.toString();
    }

	/**
	 * @return the prevPage
	 */
	public int getPrevPage() {
		return prevPage;
	}

	/**
	 * @param prevPage the prevPage to set
	 */
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
