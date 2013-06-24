package com.huayue.apply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.huayue.apply.domain.RegisterUnit;
import com.huayue.framework.util.PageInfo;
import com.huayue.framework.util.Utils;


public class UnitManager extends com.huayue.sms.data.DBAccess{
	private static final Logger log = Logger.getLogger(UnitManager.class);
	
	public PageInfo ListUnitMsgs(int isStay,int isTogether, String unit ,long startTime , long endTime ,int pageIndex,int pageSize,int standard ,int category_id)throws Exception
	{
		PageInfo pInfo = null;
		ResultSet rst = null;
		PreparedStatement prod = null;
		StringBuilder factor = new StringBuilder();
		
		try
		{
			if( isStay != ExportController.DEFAULT_SELECT ) 	factor.append(" AND T.IS_STAY = " + isStay);
			if( isTogether != ExportController.DEFAULT_SELECT) 	factor.append(" AND T.IS_TOGETHER = " + isTogether);
			if(!"".equals(unit) && unit != null) 				factor.append(" AND T.UNIT LIKE '%" + unit + "%'");
			if(startTime > 0) 									factor.append(" AND T.APPLY_TIME >= " + startTime);
			if(endTime > 0) 									factor.append(" AND T.APPLY_TIME < " + endTime);
			if(standard >= 0) 									factor.append(" AND T.CHARGE_STANDARD = " + standard);
			if(category_id > 0)									factor.append(" AND T.CATEGORY_ID = " + category_id);
			
			openConnection();
			prod = prepareStatement("SELECT COUNT(*) AS NUM FROM WEB_PLATFORM_UNIT T WHERE T.ID > 0" + factor.toString());
			rst = prod.executeQuery();
			if(!rst.next()) throw new Exception("No record exist....");
			pInfo = new PageInfo(pageIndex, rst.getInt("NUM"), pageSize);
			closeStatement(rst,null);
			
			prod = prepareStatement(Utils.getPageMysql("*", "SELECT T.ID,T.UNIT,T.IS_STAY,T.IS_TOGETHER,T.ROOM_NUM,T.APPLY_TIME,T.INVOICE_ADDRESS,T.CHARGE_STANDARD,T.CHARGE_STATUS FROM WEB_PLATFORM_UNIT T WHERE T.IS_DELETED = 0 " + factor, pageIndex, pageSize));
			rst = prod.executeQuery();
			while(rst.next())
			{
				RegisterUnit rUnit = new RegisterUnit();
				rUnit.setId(rst.getInt(1));
				rUnit.setName(rst.getString(2));
				rUnit.setIsStay(rst.getInt(3));
				rUnit.setIsTogether(rst.getInt(4));
				rUnit.setRonnNum(rst.getInt(5));
				rUnit.setApplyTime(rst.getLong(6));
				rUnit.setInvoiceAddr(rst.getString(7));
				rUnit.setCharge_standard(rst.getDouble(8));
				rUnit.setCharge_status(rst.getInt(9));
				
				pInfo.add(rUnit);
			}
		}finally{
			closeConnection();
			closeStatement(rst,prod);
		}
		return pInfo;
	}
	
	public void updateChargeStandard(String[] ids ,double standard)throws Exception
	{
		PreparedStatement prod = null;
		
		try{
			openConnection();
			beginTransaction();
			prod = prepareStatement("UPDATE WEB_PLATFORM_UNIT SET CHARGE_STANDARD = ?,CHARGE_STATUS = 1 WHERE ID = ?");
			for(int i = 0;i < ids.length ;i++ ){
				prod.setDouble(1, standard);
				prod.setInt(2, Integer.parseInt(ids[i]));
				
				prod.addBatch();
			}
			prod.executeBatch();
			endTransaction(true);
		}catch(Exception ex){
			log.error(ex);
			endTransaction(false);
			throw ex;
		}finally{
			closeConnection();
			closeStatement(null,prod);
		}
	}
}
