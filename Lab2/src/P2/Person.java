package P2;


public class Person {
	 // Abstraction function:
    //   name��ʾһ����
    // Representation invariant:
    // 
    // Safety from rep exposure:
    //   nameΪprivate final
	private final String name;
	public Person(String name)
	{
		this.name=name;
	}
	public String getname()
	{
		return this.name;
	}

}
