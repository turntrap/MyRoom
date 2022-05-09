package P3;

import static org.junit.Assert.*;

import org.junit.Test;


public class FriendshipGraphTest {

	@Test
	public void testFindPosistion() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		
		assertEquals(-1,graph.FindPosistion(ross));//���Ե㻹δ����㼯ʱ�����
		
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		
		assertEquals(0,graph.FindPosistion(rachel));//���Ե㼯�е��λ��
		assertEquals(1,graph.FindPosistion(ross));
		assertEquals(2,graph.FindPosistion(ben));
		assertEquals(3,graph.FindPosistion(kramer));
	}
	@Test
	public void testaddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		
		assertTrue(graph.addVertex(rachel));//���Ե��Ƿ���Գɹ�����㼯
		assertTrue(graph.addVertex(ross));
		assertTrue(graph.addVertex(ben));
		assertTrue(graph.addVertex(kramer));
		assertFalse(graph.addVertex(kramer));//���Ե��ظ���������
	}
	@Test
	public void testaddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person jack = new Person("jack");
		Person hiter = new Person("hiter");
		 graph.addVertex(rachel);
		 graph.addVertex(ross); 
		 graph.addVertex(ben);
		 graph.addVertex(kramer);
		 
		 assertTrue(graph.addEdge(ben, kramer));//���Ա��Ƿ���Գɹ�����߼�
		 assertTrue(graph.addEdge(kramer, ben));//���Խ�����ϵ�����
		 assertTrue(graph.addEdge(ross, kramer));
		 assertFalse(graph.addEdge(ben, ben));//�������˵���ͬʱ��߼������
		 assertFalse(graph.addEdge(jack, hiter));//�������˵�û�м���㼯ʱ�����
		 assertFalse(graph.addEdge(kramer, hiter));
		 assertFalse(graph.addEdge(ben, kramer));//�����ظ�����߼������
	}
	
	@Test
	  public void testgetDistance1() {//�����л�ͼ�����
	 	FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person jack = new Person("jack");
		Person hiter = new Person("hiter");
		graph.addVertex(rachel);
		graph.addVertex(ross); 
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(jack);
		graph.addVertex(hiter);
		graph.addEdge(rachel,ross);
		graph.addEdge(ross,rachel);
		graph.addEdge(ross,ben);
		graph.addEdge(ben,ross);
		graph.addEdge(ben,kramer);
		graph.addEdge(ben,rachel);
		graph.addEdge(rachel,ben);
		graph.addEdge(rachel,hiter);
		
		assertEquals(0,graph.getDistance(ben,ben));//������ͬ������
		assertEquals(1,graph.getDistance(ben,ross));//���ڵ�����
		assertEquals(-1,graph.getDistance(ben,jack));//�벻�����ĵ�����
		assertEquals(1,graph.getDistance(rachel,ben));//���Ի��ε����
		assertEquals(-1,graph.getDistance(kramer,hiter));//�����������
		assertEquals(2,graph.getDistance(rachel,kramer));//���Ի��ε����
	}
	
	@Test
	public void testgetDistance2() {//�����޻�ͼ�����
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person jack = new Person("jack");
		Person hiter = new Person("hiter");
		graph.addVertex(rachel);
		graph.addVertex(ross); 
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(jack);
		graph.addVertex(hiter);
		graph.addEdge(rachel,ross);
		graph.addEdge(ross,rachel);
		graph.addEdge(ross,ben);
		graph.addEdge(ben,ross);
		graph.addEdge(ben,kramer);
		graph.addEdge(rachel,hiter);
		
		assertEquals(0,graph.getDistance(ben,ben));//������ͬ������
		assertEquals(1,graph.getDistance(ben,ross));//���ڵ�����
		assertEquals(-1,graph.getDistance(ben,jack));//�벻�����ĵ�����
		assertEquals(2,graph.getDistance(rachel,ben));//���Ի��ε����
		assertEquals(-1,graph.getDistance(kramer,hiter));//�����������
		assertEquals(3,graph.getDistance(rachel,kramer));//���Ի��ε����
	}
}
