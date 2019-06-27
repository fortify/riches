package com.fortify.samples.riches.webservices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.*;

public interface BannerAdSource extends Remote
{
    File retrieveBannerAd(String clientAd) throws Exception;
}

