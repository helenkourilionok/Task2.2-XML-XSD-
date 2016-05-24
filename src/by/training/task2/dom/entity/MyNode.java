package by.training.task2.dom.entity;

import java.util.ArrayList;

public class MyNode {
	    private MyNode root;//родитель
	    private ArrayList<MyAttr> attr;//атрибуты
	    private ArrayList<MyNode> children;//дочерние элементы
	    private String content = "";//текст между открывающим и закрывающим тегом
	    private String name = "";//название тега

		public MyNode(){
	    }
	    
		
 	    public ArrayList<MyAttr> getAttr() {
			return attr;
		}

		public void setAttr(ArrayList<MyAttr> attr) {
			this.attr = attr;
		}

		public ArrayList<MyNode> getChildren() {
			return children;
		}

		public void setChildren(ArrayList<MyNode> children) {
			this.children = children;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	    public void setRoot(MyNode node){
	        root=node;
	        attr = new ArrayList<MyAttr>();
	        children = new ArrayList<MyNode>();
	    }

	    public MyNode getRoot(){
	        return root;
	    }
	    
	    public void addChild(MyNode node)
	    {
	    	children.add(node);
	    }
	    
	    public void addAttr(MyAttr attr)
	    {
	    	this.attr.add(attr);
	    }
	    
	    public String toString(){
	        String  str="";
            str+="tag --"+name+"\n";
            str+="content--"+content+"\n";
            str+="ListAttr"+"\n";
	        for(MyAttr attr1: attr){
	            str+="NameAttr -- "+attr1.getName()+"=" + "'" + attr1.getValue() + "'\n";
	        }


	        for(MyNode child : children){
	            str+=child.toString();
	        }

	        return str;
	    }
}
