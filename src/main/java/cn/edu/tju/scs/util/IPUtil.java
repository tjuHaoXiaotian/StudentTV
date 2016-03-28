package cn.edu.tju.scs.util;

import java.net.InetAddress;

/**
 * Created by jack on 2015/10/24.
 */
public class IPUtil {

    //得到计算机的ip地址和mac地址
    public static String getConfig(){
        try{
            InetAddress address = InetAddress.getLocalHost();
//            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            //ni.getInetAddresses().nextElement().getAddress();
//            byte[] mac = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
//            String sMAC = "";
//            Formatter formatter = new Formatter();
//            for (int i = 0; i < mac.length; i++) {
//                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
//                        (i < mac.length - 1) ? "-" : "").toString();
//
//            }
            System.out.println("IP：" + sIP);
//            System.out.println("MAC：" + sMAC);
            return sIP;
        }catch(Exception e){
            e.printStackTrace();
            return "172.0.0.1";
        }
    }
}
