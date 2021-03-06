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
    //   graph??????????????????
    // Representation invariant:
    //  ??????????????????????????
    // Safety from rep exposure:
    //   graph??private final
    
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
    	while((s=read.readLine())!=null) {  //??????????????????list??
    		line=s.split(" ");
			  for(int i=0;i<line.length;i++) 
				  mylist.add(line[i]);		
    	}
    	read.close();
		  for(String point:mylist) 
		  { 
			  if(!graph.vertices().contains(point)) 
			  {
		        graph.add(point); //graph????????????
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
    		graph.set(source, target, weight+1);   //graph????????????
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
			 inputlist.add(line[i]); //inputlist????????????????
    	Map<String, Integer> sources = new HashMap<>();
		Map<String, Integer> targets = new HashMap<>();
		StringBuilder string=new StringBuilder();//????????????????????????????
		for(int i=0;i<inputlist.size()-1;i++) {
			String source=inputlist.get(i).toLowerCase();
			String target=inputlist.get(i+1).toLowerCase();  
			string.append(inputlist.get(i)).append(" "); //????????????????????????????????????????
			  sources=graph.sources(target);//??????????????????????
			  targets=graph.targets(source);//??????????????????????
			int maxweight=0;
			String point=null;  //??????????
			for(String a:targets.keySet()) {   //??????????????????????????????
				if(sources.containsKey(a)&&sources.get(a)+targets.get(a)>maxweight) { 
					//??????????????????????????????point
					maxweight=sources.get(a)+targets.get(a);
					point=a;
				}
			}
			if(maxweight>0) 
				string.append(point).append(" ");//??????
		}
		if(inputlist.size()>0)
		string.append(inputlist.get(inputlist.size()-1));//??????????????????????????
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
