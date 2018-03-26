import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;

/**
 * 系统信息获取工具
 * @author iutils.cn
 */
public class SystemUtils {

    /**
     * 系统基本信息
     */
    private static Properties props = System.getProperties();

    /**
     * 运行时参数
     */
    private static Runtime runtime = Runtime.getRuntime();

    /**
     * 获取系统基本信息
     * @return
     */
    public static Map<String,Object> getSystemInfo(){
        Map<String,Object> map = Maps.newConcurrentMap();
        //版本
        map.put("javaVersion",props.getProperty("java.version"));
        //供应商
        map.put("javaVendor",props.getProperty("java.vendor"));
        //虚拟机名称
        map.put("javaVmName",props.getProperty("java.vm.name"));
        //系统名称
        map.put("osName",props.getProperty("os.name"));
        //系统架构
        map.put("osArch",props.getProperty("os.arch"));
        //服务器时间
        /*map.put("javaTime",DateUtils.formatDate(new Date(),"yyyy年MM月dd日 HH时ss分mm秒"));*/
        //物理内存使用率
        /*map.put("memery",getMemery());*/
        //JVM内存
        long freeMemoery = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemoery;
        long maxMemory = runtime.maxMemory();
        long useableMemory = maxMemory - totalMemory + freeMemoery;
        map.put("usedMemory",usedMemory/1024/1024);
        map.put("maxMemory",maxMemory/1024/1024);
        map.put("useableMemory",useableMemory/1024/1024);
        return map;
    }
    
    /**
     * 获取访问IP
     */
    public static String getHostIp(){
    	try{
    		 InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
    		 String hostAddress = address.getHostAddress();//192.168.0.121 
    		 return hostAddress;
    	}catch(Exception e){
    		return null;
    	}
    }

  
}
