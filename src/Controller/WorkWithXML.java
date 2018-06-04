package Controller;

import Model.Address;
import Model.DataBase;
import Model.Student;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.eclipse.swt.widgets.FileDialog;


import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkWithXML {
    private static final String[][] FILTERS = {{"Файлы XML (*.xml)" , "*.xml"}};
    private static final Level SEVERE = null;

    public void readXML(Shell shell, StudentController studentController) {
        FileDialog openDialog = new FileDialog(shell, SWT.OPEN);
        setFilters(openDialog);
        String fname = openDialog.open();

        if (fname != null)
            System.out.println (fname);

        if (fname == null)
            fname = "D:\\JavaProgs\\myStud.xml";
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
                String shouse = null;
                int house = 0;
                String housing = null;
                String sflat = null;
                int flat = 0;

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
                        shouse = new String(ch, start, length);
                        house = Integer.parseInt(shouse);
                        bhouse = false;
                    }
                    if (bhousing) {
                        housing = new String(ch, start, length);
                        bhousing = false;
                    }
                    if (bflat) {
                        sflat = new String(ch, start, length);
                        flat = Integer.parseInt(sflat);
                        bflat = false;
                    }

                    if (fname != null && secname != null && surname != null && country != null && region != null && city != null && street != null && house != 0 && housing != null && flat != 0) {
                        Student student = new Student(fname, secname,surname);
                        studentController.addStudentToDataBase(student);
                        Address address = new Address(country, city, region, street, house, housing, flat);
                        studentController.addAddressToDataBase(address);
                        fname = null;
                        secname = null;
                        surname = null;
                        country = null;
                        region = null;
                        city = null;
                        street = null;
                        house = 0;
                        housing = null;
                        flat = 0;
                    }

                }
            };

            saxParser.parse(fname, handler);
        }
        catch (ParserConfigurationException | SAXException
                | IOException ex) {
            Logger.getLogger(WorkWithXML.class.getName())
                    .log(WorkWithXML.SEVERE, null, ex);
        }
    }

    public void writeXML(StudentController studentController) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element root = document.createElement("myStudents");
            document.appendChild(root);

            for (int i = 0; i < studentController.getStudentListSize(); i++) {

                Element student = document.createElement("student");
                root.appendChild(student);

                Attr attr = document.createAttribute("id");
                attr.setValue("" + i);
                student.setAttributeNode(attr);

                Element firstname = document.createElement("firstname");
                firstname.setTextContent(studentController.getStudentFromDataBase(i).getFirstName());
                student.appendChild(firstname);

                Element secname = document.createElement("secondname");
                secname.setTextContent(studentController.getStudentFromDataBase(i).getSecondName());
                student.appendChild(secname);

                Element surname = document.createElement("surname");
                surname.setTextContent(studentController.getStudentFromDataBase(i).getSurName());
                student.appendChild(surname);

                Element country = document.createElement("country");
                country.setTextContent(studentController.getAddressFromDataBase(i).getCountry());
                student.appendChild(country);

                Element region = document.createElement("region");
                region.setTextContent(studentController.getAddressFromDataBase(i).getRegion());
                student.appendChild(region);

                Element city = document.createElement("city");
                city.setTextContent(studentController.getAddressFromDataBase(i).getCity());
                student.appendChild(city);

                Element street = document.createElement("street");
                street.setTextContent(studentController.getAddressFromDataBase(i).getStreet());
                student.appendChild(street);

                Element house = document.createElement("house");
                house.setTextContent(String.valueOf(studentController.getAddressFromDataBase(i).getHouse()));
                student.appendChild(house);

                Element housing = document.createElement("housing");
                housing.setTextContent(studentController.getAddressFromDataBase(i).getHousing());
                student.appendChild(housing);

                Element flat = document.createElement("flat");
                flat.setTextContent(String.valueOf(studentController.getAddressFromDataBase(i).getFlat()));
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

    private  void setFilters(FileDialog dialog)
    {
        String[] names = new String[FILTERS.length];
        String[] exts  = new String[FILTERS.length];
        for (int i = 0; i < FILTERS.length; i++) {
            names[i] = FILTERS[i][0];
            exts [i] = FILTERS[i][1];
        }
        dialog.setFilterNames(names);
        dialog.setFilterExtensions(exts);
    }
}
