package com.huayue.crm.dao;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.huayue.crm.domain.Client;
import com.huayue.framework.util.PageInfo;



public interface ClientDao {
	
	public void update(Client client);
	
	public void save(Client client);
	
	public void delete(String[] ids);
	
	public PageInfo fuzzySearch(int pagesize ,int pageindex ,String province ,String city ,String county, String name ,long categoryId ,String unit);
	
	public void saveDataFromExcel(List<Client> list)throws Exception;
	
	public List<Client> searchForExport(Client client);
	
	public Client showInfo(long id);
	
	public LinkedHashMap<String,ArrayList<Client>> listDuplicateRecord();
	
}
