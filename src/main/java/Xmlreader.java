/*
学号：202128016715003
姓名：潘榕
编写可读取xml文档的程序，通过meaven进行包管理
 */



import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Xmlreader {
    //private static ArrayList<Book> bookList = new ArrayList<Book>();
    public static void main(String[] args){
        SAXReader reader = new SAXReader();
        try{
            Document document = reader.read(new File("books.xml"));//加载books.xml文件,获取docuemnt对象
            Element books = document.getRootElement(); // 通过document对象获取根节点
            Iterator it = books.elementIterator(); // 通过element对象的elementIterator方法获取迭代器
            while (it.hasNext()) { // 遍历迭代器，获取根节点中的信息
                Element book = (Element) it.next();
                List<Attribute> bookAttrs = book.attributes(); // 获取book的属性名以及 属性值
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名:" + attr.getName() + "--属性值:" + attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    System.out.println("节点名:" + bookChild.getName() + "--节点值:" + bookChild.getStringValue());
                }
            }
        } catch (DocumentException | MalformedURLException e) {
            e.printStackTrace(); }
    }
}


