import java.util.Arrays; 
import java.util.Scanner;
import java.util.Stack;

/**
* The FindSums class is a program which finds all the distinct subsets of the given multiset for 
* which the sum of all the elements in the subset is equal to S.
* 
* This class contains one method and utilizes arrays and the stack data structure. 
* 
* @author Nicole Issagholian
*
*/

public class FindSums { 
	public static void main(String args[]) { 
		
		// Scanner class to take input from user 
		Scanner data = new Scanner(System.in); 
		
		int targetTotal = data.nextInt();
		int input = data.nextInt();
		
		int[] multiSet = new int[input];
		for (int i=0; i < input; i++) {
			multiSet[i] = data.nextInt();
		}
		
		Arrays.sort(multiSet);
		reverse(multiSet);
		
		System.out.println("Sums of " + targetTotal + ":");
		
		Stack<Integer> subSet = new Stack<>();
		boolean found = findSubsets(multiSet, 0, targetTotal, subSet);
		
		if (!found) {
			System.out.println("NONE");
		}
		
		data.close();
	} 
	
	private static boolean findSubsets(int[] multiSet, int index, int targetTotal, Stack<Integer> subSet) {
		if (targetTotal == 0) {
			printSubset(subSet);
			return true;
		}
		
		if (index >= multiSet.length || targetTotal < 0) {
			return false;
		}
		
		subSet.push(multiSet[index]);
		boolean found = findSubsets(multiSet, index+1, targetTotal - multiSet[index], subSet);
		subSet.pop();
		
		int i = index + 1;
		
		while(i < multiSet.length && multiSet[i] == multiSet[index]) {
			i++;
		}
		
		found = findSubsets(multiSet, i, targetTotal, subSet) || found;
		
		return found;
	}
	
	private static void printSubset(Stack<Integer> subSet) {
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i < subSet.size() - 1; i++) {
			sb.append(subSet.get(i)).append("+");
		}
		sb.append(subSet.peek());
		System.out.println(sb);
	}
	
	private static void reverse(int[] arr) {
		int i = 0;
		int j = arr.length - 1;
		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}
}
		
