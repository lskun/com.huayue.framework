package com.huayue.crm.control;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huayue.crm.base.util.BinaryCategoryUtil;
import com.huayue.crm.dao.ClientDao;
import com.huayue.crm.domain.Client;
import com.huayue.poi.PoiExcuter;


@Controller
@RequestMapping("/data")
public class DataOperatorController 
{
	public static final Logger log = Logger.getLogger(DataOperatorController.class);
	
	public static final String MODEL_PATH = "conf/model.xls";
	
	public static final String MODEL_NAME = "数据文件模板.xls";
	
	public static final String SUCCESS = "1";
	
	public static final String FAILED = "-1";
	
	@Autowired
	ClientDao clientDao;
	
	@RequestMapping(value="/modeldownload")
	public void downloadModel(HttpServletResponse response)
	{
		OutputStream os = null;	
		response.addHeader("Content-Type", "application/x-msdownload");
		BufferedInputStream bis = null;
		Resource res = null;
		
		try
		{
			os = response.getOutputStream();
			res = new ClassPathResource(MODEL_PATH);
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(MODEL_NAME.getBytes("UTF-8"), "ISO8859-1"));
			bis = new BufferedInputStream(res.getInputStream());
			byte[] buffer = IOUtils.toByteArray(bis);
			os.write(buffer);
			
		}catch(Exception ex){
			log.error(ex);
		}
	}
	
	@RequestMapping(value="/uploadfile",method = RequestMethod.POST)
	public String upload(ModelMap model,@RequestParam(value="myfile") MultipartFile multipartFile) {
		List<Client> list = null;
		
		try 
		{
			log.info(multipartFile.getName());
			list = PoiExcuter.readXls(multipartFile.getInputStream());
			clientDao.saveDataFromExcel(list);
			model.put("status", SUCCESS);			
		} catch (IOException e) {
			log.error(e);
			model.put("status", e);
		} catch (Exception e) {
			log.error(e);
			model.put("status", e);
		}
		return "uploadback";
	}
	
	@RequestMapping(value="/exportToFile",method = RequestMethod.POST)
	public void exportToXls(HttpServletResponse response,Client client,HttpServletRequest request)
	{
		OutputStream os = null;		
		byte[] data ;
		List<Client> list = null;
		
		try
		{
			os = response.getOutputStream();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String("客户数据.xls".getBytes("UTF-8"), "ISO8859-1"));
			
			client.setCategoryId(BinaryCategoryUtil.arrayToBinaryCategory(request.getParameterValues("categoryId")));
			//根据条件得出数据集合
			list = clientDao.searchForExport(client);
			//写入xls后缀文件的byte数组
			data = PoiExcuter.writeToXls(list).toByteArray();
			os.write(data);			
		}
		catch(Exception ex)
		{
			log.error(ex);
		}
	}
}
