package com.baselogic.tutorials.dao;


import com.baselogic.tutorials.domain.Order;

/**
 * OrderDAO
 * <p/>
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
 *
 * @author Mick Knutson
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a>
 * @since 2012
 */
public interface OrderDAO {

    Order placeOrder(Order order) throws Exception;

    Order placeDelayedOrder(Order order, long delay) throws Exception;

}
