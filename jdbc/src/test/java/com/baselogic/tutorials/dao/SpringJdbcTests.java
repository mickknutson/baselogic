package com.baselogic.tutorials.dao;

import com.baselogic.tutorials.dao.configuration.JavaConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.rowset.CachedRowSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Other things I would like to cover here:
 *
 * With the JDBC 2.0 API, you will be able to do the following:
 Scroll forward and backward in a result set or move to a specific row
 Make updates to database tables using methods in the Java programming language instead of using SQL commands
 Send multiple SQL statements to the database as a unit, or batch
 Use the new SQL3 datatypes as column values
 */
@ContextConfiguration(classes = JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJdbcTests {

    private final Logger logger = LoggerFactory
            .getLogger(SpringJdbcTests.class);

    @Autowired
    ApplicationContext applicationContext;
//
//    @Autowired
//    InventoryManager inventoryManagerImpl;
//
//
//    @Autowired
//    DVDInfoDAO dao;

    @Before
    public void beforeEachTest() {

    }

    //--- UNIT TESTS --------------------------------------------------------//

    @Test
    public void todoPreparedStatement() {
        // do PreparedStatement()
        //do PreparedStatementCreator()
        //fail();
    }

//    @Test
    public void todoSqlUpdate() {
        // do SqlUpdate()
        fail();
    }

//    @Test
    public void todoRowCallbackHandler() {
        // do RowCallbackHandler()
        fail();
    }

//    @Test
    public void todoCallableStatement() {
        // do CallableStatement()
        fail();
    }

//    @Test
    public void todoNamedParameterJdbcTemplate() {
        // do NamedParameterJdbcTemplate()
        // do MapSqlParameterSource()
        fail();
    }

//    @Test
    public void todoParameterizedBeanPropertyRowMapper() {
        // do ParameterizedBeanPropertyRowMapper()
        fail();
    }

//    @Test
    public void todoQueryForInt() {
        // do QueryForInt()
        /*
        int custCount =
        tmp.queryForInt("SELECT COUNT(*) FROM CUSTOMER WHERE ...");
        String em = (String)
        tmp.queryForObject("SELECT EMAIL ...", String.class);
         */
        fail();
    }

//    @Test
    public void todoQueryForObject() {
        // do QueryForObject()
        fail();
    }

//    @Test
    public void todoQueryForList() {
        // do QueryForList()
        /*
        List<Map<String, String>> list = tmp.queryForList("SELECT * FROM EMPLOYEE");

        for (Map map : list) {
        }
        String ssn= map.get("SSN");
        String name = map.get("NAME");
        String email = map.get("EMAIL");
         */
        fail();
    }

//    @Test
    public void todoMappingSqlQuery() {
        // do MappingSqlQuery()

        /*
        To use:
        MovieQuery query = new MovieQuery(datasource);
        List<Movie> list = (List<Movie>) query.execute("The%"); // FIXME Where is the parameter name????
        for (Movie movie : list) {
          System.out.println(movie);
        }
        */

        /*
        SqlQuery (above)
        MappingSqlQuery (above)
        SqlUpdate - Used to encapsulate a SELECT (need to give example)
        StoredProcedure - Encapsulates a storedproc call Wraps a single select statement (need to give example)
        SqlFunction - Used to encapsulate the other (*** need to give example)
         */

        fail();
    }

//    @Test
    public void todoQueryForRowSet() {
        // do QueryForRowSet()

        /*
        QueryForRowSet - Send a query and returns a JDBC 2.0 RowSet (detached CachedRowSet).
         */

        CachedRowSet crs;

        fail();
    }



} // The End...
