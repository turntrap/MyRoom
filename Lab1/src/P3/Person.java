package P3;
import java.util.ArrayList;
import java.util.List;
public class Person {
	public String name;
	List<Integer> SocialList = new ArrayList<>();
	public Person(String name)
	{
		this.name=name;
	}
	public int listsize()//获取关系列表大小;
	{
		return SocialList.size();
	}
	public void Socialadd(int posistion)//添加联系人;
	{
		SocialList.add(posistion);
	}
	public boolean edgeexist(int posistion)//判断两者之间是否有边;
	{
		if(SocialList.contains(posistion))
		    return true;
		else
			return false;
		
	}
	public int getSocial(int position) //其他联系者与person的关系;
    {
        return SocialList.get(position);
    }
	
	
}
