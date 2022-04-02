package parser;

import dataStructures.Client;
import dataStructures.CompletedOrder;
import dataStructures.Driver;
import dataStructures.Order;
import junit.framework.TestCase;

import java.time.LocalDateTime;

public class ParserTest extends TestCase {
    Order order;
    CompletedOrder completedOrder;
    Client client;
    Driver driver;
    public void testReadOrdersFromXML() {
    }

    public void testAddOrderToXML() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        Parser.addOrderToXML(order);
    }

    public void testAddCompletedOrderToXML() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        driver = new Driver("ana", "maxim");
        completedOrder = new CompletedOrder(order, 1.24, 13.2, driver);
        Parser.addCompletedOrderToXML(completedOrder);
    }

    public void testCreateOrdersXML() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        Parser.createOrdersXML(order, "src/main/resources/data.xml");
        Parser.addOrderToXML(order);
    }

    public void testCreateCompletedOrdersXML() {
    }

    public void testGetCompletedOrders() {
    }

    public void testGetOrders() {
    }

    public void testDeleteOrder() {
    }

    public void testDeleteCompletedOrder() {
    }
}