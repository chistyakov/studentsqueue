/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentsqueue;

import java.util.List;
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
public class StudentTest {
    
    public StudentTest() {
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
     * Test of getGroupName method, of class Student.
     */
    @org.junit.Test
    public void testGetGroupName() {
        System.out.println("getGroupName");
        Student instance = new Student();
        instance.setGroupName("5126");
        String expResult = "5126";
        String result = instance.getGroupName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGroupName method, of class Student.
     */
    @org.junit.Test
    public void testSetGroupName() {
        System.out.println("setGroupName");
        String groupName = "5126";
        Student instance = new Student();
        instance.setGroupName(groupName);
        assertEquals(groupName, instance.getGroupName());
    }

//    /**
//     * Test of getStudentInQueueList method, of class Student.
//     */
//    @org.junit.Test
//    public void testGetStudentInQueueList() {
//        System.out.println("getStudentInQueueList");
//        Student instance = new Student();
//        List<StudentInQueue> expResult = null;
//        List<StudentInQueue> result = instance.getStudentInQueueList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStudentInQueueList method, of class Student.
//     */
//    @org.junit.Test
//    public void testSetStudentInQueueList() {
//        System.out.println("setStudentInQueueList");
//        List<StudentInQueue> studentInQueueList = null;
//        Student instance = new Student();
//        instance.setStudentInQueueList(studentInQueueList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
