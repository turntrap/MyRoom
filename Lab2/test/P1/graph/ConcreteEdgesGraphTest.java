/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   �Կա��ж���ͱߡ�remove�����������������
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testtoString() {
    	Graph<String> graph =emptyInstance();
    	assertEquals("",graph.toString());
    	graph.add("1");
    	graph.add("2");
    	graph.add("3");
    	graph.set("1", "2", 23);
    	graph.set("3", "2", 33);
    	assertEquals("1-->2 Ȩ�أ�23"+"\n"+"3-->2 Ȩ�أ�33"+"\n",graph.toString());
    	graph.remove("1");
    	assertEquals("3-->2 Ȩ�أ�33"+"\n",graph.toString());
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // ���Ա߼����ص�Դ�㡢Ŀ�궥���Ȩ���Ƿ���ȷ
    //���Ա߼��������set��remove���Ƿ���ȷ
    
    // TODO tests for operations of Edge
    @Test
    public void testgetsource() {
		
    	Edge<String> e1=new Edge<String>("1","2",23);
    	Edge<String> e2=new Edge<String>("1","3",43);
    	assertEquals("1",e1.getsource());
    	assertEquals("1",e2.getsource());
    }
    @Test
    public void testgettarget() {
		
    	Edge<String> e1=new Edge<String>("1","2",23);
    	Edge<String> e2=new Edge<String>("1","3",43);   	
    	assertEquals("2",e1.gettarget());    	    	
    	assertEquals("3",e2.gettarget());
    }
    @Test
    public void testgetweight() {
		
    	Edge<String> e1=new Edge<String>("1","2",23);
    	Edge<String> e2=new Edge<String>("1","3",43);   
    	assertEquals(23,e1.getweight());
    	assertEquals("1-->2 Ȩ�أ�23"+"\n",e1.toString());
    	assertEquals(43,e2.getweight());
    	assertEquals("1-->3 Ȩ�أ�43"+"\n",e2.toString());
    }
   
}
