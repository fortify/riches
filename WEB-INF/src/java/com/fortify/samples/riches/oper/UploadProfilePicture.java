package com.fortify.samples.riches.oper;

import com.fortify.samples.riches.AdminSupport;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: madou
 * Date: 3/23/11
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadProfilePicture extends AdminSupport {

    private File file;
    private String contentType;
    private String filename;
    private String new_filename;

    public String getFileName() {
        return filename;
    }

    public void setUpload(File file) {
        this.file = file;
    }

    public void setUploadContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setUploadFileName(String filename) {
        this.filename = filename;
    }


    public String execute() throws Exception
    {
        super.execute();

        String home=System.getProperty("catalina.home");
        String dir_separator=System.getProperty("file.separator");

        String directory = home + dir_separator+ "webapps"+dir_separator+ "riches"+dir_separator+"profilepictures";
        File dir=new File(directory);
        dir.exists();
        new_filename =directory+dir_separator+filename;

        if(file!=null){
            addActionMessage("Thank you for uploading your profile picture.");

            try {
                File theFile = new File(new_filename);
                FileUtils.copyFile(file, theFile);
            } catch (Exception e) {
                addActionError(e.getMessage());
                return SUCCESS;
            }

            return SUCCESS;
        }
        else{
            addActionError("Failed to upload the Profile Picture");
            return SUCCESS;
        }

    }



}
