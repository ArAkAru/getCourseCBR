package arakaru.checkCourse.xmlpars;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main
{
	public static void main(String[] args) {

		final String fileName = "http://www.cbr.ru/scripts/XML_daily.asp";
		final String str1="SGD";//ищем курс по кодировке

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean flag1 = false;
				boolean flag2 = false;

				Attributes attributes1;

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					if (qName.equalsIgnoreCase("Value")&&flag1==true) {
						flag2=true;
						flag1=false;

					}
				}


				@Override
				public void characters(char ch[], int start, int length) throws SAXException {

					if(flag2){
						System.out.println(new String(ch, start, length));
						flag2=false;

					}
					if (str1.equalsIgnoreCase(new String(ch, start, length))) {
						flag1=true;
						System.out.println(new String(ch, start, length));


					}

				}
			};


			saxParser.parse(fileName, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
