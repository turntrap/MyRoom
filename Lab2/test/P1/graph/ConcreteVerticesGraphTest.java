/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   对空、有顶点和边、remove操作后三种情况测试
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testtoString() {
    	Graph<String> graph=emptyInstance();
    	assertEquals("",graph.toString());
    	graph.add("1");
    	graph.add("2");
    	graph.add("3");
    	graph.set("1", "2", 23);
    	graph.set("1", "3", 43);
    	graph.set("3", "2", 33);
    	assertEquals("1-->2 权重：23"+"\n"+"1-->3 权重：43"+"\n"+"3-->2 权重：33"+"\n",graph.toString());
    	graph.remove("1");
    	assertEquals("3-->2 权重：33"+"\n",graph.toString());
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // 对点集返回的点的名字、所有以该点为源点的目标顶点组成的map和remove以及change后的情况进行测试
    
    // TODO tests for operations of Vertex
    @Test
    public void testgetname() {
    	Map<String, Integer> map1=new HashMap<>();
    	Map<String, Integer> map2=new HashMap<>();
    	Map<String, Integer> map3=new HashMap<>();
    	map1.put("2", 23);
    	map1.put("3", 43);
    	map2.put("3", 33);
    	Vertex<String> v1=new Vertex<String>("1",map1);
    	Vertex<String> v2=new Vertex<String>("2",map2);
    	map3.put("2", 23);
    	map3.put("3", 43);
    	assertEquals(map3, v1.getsources());
    	map3.clear();
    	map3.put("3", 33);
    	assertEquals(map3, v2.getsources());
    	assertEquals("1",v1.getname());
    	assertEquals("2",v2.getname());
    	v1.remove("2");
    	map3.clear();
    	map3.put("3", 43);
    	assertEquals(map3, v1.getsources());
    	v1.change("2", 23);
    	map3.put("2", 23);
    	assertEquals(map3, v1.getsources());
    	v1.change("2", 0);
    	map3.remove("2");
    	assertEquals(map3, v1.getsources());	
    }
    @Test
    public void testgetsource() {
    	Map<String, Integer> map1=new HashMap<>();
    	Map<String, Integer> map2=new HashMap<>();
    	Map<String, Integer> map3=new HashMap<>();
    	map1.put("2", 23);
    	map1.put("3", 43);
    	map2.put("3", 33);
    	map3.put("2", 23);
    	map3.put("3", 43);
    	Vertex<String> v1=new Vertex<String>("1",map1);
    	Vertex<String> v2=new Vertex<String>("2",map2);
    	assertEquals(map3, v1.getsources());
    	map3.clear();
    	map3.put("3", 33);
    	assertEquals(map3, v2.getsources());
    	
    }
    @Test
    public void testremove() {
    	Map<String, Integer> map1=new HashMap<>();
    	Map<String, Integer> map2=new HashMap<>();
    	Map<String, Integer> map3=new HashMap<>();
    	map1.put("2", 23);
    	map1.put("3", 43);
    	map2.put("3", 33);
    	Vertex<String> v1=new Vertex<String>("1",map1);
    	map3.put("2", 23);
    	map3.put("3", 43);
    	assertEquals(map3, v1.getsources());
    	assertTrue(v1.remove("2"));
    	assertFalse(v1.remove("2"));
    	map3.clear();
    	map3.put("3", 43);
    	assertEquals(map3, v1.getsources());
    }
    @Test
    public void testchange() {
    	Map<String, Integer> map1=new HashMap<>();
    	Map<String, Integer> map3=new HashMap<>();
    	map1.put("2", 23);
    	map1.put("3", 43);
    	Vertex<String> v1=new Vertex<String>("1",map1);
    	map3.put("3", 43);
    	v1.change("2", 53);
    	map3.put("2", 53);
    	assertEquals(map3, v1.getsources());
    	v1.change("2", 0);
    	map3.remove("2");
    	assertEquals(map3, v1.getsources());	
    	
    }
}
