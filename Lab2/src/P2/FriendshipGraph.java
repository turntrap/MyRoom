package P2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import P1.graph.ConcreteEdgesGraph;
import P2.FriendshipGraph;

public class FriendshipGraph {
	 // Abstraction function:
    //   Graph��ʾ�ɵ���ɵ��罻ͼ
    // Representation invariant:
    //  
    // Safety from rep exposure:
    //   GraphΪprivate final
	private final ConcreteEdgesGraph<Person> Graph;
	
	public FriendshipGraph() {
		Graph = new ConcreteEdgesGraph<>();
	}
	
	public boolean addVertex(Person people) {
		if(!Graph.vertices().contains(people))
		{
			Graph.add(people);
			return true;
		}
		return false;
	}
	
	public void addEdge(Person people1, Person people2) {
		Graph.set(people1, people2, 1);
	}
	
	public int getDistance(Person people1, Person people2) {//ʹ�ù�ϣ��Ͷ��У����ù����������
		Map<Person, Integer> distance = new HashMap<>();
		Map<Person, Integer>visited = new HashMap<>();
		Queue<Person> queue = new LinkedList<Person>();
		if(people1.getname().equals(people2.getname()))
			return 0;
		if(!Graph.vertices().contains(people1)||!Graph.vertices().contains(people2))
			return -1;
		for(Person p:Graph.vertices())
		{
			visited.put(p, 0);
		}
		queue.add(people1);
		distance.put(people1, 0);
		while(!queue.isEmpty())//�����������
		{
			Person currentvertex=queue.remove();
			visited.put(currentvertex, 1);//����״̬
			for(Person s:Graph.targets(currentvertex).keySet())//�����ӽڵ�
			{
				int length=distance.get(currentvertex);
				if(visited.get(s)!=1)
				{
					visited.put(s, 1);
					queue.add(s);
					distance.put(s, length+1);
				}
				if(s.getname().equals(people2.getname()))
					return distance.get(s);
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
