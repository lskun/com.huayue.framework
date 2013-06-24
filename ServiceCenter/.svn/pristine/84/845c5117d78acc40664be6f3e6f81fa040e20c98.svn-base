package com.huayue.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huayue.crm.dao.ClientDao;
import com.huayue.crm.domain.Client;
import com.huayue.framework.util.DateUtil;
import com.huayue.framework.util.Format;
import com.huayue.framework.util.PageInfo;
import com.huayue.framework.util.Utils;


@Repository
public class ClientDaoImpl implements ClientDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static final Logger log = Logger.getLogger(ClientDaoImpl.class);
		
	@Transactional
	public void delete(final String[] ids) {
		// TODO Auto-generated method stub
		jdbcTemplate.batchUpdate("UPDATE CRM_CLIENT_INFO SET IS_DELETED = 1 WHERE ID = ?", new BatchPreparedStatementSetter(){

			public int getBatchSize() {				
				return ids.length - 1;
			}

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {				
				ps.setLong(1, Long.parseLong(ids[i]));
			}
			
		});
		//jdbcTemplate.update("UPDATE CRM_CLIENT_INFO SET IS_DELETED = 1 WHERE ID = ?", new Object[]{client.getId()});
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> searchForExport(Client client)
	{
		List<Client> list = null;
		
		String sql = "SELECT * FROM CRM_CLIENT_INFO WHERE IS_DELETED = 0";
		StringBuilder builder = new StringBuilder();
		
		if(!"".equals(client.getDeliverprovince())) 	 builder.append(" AND PROVINCE ='" + client.getDeliverprovince() + "'");
		if(!"".equals(client.getDelivercity()))			 builder.append(" AND CITY ='" + client.getDelivercity() + "'");
		if(!"".equals(client.getDeliverarea()))			 builder.append(" AND COUNTY = '" + client.getDeliverarea() + "'");
		if(!"".equals(client.getUnit()))				 builder.append(" AND UNIT LIKE '%" + client.getUnit() + "%'");
		if(client.getCategoryId() > 0)					 builder.append(" AND CATEGORY_ID =" + client.getCategoryId());

		builder.append(" ORDER BY ID DESC");
		list = jdbcTemplate.query(sql + builder.toString(), new ItemMapper());
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo fuzzySearch(int pagesize,int pageindex ,String province ,String city ,String county, String name ,long categoryId ,String unit ) {
		// TODO Auto-generated method stub
		PageInfo pInfo = null;
		StringBuilder factor = new StringBuilder();
		
		String sql = "SELECT * FROM CRM_CLIENT_INFO WHERE IS_DELETED = 0";
		String countSql = "SELECT COUNT(*) FROM CRM_CLIENT_INFO WHERE IS_DELETED = 0";
		if(!"".equals(province)) 	factor.append(" AND PROVINCE = '" + province + "'");
		if(!"".equals(city))		factor.append(" AND CITY = '" + city + "'");
		if(!"".equals(county))		factor.append(" AND COUNTY = '" + county + "'");
		if(!"".equals(name))		factor.append(" AND NAME LIKE '%" + name + "%'");
		if(!"".equals(unit))		factor.append(" AND UNIT LIKE '%" + unit + "%'");
		if(categoryId > 0)			factor.append(" AND CATEGORY_ID =" + categoryId);
		factor.append(" ORDER BY REGISTER_TIME DESC");
		
		int recordCount = jdbcTemplate.queryForInt(countSql + factor.toString(), new Object[]{});
		pInfo = new PageInfo(pageindex, recordCount, pagesize);
		List<Client> items = jdbcTemplate.query(Utils.getPageMysql("*" ,sql + factor.toString() , pageindex, pagesize) ,new ItemMapper());
		pInfo.setItems((ArrayList<Client>)items);
		return pInfo;
	}
	
	@Transactional
	public void saveDataFromExcel(final List<Client> list)throws Exception
	{
		String sql = "INSERT INTO CRM_CLIENT_INFO " +
		 "(PROVINCE ,CITY ,COUNTY ,UNIT ,NAME ,SEX ,CALL_NAME ,DUTY ,PHONE ,MOBILE, EMAIL, FAX ,NATION ,ADDRESS ,POST_CODE ,CATEGORY_ID ,JOINED_ACTIVITY ,REGISTER_TIME,REMARK)" +
		 "VALUES (? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
			{

				public int getBatchSize() {
					// TODO Auto-generated method stub
					return list.size();
				}

				public void setValues(PreparedStatement pst, int i)
						throws SQLException {
					// TODO Auto-generated method stub6g
					Client client = list.get(i);
					pst.setString(1,client.getDeliverprovince());
					pst.setString(2, client.getDelivercity());
					pst.setString(3, client.getDeliverarea());
					pst.setString(4, client.getUnit());
					pst.setString(5, client.getName());
					pst.setString(6, client.getSex());
					pst.setString(7, client.getCallName());
					pst.setString(8, client.getDuty());
					pst.setString(9, client.getPhone());
					pst.setString(10, client.getMobile());
					pst.setString(11, client.getEmail());
					pst.setString(12, client.getFax());
					pst.setString(13, client.getNation());
					pst.setString(14, client.getAddress());
					pst.setString(15, client.getPostCode());
					pst.setLong(16, client.getCategoryId());
					pst.setString(17, client.getJoinedActivity());
					pst.setLong(18,client.getRegisterTime());
					pst.setString(19, client.getRemark());
				}			
		});
		}catch(Exception ex){
			ex.printStackTrace();
			log.error(ex);
			throw ex;
		}
	}
	
	@Transactional
	public void save(Client client) 
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO CRM_CLIENT_INFO " +
					 "(PROVINCE ,CITY ,COUNTY ,UNIT ,NAME ,SEX ,CALL_NAME ,DUTY ,PHONE ,MOBILE, EMAIL, FAX ,NATION ,ADDRESS ,POST_CODE ,CATEGORY_ID ,JOINED_ACTIVITY ,REGISTER_TIME,REMARK)" +
					 "VALUES (? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		jdbcTemplate.update(sql, new Object[]
		                                    {
												client.getDeliverprovince(),client.getDelivercity(),client.getDeliverarea(),client.getUnit().trim(),client.getName(),
												client.getSex(),client.getCallName(),
												client.getDuty(),client.getPhone(),
												Format.isMobileNO(client.getMobile()) ? client.getMobile() : "",
												Format.isEmail(client.getEmail()) ? client.getEmail() : "",client.getFax(),client.getNation(),client.getAddress(),
												Format.isPostCode(client.getPostCode()) ? client.getPostCode() : "",client.getCategoryId(),client.getJoinedActivity(),
														System.currentTimeMillis(),client.getRemark()
		                                    }
							);
		
	}
	
	public Client showInfo(long id){
		final Client client = new Client();
		jdbcTemplate.query("SELECT * FROM CRM_CLIENT_INFO WHERE ID = ?", new Object[]{id}, new RowCallbackHandler(){
			public void processRow(ResultSet rs)throws SQLException
			{
				client.setDeliverprovince(rs.getString("PROVINCE"));
				client.setDelivercity(rs.getString("CITY"));
				client.setDeliverarea(rs.getString("COUNTY"));
				client.setName(rs.getString("NAME"));
				client.setUnit(rs.getString("UNIT"));
				client.setSex(rs.getString("SEX"));
				client.setNation(rs.getString("NATION"));
				client.setCallName(rs.getString("CALL_NAME"));
				client.setDuty(rs.getString("DUTY"));
				client.setEmail(rs.getString("EMAIL"));
				client.setPhone(rs.getString("PHONE"));
				client.setMobile(rs.getString("MOBILE"));
				client.setFax(rs.getString("FAX"));
				client.setAddress(rs.getString("ADDRESS"));
				client.setJoinedActivity(rs.getString("JOINED_ACTIVITY"));
				client.setPostCode(rs.getString("POST_CODE"));
				client.setRegisterTime(rs.getLong("REGISTER_TIME"));
				client.setCategoryId(rs.getLong("CATEGORY_ID"));
				client.setId(rs.getLong("ID"));
			}
		});
		return client;
	}
	
	@Transactional
	public void update(final Client client) {
		String sql = "UPDATE CRM_CLIENT_INFO SET PROVINCE = ?,CITY = ? ,COUNTY = ?,UNIT = ? ,NAME = ? , SEX = ?,CALL_NAME = ?,DUTY = ?,NATION = ?," +
				"PHONE = ?,MOBILE = ? ,FAX = ?,ADDRESS = ?,POST_CODE = ?,CATEGORY_ID = ?,JOINED_ACTIVITY = ? WHERE ID = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter()
		{

			public void setValues(PreparedStatement ps) throws SQLException 
			{
				// TODO Auto-generated method stub
				ps.setString(1, client.getDeliverprovince());
				ps.setString(2, client.getDelivercity());
				ps.setString(3, client.getDeliverarea());
				ps.setString(4, client.getUnit());
				ps.setString(5, client.getName());
				ps.setString(6, client.getSex());
				ps.setString(7, client.getCallName());
				ps.setString(8, client.getDuty());
				ps.setString(9, client.getNation());
				ps.setString(10, client.getPhone());
				ps.setString(11, client.getMobile());
				ps.setString(12, client.getFax());
				ps.setString(13, client.getAddress());
				ps.setString(14, client.getPostCode());
				ps.setLong(15, client.getCategoryId());
				ps.setString(16, client.getJoinedActivity());
				ps.setLong(17, client.getId());
				
			}
			
		});
	}
	
	public LinkedHashMap<String,ArrayList<Client>> listDuplicateRecord(){
		
		LinkedHashMap<String,ArrayList<Client>> records = new LinkedHashMap<String,ArrayList<Client>>();
		
		String sql = "SELECT * FROM CRM_CLIENT_INFO WHERE NAME IN (SELECT NAME FROM CRM_CLIENT_INFO WHERE IS_DELETED = 0 GROUP BY NAME HAVING COUNT(NAME) > 1) AND IS_DELETED = 0";
		List list = jdbcTemplate.queryForList(sql);
		String name;
		Client client ;
		
		for(int i = 0,j = list.size();i < j; i++ )
		{
			Map map = (Map)list.get(i);
			name = (String)map.get("NAME");
			if(name == null || "".equals(name)) continue;
			if(records.get(name) == null) records.put(name, new ArrayList<Client>());
			client = new Client();
			
			client.setId((Long)map.get("ID"));
			client.setUnit((String)map.get("UNIT"));
			client.setSex((String)map.get("SEX"));
			client.setCallName((String)map.get("CALL_NAME"));
			client.setDuty((String)map.get("DUTY"));
			client.setPhone((String)map.get("PHONE"));
			client.setMobile((String)map.get("MOBILE"));
			client.setEmail((String)map.get("EMAIL"));
			client.setFax((String)map.get("FAX"));
			client.setRegisterTime((Long)map.get("REGISTER_TIME"));
			client.setDeliverarea((String)map.get("COUNTY"));
			client.setDelivercity((String)map.get("CITY"));
			client.setDeliverprovince((String)map.get("PROVINCE"));
			client.setCategoryId((Long)map.get("CATEGORY_ID"));
			client.setNation((String)map.get("NATION"));
			client.setPostCode((String)map.get("POST_CODE"));
			client.setJoinedActivity((String)map.get("JOINED_ACTIVITY"));
			
			records.get(name).add(client);
		}
		return records;
	}
	
	@SuppressWarnings("unchecked")
	protected class ItemMapper implements RowMapper{

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Client client = new Client();
			// TODO Auto-generated method stub
			client.setId(rs.getLong("ID"));
			client.setUnit(rs.getString("UNIT"));
			client.setName(rs.getString("NAME"));
			client.setSex(rs.getString("SEX"));
			client.setCallName(rs.getString("CALL_NAME"));
			client.setDuty(rs.getString("DUTY"));
			client.setPhone(rs.getString("PHONE"));
			client.setMobile(rs.getString("MOBILE"));
			client.setEmail(rs.getString("EMAIL"));
			client.setFax(rs.getString("FAX"));
			client.setRegisterTime(rs.getLong("REGISTER_TIME"));
			client.setDeliverarea(rs.getString("COUNTY"));
			client.setDelivercity(rs.getString("CITY"));
			client.setDeliverprovince(rs.getString("PROVINCE"));
			client.setCategoryId(rs.getLong("CATEGORY_ID"));
			client.setNation(rs.getString("NATION"));
			client.setPostCode(rs.getString("POST_CODE"));
			client.setJoinedActivity(rs.getString("JOINED_ACTIVITY"));
			return client;
		}
		
	}
	/**
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	***/

}
