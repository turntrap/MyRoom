package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FriendshipGraph {
	List<Person> VertexList = new ArrayList<>();
	public int FindPosistion(Person people)//Ѱ�Ҹõ��ڵ㼯�е�λ�ã������ڷ���-1
	{
		if(VertexList.contains(people))
			for(int i=0;i<VertexList.size();i++)
				if(VertexList.get(i).equals(people))
					return i;
		return -1;	
	}
	
    public boolean addVertex(Person people) //����㼯
    {
		if(VertexList.contains(people))
		{
			System.out.println("�Ѿ�����!\n ");
			return false;
		}
		else
		{
			VertexList.add(people);
			return true;
		}		
	}
    public boolean addEdge(Person people1, Person people2) 
    {
		int posistion1=FindPosistion(people1);
		int posistion2=FindPosistion(people2);
		if(posistion1==posistion2)
		{
			System.out.println("������ͬ\n");
			return false;
		}
		else if(posistion1==-1||posistion2==-1)
		{
			System.out.println("���߲�������\n");
			return false;
		}
		else if(people1.edgeexist(posistion2))
		{
			System.out.println("�ñ��Ѿ����\n");
			return false;
		}
		else
		{
			people1.Socialadd(posistion2);
			return true;
		}
	}
    
	public int getDistance(Person people1, Person people2) {//ʹ�ù�ϣ��Ͷ��У����ù����������
		Map<Person, Integer> distance = new HashMap<>();
		Queue<Integer> queue = new LinkedList<Integer>();
		int first=FindPosistion(people1);
		int last=FindPosistion(people2);
		boolean []visited=new boolean[VertexList.size()];
		for(int i = 0; i < visited.length; i++)//���Ƿ������ı�־
            visited[i] = false;
		if(first==last)
			return 0;
		if(first==-1||last==-1)
			return -1;
		queue.add(first);
		visited[first]=true;
		distance.put(people1, 0);
		while(!queue.isEmpty())
		{
			int currentvertex=queue.remove();
			for(int i=0;i<VertexList.get(currentvertex).listsize();i++)
			{
				int visitnode=VertexList.get(currentvertex).getSocial(i);
				 if (!visited[visitnode])
				 {//ÿ����һ���߸��˵㵽��ʼ��ľ���+1��Ϊ�����Ӷ˵�ľ���ֵ
					 //�����Ӷ˵����Ŀ�Ķ˵��˳�
					 visited[visitnode]=true;
					 queue.add(visitnode);
					 distance.put(VertexList.get(visitnode), distance.get(VertexList.get(currentvertex))+1);
				 }
				 if(VertexList.get(visitnode).equals(people2))
					 return distance.get(VertexList.get(visitnode));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross)); 		
		System.out.println(graph.getDistance(rachel, ben)); 
		System.out.println(graph.getDistance(rachel, rachel)); 		
		System.out.println(graph.getDistance(rachel, kramer)); 
	}
}
