import org.junit.runner.*;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

import buddyInfo.AddressBookTest;
import buddyInfo.BuddyInfoTest;

@RunWith(Suite.class)
@SuiteClasses({ AddressBookTest.class, BuddyInfoTest.class })
public class AllTests {

}
