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
    //  对于add方法，我们使用空图加点和重复加点两种方式测试
	
	//对于set方法，我们使用空集加权重为0的边，空集加权重不为0的边
	//有未相连两点加权重不为0的边，加权重为0的边
	//有相连的两点加权重为0和不为0的边进行测试
	
	//对于remove方法，我们可以对移除已存在点和未存在点两种情况测试
	//同时测试移除点之后的源点集判断是否成功去掉边
	
	//测试vertices方法，使用空图和非空图测试
	
	//测试返回源点集，使用空源点和有源点两种测试
	
	//测试返回目标点集，使用空目标点和有目标点两种测试
    
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
