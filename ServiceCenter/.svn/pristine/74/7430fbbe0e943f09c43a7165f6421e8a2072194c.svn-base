/*
 *
 *  模块的类,自管理,销毁XMLHTTP实例
 *
 */

var AJAX_TYPE_TEXT = 0x01;
var AJAX_TYPE_XML = 0x02;

var AJAX_METHOD_GET = 0x01;
var AJAX_METHOD_POST = 0x02;

var JModules = [];   //存放一些Module实例,定期清理

//从Modules集合中获取第一个未使用的Module
JModules.Get = function(){
	for(var i=0,l=this.length;i<l;i++){
		if(null == this[i]){
			return this[i] = new Module();
		}
	}
	return this[this.length] = new Module();
}
//清理Modules
JModules.Clear = function(){
    for(var i=0,l=this.length;i<l;i++){
        if(null == this[i]) continue;
        if(this[i].ReadyState == 4){
            delete this[i].Xmlhttp;
            delete this[i];
        }
    }
}

function Module()
{
    this.ReadyState = 0;
    this.Xmlhttp = null;
    this.Href = null;
    this.Callback = null;
	this.Element = null;
	this.CallbackArguments = null;
	this.Type = AJAX_TYPE_TEXT;
    this.Load = function(callback, args, el, href, type, methodType)
    {
        if(null == callback) return;
        var _self = this, method = 'GET';
        this.Callback = callback;
		this.Element = el;
		this.CallbackArguments = args;
        this.Href = String(href);
        this.Xmlhttp = CreateAjax();
        
        if (type == AJAX_TYPE_XML) this.Type = type;
        if (methodType == AJAX_METHOD_POST) method = 'POST';
        
        if(null == this.Xmlhttp) throw new Error(-1, 'Unable to create ajax instance');
        
        this.Xmlhttp.onreadystatechange = function()
        {
            if(null == _self.Xmlhttp) return;
            _self.ReadyState = _self.Xmlhttp.readyState;
            if(4 != _self.ReadyState) return;
            
            if (_self.Type == AJAX_TYPE_TEXT)
			    _self.Callback(_self.CallbackArguments, _self.Element, 200 == _self.Xmlhttp.status ? _self.Xmlhttp.responseText : '');
            if (_self.Type == AJAX_TYPE_XML)
                _self.Callback(_self.CallbackArguments, _self.Element, 200 == _self.Xmlhttp.status ? _self.Xmlhttp.responseXML : null);

            delete _self.Xmlhttp;
            delete _self.Callback;
            delete _self.Href;
        }
        this.Xmlhttp.open(method,this.Href,true);
        this.Xmlhttp.setRequestHeader("If-Modified-Since",new Date().toGMTString());
        this.Xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        this.Xmlhttp.send(null);
    }
}
