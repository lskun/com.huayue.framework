$.extend(validatePrompt, {
	postcode:{
		onFocus:"请填写有效的邮编",
		succeed:"",
		isNull:"请填写有效的邮编",
		error:"邮编格式错误，请重新输入"
	},	
    mobile:{
        onFocus:"请填写报名联系人的手机号码，以便于我们联系",
        succeed:"",
        isNull:"请输入您的手机号码",
        error:"手机号格式错误，请重新输入"
    },
    unit:{
        onFocus:"请填写单位全称。",
        succeed:"",
        isNull:"请输入单位名称",
        error:{
            badLength:"单位名称长度只能在4-40位字符之间",
            badFormat:"单位名称只能由中文、英文、数字及“_”、“-”、()、（）组成"
        }
    },
	roomnum:{
		onFocus:"请输入阿拉伯数字",
	    succeed:"",
		isNull:"",
		error:"格式错误，请重新输入"
	},
    stay:{
        onFocus:"请选择单位所在地",
        succeed:"",
        isNull:"请选择单位所在地",
        error:""
    },
    companyaddr:{
        onFocus:"请详细填写单位地址　如：北京市海淀区苏州街20号银丰大厦B座3层",
        succeed:"",
        isNull:"请输入单位地址",
        error:{
            badLength:"单位地址长度只能在4-50位字符之间",
            badFormat:"单位地址只能由中文、英文、数字及“_”、“-”、()、（）、#组成"
        }
    }
});

$.extend(validateFunction, {
    stay:function(option) {
        var bool = (option.value == -1);
        if (bool) {
            validateSettings.isNull.run(option, "");
        }
        else {
            validateSettings.succeed.run(option);
        }
    },
	postcode:function(option){
		var format = validateRules.isPostcode(option.value);
		if(!format){
			validateSettings.error.run(option, option.prompts.error);
		}else{
			validateSettings.succeed.run(option);
		}
	},
    mobile:function(option) {
        var format = validateRules.isMobile(option.value);
        if (!format) {
            validateSettings.error.run(option, option.prompts.error);
        }
        else {
            validateSettings.succeed.run(option);
        }
    },
    unit:function(option) {
        var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"), 4, 40);
        var format = validateRules.isCompanyname(option.value);
        if (!length) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        }
        else {
            if (!format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            } else {
                validateSettings.succeed.run(option);
            }
        }
    },	
    companyaddr:function(option) {
        var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"), 4, 50);
        var format = validateRules.isCompanyaddr(option.value);
        if (!length) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        } else {
            if (!format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            }
            else {
				$("#invoiceAddr").html(option.value);
                validateSettings.succeed.run(option);
            }
        }
    },
    purpose:function(option) {
        var purpose = $("input:checkbox[@name='tree_id']");
        if (validateFunction.checkGroup(purpose)) {
            validateSettings.succeed.run(option);
        } else {
            validateSettings.error.run(option, option.prompts.isNull);
        }
    },
    FORM_validate:function() {
       // $("#username").jdValidate(validatePrompt.username, validateFunction.username, true);
       // $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true)
       //$("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2, true);
       //$("#authcode").jdValidate(validatePrompt.authcode, validateFunction.authcode, true);
       //$("#realname").jdValidate(validatePrompt.realname, validateFunction.realname, true);
       //$("#department").jdValidate(validatePrompt.department, validateFunction.department, true);
       //$("#tel").jdValidate(validatePrompt.tel, validateFunction.tel, true);
	    $("#isStay").jdValidate(validatePrompt.stay,validateFunction.stay,true);
        $("#contactWay").jdValidate(validatePrompt.mobile,validateFunction.mobile,true);
       //$("#mail").jdValidate(validatePrompt.mail, validateFunction.mail, true);
        $("#unit").jdValidate(validatePrompt.unit, validateFunction.unit, true);
        $("#unitAddress").jdValidate(validatePrompt.companyaddr, validateFunction.companyaddr, true);
        //$("#companysite").jdValidate(validatePrompt.companysite,validateFunction.companysite,true);
        $("#tree_id").jdValidate(validatePrompt.purpose, validateFunction.purpose, true);
		$("#postcode").jdValidate(validatePrompt.postcode,validateFunction.postcode,true);
        return validateFunction.FORM_submit(["#unit","#unitAddress","#tree_id","#isStay","#contactWay","#postcode"]);
    }
});




