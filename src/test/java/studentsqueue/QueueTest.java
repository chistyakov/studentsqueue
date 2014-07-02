package studentsqueue;

import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class QueueTest {
    
    public QueueTest() {
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
     * Test of getId method, of class Queue.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        Queue instance = new Queue(BigDecimal.valueOf(1), "test");
        BigDecimal expResult = BigDecimal.valueOf(1);
        BigDecimal result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Queue.
     */
    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        BigDecimal id = BigDecimal.valueOf(2);
        Queue instance = new Queue();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getName method, of class Queue.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        Queue instance = new Queue(BigDecimal.valueOf(3), "name");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Queue.
     */
    @org.junit.Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name";
        Queue instance = new Queue();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getInProcess method, of class Queue.
     */
    @org.junit.Test
    public void testGetInProcess() {
        System.out.println("getInProcess");
        Queue instance = new Queue();
        Character expResult = 'N';
        Character result = instance.getInProcess();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInProcess method, of class Queue.
     */
    @org.junit.Test
    public void testSetInProcess() {
        System.out.println("setInProcess");
        Character inProcess = 'Y';
        Queue instance = new Queue();
        instance.setInProcess(inProcess);
        assertEquals(inProcess, instance.getInProcess());
    }

//    /**
//     * Test of getStudentInQueueList method, of class Queue.
//     */
//    @org.junit.Test
//    public void testGetStudentInQueueList() {
//        System.out.println("getStudentInQueueList");
//        Queue instance = new Queue();
//        List<StudentInQueue> expResult = null;
//        List<StudentInQueue> result = instance.getStudentInQueueList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStudentInQueueList method, of class Queue.
//     */
//    @org.junit.Test
//    public void testSetStudentInQueueList() {
//        System.out.println("setStudentInQueueList");
//        List<StudentInQueue> studentInQueueList = null;
//        Queue instance = new Queue();
//        instance.setStudentInQueueList(studentInQueueList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTeacher method, of class Queue.
//     */
//    @org.junit.Test
//    public void testGetTeacher() {
//        System.out.println("getTeacher");
//        Queue instance = new Queue();
//        Teacher expResult = null;
//        Teacher result = instance.getTeacher();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setTeacher method, of class Queue.
//     */
//    @org.junit.Test
//    public void testSetTeacher() {
//        System.out.println("setTeacher");
//        Teacher teacher = null;
//        Queue instance = new Queue();
//        instance.setTeacher(teacher);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//
//    /**
//     * Test of toString method, of class Queue.
//     */
//    @org.junit.Test
//    public void testToString() {
//        System.out.println("toString");
//        Queue instance = new Queue();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
