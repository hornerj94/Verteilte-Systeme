package examples.distributed_programming.web_service.annotation.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class StringTools {
    public String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }

    @WebMethod(exclude = true)
    public static void main(String... args) {
        String address = "http://rtufkinfws003k5.fh-reutlingen.de:8080/StringTools";
        Endpoint.publish(address, new StringTools());
        System.out.println("StringTools Webservice started.");
    }
}