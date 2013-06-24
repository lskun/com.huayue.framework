/***************************************8
 *
 *  页面内的提示对话框, 可拆叠
 *
 *  Author: Matrixy K Herry
 *  Created: 2007-03-26 9:45
 *
 */

var Global = new Object();
Global.__UniqueID = 0;
Global.GetUniqueID = function(){ return "__MT_UID_" + Global.__UniqueID ++;};

var Tooltip = 
{
    /* ------------------------------- 华丽的分割线 ------------------------------- */
    // 控件唯一ID
    UniqueID : Global.GetUniqueID(),
    // 当前的尖角
    Cusp : 0,
    // 定时器
    Timeout : null,
    // Suggest区域是否己经展开
    Expanded : false,
    /* ------------------------------- 华丽的分割线 ------------------------------- */
    // 控件的显示
    Show : function(element, title, content)
    {
        var pos = null;
        var top, left;
        if ((pos = GetAbsPos(element)) == null) return;
        this.Draw();
        this.Hide();
        this.Cusp = 0;
        top = pos.Top + 10;
        left = pos.Left;
        // 如果右边越界
        if (pos.Left + 300 > document.body.offsetWidth)
        {
            this.Cusp = 2;
            left = pos.Left - 230;
        }
        // 如果左边越界
        // 如果上边越界
        if (pos.Top - 295 < 0)
        {
            top = pos.Top + 10;
        }
        // 如果下边越界
        if (pos.Top + 150 > document.body.offsetHeight)
        {
            this.Cusp = this.Cusp > 2 ? this.Cusp - 3 : (this.Cusp < 3 ? this.Cusp + 3 : this.Cusp);
            top = pos.Top - 145;
        }
        top += 00;
        left -= 10;
        this.Reset(title, content);
        this._ShowCusp(this.Cusp);
        var tbl = GetE(this.UniqueID + "_Table");
        if (tbl)
        {
            tbl.style.top = top + "px";
            tbl.style.left = left + "px";
            tbl.style.display = "";
        }
    },
    _ShowCusp : function(n)
    {
        var obj = null;
        for (var i = 0; i < 6; i++)
        {
            obj = GetE(this.UniqueID + "_Cusp_" + i);
            if ((i != n) && obj) obj.style.visibility = "hidden";
            if ((i == n) && obj) obj.style.visibility = "visible";
        }
    },
    // 界面绘制
    Draw : function()
    {
        var tbl = GetE(this.UniqueID + "_Table");
        if (tbl) return;
        document.body.innerHTML += this.toString();
    },
    // 动态展开/收缩提示框
    Expand : function()
    {
        clearTimeout(this.Timeout);
        var height = 0, top = 0;
        var td = GetE(this.UniqueID + "_TD");
        var tbl = GetE(this.UniqueID + "_Table");
        var img = GetE(this.UniqueID + "_Expand");
        var sgt = GetE(this.UniqueID + "_Suggest");
        if ((null == tbl) || (null == td) || (null == sgt) || (null == img)) return;
        top = parseInt(tbl.style.top, 10);
        height = parseInt(td.style.height, 10);
        top = isNaN(top) ? 0 : top;
        height = isNaN(height) ? 0 : height;
        if ((height < 70) && (!this.Expanded))
        {
            if (this.Cusp > 2)
            {
                tbl.style.top = (top - 4) + "px";
            }
            td.style.height = (height + 4) + "px";
            this.Timeout = setTimeout("Tooltip.Expand()", 5);
        }
        if ((height >= 70) && (!this.Expanded))
        {
            img.src = "/js/tooltip/images/fold.gif";
            this.Expanded = true;
            sgt.style.display = "";
            return;
        }
        if ((height > 0) && (this.Expanded))
        {
            sgt.style.display = "none";
            if (this.Cusp > 2)
            {
                tbl.style.top = (top + 4) + "px";
            }
            td.style.height = (height - 4) + "px";
            return (this.Timeout = setTimeout("Tooltip.Expand()", 5)), null;
        }
        if ((height <= 0) && (this.Expanded))
        {
            img.src = "/js/tooltip/images/expand.gif";
            this.Expanded = false;
            return;
        }
    },
    Hide : function()
    {
        var tbl = GetE(this.UniqueID + "_Table");
        if (tbl) tbl.style.display = "none";
    },
    Reset : function(title, content)
    {
        var obj = GetE(this.UniqueID + "_Title");
        if (obj) obj.innerHTML = title;
        obj = GetE(this.UniqueID + "_Content");
        if (obj) obj.innerHTML = content;
        obj = GetE(this.UniqueID + "_Suggest");
        if (obj) obj.style.display = "none";
        obj = GetE(this.UniqueID + "_TD");
        if (obj) obj.style.height = "0px";
        obj = GetE(this.UniqueID + "_Expand");
        if (obj) obj.src = "/js/tooltip/images/expand.gif";
        this.Expanded = false;
        clearTimeout(this.Timeout);
    },
    Close : function()
    {
        this.Hide();
    },
    Suggest : function()
    {
        // ...
    },
    toString : function()
    {
        var shtml = '<table width="300" border="0" height="133" align="center" cellpadding="0" cellspacing="0" style="position:absolute;display:none;" id="' + this.UniqueID + '_Table"><tr><td>';
        shtml += '<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr valign="bottom" height="21">';
        shtml += '<td width="11"><img src="/js/tooltip/images/top_left.gif" width="11" height="7" /></td>';
        shtml += '<td align="left" background="/js/tooltip/images/top_border.gif" style="background-repeat: repeat-x; background-position: bottom;">';
        shtml += '<img src="/js/tooltip/images/cusp_up.gif" id="' + this.UniqueID + '_Cusp_0" style="visibility:hidden;"/></td>';
        shtml += '<td width="35" align="center" valign="bottom"  background="/js/tooltip/images/top_border.gif" style="background-position: bottom; background-repeat: repeat-x;">';
        shtml += '<img src="/js/tooltip/images/cusp_up.gif" id="' + this.UniqueID + '_Cusp_1" style="visibility:hidden;"/></td><td align="right" background="/js/tooltip/images/top_border.gif" style="background-position: bottom; background-repeat: repeat-x;">';
        shtml += '<img src="/js/tooltip/images/cusp_up.gif" id="' + this.UniqueID + '_Cusp_2" style="visibility:hidden;"/></td><td width="17"><img src="/js/tooltip/images/top_right.gif" width="17" height="7" /></td>';
        shtml += '</tr></table></td></tr><tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>';
        shtml += '<td width="11" background="/js/tooltip/images/left_border.gif" style="background-repeat: repeat-y;">&nbsp;</td><td valign="top">';
        shtml += '<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff"><tr><td height="20" width="90%"><span style="font-family: 宋体; font-size: 9pt; color: #666;"><strong id="' + this.UniqueID + '_Title">原始文字 </strong></span>';
        shtml += '</td><td width="25" align="right"><a href="javascript:;" onclick="Tooltip.Close()"><img src="/js/tooltip/images/close.gif" alt="Close" width="14" height="13" border="0" /></a></td>';
        shtml += '</tr><tr><td height="25" colspan="2" valign="middle" style="border-top: dashed 1px #ccc;">';
        shtml += '<div style="border:0px;width:270px;font-family:arial;font-size:9pt;height:80px;overflow:hidden;" id="' + this.UniqueID + '_Content"></div></td>';
        shtml += '</tr></table></td>';
        shtml += '<td width="17" background="/js/tooltip/images/right_border.gif" style="background-repeat: repeat-y;">&nbsp;</td></tr></table></td></tr><tr><td>';
        shtml += '<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr height="32"><td width="11" align="center" valign="top"><img src="/js/tooltip/images/left_bottom.gif" width="11" height="17" /></td>';
        shtml += '<td align="left" valign="top" background="/js/tooltip/images/bottom_border.gif" style="background-repeat: repeat-x;"><img src="/js/tooltip/images/cusp.gif" width="39" id="' + this.UniqueID + '_Cusp_3" style="visibility:hidden;"/></td>';
        shtml += '<td width="39" align="center" valign="top" background="/js/tooltip/images/bottom_border.gif" style="background-repeat: repeat-x;"><img src="/js/tooltip/images/cusp.gif" name="cusp4" width="39" id="' + this.UniqueID + '_Cusp_4" style="visibility:hidden;"/></td>';
        shtml += '<td align="right" valign="top" background="/js/tooltip/images/bottom_border.gif" style="background-repeat: repeat-x;"><img src="/js/tooltip/images/cusp.gif" width="39" id="' + this.UniqueID + '_Cusp_5" style="visibility:hidden;"/></td>';
        shtml += '<td width="17" align="center" valign="top"><img src="/js/tooltip/images/right_bottom.gif" width="17" height="17" /></td></tr></table></td></tr></table>';
        return shtml;
    }
}

/* ------------------------------- 华丽的分割线 ------------------------------- */
// function definition
function GetE(id){ return document.getElementById(id);};
function GetAbsPos(obj){var _left = 0,_top = 0,_width = 0,_height = 0;if(null == obj) return null;while(obj){_left += obj.offsetLeft;_top += obj.offsetTop;obj = obj.offsetParent;};return {Top : _top,Left : _left};};
