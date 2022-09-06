package com.tistory.amyyzzin.trvl.util;

import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlTest {

    public static CountryBasicInfoResponseDto callCountryBasicInfoApi() {

        CountryBasicInfoResponseDto countryBasicInfoResponseDto = null;

        Map<String, Object> nodeMapData = null;
        try {
            String servicekey = "Nd%2BLFj1ko2GZ%2BMrPTWWa%2FNi3TWAnGAOEGthshlAmxbpIA3Fw50RJ2tUm7G9QRu17yNsyCesQeHdLpUOHOYvbGw%3D%3D";
            String url1 =
                "http://apis.data.go.kr/1262000/CountryBasicService/getCountryBasicList?servicekey="
                    + servicekey + "&numOfRows=1000&pageNo=1";

            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            // 받아온 XML문서 파싱 => document인스턴스에 저장
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(new InputSource(conn.getInputStream()));

            // Dom Tree를 XML문서의 구조대로 완성
            document.getDocumentElement().normalize();

            // Tag 정보들을 검색
            Node firstNode = document.getElementsByTagName("body").item(0);
            NodeList childNodeList = firstNode.getChildNodes();
            nodeMapData = getNodeList(childNodeList);
//            System.out.println(nodeMapData.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        countryBasicInfoResponseDto = (CountryBasicInfoResponseDto) nodeMapData;

        return countryBasicInfoResponseDto;
    }

    public static Map<String, Object> getNodeList(NodeList nodeList) {
        Map<String, Object> dataMap = new HashMap<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            String tagName = nodeList.item(i).getNodeName();

            dataMap.put(tagName, nodeList.item(i).getTextContent());

            System.out.println(i + " " + "tagName = " + tagName + " " + dataMap.put(tagName, nodeList.item(i).getTextContent()));
        }

        return dataMap;

    }
}
