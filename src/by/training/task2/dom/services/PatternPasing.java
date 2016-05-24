package by.training.task2.dom.services;
import java.util.regex.*;

public class PatternPasing {
	//? �������� �� ������ ����������,���� ��������� � �������� ����
	//group2 - tagcontent,group1 - alltag
	public static final Pattern findTag = Pattern.compile("(\\<[^\\?](/?[^\\>]+)\\>)");

	public static final Pattern findTagName = Pattern.compile("\\<(\\w+)");
	//<tag> ��� <tag/>
	public static final Pattern findStartTag = Pattern.compile("\\<([^/]+)([ /])?\\>");

	public static final Pattern findEndTag = Pattern.compile("\\</([^>]+)\\>");
	//xml ��������� ��������� �������� � " ��� ' �������;group1 - 
	public static final Pattern findAttr = Pattern.compile("(\\w+)=[\"|']{1}([^'\"]*)[\"|']{1}");
	
    public  static String deleteComment(String content){

        Pattern pattern = Pattern.compile("(\\<!--.*?--\\>)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m = pattern.matcher(content);
        while(m.find()) {
            content=content.replace(m.group(0), "");//�������� �����������
        }
        return content;
    }
}
