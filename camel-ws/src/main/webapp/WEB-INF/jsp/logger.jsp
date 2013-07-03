<%@ page session="false" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="${it.baseURL}static/js/jquery-1.10.1.js"></script>
<title>Mediator Logs</title>
</head>
<body>
	<script>
		var lastLogLine = 0;
        var isClicked=false;
		function getLogs() {
			var url = '${it.baseURL}logs/log';
			if (lastLogLine != 0) {
				url = "${it.baseURL}logs/log?fromLineNumber="
						+ lastLogLine.toString();
			}

            var logArea = $('#logArea');
            logArea.focusout(function (event){
               if(isClicked){
                   isClicked=false;
               }
            });
            if(logArea.val() == ''){
                isClicked=false;
            }
            logArea.click(function( event ){
                if(!isClicked){
                    isClicked=true;
                }
            });
			$.getJSON(url, function(data) {
				lastLogLine = parseInt(data.endLine, 10);

				if (data.lines != '') {
                    var currentScroll = logArea.scrollTop();
					logArea.val(logArea.val() + data.lines);
                    if(!isClicked){
                       logArea.scrollTop(logArea[0].scrollHeight - logArea.height());
                    }
                    else{
                        logArea.scrollTop(currentScroll);
                    }
			    }
			});
		}

		$(document).ready(function() {
			setInterval(getLogs, 1000);
			getLogs();
		});
	</script>

	<table bgcolor="#FFFFF9" border="1" cellpadding="2" cellspacing="2">
		<tr>
			<th>Logger</th>
			<th>Level</th>
		</tr>
		<c:forEach items="${it.loggerLevels}" var="entry">
			<tr>
				<td>${entry.key}</td>
				<td>${entry.value}</td>
			</tr>
		</c:forEach>
	</table>
	<hr/>

	<div id="logLevel">
		<form id="logLevelForm" action="${it.baseURL}logs/logLevel"
			method="post">
			<label for="logger">Logger</label> <input type="text" name="logger">

			<select id="level" name="level" class="container">
				<option value="error">ERROR</option>
				<option value="warn">WARN</option>
				<option value="info">INFO</option>
				<option value="debug">DEBUG</option>
				<option value="trace">TRACE</option>
			</select> <input type="submit" name="logLevelButton" value="Set Log Level">
		</form>
	</div>

	<div id="refresh">
		<a href="${it.baseURL}logs/logFile" id="downloadLink">Download Log
			File</a>
		<textarea id="logArea" style="width: 100%;" rows="25"></textarea>
	</div>
</body>
</html>
