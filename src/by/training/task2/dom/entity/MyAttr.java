package by.training.task2.dom.entity;

public class MyAttr {
	private String name;//name="value"
    private String value;

    public MyAttr(String name, String value){
        this.name=name;
        this.value=value;
    }

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setValue(String value){
        this.value=value;
    }
}
