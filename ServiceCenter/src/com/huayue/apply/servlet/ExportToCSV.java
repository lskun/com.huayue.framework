package com.huayue.apply.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.apply.ExportController;
import com.huayue.apply.domain.ComprehensData;
import com.huayue.framework.util.DateUtil;
import com.huayue.framework.util.DateUtils;
import com.huayue.framework.util.ServletUtils;
import com.huayue.framework.util.Utils;


public class ExportToCSV extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ExportToCSV.class);
	
	public ExportToCSV() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("GB18030");
		PrintWriter out = response.getWriter();
		
		int is_stay;
		int is_together;
		String unit;
		long start_time;
		long end_time;
		int standard;
		int category_id;
		
		ArrayList<ComprehensData> list = null;
		try{
			is_stay = ServletUtils.getInt(request, "is_stay", ExportController.DEFAULT_SELECT);
			is_together = ServletUtils.getInt(request, "is_together", ExportController.DEFAULT_SELECT);
			unit = ServletUtils.getString(request, "unit");
			standard = Integer.parseInt(request.getParameter("standard"));
			
			start_time = (request.getParameter("start_time") == null || "".equals(request.getParameter("start_time"))) ? 0L : DateUtil.stringtoDate(request.getParameter("start_time"), DateUtil.FORMAT_ONE).getTime();
			end_time = (request.getParameter("end_time") == null ||  "".equals(request.getParameter("end_time"))) ? 0L : DateUtil.stringtoDate(request.getParameter("end_time"), DateUtil.FORMAT_ONE).getTime();
			category_id = ServletUtils.getInt(request, "category_id", 0);
			
			list = new ExportController().queryToExport(is_stay, is_together, unit, start_time, end_time ,standard ,category_id);
			response.addHeader("Content-Type", "application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + DateUtils.formatDateTo(System.currentTimeMillis(), "yyyy-MM-dd") + ".csv");
            
            out.println("单位名称,姓名,职务,办公电话,手机号码,电子邮箱,民族,性别,是否需要安排住宿（是、否）,住宿标间数量,是否同意调剂与他人合住（是、否）,发票上准确的单位名称,通过何种方式知悉活动（可多选）（人民教育、中国教育新闻网、教育在线、中国教育报、信函、其他）,单位地址,邮编,收费标准");
            for(int i = 0,l = list.size(); i<l; i++){
            	ComprehensData cpre = list.get(i);
            	out.print(cpre.getUnit_name());
            	out.print(',');
            	out.print(cpre.getName());
            	out.print(',');
            	out.print(cpre.getPosition());
            	out.print(',');
            	out.print(cpre.getPhoneNumber());
            	out.print(',');
            	out.print(cpre.getMobile());
            	out.print(',');
            	out.print(cpre.getEmail());
            	out.print(',');
            	out.print(cpre.getNation());
            	out.print(',');
            	out.print(cpre.getGenderText());
            	out.print(',');
            	out.print(cpre.getIsStay() == 0 ? "否" : "是");
            	out.print(',');
            	out.print(cpre.getRonnNum());
            	out.print(',');
            	out.print(cpre.getIsTogether() == 0 ? "否" : "是");
            	out.print(',');
            	out.print(cpre.getInvoiceAddr());
            	out.print(',');
            	out.print(cpre.getUnitSurvey() == null  ? "" : ExportController.changeArr(cpre.getUnitSurvey(), ExportController.DEFAULT_UNIT_SURVEY));
            	out.print(',');
            	out.print(cpre.getUnitAddr());           	
            	out.print(',');
            	out.print(cpre.getPost_code());         
            	out.print(',');
            	out.println(cpre.getCharge_standard());
            }
		}catch(Exception ex){
			response.setHeader("Content-Type", "text/html");
            response.setHeader("Content-Disposition", "");
            log.error(ex);
            out.println(Utils.getTipHtml("", ex.toString(), null));
		}finally{
			out.flush();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
