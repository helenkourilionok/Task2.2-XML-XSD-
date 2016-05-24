package by.training.task2.dom;

import java.io.FileNotFoundException;
import java.io.IOException;
import by.training.task2.dom.entity.MyNode;
import by.training.task2.dom.services.DOMParser;


public class DomParse {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		DOMParser p=new DOMParser();

        p.parse("src/by/training/task2/xml/menu.xml");

        MyNode root=p.getRoot();

        System.out.println(root);
	}

}
