/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //  ����add����������ʹ�ÿ�ͼ�ӵ���ظ��ӵ����ַ�ʽ����
	
	//����set����������ʹ�ÿռ���Ȩ��Ϊ0�ıߣ��ռ���Ȩ�ز�Ϊ0�ı�
	//��δ���������Ȩ�ز�Ϊ0�ıߣ���Ȩ��Ϊ0�ı�
	//�������������Ȩ��Ϊ0�Ͳ�Ϊ0�ı߽��в���
	
	//����remove���������ǿ��Զ��Ƴ��Ѵ��ڵ��δ���ڵ������������
	//ͬʱ�����Ƴ���֮���Դ�㼯�ж��Ƿ�ɹ�ȥ����
	
	//����vertices������ʹ�ÿ�ͼ�ͷǿ�ͼ����
	
	//���Է���Դ�㼯��ʹ�ÿ�Դ�����Դ�����ֲ���
	
	//���Է���Ŀ��㼯��ʹ�ÿ�Ŀ������Ŀ������ֲ���
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    @Test
    public void testadd() {
    	Graph<String> graph = emptyInstance();
    	assertTrue(graph.add("1"));
    	assertFalse(graph.add("1"));
    	assertTrue(graph.add("2"));
    }
    
    @Test
    public void testset() {
    	Set<String> test = new HashSet<>();
    	Graph<String> graph = emptyInstance();
    	Map<String, Integer> map=new HashMap<>();
    	assertEquals(0,graph.set("1", "2", 0));
    	test.add("1");
    	test.add("2");
    	assertEquals(test,graph.vertices());
    	assertEquals(0,graph.set("1", "2", 1));
    	map.put("1", 1);
    	assertEquals(map,graph.sources("2"));
    	assertEquals(1,graph.set("1", "2", 2));
    	map.clear();
    	map.put("2", 2);
    	assertEquals(map,graph.targets("1"));
    	assertEquals(2,graph.set("1", "2", 0));
    	map.clear();
    	assertEquals(map,graph.targets("1"));
    }
    
    @Test
    public void testremove() {
    	Graph<String> graph =emptyInstance();
    	Map<String, Integer> map=new HashMap<>();
    	assertFalse(graph.remove("1"));
    	graph.add("1");
    	graph.add("2");
    	graph.add("3");
    	assertTrue(graph.remove("2"));
    	graph.add("2");
    	graph.set("1", "2", 23);
    	graph.set("3", "2", 33);
    	graph.remove("2");	
    	assertEquals(map,graph.sources("2"));
    }
    
    @Test
    public void testvertices() {
    	Set<String> test = new HashSet<>();
    	Graph<String> graph = emptyInstance();
    	assertEquals(graph.vertices(),test);
    	graph.add("1");
    	graph.add("2");
    	graph.add("3");
    	test.add("1");
    	test.add("2");
    	test.add("3");
    	assertEquals(test,graph.vertices());
    }
    
    @Test
    public void testsources() {
    	Graph<String> graph =emptyInstance();
    	Map<String, Integer> map=new HashMap<>();
    	graph.add("1");
    	assertEquals(map,graph.sources("1"));
    	graph.add("2");
    	graph.add("3");
    	graph.set("1", "2", 23);
    	graph.set("3", "2", 33);
    	map.put("1", 23);
    	map.put("3", 33);
    	assertEquals(map,graph.sources("2"));
    	Map<String, Integer> map1=new HashMap<>();
    	assertEquals(map1,graph.sources("3"));
    }
    
    @Test
    public void testtargets() {
    	Graph<String> graph =emptyInstance();
    	Map<String, Integer> map=new HashMap<>();
    	graph.add("1");
    	assertEquals(map,graph.targets("1"));
    	graph.add("2");
    	graph.add("3");
    	graph.set("2", "3", 23);
    	graph.set("2", "1", 33);
    	map.put("1", 33);
    	map.put("3", 23);
    	assertEquals(map,graph.targets("2"));
    	Map<String, Integer> map1=new HashMap<>();
    	assertEquals(map1,graph.targets("3"));
    }
    
    
    
    // TODO other tests for instance methods of Graph
    
}
