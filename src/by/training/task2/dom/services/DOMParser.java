package by.training.task2.dom.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import by.training.task2.dom.entity.MyAttr;
import by.training.task2.dom.entity.MyNode;

public class DOMParser {
	private String xmlContent = "";
    private String lastOpenTagName;
    private int lastOpenTagEndPos;
    private MyNode root;
	private MyNode current;
    
    public MyNode getRoot() {
		return root;
	}

    public DOMParser() {
		// TODO Auto-generated constructor stub
	}

    public void parse(String xmlFileName) throws FileNotFoundException, IOException {

    	//удаляем комментарии
        xmlContent = PatternPasing.deleteComment(FileReader.readXML(xmlFileName));
        //deleteComment(getResourceContents(xmlFileName));
        Matcher match = PatternPasing.findTag.matcher(xmlContent);
        Matcher tmpMatch;
        String tag;//<tag>,<tag/>,</tag>
        String tagName;
        String textContent;
        boolean noTextContent;

        while(match.find()){

            tag=match.group(1);//ищем теги - <tag>
            tmpMatch=PatternPasing.findStartTag.matcher(tag);
            //проверяем является ли тег окрывающим
            if(tmpMatch.matches()) {
                if(tmpMatch.group(2)!=null){//<tag/>
                	noTextContent = true;
                }else{//<tag>
                	noTextContent = false;
                }

                tagName="";
                tmpMatch= PatternPasing.findTagName.matcher(tag);
                if(tmpMatch.find()){
                    tagName=tmpMatch.group(1);//<tag> --> tagName = tag
                }
                lastOpenTagName=tagName;
                lastOpenTagEndPos=match.end();

                ArrayList<MyAttr> attributes=parseAttributes(tag);
                startElement(tagName, attributes,noTextContent);
              //если тег закрывающий, то
            }else {
                tmpMatch = PatternPasing.findEndTag.matcher(tag);
                if(tmpMatch.matches()) {

                    textContent="";
                    if(lastOpenTagName.equals(tmpMatch.group(1))){
                        textContent=xmlContent.substring(lastOpenTagEndPos, match.start());
                    }
                   
                    lastOpenTagName="";
                    endElement(tmpMatch.group(1), textContent.trim());
                }
            }
        }
    }

    public void startElement(String tagName, ArrayList<MyAttr> attributes, boolean noTextContent){
        MyNode node=new MyNode();
        node.setRoot(current);
        if(root==null){
            root=node;
        }
        if(!attributes.isEmpty()) {
            for (MyAttr atr : attributes) {
                node.addAttr(atr);
            }
        }
        node.setName(tagName);
        current=node;
        if(noTextContent){
            endElement(tagName, "");
        }
    }

    public void endElement(String tagName, String textContents){
        if(textContents!=""){
            current.setContent(textContents);
        }

        MyNode p=current.getRoot();
        if(p!=null) {//если null, то корневой элемент
            p.addChild(current);
            current = p;//поскольку найден закрытый тег 
            //устанавливаем текущий на родительский
        }
    }
    
    private ArrayList<MyAttr> parseAttributes(String tag){
        ArrayList<MyAttr> list = new ArrayList<MyAttr>();
        Matcher tmpMatch=PatternPasing.findAttr.matcher(tag);
        while(tmpMatch.find()){
        					
            MyAttr attribute=new MyAttr(tmpMatch.group(1), tmpMatch.group(2));
            list.add(attribute);
        }
        return list;
    }

}
