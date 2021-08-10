package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public InputStream getInputStream(String urlString) {
        try {
            URL url = new URL(urlString);
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public NodeList getNewsItems(InputStream inputStream) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        NodeList newsListNode = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            newsListNode = doc.getElementsByTagName("item");
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return newsListNode;
    }

    public List<String> getTitles(NodeList newsListNode) {
        List<String> titles = new ArrayList<>();

        for (int i = 0; i < newsListNode.getLength(); i++) {
            Node newsNode = newsListNode.item(i);
            Node titleNode = newsNode.getFirstChild();
            String title = titleNode.getFirstChild().getNodeValue();

            titles.add(title);
        }

        return titles;
    }

    public String getLink(String currentTitle, NodeList newsListNode) {
        String result = "";
        NodeList newsItem = getNewsItemByTitle(currentTitle, newsListNode);

        if (newsItem != null) {
            for (int j = 0; j < newsItem.getLength(); j++) {
                if (newsItem.item(j).getNodeName().equals("link")) {
                    Node imageNode = newsItem.item(j).getFirstChild();
                    result = imageNode.getNodeValue();
                    break;
                }
            }
        }
        return result;
    }

    public String getDescription(String currentTitle, NodeList newsListNode) {
        String result = "";
        NodeList newsItem = getNewsItemByTitle(currentTitle, newsListNode);

        if (newsItem != null) {
            for (int j = 0; j < newsItem.getLength(); j++) {
                if (newsItem.item(j).getNodeName().equals("description")) {
                    Node imageNode = newsItem.item(j).getFirstChild();
                    result = imageNode.getNodeValue();
                    break;
                }
            }
        }
        return result;
    }

    public String getImage(String currentTitle, NodeList newsListNode) {
        String result = "";
        NodeList newsItem = getNewsItemByTitle(currentTitle, newsListNode);

        if (newsItem != null) {
            for (int j = 0; j < newsItem.getLength(); j++) {
                if (newsItem.item(j).getNodeName().equals("media:group")) {
                    Node imageNode = newsItem.item(j).getFirstChild();
                    Element imageElement = (Element) imageNode;
                    result = imageElement.getAttribute("url");
                    break;
                }
            }
        }

        return result;
    }

    public NodeList getNewsItemByTitle(String currentTitle, NodeList newsListNode) {

        NodeList newsItem = null;
        for (int i = 0; i < newsListNode.getLength(); i++) {
            Node newsNode = newsListNode.item(i);
            newsItem = newsNode.getChildNodes();
            Node titleNode = newsNode.getFirstChild();
            String title = titleNode.getFirstChild().getNodeValue();
            if (title.equals(currentTitle)) {
                return newsItem;
            }
        }
        return newsItem;
    }
}
