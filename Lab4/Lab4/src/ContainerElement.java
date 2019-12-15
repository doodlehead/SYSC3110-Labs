import java.util.*;

public class ContainerElement extends XmlElement {
	List<XmlElement> contents;
	
	public ContainerElement(String tagName) {
		super(tagName);
		this.contents = new ArrayList<>();
	}
	public ContainerElement(String tagName, List<XmlElement> contents) {
		super(tagName);
		this.contents = contents;
	}
	
	public void addElement(XmlElement elem) {
		this.contents.add(elem);
	}
	public List<XmlElement> getContents() {
		return this.contents;
	}
	@Override
	public void printElement() {
		System.out.println("<" + this.getTagName() + ">");
		for(XmlElement elem: this.contents) {
			elem.printElement(1);
		}
		System.out.println("</" + this.getTagName() + ">");
	}
	
	public void printElement(int indents) {
		for(int i=0; i < indents; i++) {
			System.out.print("\t");
		}
		
		System.out.println("<" + this.getTagName() + ">");
		for(XmlElement elem: this.contents) {
			elem.printElement(indents + 1);
		}
		
		for(int i=0; i < indents; i++) {
			System.out.print("\t");
		}
		System.out.println("</" + this.getTagName() + ">");
	}
}
