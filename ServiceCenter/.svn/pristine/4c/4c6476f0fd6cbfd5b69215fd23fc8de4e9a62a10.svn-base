Global = new Object();
Global.__UniqueID = 0;
Global.GetUniqueID = function(){ return "__MT_UID_" + Global.__UniqueID ++;};
function GetE(id){ return document.getElementById(id);};
function GetES(id){ return document.getElementsByName(id);};
function avoid(){ return false;};
function byteLength (sStr){aMatch = sStr.match(/[^\x00-\x80]/g);return (sStr.length + (! aMatch ? 0 : aMatch.length));};
String.prototype.cut=function(n){var strTmp = "";for(var i=0,l=this.length,k=0;(i<n);k++){var ch = this.charAt(k);if(ch.match(/[\x00-\x80]/)){i+=1;}else{i+=2;};strTmp += ch;}return strTmp;};
String.prototype.repeat = function(n){var text = '';for(var i=0;i<n;i++) text += this;return text;};
String.prototype.trim = function(){return this.replace(/^(\s*)|(\s*)$/g,"");};
function GetAbsPos(obj){var _left = 0,_top = 0;if(null == obj) return null;var _offsetWidth=obj.offsetHeight,_offsetHeight=obj.offsetHeight;while(obj){_left += obj.offsetLeft;_top += obj.offsetTop;obj = obj.offsetParent;}return {Top : _top,Left : _left,Width : _offsetWidth, Height : _offsetHeight,toString : function(){ return "Top: " + this.Top + ",Left: " + this.Left;}};};
function GetParam(param,name){return String(param).match(new RegExp("\W*[&\?]" + name + "=([^&]+)","gi")),RegExp.$1;};
Date.prototype.toLocaleString = function(){var text = this.getFullYear() + "-";text += (this.getMonth() + 1) + "-";text += this.getDay() + " ";text += this.getHours() + ":";text += this.getMinutes() + ":";text += this.getSeconds();return text;};

var XMLCompoents = ["Msxml2.XMLHTTP","Msxml2.XMLHTTP.4.0","Microsoft.XMLHTTP"];
function CreateAjax(){var obj = null;if(window.ActiveXObject){for(var i=XMLCompoents.length;i>0;){obj = new ActiveXObject(XMLCompoents[--i]);if(obj) break;}}if(window.XMLHttpRequest) obj = new XMLHttpRequest();if(null == obj) throw new Error(123456,"cannot create XMLHTTP.");return obj;};
function LoadScript(src,id){if(GetE(id)) return;var h = document.getElementsByTagName("HEAD");if(h&&h.length) h = h[0];else h = document.body;var c = document.createElement("SCRIPT");c.src = src;c.id = id;c.language = "JavaScript";c.type = "text/javascript";h.appendChild(c);};
function RemoveElement(id){var obj = GetE(id);if(obj) obj.parentNode.removeChild(obj);};
function LoadCSS(src,id){if(GetE(id)) return;var h = document.getElementsByTagName("HEAD");if(h&&h.length) h = h[0];else h = document.body;var c = document.createElement("LINK");c.href = src;c.rel = "stylesheet";c.type = "text/css";h.appendChild(c);};
function Escape(text){return escape(escape(text));};

function Send(ajObj,url,method,data,callBack,async,isModi)
{
    if(null == ajObj) return;
    ajObj.onreadystatechange = callBack;
    ajObj.open(method,url,async);
    if(isModi) ajObj.setRequestHeader("If-Modified-Since",new Date().toGMTString());
    ajObj.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
    ajObj.send(data);
}

function GetDomainName(url) { var idx = url.indexOf('/', 8); var pos = url.indexOf(':', 8); if (idx == -1) idx = url.indexOf('?', 8); if (idx == -1) idx = url.indexOf('#', 8); if (idx > -1 && url.indexOf('?', 8) > -1 && url.indexOf('?') < idx) idx = url.indexOf('?', 8); if (idx > -1 && url.indexOf('#', 8) > -1 && url.indexOf('#') < idx) idx = url.indexOf('#', 8); if (pos > idx) pos = -1; return url.substring(7, (pos > -1 ? pos : (idx > -1 ? idx : url.length() - 1))); }
function GetV(id) { var obj = GetE(id); if (obj && obj.value) return obj.value; else return ""; }
function Hide(id) { var obj = GetE(id); if (obj) obj.style.display = 'none'; }
function Show(id) { var obj = GetE(id); if (obj) obj.style.display = ''; }
function SetV(id, val) { GetE(id).value = val; }
function clear(val) { return val == null ? '' : val; }

function GetRadio(name)
{
    var els = document.getElementsByName(name);
    for (var i = 0; i < els.length; i++)
    {
        if (els[i].tagName == 'INPUT' && els[i].type == 'radio' && els[i].checked) return els[i].value;
    }
    return null;
}
function SetRadio(name, val)
{
    var els = document.getElementsByName(name);
    for (var i = 0; i < els.length; i++)
    {
        if (els[i].tagName == 'INPUT' && els[i].type == 'radio' && els[i].value == val) els[i].checked = true;
        else els[i].checked = false;
    }
}

if (window) window.document.domain = 'crm.artxun.com';
