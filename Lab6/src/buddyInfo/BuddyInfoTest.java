package buddyInfo;

import static org.junit.Assert.*;

import org.junit.*;

public class BuddyInfoTest {
	private BuddyInfo b1;
	private BuddyInfo b2;
	private BuddyInfo b3;
	
	@Before
	public void setUp() {
		this.b1 = new BuddyInfo("Bob", "123 hell str", "123-4567-123");
		this.b2 = new BuddyInfo("Charlie", "348 road str", "345-2342-345");
		this.b3 = new BuddyInfo("Frank", "234 wall str", "234-3453-876");
	}
	
	@Test
	public void testCopyConstructor() {
		this.b3 = new BuddyInfo(b2);
		
		assertEquals(b3.getName(), b2.getName());
		assertEquals(b3.getAddress(), b2.getAddress());
		assertEquals(b3.getPhoneNumber(), b2.getPhoneNumber());
		
		assertFalse(b3 == b2);
	}
	
	@Test
	public void testGreeting() {
		assertEquals(b1.getGreeting(), "hi");
	}
	
	@Test
	public void testAge() {
		b1.setAge(15);
		assertEquals(b1.getAge(), 15);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidAge() {
		b1.setAge(-234);
	}
	
	@Test
	public void testIsOver18() {
		b1.setAge(12);
		b2.setAge(18);
		b3.setAge(21);
		
		assertFalse(b1.isOver18());
		assertFalse(b2.isOver18());
		assertTrue(b3.isOver18());
	}
	
	@Test
	public void testGetters() {
		assertEquals(b1.getName(), "Bob");
		assertEquals(b1.getAddress(), "123 hell str");
		assertEquals(b1.getPhoneNumber(), "123-4567-123");
	}
	
	@Test
	public void testSetters() {
		b2.setName("Jay");
		b2.setAddress("23 sheet str");
		b2.setPhoneNumber("123");
		
		assertEquals(b2.getName(), "Jay");
		assertEquals(b2.getAddress(), "23 sheet str");
		assertEquals(b2.getPhoneNumber(), "123");
	}
	
}
