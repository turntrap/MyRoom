package P2;

import static org.junit.Assert.*;

import org.junit.Test;


public class FriendshipGraphTest {
	/*
	 * Testing strategy
	 * ���Լ������ظ��������
	 */
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
	/*
	 * Testing strategy
	 * ���Լ���ߵľ��뷵�ز����Ƿ�ɹ�����
	 */
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
		 graph.addEdge(ben, kramer);
		 graph.addEdge(kramer, ben);
		 assertEquals(1, graph.getDistance(ben, kramer));//���Ա��Ƿ���Գɹ�����߼�
		 assertEquals(1, graph.getDistance(kramer, ben));//���Խ�����ϵ�����
		 graph.addEdge(ben, ben);
		 assertEquals(0,graph.getDistance(ben, ben));//�������˵���ͬʱ��߼������
		 assertEquals(-1,graph.getDistance(jack, hiter));//�������˵�û�м���㼯ʱ�����
	}
	/*
	 * Testing strategy
	 * ���л�ͼ����²��������㣬ȫ����ͼ�������ӡ�ȫ����ͼ���Ҳ����ӡ���ȫ����ͼ�е����
	 */
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
	/*
	 * Testing strategy
	 * ���޻�ͼ����²��������㣬ȫ����ͼ�������ӡ�ȫ����ͼ���Ҳ����ӡ���ȫ����ͼ�е����
	 */
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
