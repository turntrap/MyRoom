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
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
 // Abstraction function:
    // vertices表示点集
	// AF表示这种抽象数据类型到一个有向图的映射
	
    // Representation invariant:
    // 所有边的权重必须大于0
    // 不能有重复点
	
    // Safety from rep exposure:
    // 数据类型采用private final保护
	// 返回时使用防御式拷贝
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    private void checkRep()
    {
    	 Set<Vertex<L>> testvertices = new HashSet<>();
	     testvertices.addAll(vertices);
	     assert testvertices.size() == vertices.size();
    	for(Vertex<L> v:vertices)
    	{
    		Map<L,Integer> m=v.getsources();
    		for(int i:m.values())
    		{
    			assert i>0;
    		}
    	}
    }
    
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	for(Vertex<L> v:vertices)
    	{
    		L s=v.getname();
    		if(s.equals(vertex))
    			return false;
    	}
    	Map<L,Integer> m=new HashMap<L,Integer>();
    	Vertex<L> v=new Vertex<L>(vertex, m);
    	vertices.add(v);
    	return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
    	int y=0,m=0,n=0;
    	//Map<L, Integer> map=new HashMap<>();
    	for(Vertex<L> v:vertices) 
    	{
    		y++;
    		if(source.equals(v.getname()))
    		{
    			m=1;
    			//map=v.getsources();
        		break;
    		}
    	}
    	for(Vertex<L> v:vertices) 
    	{
    		if(target.equals(v.getname()))
    		{
    			n=1;
        		break;
    		}
    	}
    	if(m==0)
    	{
    		Map<L,Integer> m1=new HashMap<L,Integer>();
        	Vertex<L> v1=new Vertex<L>(source, m1);
        	vertices.add(v1);
    	}
    	if(n==0)
    	{
    		Map<L,Integer> m2=new HashMap<L,Integer>();
        	Vertex<L> v2=new Vertex<L>(target, m2);
        	vertices.add(v2);
    	}
    	if((m==0||n==0)&&weight<=0)
    	{
    		checkRep();
    		return 0;
    	}
    	int z=vertices.get(y-1).change(target, weight);
    	checkRep();
    	return z;
    	
    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	int x=0,y=0;
    	Map<L,Integer> m=new HashMap<L,Integer>();
    	for(Vertex<L> v:vertices)
    	{
    		x++;
    		L s=v.getname();
    		if(s.equals(vertex))
    		{
    			m=v.getsources();
    			y=1;
    			break;
    		}	
    	}
    	if(y==0)
    	{
    		checkRep();
    		return false;
    	}
    	for(L s:m.keySet())
    	{
    		vertices.get(x-1).change(s, 0);
    	}
    	for(Vertex<L> v:vertices)
    	{
    		if(v.getsources().containsKey(vertex))
    		{
    			v.change(vertex, 0);
    		}
    	}
    	checkRep();
    	return true;
    }
    
    @Override public Set<L> vertices() {
       //throw new RuntimeException("not implemented");
    	Set<L> ver = new HashSet<>();
    	
    	for(Vertex<L> v:vertices) 
    	{
    		ver.add(v.getname());
    	}
    	checkRep();
    	return ver;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> map=new HashMap<L,Integer>();
    	for(Vertex<L> v:vertices) 
    	{
    		if(v.getsources().containsKey(target))
    		{
    			map.put(v.getname(), v.getsources().get(target));
    		}
    	}
    	checkRep();
    	return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> map=new HashMap<L,Integer>();
    	for(Vertex<L> v:vertices) 
    	{
    		if(v.getname().equals(source))
    		{
    			return new HashMap<L, Integer>(v.getsources());
    		}
    	}
    	checkRep();
    	return map;
    }
    
    // TODO toString()
    public String toString()
    {
    	String s="";
    	for(Vertex<L> v:vertices)
    	{
    		s+=v.toString();
    	}
    	checkRep();
		return s; 	
    }

}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
	private final L name;
	private final Map<L, Integer> sources;
    
    // Abstraction function:
    // str代表点名，sources是所有以该点为源点的目标点和权重的map
	// AF表示这两种抽象数据类型到一个有向图的映射
	
    // Representation invariant:
    //   所有边的权重必须大于0
	
    // Safety from rep exposure:
    // 数据类型采用private final保护
	// 返回时使用防御式拷贝
    
    // TODO constructor
    
    // TODO checkRep
	private void checkRep()
	{
		for(int i:sources.values())
		{
			assert i>0;
		}
	}
    
    // TODO methods
	public Vertex(L s,Map<L, Integer> source)
	{
		this.name=s;
		this.sources=source;
	}
	
	public L getname()
	{
		checkRep();
		return this.name;
	}
	
	public Map<L, Integer> getsources()
	{
		checkRep();
		return new HashMap<L, Integer>(this.sources);
	}
	
	public boolean remove(L target)
	{
		if(!sources.containsKey(target))
			return false;
		change(target,0);
		checkRep();
		return true;	
	}
	
	public int change(L target,int weight)
	{
		if(!sources.containsKey(target))
		{
			if(weight>0)
			sources.put(target, weight);
			checkRep();
			return 0;
		}
		int x=sources.get(target);
		if(weight>0)
		sources.replace(target, weight);
		else if(weight==0)
			sources.remove(target);
		checkRep();
		return x;	
	}
	
	public int getweight(L target)
	{
		if(!sources.containsKey(target))
			return 0;
		checkRep();
		return sources.get(target);
	}
    
    // TODO toString()
	public String toString()
	{
		String str="";
    	for(L s:sources.keySet())
    	{
    		str+= name+"-->"+s.toString()+" 权重："+sources.get(s)+"\n";
    	}
    	checkRep();
    	return str;
	}
    
}
