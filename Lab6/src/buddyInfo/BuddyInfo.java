package buddyInfo;

public class BuddyInfo {
	private String name;
	private String address;
	private String phoneNumber;
	
	private int age;
	
	public BuddyInfo(String name, String address, String phoneNum) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNum;
	}
	
	public BuddyInfo(BuddyInfo b) {
		this(b.getName(), b.getAddress(), b.getPhoneNumber());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getGreeting() {
		return "hi";
	}
	
	public String toString() {
		return String.format("name: %s\t address: %s\t phoneNumber: %s\t", this.name, this.address, this.phoneNumber);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age < 0) {
			throw new IllegalArgumentException("Invalid age");
		}
		this.age = age;
	}
	
	public boolean isOver18() {
		return this.age > 18;
	}

}
