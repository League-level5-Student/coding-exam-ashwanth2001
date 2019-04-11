package Coding_Exam_A;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		int width = 900;
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		String c = sc.next();
		int sides = sc.nextInt();
		
		Robot[] robots = new Robot[n];		
		int w = (int)(Math.sqrt(n)+0.9999);
		double angle = 360/sides;
		int ind = 0;
		
		for(int i = 0; i<w; i++) {
			for(int j = 0; j<w; j++) {
				if(ind>=n) {
					break;
				}
				robots[ind] = new Robot();
				robots[ind].moveTo((int)(width/w*j), (int)(width/w*(i+.5)));
				robots[ind].penDown();
				robots[ind].setSpeed(10);
				robots[ind].hide();
				if(c.equalsIgnoreCase("blue")) {
					robots[ind].setPenColor(Color.BLUE);
				} else if(c.equalsIgnoreCase("red")) {
					robots[ind].setPenColor(Color.RED);
				} else if(c.equalsIgnoreCase("green")) {
					robots[ind].setPenColor(Color.GREEN);
				}
				ind++;
			}
		}
		robots[0].setWindowSize(900, 900);
		
		Thread[] threads= new Thread[n];
		
		for(int i = 0; i<n; i++) {
			int in = i;
			threads[i] = new Thread(() -> {
				for(int j = 0; j<sides; j++) {
					robots[in].setAngle((int)(angle*j));
					robots[in].move(50);
				}
			});
		}
		
		for(int i = 0; i<n; i++) {
			threads[i].start();
		}
		
		


	}
}
