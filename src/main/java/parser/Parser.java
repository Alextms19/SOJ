package parser;

import dataStructures.CompletedOrder;
import dataStructures.Order;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static Document document;
    private static DocumentBuilder documentBuilder;
    private static Element element;
    private static List<Order> orders;
    private static List<CompletedOrder> completedOrders;

    public static void readOrdersFromXML(String fileName) {
        orders = new ArrayList<>();
        try {
            NodeList nodeList = getNodeList(fileName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (!(node.getNodeType() == Node.ELEMENT_NODE)) {
                    continue;
                }
                orders.add(createOrderJavaObject((Element) node));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void readCompletedOrdersFromXML(String fileName) {
        completedOrders = new ArrayList<>();
        try {
            NodeList nodeList = getNodeList(fileName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (!(node.getNodeType() == Node.ELEMENT_NODE)) {
                    continue;
                }
                createCompletedOrderJavaObject((Element) node);
                createOrderJavaObject((Element) node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static NodeList getNodeList(String fileName) throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document parsedDocument = documentBuilder.parse(inputFile);
        parsedDocument.getDocumentElement().normalize();
        NodeList nodeList = parsedDocument.getElementsByTagName("order");
        return nodeList;
    }

    private static Order createOrderJavaObject(Element node) throws XMLParseException {
        Element nodeElement = node;
        NodeList childNodeList = nodeElement.getChildNodes();
        Order order = new Order();

        for (int i = 0; i < childNodeList.getLength(); i++) {
            Node childNode = childNodeList.item(i);
            switch (childNode.getNodeName()) {
                case "client":
                    // order.setClient(JSONClientParser.findClient(childNode.getTextContent()));
                    break;
                case "locationFrom":
                    order.setLocationFrom(childNode.getTextContent());
                    break;
                case "locationTO":
                    order.setLocationTo(childNode.getTextContent());
                    break;
                case "orderDateTime":
                    order.setOrderDateTime(LocalDateTime.parse(childNode.getTextContent()));
                    break;
                default:
                    System.out.println("wrong XML tags!");
                    throw new XMLParseException();
            }
        }
        return order;
    }

    private static CompletedOrder createCompletedOrderJavaObject(Element node) throws XMLParseException {
        Element nodeElement = node;
        NodeList childNodeList = nodeElement.getChildNodes();
        CompletedOrder completedOrder = new CompletedOrder();

        for (int i = 0; i < childNodeList.getLength(); i++) {
            Node childNode = childNodeList.item(i);
            switch (childNode.getNodeName()) {
                case "distanceInKm":
                    completedOrder.setDistanceInKm(Double.parseDouble(childNode.getTextContent()));
                    break;
                case "priceInRON":
                    completedOrder.setPriceInRON(Double.parseDouble(childNode.getTextContent()));
                    break;
                case "driver":
                    // order.setDriver(JSONDriverParser.findDriver(childNode.getTextContent()));
                    break;
                case "review":
                    completedOrder.setReview(childNode.getTextContent());
                    break;
                default:
                    System.out.println("wrong XML tags!");
                    throw new XMLParseException();
            }
        }
        completedOrder.setOrder(createOrderJavaObject(nodeElement));
        return completedOrder;
    }

    public static void addOrderToXML(Order order) {
        creatXMLElement("uncompleted", order);

    }

    private static Element creatXMLElement(String status, Order order) {
        Element orderElement = document.createElement("order");
        element.appendChild(orderElement);

        Attr attr = document.createAttribute("status");
        attr.setValue(status);
        element.setAttributeNode(attr);

        Element tmp = document.createElement("client");
        tmp.appendChild(document.createTextNode(order.getClient().getUsername()));
        orderElement.appendChild(tmp);

        tmp = document.createElement("locationFrom");
        tmp.appendChild(document.createTextNode(order.getLocationFrom()));
        orderElement.appendChild(tmp);

        tmp = document.createElement("locationTo");
        tmp.appendChild(document.createTextNode(order.getLocationTo()));
        orderElement.appendChild(tmp);

        tmp = document.createElement("orderDateTime");
        tmp.appendChild(document.createTextNode(order.getOrderDateTime().toString()));
        orderElement.appendChild(tmp);

        return orderElement;
    }

    public static void addCompletedOrderToXML(CompletedOrder completedOrder) {
       Element completedOrderElement = creatXMLElement("completed", completedOrder);

       Element tmp = document.createElement("distanceInKm");
       tmp.appendChild(document.createTextNode(String.valueOf(completedOrder.getDistanceInKm())));
       completedOrderElement.appendChild(tmp);

       tmp = document.createElement("priceInRON");
       tmp.appendChild(document.createTextNode(String.valueOf(completedOrder.getPriceInRON())));
       completedOrderElement.appendChild(tmp);

       tmp = document.createElement("review");
       tmp.appendChild(document.createTextNode(completedOrder.getReview()));
       completedOrderElement.appendChild(tmp);

       tmp = document.createElement("driver");
       tmp.appendChild(document.createTextNode(completedOrder.getDriver().getUsername()));
       completedOrderElement.appendChild(tmp);

    }

    public static void createOrdersXML(Order order, String fileName) {
        initilizeDocFactory();

        readOrdersFromXML(fileName);
        for (Order o : orders) {
            addOrderToXML(o);
        }
        addOrderToXML(order);

        transformFactoryFromFile(fileName);
    }

    private static void transformFactoryFromFile(String fileName)  {
        try {TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;

            transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);

        StreamResult streamResult = new StreamResult(fileName);
        transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void initilizeDocFactory()  {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            element = document.createElement("orders");
            document.appendChild(element);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createCompletedOrdersXML(CompletedOrder completedOrder, String fileName){
        initilizeDocFactory();

        readCompletedOrdersFromXML(fileName);
        for (CompletedOrder cOrder: completedOrders) {
            addCompletedOrderToXML(cOrder);
        }
        addCompletedOrderToXML(completedOrder);

        transformFactoryFromFile(fileName);
    }

    public static List<CompletedOrder> getCompletedOrders(String fileName) {
        if (completedOrders == null) {
            readCompletedOrdersFromXML(fileName);
        }
        return completedOrders;
    }

    public static List<Order> getOrders(String fileName) {
        if (orders == null) {
            readOrdersFromXML(fileName);
        }
        return orders;
    }

    public static void deleteOrder(Order order, String fileName){
        initilizeDocFactory();

        readOrdersFromXML(fileName);
        for(Order o: orders){
            if(!o.equals(order)){
                addOrderToXML(o);
            }
        }

        transformFactoryFromFile(fileName);
    }

    public static void deleteCompletedOrder(CompletedOrder completedOrder, String fileName){
        initilizeDocFactory();

        readOrdersFromXML(fileName);
        for(CompletedOrder o: completedOrders){
            if(!o.equals(completedOrder)){
                addCompletedOrderToXML(o);
            }
        }

        transformFactoryFromFile(fileName);
    }

}
