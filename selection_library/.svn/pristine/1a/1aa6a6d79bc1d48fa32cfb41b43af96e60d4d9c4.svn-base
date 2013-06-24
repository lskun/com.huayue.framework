package com.huayue.framework.util;

import java.io.*;
import java.util.*;
import java.text.*;

import org.jdom.*;
import org.jdom.input.*;

public class Utils
{
    public static String getTypeName(int type)
    {
        String[] Types = new String[] {
                "", "product", "article", "artist",
                "auction", "exchange", "appraisal", "view"
        };
        if (type >= Types.length || type < 0) return null;
        return Types[type];
    }

    public static String getTipHtml_bak(String title, String content, String url)
    {
        String shtml = "";
        title = null == title ? "" : title;
        content = null == content ? "无内容." : content;
        shtml += "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n";
        shtml += "<HTML><HEAD><TITLE></TITLE><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><META NAME=\"ROBOTS\" CONTENT=\"NONE\"></HEAD><BODY><CENTER>\r\n";
        shtml += "<SCRIPT LANGUAGE=\"JavaScript\" TYPE=\"text/javascript\">" + (null == url ? "" : "setTimeout(\"window.location=unescape(\'" + BBString.escape(url) + "\')\", 2000);") + "</SCRIPT>\r\n";
        shtml += "<br/><div style='width:400px;padding-top:4px;height:24;font-size:10pt;border-left:1px solid #7ECDFB;border-top:1px solid #7ECDFB;border-right:1px solid #7ECDFB;background-color:#ACE4FF;text-align:left;font-weight:bold;'>" + title + "</div>\r\n";
        shtml += "<div style='width:400px;height:100;font-size:10pt;border:1px solid #7ECDFB;background-color:#EEFAFE'><br/><br/>\r\n";
        shtml += content + "<br/><br/><a href='" + url + "'>" + (null == url ? "" : "如果你的浏览器没有反应,请点击这里.") + "</a><br/><br/></div>\r\n";
        shtml += "</body></html>";
        return shtml;
    }

    public static String getTipHtml(String title, String content, String url)
    {
        String shtml = "";
        title = null == title || "".equals(title) ? "提示信息" : title;
        content = null == content ? "无内容" : content;
        shtml += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">";
        shtml += "<head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><title></title>";
        shtml += "<link href=\"/css/qc.css\" rel=\"stylesheet\" type=\"text/css\" /></head><body bgcolor=\"#f2f2f2\"><br /><br /><br />";
        shtml += "<table width=\"600\" border=\"0\" align=\"center\" cellpadding=\"3\" cellspacing=\"1\" class=\"tableborder\">";
        shtml += "<tr class=\"header\"><td height=\"25\"><div align=\"center\">" + title + "</div></td></tr>";
        shtml += "<tr bgcolor=\"#FFFFFF\"> <td height=\"100\"> <div align=\"center\"><br><b>" + content + "</b><br><br>";
        shtml += "<a href=\"" + url + "\"><span class=\"px_12\">" + (null == url ? "" : "如果您的浏览器没有反应,请点击这里.") + "</span></a><br><br></div></td>";
        shtml += "</tr></table><script language=\"javascript\" type=\"text/javascript\">" + (null == url ? "" : "setTimeout(\"window.location=unescape(\'" + BBString.escape(url) + "\')\", 3000);") + "</script></body></html>";

        return shtml;
    }


    public static String cut(String text, int len)
    {
        char ch;
        String ret = "";
        if (null == text) return ret;
        if (len < 1) return ret;

        for (int i = 0, l = text.length(), k = 0; i < len; k++)
        {
            ch = text.charAt(k);
            if (ch < 0x80) i += 1;
            else i += 2;
            ret += ch;
        }
        return ret;
    }

    public static String getDate(java.util.Date date)
    {
        String ret = "";
        ret = date.getMonth() + 1 + "-" + date.getDate();
        date = null;
        return ret;
    }

