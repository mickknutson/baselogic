package com.baselogic.tutorials.dao;


import com.baselogic.tutorials.dao.OrderDAO;
import com.baselogic.tutorials.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * CREATE TABLE MEMBER(
 * MEMBER_ID VARCHAR(10) NOT NULL PRIMARY KEY,
 * USERNAME VARCHAR(20) NOT NULL,
 * PASSWORD VARCHAR(10),
 * STREET VARCHAR(30),
 * CITY VARCHAR(30),
 * ZIPCODE VARCHAR(20),
 * COUNTRY VARCHAR(30),
 * FFNUMBER VARCHAR(30),
 * AIRLINECODE VARCHAR(3),
 * EMAIL VARCHAR(30),
 * STATUS CHAR(1),
 * FULLNAME VARCHAR(40)
 * )
 * </p>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

    public Order placeOrder(Order order) throws Exception{
        return null;
    }

    public Order placeDelayedOrder(Order order, long delay) throws Exception{
        return null;
    }
}
