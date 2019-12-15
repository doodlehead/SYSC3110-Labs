
public class TextElement extends XmlElement{
	private String text;
	
	public TextElement(String tagName, String text) {
		super(tagName);
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void printElement() {
		String tagName = this.getTagName();
		System.out.printf("<%s>%s</%s>\n", tagName, this.text, tagName);
	}
	
	public void printElement(int indents) {
		for(int i=0; i < indents; i++) {
			System.out.print("\t");
		}
		String tagName = this.getTagName();
		System.out.printf("<%s>%s</%s>\n", tagName, this.text, tagName);
	}
}
