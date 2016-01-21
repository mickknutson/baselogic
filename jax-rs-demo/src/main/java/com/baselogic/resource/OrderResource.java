package com.baselogic.resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import com.baselogic.model.Order;
import com.baselogic.model.OrderDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// http://localhost:8080/jax-rs-demo/jaxrs/

@Path("/")
public class OrderResource {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderResource.class);


    @Context
    SecurityContext securityContext;

    private List<Order> orderDB = null;

    public OrderResource() {
        orderDB = OrderDB.getInstance();

        //throw new SecurityContentException();
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
//    @Path("")
    public String getLinks() {
        logger.info("getLinks()");

        StringBuilder sb = new StringBuilder();
        sb.append("Order: <a href=\"http://localhost:8080/jax-rs-demo/jaxrs/orderList\">orderList</a><br />");
        //sb.append("Order: <a href=\"http://localhost:8080/jax-rs-demo/jaxrs/order\">order/{number}</a><br />");
        return sb.toString();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order/{number}") //     ?number=10
    public String getOrder(@PathParam("number") int number) {
        logger.info("getOrder({})", number);

        for (Order o : orderDB) {
            logger.info("Order retrieved: " + o.getNumber());
            if (o.getNumber() == number) {
                logger.info("Found");
                JSONObject jo = new JSONObject();
                try {
                    jo.put("number", o.getNumber());
                    jo.put("desc", o.getDesc());
                    jo.put("entryDate", o.getEntryDate().toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return jo.toString();
            }
        }

        logger.info("Not found!");

        JSONObject joNotFound = new JSONObject();
        try {
            joNotFound.put("number", -1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return joNotFound.toString();
    }

    @GET
    //@Produces("text/plain")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/orderList")
    public String getOrderList() {
        logger.info("getOrderList()");

        JSONArray jArray = new JSONArray();

        for (int i = 0; i < 100; i++) {
            list.add(new BigDecimal(new Random(1_000).nextDouble()));
        }

        for (Order o : orderDB) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("number", o.getNumber());
                jo.put("desc", o.getDesc());
                jo.put("entryDate", o.getEntryDate().toString());
                jo.put("list", list);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jArray.add(jo);
        }


        logger.info("orderList" + jArray.toString());
        return jArray.toString();
    }

    private List<Object> list = new LinkedList<>();

    @PUT
    @Produces("text/plain")
    @Path("/order/{number}/{desc}")
    public String addOrder(@PathParam("number") int number,
                           @PathParam("desc") String desc) {
        logger.info("addOrder({}, {})", number, desc);
        orderDB.add(new Order(number, desc, new Date()));
        logger.info("Order added in put w order number: " + number);
        return "Order " + number + " added to database!";
    }

    @DELETE
    @Produces("text/plain")
    @Path("/order/{number}")
    public String deleteOrder(@PathParam("number") int number) {
        logger.info("addOrder({}, {})", number);
        for (Order o : orderDB) {
            logger.info("Order retrieved: " + o.getNumber());
            if (o.getNumber() == number) {
                orderDB.remove(o);
                return "Order deleted";
            }
        }
        return "Order not found!";
    }


} // the end...
