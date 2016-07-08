<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>클라이언트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/sendFile.js"></script>

<link rel="stylesheet" href="/resources/css/style.css" />

</head>
<body>
<div class="container">
	<h3>파일 업로드</h3>
	
	<div class="file" style="position: relative;">
		<input type="file" id="file" name="file" />
		<label for="file" class="upload"><span>업로드 하고 싶은 파일들을<br />드래그 해서 놓으세요.</span></label>
	</div>
	<ul class="file_list">
	
	</ul>
	<div class="btn_group">
		<button type="button" id="clickSecd" onclick="clickSend()">SEND</button>
		<button type="button" id="fileDisconnect" onclick="fileDisconnect()">DISCONNECT</button>
	</div>
	<div class="statusArea"></div>
</div>
</body>
</html>
