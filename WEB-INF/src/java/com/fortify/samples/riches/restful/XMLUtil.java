package com.fortify.samples.riches.restful;

import com.fortify.samples.riches.model.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.json.JSONObject;
import org.json.JSONArray;

public class XMLUtil
{

    private XMLUtil()
    {
    }

    private static Document getDocument(String xml)
    {
	    try
        {
            DocumentBuilderFactory factory;
            factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            return factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
        }
        catch (SAXException ex)
        {
            return null;
        }
        catch (ParserConfigurationException ex)
        {
            return null;
        }
        catch (IOException ex)
        {
            return null;
        }
    }

    private static boolean validateAccountXML(Document doc)
    {
        NodeList nodeList = null;
        nodeList = doc.getElementsByTagName("account");
        if(nodeList.getLength() != 1)
        {
            return false;
        }
        nodeList = doc.getElementsByTagName("username");
        if(nodeList.getLength() != 1)
        {
            return false;
        }
        String username = getValue((Element)doc.getElementsByTagName("account").item(0), "username");
        if(username == null || username.isEmpty())
        {
            return false;
        }
        nodeList = doc.getElementsByTagName("acctno");
        if(nodeList.getLength() != 1)
        {
            return false;
        }
        String acctno = getValue((Element)doc.getElementsByTagName("account").item(0), "acctno");
        return acctno != null && !acctno.isEmpty();
    }

    private static String getValue(Element ele, String tagName)
    {
        String value = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0)
        {
            Element el = (Element)nl.item(0);
            value = el.getFirstChild().getNodeValue();
        }
        return value;
    }

    public static String GetValueXML(String xml, String root, String tag)
    {
        Document doc = getDocument(xml);
        return getValue((Element)doc.getElementsByTagName(root).item(0), tag);
    }

    public static String GetAccountsXML(List acctList)
    {
        StringBuilder xml = new StringBuilder();
        xml.append("<accounts>");
        for(int i = 0; i < acctList.size(); i++)
        {
            Account acct = (Account)acctList.get(i);
            xml.append("<account>");
            xml.append("<id>").append(acct.getId()).append("</id>");
            xml.append("<acctno>").append(acct.getAcctno()).append("</acctno>");
            xml.append("<balance>").append(acct.getBalance()).append("</balance>");
            xml.append("<username>").append(acct.getUsername()).append("</username>");
            xml.append("<ccn>").append(acct.getCcn()).append("</ccn>");
            xml.append("</account>");
        }

        xml.append("</accounts>");
        return xml.toString();
    }

    public static String GetAccountsJSON(List acctList)
    {
        JSONArray jsonArray = new JSONArray();

         for(int i = 0; i < acctList.size(); i++)
        {
            Account acct = (Account)acctList.get(i);
            JSONObject json = new JSONObject();
            json.put("id", acct.getId());
            json.put("acctno", acct.getAcctno());
            json.put("balance", acct.getBalance());
            json.put("username", acct.getUsername());
            json.put("ccn", acct.getCcn());
            jsonArray.put(json);
        }

        return jsonArray.toString();
    }

    public static String GetTransactionsXML(List tranList)
    {
        StringBuilder xml = new StringBuilder();
        xml.append("<transactions>");
        for(int i = 0; i < tranList.size(); i++)
        {
            Transaction tran = (Transaction)tranList.get(i);
            xml.append("<transaction>");
            xml.append("<id>").append(tran.getId()).append("</id>");
            xml.append("<acctno>").append(tran.getAcctno()).append("</acctno>");
            xml.append("<description>").append(tran.getDescription()).append("</description>");
            xml.append("<amount>").append(tran.getAmount()).append("</amount>");
            xml.append("<payee>").append(tran.getPayee()).append("</payee>");
            xml.append("<date>").append(tran.getDate()).append("</date>");
            xml.append("</transaction>");
        }

        xml.append("</transactions>");
        return xml.toString();
    }

    public static String GetTransactionsJSON(List tranList)
    {
        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i < tranList.size(); i++)
        {
            Transaction tran = (Transaction)tranList.get(i);
            JSONObject json = new JSONObject();
            json.put("id", tran.getId());
            json.put("acctno", tran.getAcctno());
            json.put("description", tran.getDescription());
            json.put("amount", tran.getAmount());
            json.put("payee", tran.getPayee());
            json.put("date", tran.getDate());
            jsonArray.put(json);
        }

        return jsonArray.toString();
    }


}
