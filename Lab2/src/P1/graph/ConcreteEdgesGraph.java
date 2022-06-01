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
    //   vertices 表示图中的点
    //   edges表示图中的边
    //AF表示这两种数据结构到有向图的映射
    
    // Representation invariant:
    //  edges中必须有两侧端点
    // 权重必须大于0
    
    // Safety from rep exposure:
    //  vertices和edges都为private final类型
    //返回时使用防御式拷贝
    
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
    //  由source,target,weight组成的抽象数据类型
    //AF表示这两种数据结构到从源点到目标顶点表示的带权重的有向边组成的有向图的映射
    
    // Representation invariant:
    //  source,target非空;weight>0;
    
    // Safety from rep exposure:
    // 变量都由private和final关键字修饰，类中并没有定义其他方法
    //成员变量都不能从类外部获取或者赋值，保证了数组不会外泄。
    
    
    // constructor
    //public String getsource(); 返回源点
    //public String gettarget();返回目标顶点
    //public int getweight();返回权重
    //public String toString();返回一个字符串
    
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
    	return this.source+"-->"+this.target+" 权重："+weight+"\n";
    }
}
