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
	public int listsize()//��ȡ��ϵ�б��С;
	{
		return SocialList.size();
	}
	public void Socialadd(int posistion)//�����ϵ��;
	{
		SocialList.add(posistion);
	}
	public boolean edgeexist(int posistion)//�ж�����֮���Ƿ��б�;
	{
		if(SocialList.contains(posistion))
		    return true;
		else
			return false;
		
	}
	public int getSocial(int position) //������ϵ����person�Ĺ�ϵ;
    {
        return SocialList.get(position);
    }
	
	
}
