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
    // ���ڹ��캯��:
	//1��������ļ�Ϊ��
	//2������������ļ�ֻ��һ��
	//34������������ļ��ж���
	
	//����poem����:
	//1��������ַ���Ϊ�� 
	//2.������ַ�������Ϊ1 
	//3��������ַ�����Ϊ�գ���Ȩ�ض�Ϊ1 
    //4��������ַ�����Ϊ�գ�Ȩ�ز���Ϊ1 
	
	// ����toString����:
	//1�����ļ� 
	//2�����ǿ��ļ�
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/empty.txt"));//�����Ͽ�
         final String input = "to seek out life.";
         assertEquals("to seek out life.",nimoy.poem(input));
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/oneline.txt"));//һ�����Ͽ�
         final String input1 = "to seek out life";
         assertEquals("to seek out new life",nimoy1.poem(input1));
         final GraphPoet nimoy2 = new GraphPoet(new File("test/P1/poet/moreline.txt"));//�������Ͽ�
         final String input2 = "explore strange new worlds seek";
         assertEquals("explore strange new worlds to seek",nimoy2.poem(input2));  
    }
    
    @Test
    public void testPoem() throws IOException{
    	
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/oneweight.txt"));//����Ȩ��Ϊ1
    	 final String i="";//�����ַ���Ϊ��
    	 assertEquals("",nimoy.poem(i));
    	 final String input0 = "to";//�����ַ���ֻ��һ���ַ�
         assertEquals("to",nimoy.poem(input0));
         final String input = "to seek out life";//oneweight.txt�µ��������
         assertEquals("to seek out new life",nimoy.poem(input));
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/moreweight.txt"));//��Ȩ�ز�Ϊ1�����
         final String input1 = "new worlds explore";//mutiweight.txt�µ��������
         assertEquals("new worlds to explore",nimoy1.poem(input1));  
    }
    @Test
    public void testtoString() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/empty.txt"));
         assertEquals("",nimoy.toString());
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/oneline.txt"));
         assertEquals("to-->seek Ȩ�أ�1\n" + "seek-->out Ȩ�أ�1\n" + "out-->new Ȩ�أ�1\n" + 
         "new-->life Ȩ�أ�1\n" + "life-->and Ȩ�أ�1\n" + "and-->new Ȩ�أ�1\n" + 
         		      "new-->civilizations Ȩ�أ�1\n",nimoy1.toString());
    }
    
}