    public static java.util.Date getTime(String date)
    {
        if (null == date) return null;
        if (!date.matches("^\\d{14}$")) return null;
        return new java.util.Date(
                Integer.parseInt(date.replaceAll("^(\\d{4})\\d*$", "$1"), 10) - 1900,
                Integer.parseInt(date.replaceAll("^\\d{4}(\\d{2})\\d*$", "$1"), 10) - 1,
                Integer.parseInt(date.replaceAll("^\\d{6}(\\d{2})\\d*$", "$1"), 10),
                Integer.parseInt(date.replaceAll("^\\d{8}(\\d{2})\\d*$", "$1"), 10),
                Integer.parseInt(date.replaceAll("^\\d{10}(\\d{2})\\d*$", "$1"), 10),
                Integer.parseInt(date.replaceAll("^\\d{12}(\\d{2})\\d*$", "$1"), 10)
            );
    }

    public static String clear(String text)
    {
        if (null == text) return "";
        text = text.replaceAll("\\s+(o|O)(n|N)\\w+=[\"\'][\\s\\S]*?[\"\']", "");                                                                                    // 脚本
        text = text.replaceAll("\\s+\\&#111;(n|N)\\w+=[\"\'][\\s\\S]*?[\"\']", "");                                                                                 // 脚本
        text = text.replaceAll("<(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)[\\s\\S]*?>[\\s\\S]*?</(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)[\\s\\S]*?>", "");                              // 脚本
        text = text.replaceAll("(s|S)(t|T)(y|Y)(l|L)(e|E)=\"[\\s\\S]*?\"", "");                                                                                     // 样式
        text = text.replaceAll("(s|S)(t|T)(y|Y)(l|L)(e|E)=\'[\\s\\S]*?\'", "");                                                                                     // 样式

        //text = text.replaceAll("(\\s*<(b|B)(r|R)[\\s\\S]*?>\\s*)+", "<br/>");                                                                                       // 杂项
        //text = text.replaceAll("<(i|I)(m|M)(g|G)[\\s\\S]*?>", "");                                                                                                  // 杂项
        text = text.replaceAll("<(o|O)(b|B)(j|J)(e|E)(c|C)(t|T)[\\s\\S]*?>[\\s\\S]*?</(o|O)(b|B)(j|J)(e|E)(c|C)(t|T)[\\s\\S]*?>", "");                              // 杂项
        text = text.replaceAll("<(a|A)[\\s\\S]*?>", "").replaceAll("</(a|A)[\\s\\S]*?>", "");                                                                       // 杂项
        text = text.replaceAll("<(d|D)(i|I)(v|V)[\\s\\S]*?>", "").replaceAll("</(d|D)(i|I)(v|V)[\\s\\S]*?>", "");                                                   // 杂项
        text = text.replaceAll("<(i|I)(f|F)(r|R)(a|A)(m|M)(e|E)[\\s\\S]*?>[\\s\\S]*?(</(i|I)(f|F)(r|R)(a|A)(m|M)(e|E)[\\s\\S]*?>)?", "");                           // 杂项
        text = text.replaceAll("<(e|E)(m|M)(b|B)(e|E)(d|D)[\\s\\S]*?>[\\s\\S]*?</(e|E)(m|M)(b|B)(e|E)(d|D)[\\s\\S]*?>", "");                                        // 杂项
        text = text.replaceAll("<(m|M)(a|A)(r|R)(q|Q)(u|U)(e|E)(e|E)[\\s\\S]*?>[\\s\\S]*?(</(m|M)(a|A)(r|R)(q|Q)(u|U)(e|E)(e|E)[\\s\\S]*?>)?", "");                 // 杂项
        
        text = text.replaceAll("</?(i|I)(n|N)(p|P)(u|U)(t|T)[\\s\\S]*?>", "");                                                                                      // 表单
        text = text.replaceAll("<(t|T)(e|E)(x|X)(t|T)(a|A)(r|R)(e|E)(a|A)[\\s\\S]*?>[\\s\\S]*?(</(t|T)(e|E)(x|X)(t|T)(a|A)(r|R)(e|E)(a|A)[\\s\\S]*?)?>", "");       // 表单
        text = text.replaceAll("<(s|S)(e|E)(l|L)(e|E)(c|C)(t|T)[\\s\\S]*?>[\\s\\S]*?</(s|S)(e|E)(l|L)(e|E)(c|C)(t|T)[\\s\\S]*?>", "");                              // 表单
        text = text.replaceAll("<(o|O)(p|P)(t|T)(i|I)(o|O)(n|N)[\\s\\S]*?>[\\s\\S]*?</(o|O)(p|P)(t|T)(i|I)(o|O)(n|N)[\\s\\S]*?>", "");                              // 表单
        text = text.replaceAll("</?(f|F)(i|I)(e|E)(l|L)(d|D)(s|S)(e|E)(t|T)[\\s\\S]*?>", "");                                                                       // 表单
        text = text.replaceAll("</?(l|L)(e|E)(g|G)(e|E)(n|N)(d|D)[\\s\\S]*?>", "");                                                                                 // 表单
        text = text.replaceAll("</?(f|F)(o|O)(r|R)(m|M)[\\s\\S]*?>", "");                                                                                           // 表单
        text = text.replaceAll("<(b|B)(u|U)(t|T)(t|T)(o|O)(n|N)[\\s\\S]*?>[\\s\\S]*?</?(b|B)(u|U)(t|T)(t|T)(o|O)(n|N)[\\s\\S]*?>", "");                             // 表单
        text = text.replaceAll("</?(l|L)(a|A)(b|B)(e|E)(l|L)>", "");                                                                                                // 表单
        
        text = text.replaceAll("</?(t|T)(a|A)(b|B)(l|L)(e|E)[\\s\\S]*?>", "");                                                                                      // 杂项
        text = text.replaceAll("</?(t|T)(b|B)(o|O)(d|D)(y|Y)[\\s\\S]*?>", "");                                                                                      // 杂项
        text = text.replaceAll("</?(t|T)(h|H)[\\s\\S]*?>", "");                                                                                                     // 杂项
        text = text.replaceAll("</?(t|T)(d|D)[\\s\\S]*?>", "");                                                                                                     // 杂项
        text = text.replaceAll("</?(t|T)(r|R)[\\s\\S]*?>", "<br/>");                                                                                                // 杂项
        text = text.replaceAll("</?(a|A)(p|P)(p|P)(l|L)(e|E)(t|T)[\\s\\S]*?>", "");                                                                                 // 杂项
        text = text.replaceAll("</?(f|F)(r|R)(a|A)(m|M)(e|E)(s|S)(e|E)(t|T)[\\s\\S]*?>", "");                                                                       // 杂项
        
        text = text.replaceAll("</?(f|F)(o|O)(n|N)(t|T)[\\s\\S]*?>", "<span>");                                                                                     // 样式
        text = text.replaceAll("<(s|S)(t|T)(y|Y)(l|L)(e|E)[\\s\\S]*?>[\\s\\S]*?</(s|S)(t|T)(y|Y)(l|L)(e|E)[\\s\\S]*?>", "");                                        // 样式
        text = text.replaceAll("</?(l|L)(i|I)(n|N)(k|K)[\\s\\S]*?>", "");                                                                                           // 样式
        text = text.replaceAll("</?(h|H)\\d+[\\s\\S]*?>", "");                                                                                                      // 样式

        text = text.replaceAll("</?(h|H)(r|R)[\\s\\S]*?/?>", "");                                                                                                   // 杂项
        text = text.replaceAll("</?(l|L)(i|I)[\\s\\S]*?>", "");                                                                                                     // 杂项
        text = text.replaceAll("</?(u|U)(l|L)[\\s\\S]*?>", "");                                                                                                     // 杂项
        text = text.replaceAll("</?(B|b)(O|o)(D|d)(Y|y)[\\s\\S]*?>", "");                                                                                           // 杂项
        text = text.replaceAll("</?(H|h)(t|T)(m|M)(l|L)[\\s\\S]*?>", "");                                                                                           // 杂项
        text = text.replaceAll("</?(h|H)(e|E)(a|A)(d|D)[\\s\\S]*?>", "");                                                                                           // 杂项
        text = text.replaceAll("</?(m|M)(e|E)(t|T)(a|A)[\\s\\S]*?>", "");                                                                                           // 杂项
        text = text.replaceAll("</?(t|T)(i|I)(t|T)(l|L)(e|E)[\\s\\S]*?>", "");                                                                                      // 杂项
        text = text.replaceAll("<!--[\\s\\S]*?-->", "");                                                                                                            // 杂项
        text = text.replaceAll("</?(n|N)(o|O)(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)[\\s\\S]*?>", "");                                                                       // 杂项
        text = text.replaceAll("</?(c|C)(e|E)(n|N)(t|T)(e|E)(r|R)[\\s\\S]*?>", "");                                                                                 // 杂项
        text = text.replaceAll("<!DOCTYPE[\\s\\S]*?>", "");                                                                                                         // 杂项
        //text = text.replaceAll("(?is)(<img[\\s\\S]*?)src=[\'\"]?(.+?)[\'\"]?([\\s\\S]*?)>", "<img src=\"$2\" />");
        //text = text.replaceAll("(\\s*<(b|B)(r|R)[\\s\\S]*?>\\s*)+", "<br />");                                                                                      // 杂项
        //text = text.replaceAll("<[\\s\\S]+?style=[\"\']display:none", "");

        text = text.replaceAll("&amp;", "&");
        return text;
    }

