import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    private List<Beast> beasts;

    public List<Beast> getBeasts() {
        return beasts;
    }

    boolean bName = false;
    boolean bArea = false;
    boolean bPrey = false;

    String name;
    Area area;
    String prey;




    public MyHandler(){
        super();
        beasts = new ArrayList<>();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("name")) {
            bName = true;
        } else if (qName.equals("area")) {
            bArea = true;
        } else if (qName.equals("prey")) {
            bPrey = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bName) {
            bName = false;
        } else if (bArea) {
            bArea = false;
        } else if (bPrey) {
            bPrey = false;
        }else if (qName.equals("Beast")) {
            try {
                beasts.add(new Beast(name, area, prey));
            } catch (NumberFormatException exc){
                exc.printStackTrace();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(bName || bArea || bPrey){
            String data = new String(ch, start, length).trim();
            if(bName){
                name = data;
            } else if(bArea){
                area = Area.valueOf(data);
            } else if(bPrey){
                prey = data;
            }
        }
    }
}