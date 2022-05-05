import java.util.Iterator;

public class Main {
	
	public static int findItem(int item, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == item)
				return item;
		}
		return -1;
	}

	public static void main(String[] args) {
		java.util.Random rand = new java.util.Random();
		SkipList<Integer,Integer> sl = new SkipList<Integer,Integer>();

		//simple inserts to test the construction of the SkipList
		sl.insert(5,5);
		sl.insert(6,6);
		sl.insert(2, 2);
		sl.insert(4, 4);

		//remove a single element
		sl.remove(5, 5);

		System.out.println("Elements: ");
		Iterator<IEntry<Integer, Integer>> iter = sl.entries();
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println("\n");

		//create a new list for the benchmark
		sl = new SkipList<>();

		int size = 100000;
		int[] array = new int[size];
		
		System.out.println("Adding " + size + " items to an array and a skiplist");
		for (int i = 0; i < size; i++) {
			int item = rand.nextInt();
			array[i] = item;
			sl.insert(item, i);
		}
		
		//create a sorted copy of the array
		int[] sorted = java.util.Arrays.copyOf(array, array.length);
		java.util.Arrays.sort(sorted);
		System.out.println("Finding each item in turn and timing it");
		long start  = System.currentTimeMillis();
		for (int j = 0; j < sorted.length; j++) {
			int index = findItem(sorted[j], array);
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Array done: " + end + "ms");
		
		//find each item in the skiplist
		System.out.println("Finding each item in the skiplist timing it");
		start  = System.currentTimeMillis();
		for (int j = 0; j < sorted.length; j++) {
			int index = sl.find(sorted[j]).getKey();
		}
		end = System.currentTimeMillis() - start;
		System.out.println("Skiplist done: " + end + "ms");
		
		System.out.println("Done");
	}
}
