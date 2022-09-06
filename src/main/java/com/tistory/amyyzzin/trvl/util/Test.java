package com.tistory.amyyzzin.trvl.util;

import com.tistory.amyyzzin.trvl.dto.CountryBasicInfoResponseDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Test {

    private static Map<String, Map<String, Object>> map = new HashMap<>();

    public CountryBasicInfoResponseDto callCountryBasicInfoApi() {

        List<Map<String, Object>> list = new ArrayList<>();
        List<String> countries = new ArrayList<>();
        CountryBasicInfoResponseDto countryBasicInfoResponseDto = null;

        try {
            String servicekey = "Nd%2BLFj1ko2GZ%2BMrPTWWa%2FNi3TWAnGAOEGthshlAmxbpIA3Fw50RJ2tUm7G9QRu17yNsyCesQeHdLpUOHOYvbGw%3D%3D";
            String url =
                "http://apis.data.go.kr/1262000/CountryBasicService/getCountryBasicList?servicekey="
                    + servicekey + "&numOfRows=1000&pageNo=1";

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            NodeList countryName = root.getElementsByTagName("countryName");
            NodeList basicInfo = root.getElementsByTagName("basic");

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            for (int i = 0; i < countryName.getLength(); i++) {
                Node nNode = countryName.item(i);
                Node temp = nNode.getFirstChild();
                countries.add(temp.getNodeValue());
                list1.add(temp.getNodeValue());

                nNode = basicInfo.item(i);
                temp = nNode.getFirstChild();
                list2.add(temp.getNodeValue());
            }

            for (int i = 0; i < countries.size(); i++) {
                String country = countries.get(i);

                for (int j = 0; j < 1; j++) {

                    String info = list2.get(i + j);
                    Map<String, Object> putter = new HashMap<String, Object>();
                    putter.put("countryName", country);
                    putter.put("basic", info);
                    System.out.println("국가명: " + putter.get("countryName"));
                    System.out.println("정보: " + putter.get("basic"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        countryBasicInfoResponseDto = (CountryBasicInfoResponseDto) map;

        return countryBasicInfoResponseDto;
    }

}
