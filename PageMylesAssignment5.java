import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*
Myles Page
Cs 1450 002
Monday - Wednesday
Due 03-04-2021
Assignment 5
Generics and Stacks
*/

public class PageMylesAssignment5 {
	public static void main(String[] args) throws FileNotFoundException {
		
		//Part 1
		//_____________________________________________________________________________
		//array on ints 
		int[] values = {10, 17, 19, 63, 42, 8, 2, 7};
		//first generic stack
		GenericStack<Integer> firstStack = new GenericStack<Integer>();
		//push from array to stack
		for(int i = 0; i < values.length; i++) {
			firstStack.push(values[i]);
		}
		//print information and call functions
		System.out.println("First Stack");
		System.out.println("The largest value is: " + findLargest(firstStack));
		printStack(firstStack);
		
		
		//Part 2 Int 
		//_____________________________________________________________________________
		
		//second generic stack
		GenericStack<Integer> secondStack = new GenericStack<Integer>();
		//read in from file
		File read = new File("numbers.txt");
		Scanner numRead = new Scanner(read);
		//add file to stack
		while(numRead.hasNext()) {
			secondStack.push(numRead.nextInt());
		}
		//print information and call functions
		System.out.println("\n\n\nSecond Stack");
		System.out.println("The largest value is: " + findLargest(secondStack));
		printStack(secondStack);
		//find duplicats
		GenericStack<Integer> duplicateStackInt = new GenericStack<Integer>();
		createDuplicatStack(secondStack, duplicateStackInt);
		//print information and call functions
		System.out.println("\n\n\nDuplicate Stack Int");
		printStack(duplicateStackInt);
		System.out.println("\n\n\nUnchanged Second Stack");
		printStack(secondStack);
		
		//Part 2 String
		//_____________________________________________________________________________
		
		//third generic stack
		GenericStack<String> thirdStack = new GenericStack<String>();
		//read in from file
		read = new File("strings.txt");
		Scanner stringRead = new Scanner(read);
		//add file to stack
		while(stringRead.hasNext()) {
			thirdStack.push(stringRead.next());
		}
		//print information and call functions
		System.out.println("\n\n\nThird Stack");
		printStack(thirdStack);
		//find duplicats
		GenericStack<String> duplicateStackString = new GenericStack<String>();
		createDuplicatStack(thirdStack, duplicateStackString);
		//print information and call functions
		System.out.println("\n\n\nDuplicate Stack String");
		printStack(duplicateStackString);
		System.out.println("\n\n\nUnchanged Second Stack");
		printStack(thirdStack);
		
	}
	
	
	public static int findLargest (GenericStack<Integer> stack) {
		//makes varibles to hold information
		int size = stack.getSize();
		int copyArray[] = new int[size];
		
		//move from stack to array 
		for(int i = 0; i < size; i++) {
			copyArray[i] = stack.pop();
		}
		//move from array to stack 
		for(int i = 0; i < size; i++) {
			stack.push(copyArray[i]);
		}
		//sort 
		Arrays.sort(copyArray);
		//largest variable in the last spot 
		return copyArray[size-1];
	}
	
	public static <E> void printStack(GenericStack<E> stack) {
		//makes the variables
		int size = stack.getSize();
		GenericStack<E> tempStack = new GenericStack<E>();
		//makes temp equal to main
		tempStack = stack;
		//makes a temp array 
		E[] copyArray = (E[])new Object[size];
		
		//prints from temp and adds to array 
		for(int i = 0; i < size; i++) {
			E value = tempStack.pop();
			System.out.print(value + " ");
			copyArray[i] = (value);
		}
		//moes array back into main 
		for(int i = 0; i < size; i++) {
			stack.push(copyArray[i]);
		}
	}
	
	public static <E> void createDuplicatStack(GenericStack<E> main, GenericStack<E> duplicate) {
		//makes variables
		int size = main.getSize();
		E[] copyArray = (E[])new Object[size];  
		
		//temp stack
		GenericStack<E> tempStack = new GenericStack<E>();
		
		//moves main to array 
		for(int i = 0; i < size; i++) {
			E value = main.pop();
			copyArray[i] = (value);
		}
		//makes main again from array
		for(int i = 0; i < size; i++) {
			tempStack.push(copyArray[i]);
			main.push(copyArray[i]);
		}
		//check spot by spot to find duplicats and add to duplicats stack
		for (int i = 0; i < size; i++) {
		     for (int j = i + 1 ; j < size; j++) {
		          if (copyArray[i].equals(copyArray[j])) {
		        	  duplicate.push(copyArray[i]);
		          }
		     }
		 }
	}
}
	

class GenericStack<E>{
	Stack<E> stack;
	
	//constructor
	public GenericStack(){
		stack = new Stack<E>();
	}
	//getters and setters
	public int getSize() {
		return stack.size();
	}
	
	public boolean isEmpty() {
		return stack.empty();
	}
	
	public E peek() {
		return stack.peek();
	}
	
	public void push(E string) {
		stack.push(string);
	}
	
	public E pop(){
		return stack.pop();
	}
}
