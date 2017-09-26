<#assign basePath="${request.getContextPath()}">
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Web Socket JavaScript Echo Client</title>

  <script src="${basePath}/js/jquery-1.8.3.min.js"></script>
  <script src="${basePath}/js/comm.js"></script>
  <script src="${basePath}/js/jquery.ajax.enhancer.js"></script>
  
  <script language="javascript" type="text/javascript">
	function kickUser()
	{
		$.ajax({
    	    url: '/kickUser',
    	    type:'POST', //GET
    	    async:true,    //或false,是否异步
    	    data:{},
    	    timeout:5000,   
    	    dataType:'json',   //返回的数据格式：json/xml/html/script/jsonp/text

    	    success:function(data){
    	        alert(data.status);
    	    }
    	});
	}

  </script>
</head>
<body>
<@security.authorize code="990101">
    aaaaaaaaa
</@security.authorize>
<security:authorize access="hasRole(1)">
eeeeeee
</security:authorize>
<security:authorize access="hasRole('3')">
fffffff
</security:authorize>
<h1>Echo Server</h1>
<div style="text-align: left;">
  hello
</div>
<div style="text-align: left;">
  <a href="/logout">退出</a>
</div>
<div id="output">
	<input type="button" onclick="kickUser()" value="踢出id为1的用户" />
</div>
</body>
</html>
