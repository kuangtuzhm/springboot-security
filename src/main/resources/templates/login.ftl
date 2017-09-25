<#assign basePath="${request.getContextPath()}">
<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Web Socket JavaScript Echo Client</title>
</head>
<script src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script language="javascript" type="text/javascript">
	function mylogin()
	{
		$.ajax({
    	    url: '/login',
    	    type:'POST', //GET
    	    async:true,    //或false,是否异步
    	    data:{
    	    	username:$("#username").val(),
    	    	pwd:$("#password").val()
    	    },
    	    timeout:5000,   
    	    dataType:'json',   //返回的数据格式：json/xml/html/script/jsonp/text

    	    success:function(data){
    	        if(data.status == "success")
    	        {
    	        	window.location.href="/mainFrame"
    	        }
    	        else
    	        {
    	        	alert(data.errMsg);
    	        }
    	    }
    	});
	}

</script>
<body>
<div class="container">
    <div class="starter-template">
    	<#if logout??>  
    		<p class="bg-warning">已注销</p>
    	</#if>
    	<#if error??>  
    		<p class="bg-danger">${error}</p>
    	</#if>
        
        <h2>使用账号密码登录</h2>
        <form class="form-signin" name="form" action="/login" method="post">
            <div class="form-group">
                <label for="username">账号</label>
                <input type="text" class="form-control" id="username" name="username" value="" placeholder="账号"/>
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="密码"/>
            </div>
            <input type="submit" id="login" value="Login" class="btn btn-primary"/>
        </form>
    </div>
</div>
</body>
</html>