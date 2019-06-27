package com.fortify.samples.riches.webservices;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BannerAdClient {

	private static String imageRepositoryBasepath = "/images/repository/";
	
    private BannerAdClient() {}

	private static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
	
    public static void main(String[] args) 
	{

	String host = (args.length < 1) ? null : args[0];
	try 
	{
	    Registry registry = LocateRegistry.getRegistry(host);
	    BannerAdSource stub = (BannerAdSource) registry.lookup("BannerAdSource");
	    File bannerAd = stub.retrieveBannerAd("0.jpg");
		File targetBannerAdFile = new File(imageRepositoryBasepath + bannerAd.getName());
		copy(bannerAd, targetBannerAdFile);
		
	    System.out.println("file copied to :"+targetBannerAdFile.getAbsolutePath());
	} 
	catch (Exception e)
	{
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
	
    }
}
