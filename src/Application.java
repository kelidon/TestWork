import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Application extends JFrame {

    Application() {
        super("Testwork app");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(openItem);
        menuBar.add(dataMenu);
        dataMenu.add(byMassItem);
        dataMenu.add(byAreaItem);
        dataMenu.add(fittersItem);
        dataMenu.add(areasItem);
        dataMenu.add(searchItem);
        dataMenu.add(amountItem);

        var tabbedPane = new JTabbedPane();
        var fir = new FirstTab();
        var sec = new SecondTab();
        var thi = new ThirdTab();
        var fou=new FourthTab();
        var fif = new FifthTab();
        tabbedPane.addTab("Beasts unsort",fir );
        tabbedPane.addTab("Area sort", sec);
        tabbedPane.addTab("Mass sort",thi );
        tabbedPane.addTab("Fitters",fou );
        tabbedPane.addTab("Areas", fif);
        add(tabbedPane);
        pack();
        setVisible(true);

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readXml();
                tabbedPane.setSelectedIndex(BEAST_UNSORT_TAB_INDEX);
                for(var b:beasts)
                    fir.list.append(b.toString()+ "\n");
            }
        });

        byAreaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sec.list.setText("");
                tabbedPane.setSelectedIndex(AREA_SORT_TAB_INDEX);
                beasts.sort(new AreaComparator());
                for(var b:beasts)
                    sec.list.append(b.toString()+ "\n");
            }
        });

        byMassItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thi.list.setText("");
                tabbedPane.setSelectedIndex(MASS_SORT_TAB_INDEX);
                beasts.sort(new MassComparator());
                for(var b:beasts)
                    thi.list.append(b.toString()+ "\n");
            }
        });
        fittersItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(FITTERS_INDEX_TAB);
            }
        });
        areasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fif.list.setText("");
                tabbedPane.setSelectedIndex(AREAS_INDEX_TAB);
                HashSet<Area> hs = new HashSet<>();
                for(var b:beasts)
                    hs.add(b.getArea());
                for(var i:hs)
                 fif.list.append(i.toString() + "\n");
            }
        });

        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = 0;
                for(var b :beasts){
                    if(b.countMass()>max)
                        max = b.countMass();
                }
                Map<Integer, Beast> m = new HashMap<>();
                for(var b :beasts){
                    m.put(b.countMass(), b);
                }
                if(m.get(max/2) != null)
                JOptionPane.showMessageDialog(null, m.get(max/2));
                else
                    JOptionPane.showMessageDialog(null, "no such beast");

            }
        });

        amountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        });
    }

    class FirstTab extends JPanel {
        TextArea list = new TextArea();

        public FirstTab() {

            add(list);
            for (var beast : beasts) {
                list.append(beast.toString() + "\n");
            }
        }
    }

    class SecondTab extends JPanel {
        TextArea list = new TextArea();
        public SecondTab() {

            add(list);

        }

    }

    class ThirdTab extends JPanel {
        TextArea list = new TextArea();
        public ThirdTab() {
            add(list);
        }
    }
    class FourthTab extends JPanel {
        TextArea list = new TextArea();
        public FourthTab() {
            add(list);
        }
    }
    class FifthTab extends JPanel {
        TextArea list = new TextArea();
        public FifthTab() {
            add(list);
        }
    }
    public static void main(String[] args) {
        new Application();
    }

    private void readXml() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(fileXML, handler);

            beasts = (ArrayList) handler.getBeasts();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private JMenuBar
            menuBar = new JMenuBar();
    private JMenu
            fileMenu = new JMenu("File"),
            dataMenu = new JMenu("Data");
    private JMenuItem
            openItem = new JMenuItem("Open"),

            byAreaItem = new JMenuItem("By area"),
            byMassItem = new JMenuItem("By mass"),
            fittersItem = new JMenuItem("Fitters"),
            areasItem = new JMenuItem("Areas"),
            searchItem = new JMenuItem("Search"),
            amountItem = new JMenuItem("Amount");
    private final int
            BEAST_UNSORT_TAB_INDEX = 0,
            AREA_SORT_TAB_INDEX = 1,
            MASS_SORT_TAB_INDEX = 2,
            FITTERS_INDEX_TAB = 3,
            AREAS_INDEX_TAB = 4;
    File fileXML = new File("xml.txt");
    private ArrayList<Beast> beasts = new ArrayList<>();
}
