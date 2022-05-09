/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Scanner;
import java.lang.Math;
import java.util.Set;

import javax.management.RuntimeErrorException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	 //throw new RuntimeException("implement me!");
    		 for(int i=0;i<4;i++)
             {
             	 turtle.forward(sideLength);
                  turtle.turn(90);
             }
       
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     * @throws Exception 
     */
    public static double calculateRegularPolygonAngle(int sides)  {
    	 //throw new RuntimeException("implement me!");
    	double angles=0;
    	if(sides<=2)
    		throw new RuntimeException("sides must be > 2");
    	angles=((double)(sides-2)*180)/sides;
		return angles;
    	
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	 //throw new RuntimeException("implement me!");
    	if(angle>=180||angle<=0)
    		throw new RuntimeException("0 < angle < 180");
    	int sides;
    	sides=(int)(360/(180-(angle+0.01)));
		return sides;
    	
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	//throw new RuntimeException("implement me!");
    	double angles;
			angles = calculateRegularPolygonAngle(sides);
    	for(int i=0;i<sides;i++)
    	{
    		turtle.forward(sideLength);
            turtle.turn(180-angles);
    	}
       
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	//throw new RuntimeException("implement me!");
    	double angle=0;
    	int x=currentX-targetX;
    	int y=currentY-targetY;
    	if(y>0)//Ŀ�ĵ��ڵ�ǰ����·�
    		angle=Math.toDegrees(Math.atan(((double)x/y)))+180-currentBearing; 
    	else if(y<0)//Ŀ�ĵ��ڵ�ǰ����Ϸ�
    	    angle=Math.toDegrees(Math.atan(((double)x/y)))+360-currentBearing;
    	else if(x>0)//Ŀ�ĵ��뵱ǰ��y����һ�����ڵ�ǰ�����
    	    angle=270-currentBearing;
    	else if(x<0)//Ŀ�ĵ��뵱ǰ��y����һ�����ڵ�ǰ���ұ�
    		angle=90-currentBearing;
    	if(angle<0)
    	angle=360+angle;
    	if(angle>=360)
    	angle=angle-360;
		return angle;
        
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        //throw new RuntimeException("implement me!");
    	List<Double> result = new ArrayList<>();
    	int x=xCoords.size();
    	double n=0;
    	for(int i=0;i<x-1;i++)
    	{
    		double m=n;
    		n=calculateBearingToPoint(m,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
    		result.add(n);
    	}
		return result;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
        //throw new RuntimeException("implement me!");
    	Set<Point> convexHull = new HashSet<Point>();
    	Point pointNow;
    	Point pointNext = null;
    	Point pointMin = null;
    	double bearingNow;
    	double bearingNext;
    	double bearingPre=0;
    	double lengthNext;
    	if(points.size()<3)
    		return points;
    	
    	for(Point point : points)//ȡ�������½ǵĵ�
    	{
    		if(pointMin==null)
    			pointMin=point;
    		else if(pointMin.x()>point.x())//�ҵ�����ߵĵ�
    			pointMin=point;
    		else if(pointMin.x()==point.x())
    			if(pointMin.y()>point.y())
    				pointMin=point;
    	}
    	convexHull.add(pointMin);//�������½ǵĵ㿴����ʼ��
    	pointNow=pointMin;
    	while(true)//��ʼ�����еĵ�������ӵ�һ���㿪ʼ��Ѱ�������н���С���������ĵ���Ϊ��һ���㣬ֱ����һ���������½ǵĵ�
    	{
    		bearingNext=360;
        	lengthNext=Double.POSITIVE_INFINITY;
        	for(Point point : points)
        	{
        		if(point.equals(pointNow))
        			continue;
        		bearingNow=calculateBearingToPoint(bearingPre,(int)pointNow.x(),(int)pointNow.y(),(int)point.x(),(int)point.y());
        		double length=Math.pow(pointNow.x()-point.x(),2)+Math.pow(pointNow.y()-point.y(),2);
        		if(bearingNow==bearingNext)
        		{
        			if(length>=lengthNext)//�н���ȵ�ʱ��ʼ�жϾ���
        			{
        				pointNext=point;
        				lengthNext=length;
        			}
        		}
        		else if(bearingNext>bearingNow)//�ҵ��нǸ�С�ĵ�
        		{
        			pointNext=point;
    				lengthNext=length;
    				bearingNext=bearingNow;
        		}
        	}
        	if(pointNext.equals(pointMin))//ѭ����������
    		break;
        	pointNow=pointNext;
        	bearingPre+=bearingNext;
        	convexHull.add(pointNext);
    	}
    	return convexHull;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        //throw new RuntimeException("implement me!");
    	turtle.turn(90);
    	for(int i=180;i>0;i--) {
    		if(i<=90)
    			turtle.color(PenColor.YELLOW);
    		else
    			turtle.color(PenColor.ORANGE);
    		turtle.forward(2);
    		turtle.turn(358);
    	}
    	for(int i=180;i>0;i--) {
    		if(i<=90)
    			turtle.color(PenColor.RED);
    		else
    			turtle.color(PenColor.MAGENTA);
    		
    		turtle.forward(1);
    		turtle.turn(358);
    	}
    	for(int i=0;i<180;i++) {
    		if(i<=90)
    			turtle.color(PenColor.RED);
    		else
    			turtle.color(PenColor.MAGENTA);
    		
    		turtle.forward(1);
    		turtle.turn(2);
    	}
    	for(int i=0;i<180;i++) {
    		if(i<=90)
    			turtle.color(PenColor.YELLOW);
    		else
    			turtle.color(PenColor.ORANGE);
    		
    		turtle.forward(2);
    		turtle.turn(2);
    	}
    	turtle.turn(270);
    	for(int i=0;i<180;i++) {
    		if(i<=90)
    			turtle.color(PenColor.CYAN);
    		else
    			turtle.color(PenColor.PINK);
    		
    		turtle.forward(1);
    		turtle.turn(2);
    	}
    	for(int i=0;i<180;i++) {
    		if(i<=90)
    			turtle.color(PenColor.BLUE);
    		else
    			turtle.color(PenColor.GREEN);
    		
    		turtle.forward(2);
    		turtle.turn(2);
    	}
    	for(int i=0;i<180;i++) {
    		if(i>90)
    			turtle.color(PenColor.CYAN);
    		else
    			turtle.color(PenColor.PINK);
    		
    		turtle.forward(1);
    		turtle.turn(358);
    	}
    	for(int i=0;i<180;i++) {
    		if(i>90)
    			turtle.color(PenColor.BLUE);
    		else
    			turtle.color(PenColor.GREEN);
    		
    		turtle.forward(2);
    		turtle.turn(358);
    	}
    	
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        Scanner s=new Scanner(System.in);
       while(true)
       {
    	   System.out.println("����ѡ��:\n");
    	   System.out.println("1.����������:\n");
    	   System.out.println("2.���������ڽ�:\n");
    	   System.out.println("3.�������α���:\n");
    	   System.out.println("4.����һ�������:\n");
    	   System.out.println("5.�������:\n");
    	   System.out.println("6.����͹��:\n");
    	   System.out.println("7.�������:\n");
    	   System.out.println("0.�˳�:\n");
    	   int input=s.nextInt();
    	   if(input==0)
    		   break;
    	   switch(input)
    	   {
    	   case 1:
    		   drawSquare(turtle, 40);
    	        turtle.draw();
    	        break;
    	   case 2:
    			System.out.println("�������:\n");    			   
    			int sides=s.nextInt();   				 
    		    System.out.println("�ڽ�Ϊ��"+calculateRegularPolygonAngle(sides)+"\n");
    	        break;
    	   case 3:    		   
    			System.out.println("�����ڽ�:\n");   			   
    			double angle=s.nextFloat();  
    		    System.out.println("����Ϊ��"+calculatePolygonSidesFromAngle(angle)+"\n");
   	            break;
   	       case 4:   	    	
 			    System.out.println("�������:\n"); 			   
 			    int n=s.nextInt(); 				   		  
			    System.out.println("����߳�:\n");			   
				int l=s.nextInt();				 
    		    drawRegularPolygon(turtle,n,l);
    		    turtle.draw();
    	        break;
    	   case 5:    	      	
    		    System.out.println("�����ĸ���:\n");    				   
    			int number=s.nextInt();
    	      	List<Integer> xpoints = new ArrayList<>();
    			List<Integer> ypoints = new ArrayList<>();
    	      	for(int i=0;i<number;i++)
    	      	{   	      		
    	  		   System.out.println("�����"+(i+1)+"�����x����:\n");    	  			   
    	  		   int x=s.nextInt();    	  				     	  		  
    	 		   System.out.println("�����"+(i+1)+"�����y����:\n");   	 			   
    	 		   int  y=s.nextInt();    	 				 
    	 		   xpoints.add(x);
    	 		   ypoints.add(y);
    	      	 }
    	      	List<Double> result=calculateBearings(xpoints,ypoints);
    	      	System.out.println("���Ϊ��\n");
    	      	for(Double r:result) 
    	      		System.out.println(r+"\n");
        	    break;
           case 6:
        	   Set<Point> points = new HashSet<Point>();
       		   Set<Point> convexHull = new HashSet<Point>(); 	       		
			   System.out.println("�����ĸ���:\n");			   
			   number=s.nextInt();	 
	      	   for(int i=0;i<number;i++)
	      	   {
	  			   System.out.println("�����"+(i+1)+"�����x����:\n");
	  			   int x=s.nextInt();	  		   	  		  
	 			   System.out.println("�����"+(i+1)+"�����y����:\n");	 			  
	 			   int  y=s.nextInt();	 				 
	 		       Point p = new Point(x, y);
	 		       points.add(p);
	      	 }
	      	convexHull=convexHull(points);
	      	System.out.println("͹��Ϊ:\n");
	      	for(Point c:convexHull)
	      		System.out.println("("+c.x()+","+c.y()+")\n");   	
            	break;
           case 7:
        	   drawPersonalArt(turtle);
        	   turtle.draw();
           	break;  
    	   }  
       }		 
    }

}
