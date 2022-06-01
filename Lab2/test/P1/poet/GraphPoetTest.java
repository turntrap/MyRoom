/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    // 对于构造函数:
	//1、传入的文件为空
	//2、传入的语料文件只有一行
	//34、传入的语料文件有多行
	
	//对于poem函数:
	//1、传入的字符串为空 
	//2.传入的字符串长度为1 
	//3、传入的字符串不为空，但权重都为1 
    //4、传入的字符串不为空，权重不都为1 
	
	// 对于toString函数:
	//1、空文件 
	//2、不是空文件
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/empty.txt"));//空语料库
         final String input = "to seek out life.";
         assertEquals("to seek out life.",nimoy.poem(input));
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/oneline.txt"));//一行语料库
         final String input1 = "to seek out life";
         assertEquals("to seek out new life",nimoy1.poem(input1));
         final GraphPoet nimoy2 = new GraphPoet(new File("test/P1/poet/moreline.txt"));//多行语料库
         final String input2 = "explore strange new worlds seek";
         assertEquals("explore strange new worlds to seek",nimoy2.poem(input2));  
    }
    
    @Test
    public void testPoem() throws IOException{
    	
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/oneweight.txt"));//所有权重为1
    	 final String i="";//传入字符串为空
    	 assertEquals("",nimoy.poem(i));
    	 final String input0 = "to";//传入字符串只有一个字符
         assertEquals("to",nimoy.poem(input0));
         final String input = "to seek out life";//oneweight.txt下的正常情况
         assertEquals("to seek out new life",nimoy.poem(input));
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/moreweight.txt"));//有权重不为1的情况
         final String input1 = "new worlds explore";//mutiweight.txt下的正常情况
         assertEquals("new worlds to explore",nimoy1.poem(input1));  
    }
    @Test
    public void testtoString() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/empty.txt"));
         assertEquals("",nimoy.toString());
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/oneline.txt"));
         assertEquals("to-->seek 权重：1\n" + "seek-->out 权重：1\n" + "out-->new 权重：1\n" + 
         "new-->life 权重：1\n" + "life-->and 权重：1\n" + "and-->new 权重：1\n" + 
         		      "new-->civilizations 权重：1\n",nimoy1.toString());
    }
    
}
