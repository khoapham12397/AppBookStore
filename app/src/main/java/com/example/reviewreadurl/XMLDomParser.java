package com.example.reviewreadurl;

import android.util.Log;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.annotation.ElementType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLDomParser {
    public Document getDocument(String xml){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Document document=null;
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();
            InputSource is=new InputSource();
            is.setCharacterStream(new StringReader(xml));
            is.setEncoding("UTF-8");
            document=builder.parse(is);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    public String getValue(Element element,String item){
        //sau do se de dang: co duoc
        NodeList nodes= element.getElementsByTagName(item);

        return getTextValue(nodes.item(0));
    }
    public String getPathImg(Element element){
        Node node=element.getElementsByTagName("description").item(0);
        Node child= node.getFirstChild();
        CDATASection cdata= (CDATASection) child;
        String data=cdata.getData();
        String path="";
        int pos=data.indexOf("img src=");
        for(int i=pos+9;i<data.length();i++){
            if(data.charAt(i)!='\"'){
                path+=data.charAt(i);
            }else break;
        }
        Log.d("AAA",data);
        Log.d("PathIMG",path);
        return path;
    }
    public String getTextValue(Node ele){
        if(ele!=null){
            if(ele.hasChildNodes()){
                Node child;

                for(child=ele.getFirstChild();child!=null;child=child.getNextSibling()){
                    if(child.getNodeType() == Node.TEXT_NODE){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
