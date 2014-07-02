package studentsqueue;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {
    
    public UserTest() {
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
     * Test of getId method, of class User.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User(BigDecimal.valueOf(1));
        BigDecimal expResult = BigDecimal.valueOf(1);
        BigDecimal result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class User.
     */
    @org.junit.Test
    public void testSetId() {
        System.out.println("setId");
        BigDecimal id = BigDecimal.valueOf(2);
        User instance = new User();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getUsername method, of class User.
     */
    @org.junit.Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User(BigDecimal.valueOf(0),
                "username",
                "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                "User");
        String expResult = "username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of setUsername method, of class User.
     */
    @org.junit.Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "username";
        User instance = new User();
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of getPasswordHash method, of class User.
     */
    @org.junit.Test
    public void testGetPasswordHash() {
        System.out.println("getPasswordHash");
        User instance = new User(BigDecimal.valueOf(0),
                "username",
                "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                "User");
        String expResult = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        String result = instance.getPasswordHash();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPasswordHash method, of class User.
     */
    @org.junit.Test
    public void testSetPasswordHash() {
        System.out.println("setPasswordHash");
        String passwordHash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        User instance = new User();
        instance.setPasswordHash(passwordHash);
        assertEquals(passwordHash, instance.getPasswordHash());
    }

    /**
     * Test of getRealName method, of class User.
     */
    @org.junit.Test
    public void testGetRealName() {
        System.out.println("getRealName");
        User instance = new User(BigDecimal.valueOf(0),
                "username",
                "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                "User Name");
        String expResult = "User Name";
        String result = instance.getRealName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRealName method, of class User.
     */
    @org.junit.Test
    public void testSetRealName() {
        System.out.println("setRealName");
        String realName = "User Name";
        User instance = new User();
        instance.setRealName(realName);
        assertEquals(realName, instance.getRealName());
    }

//    /**
//     * Test of toString method, of class User.
//     */
//    @org.junit.Test
//    public void testToString() {
//        System.out.println("toString");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
