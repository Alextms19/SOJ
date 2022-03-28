package parser;

import dataStructures.CompletedOrder;
import dataStructures.Order;
import org.w3c.dom.*;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static Document document;
    private static DocumentBuilder documentBuilder;
    private static Element element;
    private static List<Order> orders;
    private static List<CompletedOrder> completedOrders;

    public static void readFromXML(String fileName) {
        orders = new ArrayList<>();

        File inputFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document parsedDocument = documentBuilder.parse(inputFile);
            parsedDocument.getDocumentElement().normalize();
            NodeList nodeList = parsedDocument.getElementsByTagName("order") ;
            for(int i =0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(!(node.getNodeType() == Node.ELEMENT_NODE)){
                    continue;
                }
                createJavaObject((Element) node);

            }

        } catch (Exception e){

        } finally {

        }

    }

    private static void createJavaObject(Element node) throws XMLParseException {
        Element nodeElement = node;
        NodeList childNodeList = nodeElement.getChildNodes();
        Order order = new Order();

        for (int i=0; i<childNodeList.getLength(); i++){
            Node childNode = childNodeList.item(i);
            switch (childNode.getNodeName()) {
                case "client":
                    // order.setClient(JSONClinetParser.findClient(childNode.getTextContent()));
                    break;
                case "locationFrom":
                    order.setLocationFrom(childNode.getTextContent());
                    break;
                case "locationTO":
                    order.setLocationTo(childNode.getTextContent());
                    break;
                case  "orderDateTime":
                    order.setOrderDateTime(LocalDateTime.parse(childNode.getTextContent()));
                    break;
                case "princeInRON":
                    order.setPriceInRON(Double.parseDouble(childNode.getTextContent()));
                    break;
                case "distance":
                    order.setDistanceInKm((Double.parseDouble(childNode.getTextContent())));
                    break;
                default:
                    System.out.println("wrong XML tags!");
                    throw new XMLParseException();
            }
        }
        orders.add(order);
    }

    public static void addOrderToXML(Order order){
        Element orderElement = document.createElement("order");
        element.appendChild(orderElement);

        Attr attr  = document.createAttribute("status");
        attr.setValue("uncompleted");
        element.setAttributeNode(attr);

        Element clientElement = document.createElement("client");
        clientElement.appendChild(document.createTextNode(order.getClient().getUsername()));
        orderElement.appendChild(clientElement);

        Element locationFromElement = document.createElement("locationFrom");
        clientElement.appendChild(document.createTextNode(order.getLocationFrom()));
        orderElement.appendChild(locationFromElement);

        Element locationToElement = document.createElement("locationTo");
        clientElement.appendChild(document.createTextNode(order.getLocationTo()));
        orderElement.appendChild(locationToElement);

        Element distanceElement = document.createElement("distance");
        clientElement.appendChild(document.createTextNode(String.valueOf(order.getDistanceInKm())));
        orderElement.appendChild(distanceElement);

        Element dateTimeElement = document.createElement("orderDateTime");
        clientElement.appendChild(document.createTextNode(order.getOrderDateTime().toString()));
        orderElement.appendChild(dateTimeElement);

        Element priceElement = document.createElement("princeInRON");
        clientElement.appendChild(document.createTextNode(String.valueOf(order.getPriceInRON())));
        orderElement.appendChild(priceElement);
    }

    public static void addCompletedOrderToXML(CompletedOrder completedOrder) {
        // TODO
    }

    public static void createXML(Order order,String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            element = document.createElement("orders");
            document.appendChild(element);
            readFromXML(fileName);
            for(Order o : orders){
                addOrderToXML(o);
            }
            addOrderToXML(order);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(fileName);
            transformer.transform(source, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