    public static String clearText(String text)
    {
        if (null == text) return "";
        text = text.replaceAll("&amp;", "&");
        //text = text.replaceAll("&lt;", "<");
        //text = text.replaceAll("&gt;", ">");
        text = text.replaceAll("<[\\s\\S]*?>", "");
        text = text.replaceAll("&nbsp;", " ");
        text = text.replaceAll("[\r\n]*", "");
        return text;
    }

    public static String getPageSql(String fields, String query, int PageIndex, int PageSize)
    {
        if (PageIndex == 1)
        {
            query = "SELECT " + fields + " FROM (" + query + ") WHERE ROWNUM <=  " + PageSize;
        }
        else
        {
            query = "SELECT " + fields + " FROM (SELECT A.*,ROWNUM AS ROW_NUM FROM (" + query + ") A )B WHERE B.ROW_NUM BETWEEN " + (PageSize * (PageIndex - 1) + 1) + " AND " + (PageSize * PageIndex);
        }
        return query;
    }
    
    public static String getPageMysql(String fields, String query,int PageIndex, int PageSize){
    	if(PageIndex == 1){
    		query = "SELECT " + fields + " FROM (" + query + ")A ORDER BY A.ID DESC LIMIT " + PageSize;
    	}
    	else{
    		query = "SELECT " + fields + " FROM (" + query + ")A ORDER BY A.ID DESC LIMIT " + (PageSize * (PageIndex - 1)) + "," + PageSize;
    	}
    	return query;
    }
    
