package com.bridgelabz.hashtable;

import java.util.LinkedList;

public class MyHashTable {

	private int capacity;
	private LinkedList<MyMapNode>[] buckets;

	public MyHashTable() {
		this.capacity = 10;
		this.buckets = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			buckets[i] = new LinkedList<>();
		}
	}

	private int getBucketIndex(String key) {
		int hashCode = Math.abs(key.hashCode());
		return hashCode % capacity;
	}

	public int get(String key) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> bucket = buckets[bucketIndex];
		for (MyMapNode node : bucket) {
			if (node.key.equals(key)) {
				return node.value;
			}
		}
		return -1;
	}

	public void put(String key, int value) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> bucket = buckets[bucketIndex];
		for (MyMapNode node : bucket) {
			if (node.key.equals(key)) {
				node.value = value;
				return;
			}
		}
		bucket.add(new MyMapNode(key, value));
	}

	public void remove(String key) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> bucket = buckets[bucketIndex];
		for (MyMapNode node : bucket) {
			if (node.key.equals(key)) {
				bucket.remove(node);
				return;
			}
		}
	}

	public void recalculateFrequencies(String phrase) {
		// Remove the word "avoidable" from the hash table
		int indexToRemove = Math.abs("avoidable".hashCode() % capacity);
		MyMapNode previous = null;
		MyMapNode current = buckets[indexToRemove].getFirst();
		while (current != null) {
			if (current.key.equals("avoidable")) {
				if (previous == null) {
					buckets[indexToRemove].removeFirst();
				} else {
					buckets[indexToRemove].remove(current);
				}
				break;
			}
			previous = current;
			current = current.next;
		}

		// Recalculate the frequencies
		String[] words = phrase.split(" ");
		for (int i = 0; i < capacity; i++) {
			LinkedList<MyMapNode> bucket = buckets[i];
			for (int j = 0; j < bucket.size(); j++) {
				MyMapNode node = bucket.get(j);
				node.value = 0;
				for (String word : words) {
					if (node.key.equals(word)) {
						node.value++;
					}
				}
				System.out.println(node.key + ": " + node.value);
			}
		}
	}

	public static void main(String[] args) {
		String phrase = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
		String[] words = phrase.split(" ");

		MyHashTable map = new MyHashTable();
		for (String word : words) {
			int frequency = map.get(word);
			if (frequency == -1) {
				map.put(word, 1);
			} else {
				map.put(word, frequency + 1);
			}
		}

		map.recalculateFrequencies(phrase);
	}
}
