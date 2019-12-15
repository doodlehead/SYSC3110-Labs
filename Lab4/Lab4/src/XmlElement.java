import java.util.*;

public abstract class XmlElement {
	private String tagName;
	private Map<String, String> attributes;
	
	public XmlElement(String tagName) {
		this.tagName = tagName;
		this.attributes = new HashMap<>();
	}
	public XmlElement(String tagName, Map<String, String> attributes) {
		this(tagName);
		this.attributes = attributes;
	}
	
	public String getTagName() {
		return this.tagName;
	}
	public Map<String, String> getAttributes() {
		return this.attributes;
	}
	public abstract void printElement();
	public abstract void printElement(int indents);
}
