<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
			<form role="form" class="form-horizontal"
				action="/mongoTest/score/insert" method="POST">
				<fieldset>
					<div id="legend">
						<legend>아래양식을 작성해주세요.</legend>
					</div>
					<div class="form-group">
						<!-- �μ��ڵ� -->
						<label class="control-label col-sm-2" for="orgcode">아이디</label>
						<div class="col-sm-3">
							<input type="text" id="orgcode" name="id"
								placeholder="아이디" class="form-control"
								 required>
						</div>
					</div>

					
					
					<div class="form-group">
						<!-- �μ���-->
						<label class="control-label col-sm-2" for="orgname">성명</label>
						<div class="col-sm-3">
							<input type="text" id="name" name="name"
								placeholder="성명" class="form-control" minlength="4" required>

						</div>
					</div>



				


					
					<div class="form-group">
						<!-- �μ���ġ-->
						<label class="control-label col-sm-2" for="orgloc">부서</label>
						<div class="col-sm-3">
							<input type="text" id="dept" name="dept"
								placeholder="부서" class="form-control" minlength="4" >

						</div>
					</div>
					<div class="form-group">
						<!-- ��ȭ��ȣ-->
						<label class="control-label col-sm-2" for="orgtel">주소</label>
						<div class="col-sm-3">
							<input type="text" id="addr" name="addr" 
							placeholder="주소"
								class="form-control" minlength="4" required>

						</div>
					</div>
					<div class="form-group">
						<!-- �μ��ڵ� -->
						<label class="control-label col-sm-2" for="orgcode">자바점수</label>
						<div class="col-sm-3">
							<input type="text" id="java" name="java"
								placeholder="자바점수" class="form-control"
								 required>
						</div>
					</div>

					
					
					<div class="form-group">
						<!-- �μ���-->
						<label class="control-label col-sm-2" for="orgname">서블릿점수</label>
						<div class="col-sm-3">
							<input type="text" id="servlet" name="servlet"
								placeholder="서블릿점수" class="form-control" minlength="4" required>

						</div>
					</div>



				


					
					<div class="form-group">
						<!-- �μ���ġ-->
						<label class="control-label col-sm-2" for="orgloc">spring</label>
						<div class="col-sm-3">
							<input type="text" id="spring" name="spring"
								placeholder="spring" class="form-control" minlength="4" >

						</div>
					</div>
					<!-- <div class="form-group">
						��ȭ��ȣ
						<label class="control-label col-sm-2" for="orgtel">���ʽ�</label>
						<div class="col-sm-3">
							<input type="text" id="bonus" name="bonus" 
							placeholder="���ʽ�"
								class="form-control" minlength="4" required value="10000">

						</div>
					</div> -->

					<div class="form-group">
						<!-- Button -->
						<div class="col-sm-3 col-sm-offset-2">
							<input type="submit" value="수정하기" class="btn btn-success"/>
							
						</div>
					</div>
				</fieldset>
			</form>
	</div>
</body>
</html>