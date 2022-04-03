package parser;


import dataStructures.Client;
import dataStructures.CompletedOrder;
import dataStructures.Order;
import graphicalUserInterface.driverPage.OrdersList;
import jsonClasses.JSONClient;
import jsonClasses.JSONEditProfile;
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
import java.util.Objects;

public class Parser {
    private static Document document;
    private static DocumentBuilder documentBuilder;
    private static Element element;
    private static List<Order> orders;
    private static List<CompletedOrder> completedOrders;

    private Parser() {

    }

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
                completedOrders.add(createCompletedOrderJavaObject((Element) node));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteOrder(Order order, String fileName) {
        initilizeDocFactory();

        readOrdersFromXML(fileName);
        for (Order o : orders) {
            if (!o.equals(order)) {
                addOrderToXML(o);
            }
        }

        transformFactoryFromFile(fileName);
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
                    completedOrder.setDriver(JSONEditProfile.getSofer(childNode.getTextContent()));
                    break;
                case "review":
                    completedOrder.setReview(childNode.getTextContent());
                    break;
                default:


            }
        }
        completedOrder.setOrder(createOrderJavaObject(nodeElement));
        return completedOrder;
    }

    public static void addOrderToXML(Order order) {

        createXMLOrderElement("uncompleted", order);

    }

    private static Element createXMLOrderElement(String status, Order order) {
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

        Element completedOrderElement = createXMLOrderElement("completed", completedOrder.getOrder());

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

    public static void createCompletedOrdersXML(CompletedOrder completedOrder, String fileName) {
        initilizeDocFactory();

        readCompletedOrdersFromXML(fileName);
        for (CompletedOrder cOrder : completedOrders) {
            addCompletedOrderToXML(cOrder);
        }
        addCompletedOrderToXML(completedOrder);

        transformFactoryFromFile(fileName);
    }

    public static List<CompletedOrder> getCompletedOrders(String fileName) {
            readCompletedOrdersFromXML(fileName);
        return completedOrders;
    }

    public static List<Order> getOrders(String fileName) {
            readOrdersFromXML(fileName);
        return orders;
    }

    public static void afisareXML() {
        readOrdersFromXML("src/main/resources/data.xml");
        new OrdersList(orders);
    }

    public static List<CompletedOrder> getEfectuate(String fileName) {
        readCompletedOrdersFromXML(fileName);
        return completedOrders;
    }

    public static List<Order> getNepreluata(String fileName){
        readOrdersFromXML(fileName);
        return orders;
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
                    order.setClient(new Client(Objects.requireNonNull(JSONClient.findClient(childNode.getTextContent()))));
                    break;
                case "locationFrom":
                    order.setLocationFrom(childNode.getTextContent());
                    break;
                case "locationTo":
                    order.setLocationTo(childNode.getTextContent());
                    break;
                case "orderDateTime":
                    order.setOrderDateTime(LocalDateTime.parse(childNode.getTextContent()));
                    break;
                default:


            }
        }
        return order;
    }

    private static void initilizeDocFactory() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            element = document.createElement("orders");
            document.appendChild(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transformFactoryFromFile(String fileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(fileName);
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
