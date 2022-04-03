package parser;

import dataStructures.Client;
import dataStructures.CompletedOrder;
import dataStructures.Driver;
import dataStructures.Order;
import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.util.List;

public class ParserTest extends TestCase {
    public static final String SRC_TEST_RESOURCES_DATA_XML = "src/test/resources/data.xml";
    public static final String SRC_TEST_RESOURCES_COMPLETED_ORDERS_XML = "src/test/resources/completedOrders.xml";
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
        boolean isPresent = false;
        for (Order o: Parser.getOrders(SRC_TEST_RESOURCES_DATA_XML)) {
            if(o.equals(order)){
                isPresent = true;
            }
        }
        assertTrue(isPresent);
    }

    public void testAddCompletedOrderToXML() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        driver = new Driver("ana", "maxim");
        completedOrder = new CompletedOrder(order, 1.24, 13.2, driver);
        Parser.addCompletedOrderToXML(completedOrder);
        boolean isPresent = false;
        for (CompletedOrder o: Parser.getCompletedOrders(SRC_TEST_RESOURCES_COMPLETED_ORDERS_XML)) {
            if(o.equals(completedOrder)){
                isPresent = true;
            }
        }
        assertTrue(isPresent);
    }

    public void testCreateOrdersXML() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        Parser.createOrdersXML(order, SRC_TEST_RESOURCES_DATA_XML);
        Parser.addOrderToXML(order);
    }

    public void testCreateCompletedOrdersXML() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        driver = new Driver("ana", "maxim");
        completedOrder = new CompletedOrder(order, 1.24, 13.2, driver);
        Parser.createCompletedOrdersXML(completedOrder, SRC_TEST_RESOURCES_COMPLETED_ORDERS_XML);
    }

    public void testGetCompletedOrders() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        driver = new Driver("ana", "maxim");
        completedOrder = new CompletedOrder(order, 1.24, 13.2, driver);
        Parser.createCompletedOrdersXML(completedOrder, SRC_TEST_RESOURCES_COMPLETED_ORDERS_XML);
        List<CompletedOrder> completedOrders = Parser.getCompletedOrders(SRC_TEST_RESOURCES_COMPLETED_ORDERS_XML);
        assertFalse(completedOrders.isEmpty());


    }

    public void testGetOrders() {
        int beforeAdding = Parser.getOrders(SRC_TEST_RESOURCES_DATA_XML).size();
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "here", "there");
        Parser.createOrdersXML(order, SRC_TEST_RESOURCES_DATA_XML);
        int afterAdding = Parser.getOrders(SRC_TEST_RESOURCES_DATA_XML).size();
        assertTrue(afterAdding > beforeAdding);
    }

    public void testDeleteOrder() {
        client = new Client("maria", "popescu");
        order = new Order(LocalDateTime.now(), client, "deleteBefore", "deleteAfter");
        Parser.createOrdersXML(order, SRC_TEST_RESOURCES_DATA_XML);
        int ordersBeforeDelete = Parser.getOrders(SRC_TEST_RESOURCES_DATA_XML).size();
        Parser.deleteOrder(order, SRC_TEST_RESOURCES_DATA_XML);
        int ordersAfterDelete = Parser.getOrders(SRC_TEST_RESOURCES_DATA_XML).size();
        assertTrue(ordersAfterDelete < ordersBeforeDelete);
    }

}