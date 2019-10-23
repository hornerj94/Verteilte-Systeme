package examples.distributed_programming.web_service.dynamic_proxy;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;

import examples.distributed_programming.web_service.bookservice.BookIF;

public class BookClient {
    // ---------------------------------------------------------------------------------------------

    // Funktioniert nicht ohne Konfigurationen, wie das geht hat er nicht gezeigt.
    public static void main(String[] args) {
        try {
            String urlString = args[0] + "?WSDL";
            String nameSpaceUri = "urn:Foo";
            String serviceName = "MyBookService";
            String portName = "BookIFPort";

            System.out.println("UrlString = " + urlString);

            URL bookWsdlUrl = new URL(urlString);
            ServiceFactory serviceFactory = ServiceFactory.newInstance();
            Service bookService = 
                    serviceFactory.createService(bookWsdlUrl, new QName(nameSpaceUri, serviceName));
            BookIF myProxy = 
                    (BookIF) bookService.getPort(new QName(nameSpaceUri, portName), BookIF.class);

            System.out.println(myProxy.getBookTitle("0130293636"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------
}