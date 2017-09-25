<#assign basePath="${request.getContextPath()}">
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Web Socket JavaScript Echo Client</title>

  <script src="${basePath}/js/jquery-1.8.3.min.js"></script>
  <script language="javascript" type="text/javascript">


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
<div id="output"></div>
</body>
</html>