//默认下用户名框获得焦点
setTimeout(function() {
   $("#unit").get(0).focus();
}, 0);
//用户名验证
//$("#username").jdValidate(validatePrompt.username, validateFunction.username);
//密码验证
//$("#pwd").bind("keyup",function(){
//	validateFunction.pwdstrength();
//}).jdValidate(validatePrompt.pwd, validateFunction.pwd)
//二次密码验证
//$("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2);
//邮箱验证
//$("#mail").jdValidate(validatePrompt.mail, validateFunction.mail);
//发票备注
$("#invoiceRemark").bind("keydown",function(){
	$(this).css({"color":"#333333","font-size":"14px"});
}).bind("keyup",function(){
	if($(this).val() == "" || $(this).val() == "可不填"){
		$(this).css({ "color": "#999999", "font-size": "12px" });
	}
}).bind("blur",function(){
	if($(this).val() == "" || $(this).val() == "可不填"){
		$(this).css({"color":"#999999","font-size":"12px"}).jdValidate(validatePrompt.referrer, validateFunction.referrer, "可不填");
	}
})
//验证码验证
//$("#authcode").jdValidate(validatePrompt.authcode, validateFunction.authcode);
//联系人姓名验证
//$("#realname").jdValidate(validatePrompt.realname, validateFunction.realname);
//部门验证
//$("#department").jdValidate(validatePrompt.department, validateFunction.department);
//固定电话验证
//$("#tel").jdValidate(validatePrompt.tel, validateFunction.tel);
//手机验证
$("#contactWay").jdValidate(validatePrompt.mobile, validateFunction.mobile);
//公司名称验证
$("#unit").jdValidate(validatePrompt.unit, validateFunction.unit);
//公司地址验证
$("#unitAddress").jdValidate(validatePrompt.companyaddr, validateFunction.companyaddr);

$("#isStay").jdValidate(validatePrompt.stay,validateFunction.stay);
$("#postcode").jdValidate(validatePrompt.postcode,validateFunction.postcode);
//公司网址验证
//$("#companysite").jdValidate(validatePrompt.companysite, validateFunction.companysite);
//显示密码事件

//$("#viewpwd").bind("click", function() {
//    if ($(this).attr("checked") == true) {
//        validateFunction.showPassword("text");
//        $("#o-password").addClass("pwdbg");
//    } else {
//        validateFunction.showPassword("password");
//        $("#o-password").removeClass("pwdbg");
//    }
//9});


//购买类型/用途验证
/*****
$("input:checkbox[@name='purposetype']").bind("click", function() {
    var value1 = $("#purpose").val();
    var value2 = $(this).val();
    if ($(this).attr("checked") == true) {
        if (value1.indexOf(value2) == -1) {
            $("#purpose").val(value1 + value2);
            $("#purpose").attr("sta", 2);
            $("#purpose_error").html("");
            $("#purpose_succeed").addClass("succeed");
        }
    } else {
        if (value1.indexOf(value2) != -1) {
            value1 = value1.replace(value2, "");
            $("#purpose").val(value1);
            if ($("#purpose").val() == "") {
                $("#purpose").attr("sta", 0);
                $("#purpose_succeed").removeClass("succeed");
            }
        }
    }
});
****/

//键盘输入验证码验证

/***
$("#authcode").bind('keyup', function(event) {
    if (event.keyCode == 13) {
        $("#registsubmit").click();
    }
});
****/

//确认协议才能提交
/***
$("#protocol").click(function() {
    if ($("#protocol").attr("checked") != true) {
        $("#registsubmit").attr({ "disabled": "disabled" });
		$("#registsubmit").addClass("disabled");
    }
    else {
        $("#registsubmit").removeAttr("disabled");
		$("#registsubmit").removeClass("disabled");
    }
});
****/

//表单提交验证和服务器请求
$("#registsubmit").click(function() {
    var flag = validateFunction.FORM_validate();
    if (flag) {
		alert($("#formpersonal").serialize());
        $(this).attr({"disabled":"disabled"}).attr({"value":"提交中,请稍等"});
        $.ajax({
            type: "POST",
            url: "http://www.mailuke.com/RegistService.php?rtype=personal",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#formpersonal").serialize(),
            success: function(result) {
                if (result == 1) {
                    window.location = "http://www.mailuke.com/vipmanage";
                }
            }
        });
    }
});