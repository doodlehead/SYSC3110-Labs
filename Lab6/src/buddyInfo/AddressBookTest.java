package buddyInfo;

import static org.junit.Assert.*;

import org.junit.*;

public class AddressBookTest {
	private AddressBook book;
	
	private BuddyInfo buddy;
	
	@Before
	public void setUp() {
		this.book = new AddressBook();
		this.buddy =  new BuddyInfo("Bob", "23 str", "123");
	}
	
	@Test
	public void testSize() {
		assertEquals(book.size(), 0);
		
		this.book.add(buddy);
		
		assertEquals(book.size(), 1);
	}
	
	@Test
	public void testClear() {
		this.book.add(new BuddyInfo(buddy));
		this.book.add(new BuddyInfo(buddy));
		this.book.add(new BuddyInfo(buddy));
		this.book.add(new BuddyInfo(buddy));
		
		this.book.clear();
		
		assertEquals(this.book.size(), 0);
	}
	
	@Test
	public void testAdd() {
		assertEquals(book.size(), 0);
		this.book.add(buddy);
		
		assertEquals(book.size(), 1);
		assertTrue(buddy == book.get(0));
	}
	
	@Test
	public void testRemove() {
		assertEquals(book.size(), 0);
		this.book.add(buddy);
		
		assertEquals(book.size(), 1);
		this.book.remove(0);
		assertEquals(book.size(), 0);
	}
	
}
