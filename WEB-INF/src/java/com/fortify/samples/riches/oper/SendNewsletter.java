package com.fortify.samples.riches.oper;

import com.fortify.samples.riches.AdminSupport;
import com.fortify.samples.riches.model.ProfileService;
import com.fortify.samples.riches.model.Message;
import com.fortify.samples.riches.model.MessageService;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class SendNewsletter extends AdminSupport {

    private String subject;
    private String body;

    public String execute() throws Exception{

        String[] cmd = getMailCommand();
        sendMail(cmd);

        addActionMessage("Newsletter has been successfully sent!!");
        super.execute();
        return SUCCESS;
    }

    public String[] getMailCommand()
    {
        String java;
        String cp;

        String[] cmd = new String[3];
        if (System.getProperty("os.name").toLowerCase().contains("windows"))
        {
            cmd[0] = "cmd.exe";
            cmd[1] = "/c";
            java = System.getProperty("java.home") + "\\bin\\java";
            cp = System.getProperty("catalina.home") + "\\webapps\\riches\\WEB-INF\\classes";
            cmd[2] = "\"\"" + java + "\" -cp \""+ cp +"\" com.fortify.samples.riches.legacy.mail.SendMail \"" + subject + "\" \"" + "high" + "\" \"" + body + "\" " + "all@rwi.com" + "\"";
        }
        else
        {
            cmd[0] = "/bin/sh";
            cmd[1] = "-c";
            java = System.getProperty("java.home") + "/bin/java";
            cp = System.getProperty("catalina.home") + "/webapps/riches/WEB-INF/classes";
            cmd[2] = "\"" + java + "\" -cp \""+ cp +"\" com.fortify.samples.riches.legacy.mail.SendMail \"" + subject + "\" \"" + "high" + "\" \"" + body + "\" " + "all@rwi.com";
        }

        return cmd;
    }

    public String sendMail(String[] cmd)
    {
        Runtime rt = Runtime.getRuntime();
        //call "legacy" mail program
        Process proc = null;
        StringBuilder message = new StringBuilder();
        try
        {
            proc = rt.exec(cmd);
            System.out.println(cmd[2]);

            InputStream is = proc.getInputStream();
            byte[] buf = new byte[256];
            int read;
            while( (read = is.read()) > 0)
            {
                message.append((char)read);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (proc != null)
                proc.destroy();
        }

        //update local mail too
        updateMail();
        return message.toString();
    }

    private void updateMail() {
        String sender = "Riches Bank";
        Date date = new Date(System.currentTimeMillis());
        try
        {
            List addresses = ProfileService.getAllEmail();
            for(int i=0;i<addresses.size();i++){
                String to=(String)addresses.get(i);

                System.out.println(ProfileService.getUsername(to));
                System.out.println(sender);
                System.out.println(subject);
                System.out.println("high");
                System.out.println(body);
                System.out.println(date);

                Message message = new Message(ProfileService.getUsername(to), sender, subject, "high", body, date);
                MessageService.addMessage(message);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}