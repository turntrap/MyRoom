/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   graph表示语料库组成的图
    // Representation invariant:
    //  必须要构建语料库的图并保存
    // Safety from rep exposure:
    //   graph为private final
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        //throw new RuntimeException("not implemented");
    	BufferedReader read=new BufferedReader(new FileReader(corpus));
    	String s;
    	String[] line = null;
    	List<String> mylist=new ArrayList<>();
    	Map<String,Integer> map=new HashMap<>();
    	while((s=read.readLine())!=null) {  //将文件一行字符存入list中
    		line=s.split(" ");
			  for(int i=0;i<line.length;i++) 
				  mylist.add(line[i]);		
    	}
    	read.close();
		  for(String point:mylist) 
		  { 
			  if(!graph.vertices().contains(point)) 
			  {
		        graph.add(point); //graph中加入所有点
		      } 
		  } 		 
    	for(int i=0;i<mylist.size()-1;i++) {
    		String source=mylist.get(i).toLowerCase();  
    		String target=mylist.get(i+1).toLowerCase();
    		String edge=source+target;
    		int weight=0;
    		if(map.containsKey(edge)) {
    			weight=map.get(edge);
    		}
    		map.put(edge, weight+1);
    		graph.set(source, target, weight+1);   //graph中加入所有边
    	}
    	checkRep();
    }
    // TODO checkRep
    private void checkRep() {
		assert graph != null;
	}
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
       //throw new RuntimeException("not implemented");
    	List<String> inputlist=new ArrayList<>();
    	String[] line=input.split(" ");
		 for(int i=0;i<line.length;i++) 
			 inputlist.add(line[i]); //inputlist存储输入的字符串
    	Map<String, Integer> sources = new HashMap<>();
		Map<String, Integer> targets = new HashMap<>();
		StringBuilder string=new StringBuilder();//最大权重的最终字符串的缓冲集
		for(int i=0;i<inputlist.size()-1;i++) {
			String source=inputlist.get(i).toLowerCase();
			String target=inputlist.get(i+1).toLowerCase();  
			string.append(inputlist.get(i)).append(" "); //每次在最终字符串中加入一个输入字符串的点
			  sources=graph.sources(target);//存储目标结点的所有源点
			  targets=graph.targets(source);//存储源点的所有目标结点
			int maxweight=0;
			String point=null;  //待加入的点
			for(String a:targets.keySet()) {   //从前一个源点的所有终点表中选点
				if(sources.containsKey(a)&&sources.get(a)+targets.get(a)>maxweight) { 
					//寻找权重更大的点，确定为暂时的point
					maxweight=sources.get(a)+targets.get(a);
					point=a;
				}
			}
			if(maxweight>0) 
				string.append(point).append(" ");//加入点
		}
		if(inputlist.size()>0)
		string.append(inputlist.get(inputlist.size()-1));//加入输入字符串的最后一个点
		String result=string.toString();
		checkRep();
		return result;
    }
    
    // TODO toString()
    public String toString() {
    	String s=graph.toString();;
		return s;
	}
    
}
