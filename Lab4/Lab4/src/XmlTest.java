
public class XmlTest {
	public static void main(String[] args) {
		ContainerElement root = new ContainerElement("course");
		TextElement code = new TextElement("code", "SYSC3110");
		TextElement prof = new TextElement("prof", "Babak");
		
		ContainerElement classElem = new ContainerElement("class");
		TextElement student1 = new TextElement("student", "Michael");
		TextElement student2 = new TextElement("student", "Alan");
		
		root.addElement(code);
		root.addElement(prof);
		classElem.addElement(student1);
		classElem.addElement(student2);
		root.addElement(classElem);

		root.printElement();
	}
}
