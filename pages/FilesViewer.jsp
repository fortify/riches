<%@ page import="java.util.*,java.io.*" %>
<html>
<head><title>Files Viewer</title></head>
<body>
<h1>File: secrets.txt</h1>
<pre>
<%
	// application.getRealPath() will not work in WebSphere and WebLogic for WAR deployment, it will be null
	BufferedReader reader = new BufferedReader(new FileReader(application.getRealPath("/secrets.txt")));
	try {
		String line = null;
		while(true) {
			line = reader.readLine();
			if ( null == line ) break;
			out.println(line);
		}
	} finally {
		if ( null != reader ) {
			try { reader.close(); } catch ( Exception e ) { }
		}
	}
%>
</pre>
</body>
</html>