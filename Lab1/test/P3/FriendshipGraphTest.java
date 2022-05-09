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
		
		assertEquals(-1,graph.FindPosistion(ross));//测试点还未加入点集时的情况
		
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		
		assertEquals(0,graph.FindPosistion(rachel));//测试点集中点的位置
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
		
		assertTrue(graph.addVertex(rachel));//测试点是否可以成功加入点集
		assertTrue(graph.addVertex(ross));
		assertTrue(graph.addVertex(ben));
		assertTrue(graph.addVertex(kramer));
		assertFalse(graph.addVertex(kramer));//测试点重复加入的情况
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
		 
		 assertTrue(graph.addEdge(ben, kramer));//测试边是否可以成功加入边集
		 assertTrue(graph.addEdge(kramer, ben));//测试建立联系的情况
		 assertTrue(graph.addEdge(ross, kramer));
		 assertFalse(graph.addEdge(ben, ben));//测试两端点相同时入边集的情况
		 assertFalse(graph.addEdge(jack, hiter));//测试两端点没有加入点集时的情况
		 assertFalse(graph.addEdge(kramer, hiter));
		 assertFalse(graph.addEdge(ben, kramer));//测试重复加入边集的情况
	}
	
	@Test
	  public void testgetDistance1() {//测试有环图的情况
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
		
		assertEquals(0,graph.getDistance(ben,ben));//测试相同点的情况
		assertEquals(1,graph.getDistance(ben,ross));//相邻点的情况
		assertEquals(-1,graph.getDistance(ben,jack));//与不相连的点的情况
		assertEquals(1,graph.getDistance(rachel,ben));//测试环形的情况
		assertEquals(-1,graph.getDistance(kramer,hiter));//测试有向情况
		assertEquals(2,graph.getDistance(rachel,kramer));//测试环形的情况
	}
	
	@Test
	public void testgetDistance2() {//测试无环图的情况
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
		
		assertEquals(0,graph.getDistance(ben,ben));//测试相同点的情况
		assertEquals(1,graph.getDistance(ben,ross));//相邻点的情况
		assertEquals(-1,graph.getDistance(ben,jack));//与不相连的点的情况
		assertEquals(2,graph.getDistance(rachel,ben));//测试环形的情况
		assertEquals(-1,graph.getDistance(kramer,hiter));//测试有向情况
		assertEquals(3,graph.getDistance(rachel,kramer));//测试环形的情况
	}
}