    public static HashMap unserialize(String xml) throws Exception
    {
        int idx = 0;
        Element node, temp;
        Document doc = null;
        SAXBuilder builder = null;
        Iterator itr = null;
        //ArrayList list = new ArrayList(10);
        HashMap data = new HashMap();

        try
        {
            builder = new SAXBuilder();
            doc = builder.build(new StringReader(xml));
            itr = doc.getRootElement().getChildren().iterator();
            while (itr.hasNext())
            {
                node = (Element)itr.next();
                idx = Integer.parseInt(node.getName().replaceAll("^item_(\\d+)$", "$1"), 10);
                //list.add(idx, node.getText());
                data.put(idx, node.getText());
            }
        }
        finally
        {
            itr = null;
            doc = null;
            node = null;
            builder = null;
        }

        return data;
    }

    public static ArrayList parseFriends(String xml) throws Exception
    {
        int idx = 0;
        Element node, temp;
        Document doc = null;
        SAXBuilder builder = null;
        Iterator itr = null, attributes;

        HashMap data = null;
        ArrayList list = new ArrayList();

        try
        {
            builder = new SAXBuilder();
            doc = builder.build(new StringReader(xml));
            itr = doc.getRootElement().getChildren().iterator();
            while (itr.hasNext())
            {
                data = new HashMap();
                node = (Element)itr.next();
                attributes = node.getChildren().iterator();
                while (attributes.hasNext())
                {
                    temp = (Element)attributes.next();
                    data.put(temp.getName(), temp.getText());
                }
                list.add(data);
                data = null;
            }
        }
        finally
        {
            itr = null;
            doc = null;
            node = null;
            builder = null;
        }

        return list;
    }

}
