package com.huayue.poi;

import java.io.*;
 
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.List;  
   
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huayue.crm.base.util.BinaryCategoryUtil;
import com.huayue.crm.base.util.ClientCategory;
import com.huayue.crm.base.util.XlsImpUtil;
import com.huayue.crm.domain.Client;
import com.huayue.framework.util.DateUtil;
import com.huayue.framework.util.Format;


   
public class PoiExcuter {
	 
	 public static final Logger log = Logger.getLogger(PoiExcuter.class);
	 
	 /*
	  * 写入xls文件
	  */
	 public static ByteArrayOutputStream writeToXls(List<Client> list)throws Exception
	 {
	    HSSFWorkbook wb = null;
	    Client client = null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
    	try 
    	{
	    	wb = new HSSFWorkbook();
	    	HSSFSheet s = wb.createSheet();
	    	s.setDefaultColumnWidth(20);
	    	HSSFRow row = null;
	    	row = s.createRow(0);	  
	    	row.setHeightInPoints(20);
	    	
	    	createCell(wb,row,0,"省",true);
	    	createCell(wb,row,1,"市",true);
	    	createCell(wb,row,2,"地级市/县/区",true);
	    	createCell(wb,row,3,"单位",true);
	    	createCell(wb,row,4,"姓名",true);
	    	createCell(wb,row,5,"性别",true);
	    	createCell(wb,row,6,"称呼",true);
	    	createCell(wb,row,7,"职务",true);
	    	createCell(wb,row,8,"办公电话",true);
	    	createCell(wb,row,9,"手机号码",true);
	    	createCell(wb,row,10,"电子邮箱",true);
	    	createCell(wb,row,11,"传真",true);
	    	createCell(wb,row,12,"民族",true);
	    	createCell(wb,row,13,"地址",true);
	    	createCell(wb,row,14,"邮编",true);
	    	createCell(wb,row,15,"类别",true);
	    	createCell(wb,row,16,"参加过的活动",true);
	    	createCell(wb,row,17,"信息录入时间",true);
	    	createCell(wb,row,18,"补充说明",true);
	    	
    	    for (int i = 0;i < list.size(); i++ ) 
    	    {
    	    	row = s.createRow(i + 1);
    	    	client = list.get(i);
    	    	createCell(wb,row,0,client.getDeliverprovince()  ,false);
    	    	createCell(wb,row,1,client.getDelivercity(),false);
    	    	createCell(wb,row,2,client.getDeliverarea(),false);
    	    	createCell(wb,row,3,client.getUnit() ,false);
    	    	createCell(wb,row,4,client.getName(),false);
    	    	createCell(wb,row,5,client.getSex(),false);
    	    	createCell(wb,row,6,client.getCallName() ,false);
    	    	createCell(wb,row,7,client.getDuty() ,false);
    	    	createCell(wb,row,8,client.getPhone() ,false);
    	    	createCell(wb,row,9,Format.isMobileNO(client.getMobile()) ? client.getMobile() : "",false);
    	    	createCell(wb,row,10,Format.isEmail(client.getEmail()) ? client.getEmail() : "" ,false);
    	    	createCell(wb,row,11,client.getFax(),false);
    	    	createCell(wb,row,12,client.getNation() ,false);
    	    	createCell(wb,row,13,client.getAddress(),false);
    	    	createCell(wb,row,14,Format.isPostCode(client.getPostCode())? client.getPostCode() : "" ,false);
    	    	createCell(wb,row,15,BinaryCategoryUtil.getCategoryExportStringValue(client.getCategoryId()),false);
    	    	createCell(wb,row,16,client.getJoinedActivity() ,false);
    	    	createCell(wb,row,17,DateUtil.dateToString(new java.util.Date(client.getRegisterTime()),DateUtil.MONTG_DATE_FORMAT),false);
    	    	createCell(wb,row,18,client.getRemark(),false);
    	    }
	    } catch (Exception ex) {
	    	// TODO Auto-generated catch block
	    	//ex.printStackTrace();
	    	log.error(ex);
	    }
	    wb.write(baos);
		return baos;
	 }
	 
