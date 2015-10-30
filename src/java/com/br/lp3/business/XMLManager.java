/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.business;

import com.br.lp3.model.MarvelCharacter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 31240550
 */

//aqui lemos o arquivo do xml e ele constrói o objeto document
public class XMLManager {
    private static Document doc;
    
    public static void openXML(String nameFile) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(nameFile);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<MarvelCharacter> parseFeed() {
        List<MarvelCharacter> characters = new ArrayList<>();
        
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        System.out.println("RAIZ: " + root.getNodeName());
        
        NodeList nList = root.getElementsByTagName("character");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i); 
            if(node.getNodeType() == node.ELEMENT_NODE) {
                Element nodeElement = (Element) node;
                int id = Integer.parseInt(nodeElement.getAttribute("id"));
                String name = nodeElement.getElementsByTagName("name").item(0).getTextContent();
                String description = nodeElement.getElementsByTagName("description").item(0).getTextContent();
                
                String path = "";
                String extension = "";
                
                NodeList nList2 = nodeElement.getElementsByTagName("thumbnail");
                for (int j = 0; j < nList2.getLength(); j++) {
                    Node node2 = nList2.item(j);
                    if(node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element nodeElement2 = (Element) node2;
                        path = nodeElement2.getElementsByTagName("path").item(0).getTextContent();
                        extension = nodeElement2.getElementsByTagName("extension").item(0).getTextContent();
                    }
                }
                MarvelCharacter mc = new MarvelCharacter(id, name, description, path + "." + extension);
                characters.add(mc);
            }
        }
        return characters;
    }
}
