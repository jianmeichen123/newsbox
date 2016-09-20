<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	request.setAttribute("path", path);
	request.setAttribute("basePath", basePath);
%>

<script type="text/javascript" src="${path }/common/js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${path }/common/js/json2.js"></script>
<script type="text/javascript" src="${path }/common/js/Utils.js"></script>

<script type="text/javascript">
	var path = "${path}";
	var basePath = "${basePath}";
	var find_params = {};
</script>