	 /**  
     * 读取xls文件内容  
     *  
     * @return List<XlsDto>对象  
	 * @throws IOException 
     * @throws IOException  
     *             输入/输出(i/o)异常  
     */ 
    public static List<Client> readXls(InputStream inp) throws Exception
    {   
        Workbook bk = null;
        List<Client> list = null;
        
        try{
        	bk = XlsImpUtil.create(inp);
        	Client xlsDto = null;  
        	list = new ArrayList<Client>();  
        	// 循环工作表Sheet  
	        for (int numSheet = 0; numSheet < bk.getNumberOfSheets(); numSheet++) 
	        {  
	            Sheet hssfSheet = bk.getSheetAt(numSheet);  
	            if (hssfSheet == null) {  
	                continue;  
	            }  
	            // 循环行Row  
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++ ) 
	            {  
	                Row hssfRow = hssfSheet.getRow(rowNum);  
	                if (hssfRow == null) {  
	                    continue;  
	                }  
	                xlsDto = new Client();  
	                
	                //省
	                Cell deliverprovince = hssfRow.getCell(0);  
	                xlsDto.setDeliverprovince(getValue(deliverprovince)); 
	                
	                //市
	                Cell delivercity = hssfRow.getCell(1);  
	                xlsDto.setDelivercity(getValue(delivercity));  
	                
	                //地级市/县/区
	                Cell deliverarea = hssfRow.getCell(2);
	                xlsDto.setDeliverarea(getValue(deliverarea));
	                
	                //单位
	                Cell unit = hssfRow.getCell(3);  
	                xlsDto.setUnit(getValue(unit));  
	                
	                //姓名
	                Cell name = hssfRow.getCell(4);   
	                xlsDto.setName(getValue(name));
	                
	                //性别
	                Cell sex = hssfRow.getCell(5);
	                xlsDto.setSex(getValue(sex));
	                
	                //称呼
	                Cell callname = hssfRow.getCell(6);
	                xlsDto.setCallName(getValue(callname));
	                
	                //职务
	                Cell duty = hssfRow.getCell(7);
	                xlsDto.setDuty(getValue(duty));
	                
	                //办公电话
	                Cell phoneNumber = hssfRow.getCell(8);
	                xlsDto.setPhone(getValue(phoneNumber));
	                
	                //手机号码
	                Cell mobile = hssfRow.getCell(9);
	                xlsDto.setMobile(getCellFormatValue(mobile));
	                
	                //邮箱
	                Cell email = hssfRow.getCell(10);
	                xlsDto.setEmail(getValue(email));
	                
	                //传真
	                Cell fax = hssfRow.getCell(11);
	                xlsDto.setFax(getValue(fax));
	                
	                //民族
	                Cell nation = hssfRow.getCell(12);
	                xlsDto.setNation(getValue(nation));
	                
	                //地址
	                Cell address = hssfRow.getCell(13);
	                xlsDto.setAddress(getValue(address));
	                
	                //邮编
	                Cell postcode = hssfRow.getCell(14);
	                xlsDto.setPostCode(getCellFormatValue(postcode));
	                
	                //类别
	                Cell category = hssfRow.getCell(15);	
	                System.out.println("category --------" + category);
	                xlsDto.setCategoryId(getCategoryLongValue(getStringCellValue(category).trim()));
	                
	                //参加过的活动
	                Cell joinedActivity = hssfRow.getCell(16);
	                xlsDto.setJoinedActivity(getValue(joinedActivity));
	                
	                Cell registerTime = hssfRow.getCell(17);
	                //System.out.println(" registerTime ---------" + registerTime + "----" + name);
	                if(registerTime == null || "".equals(registerTime)) 
	                	xlsDto.setRegisterTime(System.currentTimeMillis());
	                else{
	                	String tmp = getDateValue(registerTime);
	                	xlsDto.setRegisterTime(!"".equals(tmp) ? (DateUtil.stringtoDate(tmp, DateUtil.MONTG_DATE_FORMAT)).getTime():System.currentTimeMillis()  );
	                }	
	                //补充说明
	                Cell remark = hssfRow.getCell(18);
	                xlsDto.setRemark(getStringCellValue(remark));
	                
	                list.add(xlsDto);  
	            }
	        }
        }catch(Exception ex){
        	ex.printStackTrace();
        	log.error(ex);
        	throw ex;
        }
        /****
        try
        {
        	try {	book = new XSSFWorkbook(new ByteArrayInputStream(data)); } catch (IOException e) {}
        	XSSFWorkbook bk = (XSSFWorkbook)book;
        	Client xlsDto = null;  
        	list = new ArrayList<Client>();  

        	// 循环工作表Sheet  
	        for (int numSheet = 0; numSheet < bk.getNumberOfSheets(); numSheet++) 
	        {  
	            XSSFSheet hssfSheet = (XSSFSheet)bk.getSheetAt(numSheet);  
	            if (hssfSheet == null) {  
	                continue;  
	            }  
	            // 循环行Row  
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++ ) 
	            {  
	                XSSFRow hssfRow = hssfSheet.getRow(rowNum);  
	                if (hssfRow == null) {  
	                    continue;  
	                }  
	                xlsDto = new Client();  
	                
	                //省
	                XSSFCell deliverprovince = hssfRow.getCell(0);  
	                xlsDto.setDeliverprovince(getValue(deliverprovince)); 
	                
	                //市
	                XSSFCell delivercity = hssfRow.getCell(1);  
	                xlsDto.setDelivercity(getValue(delivercity));  
	                
	                //地级市/县/区
	                XSSFCell deliverarea = hssfRow.getCell(2);
	                xlsDto.setDeliverarea(getValue(deliverarea));
	                
	                //单位
	                XSSFCell unit = hssfRow.getCell(3);  
	                xlsDto.setUnit(getValue(unit));  
	                
	                //姓名
	                XSSFCell name = hssfRow.getCell(4);   
	                xlsDto.setName(getValue(name));
	                
	                //性别
	                XSSFCell sex = hssfRow.getCell(5);
	                xlsDto.setSex(getValue(sex));
	                
	                //称呼
	                XSSFCell callname = hssfRow.getCell(6);
	                xlsDto.setCallName(getValue(callname));
	                
	                //职务
	                XSSFCell duty = hssfRow.getCell(7);
	                xlsDto.setDuty(getValue(duty));
	                
	                //办公电话
	                XSSFCell phoneNumber = hssfRow.getCell(8);
	                xlsDto.setPhone(getValue(phoneNumber));
	                
	                //手机号码
	                XSSFCell mobile = hssfRow.getCell(9);
	                xlsDto.setMobile(getCellFormatValue(mobile));
	                
	                //邮箱
	                XSSFCell email = hssfRow.getCell(10);
	                xlsDto.setEmail(getValue(email));
	                
	                //传真
	                XSSFCell fax = hssfRow.getCell(11);
	                xlsDto.setFax(getValue(fax));
	                
	                //民族
	                XSSFCell nation = hssfRow.getCell(12);
	                xlsDto.setNation(getValue(nation));
	                
	                //地址
	                XSSFCell address = hssfRow.getCell(13);
	                xlsDto.setAddress(getValue(address));
	                
	                //邮编
	                XSSFCell postcode = hssfRow.getCell(14);
	                xlsDto.setPostCode(getCellFormatValue(postcode));
	                
	                //类别
	                XSSFCell category = hssfRow.getCell(15);	
	                xlsDto.setCategoryId(getCategoryLongValue(getStringCellValue(category).trim()));
	                
	                //参加过的活动
	                XSSFCell joinedActivity = hssfRow.getCell(16);
	                xlsDto.setJoinedActivity(getValue(joinedActivity));
	                
	                XSSFCell registerTime = hssfRow.getCell(17);
	                xlsDto.setRegisterTime(registerTime != null ? (DateUtil.stringtoDate(getDateValue(registerTime), DateUtil.MONTG_DATE_FORMAT)).getTime() : System.currentTimeMillis());
	                
	                //补充说明
	                XSSFCell remark = hssfRow.getCell(18);
	                xlsDto.setRemark(getStringCellValue(remark));
	                
	                list.add(xlsDto);  
	            }  
	        }  
        	
        }
        catch(Exception ex)
        {
        	
        	ex.printStackTrace();
        	try{book = new HSSFWorkbook(new ByteArrayInputStream(data));}catch(Exception e){}  
	        Client xlsDto = null;  
	        list = new ArrayList<Client>();  
	        
	        // 循环工作表Sheet  
	        for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) 
	        {  
	            HSSFSheet hssfSheet = (HSSFSheet)book.getSheetAt(numSheet);  
	            if (hssfSheet == null) {  
	                continue;  
	            }  
	            // 循环行Row  
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++ ) 
	            {  
	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
	                if (hssfRow == null) {  
	                    continue;  
	                }  
	                xlsDto = new Client();  
	               
	                HSSFCell deliverprovince = hssfRow.getCell(0);  
	                xlsDto.setDeliverprovince(getValue(deliverprovince)); 
	                
	                HSSFCell delivercity = hssfRow.getCell(1);  
	                xlsDto.setDelivercity(getValue(delivercity));  
	                
	                HSSFCell deliverarea = hssfRow.getCell(2);  
	                xlsDto.setDeliverarea(getValue(deliverarea));
	                
	                HSSFCell unit = hssfRow.getCell(3);  
	                xlsDto.setUnit(getValue(unit));  
	                
	                HSSFCell name = hssfRow.getCell(4);  
	                xlsDto.setName(getValue(name));
	                
	                HSSFCell sex = hssfRow.getCell(5);
	                xlsDto.setSex(getValue(sex));
	                
	                HSSFCell callname = hssfRow.getCell(6);
	                xlsDto.setCallName(getValue(callname));
	                
	                HSSFCell duty = hssfRow.getCell(7);
	                xlsDto.setDuty(getValue(duty));
	                
	                HSSFCell phoneNumber = hssfRow.getCell(8);
	                xlsDto.setPhone(getValue(phoneNumber));
	                
	                HSSFCell mobile = hssfRow.getCell(9);
	                xlsDto.setMobile(getCellFormatValue(mobile));
	                
	                HSSFCell email = hssfRow.getCell(10);
	                xlsDto.setEmail(getValue(email));
	                
	                HSSFCell fax = hssfRow.getCell(11);
	                xlsDto.setFax(getValue(fax));
	                
	                HSSFCell nation = hssfRow.getCell(12);
	                xlsDto.setNation(getValue(nation));
	                
	                HSSFCell address = hssfRow.getCell(13);
	                xlsDto.setAddress(getValue(address));
	                
	                HSSFCell postcode = hssfRow.getCell(14);
	                xlsDto.setPostCode(getCellFormatValue(postcode));
	                
	                HSSFCell category = hssfRow.getCell(15);
	                xlsDto.setCategoryId(getCategoryLongValue(getStringCellValue(category).trim()));
	                
	                HSSFCell joinedActivity = hssfRow.getCell(16);
	                xlsDto.setJoinedActivity(getValue(joinedActivity));
	                
	                HSSFCell registerTime = hssfRow.getCell(17);
	                xlsDto.setRegisterTime(registerTime != null ? (DateUtil.stringtoDate(getDateValue(registerTime), DateUtil.MONTG_DATE_FORMAT)).getTime() : System.currentTimeMillis());
	                
	                HSSFCell remark = hssfRow.getCell(18);
	                xlsDto.setRemark(getValue(remark));
	                list.add(xlsDto);  
	            }  
	        }  
        }****/
        return list;  
    }  
    
	/**  
     * 得到Excel表中的值  
     *  
     * @param hssfCell  
     *            Excel中的每一个格子  
     * @return Excel中每一个格子中的值  
     */ 
    public static String getValue(Cell cell) 
    {  
    	if (cell == null) return "";
       
    	String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) return "";       
        return strCell;
    }
    
    public static String getDateValue(Cell cell) throws Exception{
    	String cellValue = "";

    	if (HSSFDateUtil.isCellDateFormatted(cell)) {  
            double d = cell.getNumericCellValue();  
            Date date = HSSFDateUtil.getJavaDate(d); 
            SimpleDateFormat dformat=new SimpleDateFormat("yyyy/MM/dd");
            cellValue=dformat.format(date);
         }else{
        	 cellValue=	cell.getDateCellValue() == null ? "" : String.valueOf(cell.getDateCellValue().toLocaleString());
         } 
    
    	return cellValue;
    }
    
    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    public static String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    cellvalue = sdf.format(date);                 
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                	DecimalFormat df = new DecimalFormat("#");
                    cellvalue = df.format(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    
    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getStringCellValue(Cell cell) {
        String strCell = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }      
        return strCell;
    }
    
    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    public static String getDateCellValue(Cell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }
     
    public static void createCell(HSSFWorkbook wb, HSSFRow row, int col, String val ,boolean isSetFont) {

	    HSSFCell cell = row.createCell(col);
	    
	    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	    
	    cell.setCellValue(val);
	    
	    //设置单元格格式	    
	    if(isSetFont){
	    	HSSFCellStyle cellstyle = wb.createCellStyle();
		    //cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	    	cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);//设置水平对齐方式
		    cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置垂直对齐方式
		    Font font = wb.createFont();
		    font.setFontName("宋体");
		    //设置字体
		    font.setFontHeightInPoints((short)15);
		    //设置粗体
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    cellstyle.setFont(font);
		    cell.setCellStyle(cellstyle);
	    }
	    
   }
    
    public static long getCategoryLongValue(String str){
    	return BinaryCategoryUtil.arrayToBinaryCategory(analysiCategory(str));
    }
    
    public static Object[] analysiCategory(String category){
    	if("".equals(category)) return new Object[]{};
    	
    	if(category.indexOf(",") == -1) return new Object[]{ClientCategory.map.get(category)};
    	else{
    		String[] categories = category.split(",");
    		Object[] tmp = new Object[categories.length];
    		for(int i = 0; i < categories.length ;i++ ){
    			if(!"".equals(categories[i])) tmp[i] = ClientCategory.map.get(categories[i]);
    		}
    		return tmp;
    	}
    	
    }
    
    public static void main(String[] args)throws Exception{
    	System.out.println(DateUtil.stringtoDate("2012年9月".trim().replaceAll("年", "-").replaceAll("月",""), DateUtil.MONTG_DATE_FORMAT).getTime());
    }
}
