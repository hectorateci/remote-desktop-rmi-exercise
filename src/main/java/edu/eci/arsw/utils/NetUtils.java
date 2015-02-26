/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author hcadavid
 */
public class NetUtils {
    
    public static String getIPAddress(){
        String result = null;
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            // handle error
        }

        if (interfaces != null) {
            while (interfaces.hasMoreElements() && result==null) {
                NetworkInterface i = interfaces.nextElement();
                Enumeration<InetAddress> addresses = i.getInetAddresses();
                while (addresses.hasMoreElements() && (result == null || result.isEmpty())) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLoopbackAddress()  &&
                            address.isSiteLocalAddress()) {
                        result = address.getHostAddress();
                    }
                }
            }
        }
        return result;
    }
    
    
}
