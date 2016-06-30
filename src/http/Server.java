package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
  
public class Server {  
    public static void httpserverService() throws IOException {  
        HttpServerProvider provider = HttpServerProvider.provider();  
        HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(8081), 100);
        httpserver.createContext("/", new MyHttpHandler());   
        httpserver.setExecutor(null);  
        httpserver.start();  
        System.out.println("server started");  
    }  
    
    static class MyHttpHandler implements HttpHandler {  
        public void handle(HttpExchange httpExchange) throws IOException {  
        	System.out.println("#########");
            
        	//http://localhost:8081/myApp?a=123
        	System.out.println(httpExchange.getRequestURI().toString());
        	System.out.println("@@@"+httpExchange.getAttribute("a"));
            InputStream in = httpExchange.getRequestBody();
            System.out.println(IOUtils.toString(in));
            
            String responseMsg = "ok"; 
            httpExchange.sendResponseHeaders(200, responseMsg.length()); 
            OutputStream out = httpExchange.getResponseBody(); 
            out.write(responseMsg.getBytes());  
            out.flush();  
            httpExchange.close();                                 
        }  
    }  
    public static void main(String[] args) throws IOException {  
        httpserverService();  
    }  
}  