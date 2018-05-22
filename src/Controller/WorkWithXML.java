package Controller;

import Model.Address;
import Model.DataBase;
import Model.Student;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WorkWithXML {
    public void readXML(DataBase dataBase) {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean bfname = false;
                boolean bsecname = false;
                boolean bsurname = false;
                boolean bcountry = false;
                boolean bregion = false;
                boolean bcity = false;
                boolean bstreet = false;
                boolean bhouse = false;
                boolean bhousing = false;
                boolean bflat = false;

                String fname = null;
                String secname = null;
                String surname = null;
                String country = null;
                String region = null;
                String city = null;
                String street = null;
                String house = null;
                String housing = null;
                String flat = null;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("firstname")) {
                        bfname = true;
                    }
                    if (qName.equalsIgnoreCase("secondname")) {
                        bsecname = true;
                    }
                    if (qName.equalsIgnoreCase("surname")) {
                        bsurname = true;
                    }
                    if (qName.equalsIgnoreCase("country")) {
                        bcountry = true;
                    }
                    if (qName.equalsIgnoreCase("region")) {
                        bregion = true;
                    }
                    if (qName.equalsIgnoreCase("city")) {
                        bcity = true;
                    }
                    if (qName.equalsIgnoreCase("street")) {
                        bstreet = true;
                    }
                    if (qName.equalsIgnoreCase("house")) {
                        bhouse = true;
                    }
                    if (qName.equalsIgnoreCase("housing")) {
                        bhousing = true;
                    }
                    if (qName.equalsIgnoreCase("flat")) {
                        bflat = true;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (bfname) {
                        fname = new String(ch, start, length);
                        bfname = false;
                    }
                    if (bsecname) {
                        secname = new String(ch, start, length);
                        bsecname = false;
                    }
                    if (bsurname) {
                        surname = new String(ch, start, length);
                        bsurname = false;
                    }
                    if (bcountry) {
                        country = new String(ch, start, length);
                        bcountry = false;
                    }
                    if (bregion) {
                        region = new String(ch, start, length);
                        bregion = false;
                    }
                    if (bcity) {
                        city = new String(ch, start, length);
                        bcity = false;
                    }
                    if (bstreet) {
                        street = new String(ch, start, length);
                        bstreet = false;
                    }
                    if (bhouse) {
                        house = new String(ch, start, length);
                        bhouse = false;
                    }
                    if (bhousing) {
                        housing = new String(ch, start, length);
                        bhousing = false;
                    }
                    if (bflat) {
                        flat = new String(ch, start, length);
                        bflat = false;
                    }

                    if (fname != null && secname != null && surname != null && country != null && region != null && city != null && street != null && house != null && housing != null && flat != null) {
                        Student student = new Student(fname, secname,surname);
                        dataBase.addStudent(student);
                        Address address = new Address(country, city, region, street, house, housing, flat);
                        dataBase.addAddress(address);
                        fname = null;
                        secname = null;
                        surname = null;
                        country = null;
                        region = null;
                        city = null;
                        street = null;
                        house = null;
                        housing = null;
                        flat = null;
                    }

                }
            };

            saxParser.parse("myStud.xml", handler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeXML(DataBase dataBase) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element root = document.createElement("myStudents");
            document.appendChild(root);

            for (int i = 0; i < dataBase.studentList.size(); i++) {

                Element student = document.createElement("student");
                root.appendChild(student);

                Attr attr = document.createAttribute("id");
                attr.setValue("" + i);
                student.setAttributeNode(attr);

                Element firstname = document.createElement("firstname");
                firstname.setTextContent(dataBase.getStudent(i).getFirstName());
                student.appendChild(firstname);

                Element secname = document.createElement("secondname");
                secname.setTextContent(dataBase.getStudent(i).getSecondName());
                student.appendChild(secname);

                Element surname = document.createElement("surname");
                surname.setTextContent(dataBase.getStudent(i).getSurName());
                student.appendChild(surname);

                Element country = document.createElement("country");
                country.setTextContent(dataBase.getAddress(i).getCountry());
                student.appendChild(country);

                Element region = document.createElement("region");
                region.setTextContent(dataBase.getAddress(i).getRegion());
                student.appendChild(region);

                Element city = document.createElement("city");
                city.setTextContent(dataBase.getAddress(i).getCity());
                student.appendChild(city);

                Element street = document.createElement("street");
                street.setTextContent(dataBase.getAddress(i).getStreet());
                student.appendChild(street);

                Element house = document.createElement("house");
                house.setTextContent(dataBase.getAddress(i).getHouse());
                student.appendChild(house);

                Element housing = document.createElement("housing");
                housing.setTextContent(dataBase.getAddress(i).getHousing());
                student.appendChild(housing);

                Element flat = document.createElement("flat");
                flat.setTextContent(dataBase.getAddress(i).getFlat());
                student.appendChild(flat);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("writeStud.xml"));
            transformer.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
