package com.huayue.framework.util;

import java.util.*;

public class PageInfo
{
    int PageIndex = 0;    //当前页
    int PageCount = 0;    //总页数
    int RecordCount = 0;  //记录总数
    int PageSize = 10;    //每页显示的记录
    @SuppressWarnings("unchecked")
	ArrayList Items = new ArrayList();

    public PageInfo(int page, int total, int pSize)
    {
        PageIndex = page;
        RecordCount = total;
        PageSize = pSize;

        PageIndex = PageIndex < 1 ? 1 : PageIndex;
        if (( RecordCount > 0) && (PageSize > 0))
        {
            PageCount = (int)Math.ceil((float)RecordCount / (float)PageSize);
        }
        PageIndex = PageIndex > PageCount ? PageCount : PageIndex;
    }

    @SuppressWarnings("unchecked")
	public void add(Object obj)
    {
        Items.add(obj);
    }

    public Object get(int i)
    {
        if (i < Items.size()) return Items.get(i);
        else return null;
    }

    public int getRecordCount()
    {
        return RecordCount;
    }

    public int getPageIndex()
    {
        return PageIndex;
    }

    public int size()
    {
        return Items.size();
    }

    public int getPageCount()
    {
        return PageCount;
    }

    public int getPageSize()
    {
        return PageSize;
    }

    @SuppressWarnings("unchecked")
	public ArrayList getItems() {
		return Items;
	}

	@SuppressWarnings("unchecked")
	public void setItems(ArrayList items) {
		Items = items;
	}

	public int prevPage()
    {   	   	
        return (PageIndex > 1 ? PageIndex - 1 : 1);   
    }

    public int nextPage()
    {   	
        return (PageIndex >= PageCount ? PageCount : PageIndex + 1);
    }

    protected void finalize() throws Throwable
    {
        Items.clear();
    }

    public String toString()
    {
        return null == Items ? "" : Items.toString();
    }
}
