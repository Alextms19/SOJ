package parser;


import dataStructures.Client;
import dataStructures.CompletedOrder;
import dataStructures.Order;
import graphicalUserInterface.driverPage.OrdersList;
import jsonClasses.JSONClient;
import jsonClasses.JSONEditProfile;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

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
    private static Element element;
    private static List<Order> orders;
    private static List<CompletedOrder> completedOrders;

    /**
     * Created private constructor to hide implicit one
     */
    private Parser() {

    }

    /**
     * Delete Order
     * This function will receive an order and will delete it from the file if it exists
     * @param order the order to delete
     * @param fileName fileName from which order should be deleted
     */
    public static void deleteOrder(Order order, String fileName) {
        initializeDocFactory();

        readOrdersFromXML(fileName);
        for (Order o : orders) {
            if (!o.equals(order)) {
                addOrderToXML(o);
            }
        }

        transformFactoryFromFile(fileName);
    }

    /**
     * Add Order to XML
     * @param order that needs to be added to XML file
     */
    private static void addOrderToXML(Order order) {
        createXMLOrderElement("uncompleted", order);
    }

    /**
     * Add Completed Order to XML
     * @param completedOrder the completed order that needs to be added.
     */
    private static void addCompletedOrderToXML(CompletedOrder completedOrder) {

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

    /**
     * Create Orders XML, will create Orders and add them to the file that was passed
     * @param order the order that needs to be added
     * @param fileName filename that needs to be added the order
     */
    public static void createOrdersXML(Order order, String fileName) {
        initializeDocFactory();

        readOrdersFromXML(fileName);
        for (Order o : orders) {
            addOrderToXML(o);
        }
        addOrderToXML(order);

        transformFactoryFromFile(fileName);
    }

    /**
     * Create Completed Orders XML, will create Completed Orders and add them to the file that was passed
     * @param completedOrder the completed order that needs to be added
     * @param fileName filename that needs to be added the completed order
     */
    public static void createCompletedOrdersXML(CompletedOrder completedOrder, String fileName) {
        initializeDocFactory();

        readCompletedOrdersFromXML(fileName);
        for (CompletedOrder cOrder : completedOrders) {
            addCompletedOrderToXML(cOrder);
        }
        addCompletedOrderToXML(completedOrder);

        transformFactoryFromFile(fileName);
    }

    /**
     * Get Completed Orders
     * @param fileName from which completed orders need to be read and returned as Java Objects
     * @return list of CompletedOrder from file
     */
    public static List<CompletedOrder> getCompletedOrders(String fileName) {
            readCompletedOrdersFromXML(fileName);
        return completedOrders;
    }

    /**
     * Get Orders
     * @param fileName from which  orders need to be read and returned as Java Objects
     * @return list of Order from file
     */
    public static List<Order> getOrders(String fileName) {
            readOrdersFromXML(fileName);
        return orders;
    }

    /**
     * Used to open Order List tab with all uncompleted orders.
     * @see OrdersList
     */
    public static void showXML() {
        readOrdersFromXML("src/main/resources/data.xml");
        new OrdersList(orders);
    }

    /**
     * Read Orders From XML
     * This function will read Orders and add them to Order List
     * which can be accessed with
     * @function getOrders(filename);
     * @param fileName the file from which the orders will be read
     *
     */
    private static void readOrdersFromXML(String fileName) {
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

    /**
     * Read Completed Orders From XML
     * This function will read Orders and add them to Order List
     * which can be accessed with
     * @function getCompletedOrders(filename);
     * @param fileName the file from which the orders will be read
     *
     */
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

    /**
     * Create Completed Order Java Object
     * @param node xml Element that contains data that Java Object needs to be created from
     * @return the Completed Order Object from data provided (if missing will be null)
     */
    private static CompletedOrder createCompletedOrderJavaObject(Element node) {
        NodeList childNodeList = node.getChildNodes();
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
        completedOrder.setOrder(createOrderJavaObject(node));
        return completedOrder;
    }

    /**
     * create XML Order Element
     * @param status status if it's un/completed
     * @param order the
     * @return the xml Element that contains provided data
     */
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

    /**
     * get Node List of type order
     * @param fileName the file name from which orders need to be read
     * @return NodeList with all element of type order
     * @throws ParserConfigurationException ' '
     * @throws SAXException ' '
     * @throws IOException ' '
     */
    private static NodeList getNodeList(String fileName) throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document parsedDocument = documentBuilder.parse(inputFile);
        parsedDocument.getDocumentElement().normalize();
        return parsedDocument.getElementsByTagName("order");
    }

    /**
     * Create Order Java Object
     * @param node xml Element that contains data that Java Object needs to be created from
     * @return the Order Object from data provided (if missing will be null)
     */
    private static Order createOrderJavaObject(Element node) {
        NodeList childNodeList = node.getChildNodes();
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

    /**
     * initialize document factory to which will be added elements under the "orders" tag
     */
    private static void initializeDocFactory() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            element = document.createElement("orders");
            document.appendChild(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * close session with file and write data to that file from Document source
     * @param fileName the file to which data should be written.
     */
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
