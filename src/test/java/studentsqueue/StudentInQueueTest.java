/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentsqueue;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class StudentInQueueTest {
    
    public StudentInQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class StudentInQueue.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        StudentInQueue instance = new StudentInQueue(BigDecimal.valueOf(1));
        BigDecimal expResult = BigDecimal.valueOf(1);
        BigDecimal result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class StudentInQueue.
     */
    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        BigDecimal id = BigDecimal.valueOf(2);
        StudentInQueue instance = new StudentInQueue();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getRank method, of class StudentInQueue.
     */
    @org.junit.Test
    public void testGetRank() {
        System.out.println("getRank");
        StudentInQueue instance = new StudentInQueue();
        instance.setRank(BigDecimal.valueOf(0));
        BigDecimal expResult = BigDecimal.valueOf(0);
        BigDecimal result = instance.getRank();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRank method, of class StudentInQueue.
     */
    @org.junit.Test
    public void testSetRank() {
        System.out.println("setRank");
        BigDecimal rank = BigDecimal.valueOf(0);
        StudentInQueue instance = new StudentInQueue();
        instance.setRank(rank);
        assertEquals(rank, instance.getRank());
    }

    /**
     * Test of getDescription method, of class StudentInQueue.
     */
    @org.junit.Test
    public void testGetDescription() {
        System.out.println("getDescription");
        StudentInQueue instance = new StudentInQueue();
        instance.setDescription("description");
        String expResult = "description";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class StudentInQueue.
     */
    @org.junit.Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "description";
        StudentInQueue instance = new StudentInQueue();
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

//    /**
//     * Test of getStudent method, of class StudentInQueue.
//     */
//    @org.junit.Test
//    public void testGetStudent() {
//        System.out.println("getStudent");
//        StudentInQueue instance = new StudentInQueue();
//        Student expResult = null;
//        Student result = instance.getStudent();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStudent method, of class StudentInQueue.
//     */
//    @org.junit.Test
//    public void testSetStudent() {
//        System.out.println("setStudent");
//        Student student = null;
//        StudentInQueue instance = new StudentInQueue();
//        instance.setStudent(student);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getQueue method, of class StudentInQueue.
//     */
//    @org.junit.Test
//    public void testGetQueue() {
//        System.out.println("getQueue");
//        StudentInQueue instance = new StudentInQueue();
//        Queue expResult = null;
//        Queue result = instance.getQueue();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setQueue method, of class StudentInQueue.
//     */
//    @org.junit.Test
//    public void testSetQueue() {
//        System.out.println("setQueue");
//        Queue queue = null;
//        StudentInQueue instance = new StudentInQueue();
//        instance.setQueue(queue);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//
//    /**
//     * Test of toString method, of class StudentInQueue.
//     */
//    @org.junit.Test
//    public void testToString() {
//        System.out.println("toString");
//        StudentInQueue instance = new StudentInQueue();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
