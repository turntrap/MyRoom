package P2;


public class Person {
	 // Abstraction function:
    //   name表示一个点
    // Representation invariant:
    // 
    // Safety from rep exposure:
    //   name为private final
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
