
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>인사관리시스템</title>
 <link rel="stylesheet" type="text/css" href="/bigdataShop/common/css/jqcloud.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
    <script type="text/javascript" src="/bigdataShop/common/js/jqcloud-1.0.4.js"></script>
   <script type="text/javascript">
		var word_array = new Array();
		<c:forEach var="ranklist" items="${ranklist}" >
			var rank = new Object();
			rank.text = "${ranklist.key}"
			rank.weight = "${ranklist.val}"
			word_array.push(rank);
		</c:forEach>
     $(function() {
       $("#example").jQCloud(word_array);
     });
     </script>
<style>
	/* Remove the navbar's default rounded borders and increase the bottom margin */
	#tablesize {
		width : 300px;
	}
</style>
</head>
<body>
	<h3>상품댓글분석</h3>
	<table class="table col-md-4" id="tablesize">
		<thead>
			<tr>
				<th>내용</th>
				<th>횟수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ranklist" items="${ranklist}">
				<tr>
					<td>${ranklist.key}</td>
					<td>${ranklist.val}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="example" class="col-md-8" style="width: 550px; height: 350px;" >
		
	</div>
</body>
</html>










