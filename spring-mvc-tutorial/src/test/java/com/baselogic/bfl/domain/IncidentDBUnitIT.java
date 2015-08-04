package com.baselogic.tutorials.domain;

import com.baselogic.bfl.configuration.DatabaseConfiguration;
import com.baselogic.bfl.repositories.jpa.IncidentJpaRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManagerFactory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


/**
 * http://springtestdbunit.github.io/spring-test-dbunit/sample.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class})
//@ContextConfiguration({"classpath:test-applicationContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
//@ActiveProfiles("embedded")
@DatabaseSetup("dataset.xml")
public class IncidentDBUnitIT {

    private static final Logger logger = LoggerFactory.getLogger(IncidentDBUnitIT.class);

    @Autowired
    protected IncidentJpaRepository repository;

//    @Autowired
//    protected EntityManagerFactory entityManagerFactory;

    public static final String dataSetFile = "dataset.xml";
    public static final String expectedDataFile = "expectedData.xml";
    public static final String dataSetOutputFile = "./target/test-dataset_dump.xml";
    public static final String[] nullPrimaryKeyFilters =
            {"ID", "RELEASE_NOTE", "ADDRESS_KEY", "P_NUMBER", "HOBBY_NAME"};

    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
//    @Before
//    public void initTransaction() throws Exception {
//        logger.warn(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        logger.warn(">>> SEED >>>");
//        TestUtils.seedData(em,
//                dataSetFile,
//                nullPrimaryKeyFilters);
//    }

    /*@After
    public void afterTests() throws Exception {
        TestUtils.dumpData(entityManagerFactory.createEntityManager(),
                dataSetOutputFile,
                nullPrimaryKeyFilters);
    }*/

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
//    @DatabaseSetup(dataSetFile)
    public void test_FindSingleIncident() throws Exception {
        Incident incident = this.repository.findOne(100101L);
        assertThat(incident.getId(), is(100101L));
        assertThat(incident.getName(), is("Carl"));

        logger.debug("---------------------------------------------------------");
        logger.debug("incident: {}", incident);

//        assertThat(incident.getComments().size(), is(1));
    }

//    @Test
//    @DatabaseSetup(dataSetFile)
//    @ExpectedDatabase(expectedDataFile)
//    public void testRemove() throws Exception {
//        // Creates an instance of Customer
//        SudRequest sudRequest = this.repository.findById(100101L);
//        this.repository.delete(sudRequest);
//    }

    /*@Test
    @DatabaseSetup(dataSetFile)
    //@ExpectedDatabase(expectedDataFile)
    public void testCreateAndReadNewSudRequest() throws Exception {
        logger.warn("*****************************************************");

        // Creates an instance of Customer
        SudRequest request = SudRequestFixture.sudRequest();

        request.setComments(SudRequestFixture.comments());

        SudRequest response = this.repository.save(request);

        assertNotNull("ID should not be null", response.getId());
        assertThat(response.getComments().size(), is(3));

        this.repository.delete(request);

        //assertNotNull("ID should not be null", response.getId());
        //assertThat(response.getReleaseNotes().size(), is(3));

    }*/

//    @Test
//    public void test__DeleteCustomer() throws Exception {
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        // Uses Sting Based Criteria
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Customer> c = cb.createQuery(Customer.class);
//        Root<Customer> cust = c.from(Customer.class);
//        c.select(cust)
//                .where(cb.equal(cust.get("username"), "user1"));
//        Customer result = em.createQuery(c).getSingleResult();
//
//        em.remove(result);
//
//        // Retrieves all the Customers from the database
//        TypedQuery<Customer> q = em.createNamedQuery(
//                Constants.FINDALLFINDERNAME, Customer.class);
//        List<Customer> customers = q.getResultList();
//
//        tx.commit();
//
//        assertThat(customers.size(), is(2));
//    }
//
//    @Test
//    public void test__InsertCustomer__WithCollectionTables() throws Exception {
//        // Creates an instance of Customer
//        Customer customer = CustomerFixture.createSingleCustomer();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        em.persist(customer);
//        tx.commit();
//
//        assertNotNull("ID should not be null", customer.getId());
//
//        tx.begin();
//        // Retrieves a single Customer from the database
//        TypedQuery<Customer> q = em.createNamedQuery(
//                Constants.FINDALLFINDERNAME, Customer.class);
//        List<Customer> customers = q.getResultList();
//        tx.commit();
//
//        assertThat(customers.size(), is(4));
//
//        Customer cust1 = customers.get(0);
//        assertThat(cust1.getHobbies().size(), is(3));
//        assertThat(cust1.getAddresses().size(), is(1));
//        assertThat(cust1.getPhones().size(), is(1));
//    }
//
//    @Test
//    public void test__UpdateCustomerWithCollectionTables() throws Exception {
//        // Creates an instance of Customer
//        Customer customer = CustomerFixture.createSingleCustomer();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        em.persist(customer);
//        tx.commit();
//
//        assertNotNull("ID should not be null", customer.getId());
//
//        tx.begin();
//        // Retrieves a single Customer from the database
//        TypedQuery<Customer> q = em.createNamedQuery(
//                Constants.FINDALLFINDERNAME, Customer.class);
//        List<Customer> customers = q.getResultList();
//
//        tx.commit();
//
//        assertThat(customers.size(), is(4));
//    }
//
//    @Test
//    public void test__Read_and_Update__PESSIMISTIC_LOCK() throws Exception {
//        // Persists the Customer to the database
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        Customer customer = em.find(Customer.class, 100200L);
//
//        // Lock is performed after read
//        em.lock(customer, LockModeType.PESSIMISTIC_READ);
//
//        // Update some fields
//        customer.setUsername("newUsername1");
//
//        em.merge(customer);
//
//        tx.commit();
//
//        assertThat(customer.getUsername(), is("newUsername1"));
//
//    }
//
//    //@Test
//    public void test__LoadTest() throws Exception {
//        // You can turn the number of operations up to larger numbers to
//        // be able to detect issue.
//        for(int i =0; i < 10000; i++){
//            Customer customer = createCustomer();
//            assertNotNull("ID should not be null", customer.getId());
//
//            deleteCustomer(customer);
//        }
//    }
//
//    private Customer createCustomer() throws Exception {
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        Customer customer = CustomerFixture.createSingleCustomer();
//
//        // Persists the Customer to the database
//        em.persist(customer);
//
//        tx.commit();
//
//        return customer;
//    }
//
//    private void deleteCustomer(Customer customer) throws Exception {
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        // Uses Sting Based Criteria
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Customer> c = cb.createQuery(Customer.class);
//        Root<Customer> cust = c.from(Customer.class);
//        c.select(cust)
//                .where(cb.equal(cust.get("username"), customer.getUsername()));
//        Customer result = em.createQuery(c).getSingleResult();
//
//        em.remove(result);
//
//        // Retrieves all the Customers from the database
//        TypedQuery<Customer> q = em.createNamedQuery(
//                Constants.FINDALLFINDERNAME, Customer.class);
//        List<Customer> customers = q.getResultList();
//
//        tx.commit();
//    }
}
