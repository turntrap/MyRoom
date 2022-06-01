/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 * @param <L>
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   vertices ��ʾͼ�еĵ�
    //   edges��ʾͼ�еı�
    //AF��ʾ���������ݽṹ������ͼ��ӳ��
    
    // Representation invariant:
    //  edges�б���������˵�
    // Ȩ�ر������0
    
    // Safety from rep exposure:
    //  vertices��edges��Ϊprivate final����
    //����ʱʹ�÷���ʽ����
    
    // TODO constructor
    
    // TODO checkRep
    private void checkRep()
    {
    		for (Edge<L> edge : edges) {
                assert vertices.contains(edge.getsource());
                assert vertices.contains(edge.gettarget());
                assert edge.getweight()>0;
            }
    }
    
    @Override public boolean add(L vertex) {
    	if(vertices.contains(vertex))
    		return false;
    	vertices.add(vertex);
    		return true;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
    	int x=1;
    	Map<L, Integer> map=new HashMap<L,Integer>();
    	if(!vertices.contains(source)) 
    	{
    		add(source);
    		x=0;
    	}
    	if(!vertices().contains(target))
    	{
    		add(target);
    		x=0;
    	}
    	map=sources(target);
    	if(x==0||!map.containsKey(source))
    	{
    		if(weight>0)
    		{
    			Edge<L> edge=new Edge<L>(source, target, weight);
    			edges.add(edge);
    		}
    		return 0;
    	}
    	for(int i=0;i<edges.size();i++)
    	{
    		if(edges.get(i).getsource().equals(source)&&edges.get(i).gettarget().equals(target))
    		{
    			x=edges.get(i).getweight();
    			edges.remove(i);
    			if(weight!=0)
    				{
    				    Edge<L> e=new Edge<L>(source, target, weight);
    			        edges.add(i,e);
    				}
    		}
    	}
    	checkRep();
    	return x;		
    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	if(!vertices.contains(vertex)) 
    	{
    		return false;
    	}
    	else {
    		for(L s:vertices)
    		{
    			set(s, vertex, 0);
    			set(vertex, s, 0);
    		}
    		checkRep();
    		return true;
		}
    }
    
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	return new HashSet<L>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
       //throw new RuntimeException("not implemented");
    	Map<L, Integer> map=new HashMap<>();
    	for(Edge<L> e:edges)
    	{
    		if(e.gettarget().equals(target))
    		{
    			map.put(e.getsource(), e.getweight());
    		}
    	}
    	checkRep();
    	return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> map=new HashMap<>();
    	for(Edge<L> e:edges)
    	{
    		if(e.getsource().equals(source))
    		{
    			map.put( e.gettarget(), e.getweight());
    		}
    	}
    	checkRep();
    	return map;
    }
    
    
    // toString()
    public String toString()
    {
    	String s="";
		for(Edge<L> e:edges)
		{
			s=s+e.toString();
		}
		checkRep();
		return s;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 * @param <L>
 */
class Edge<L> {
	// fields
    private final L source;
    private final L target;
    private final int weight;
    
    // Abstraction function:
    //  ��source,target,weight��ɵĳ�����������
    //AF��ʾ���������ݽṹ����Դ�㵽Ŀ�궥���ʾ�Ĵ�Ȩ�ص��������ɵ�����ͼ��ӳ��
    
    // Representation invariant:
    //  source,target�ǿ�;weight>0;
    
    // Safety from rep exposure:
    // ��������private��final�ؼ������Σ����в�û�ж�����������
    //��Ա���������ܴ����ⲿ��ȡ���߸�ֵ����֤�����鲻����й��
    
    
    // constructor
    //public String getsource(); ����Դ��
    //public String gettarget();����Ŀ�궥��
    //public int getweight();����Ȩ��
    //public String toString();����һ���ַ���
    
    // checkRep
    private void checkRep()
    {
    	assert source!=null;
    	assert target!=null;
    	assert weight>0;
    }
    
    // TODO methods
    public  Edge(L source, L target,int weight)
    {
    	this.source=source;
    	this.target=target;
    	this.weight=weight;
    }
    public L getsource()
    {
    	checkRep();
    	return this.source;
    }
    public L gettarget() 
    {
    	checkRep();
    	return this.target;
    }
    public int getweight()
    {
    	checkRep();
    	return this.weight;
    }
    // TODO toString()
    public String toString()
    {
    	checkRep();
    	return this.source+"-->"+this.target+" Ȩ�أ�"+weight+"\n";
    }
}
