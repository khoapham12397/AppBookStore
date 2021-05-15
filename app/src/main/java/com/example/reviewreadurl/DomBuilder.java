package com.example.reviewreadurl;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomBuilder {

    public Document getDocument(String xml){
        Document document=null;
        try {
            DocumentBuilder builder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document=builder.parse(xml);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }
    //vay la ok chua dung roi hop ly:
    //sau do phu thuoc vao cau truc file ma xu ly cho dung:
    //cac ham xu ly chuoi trong js:
    //co the la : str.length : str.substring(a,v);
    //str.indexOf() -> ra duoc ez : dung la nhu vay :
    //nhung 1 cach co ban thi code no cung tuong tu nhau thoi
    //for($i=0;$i<count($arr);$i++) {//do somethings}
    //array_pop()-> pop phan tu cuoi cung ra :
    //tiep theo trong python: pop(0)-> ra phan tu dau tien :

}
