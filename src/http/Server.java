package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
  
/** 
 * @project SimpleHttpServer 
 * @author sunnylocus 
 * @vresion 1.0 2009-9-2 
 * @description  自定义的http服务器 
 */  
public class Server {  
    //启动服务，监听来自客户端的请求  
    public static void httpserverService() throws IOException {  
        HttpServerProvider provider = HttpServerProvider.provider();  
        HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(8081), 100);//监听端口6666,能同时接 受100个请求  
        httpserver.createContext("/myApp", new MyHttpHandler());   
        httpserver.setExecutor(null);  
        httpserver.start();  
        System.out.println("server started");  
    }  
    
    //Http请求处理类  
    static class MyHttpHandler implements HttpHandler {  
        public void handle(HttpExchange httpExchange) throws IOException {  
        	System.out.println("#########");
            
        	//http://localhost:8081/myApp?a=123
        	System.out.println(httpExchange.getRequestURI().toString());
        	System.out.println("@@@"+httpExchange.getAttribute("a"));
            InputStream in = httpExchange.getRequestBody(); //获得输入流  
            System.out.println(IOUtils.toString(in));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
//            String temp = null;  
//            while((temp = reader.readLine()) != null) {  
//                System.out.println("client request:"+temp);  
//            }  
            
            String responseMsg = "ok";   //响应信息  
            httpExchange.sendResponseHeaders(200, responseMsg.length()); //设置响应头属性及响应信息的长度  
            OutputStream out = httpExchange.getResponseBody();  //获得输出流  
            out.write(responseMsg.getBytes());  
            out.flush();  
            httpExchange.close();                                 
        }  
    }  
    public static void main(String[] args) throws IOException {  
        httpserverService();  
    }  
}  