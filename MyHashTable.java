package com.bridgelabz.hashtable;

import java.util.LinkedList;

public class MyHashTable {

	private LinkedList<MyMapNode>[] bucketArray;
	private int numBuckets;
	private int size;

	public MyHashTable() {
		numBuckets = 10; // can be any prime number
		bucketArray = new LinkedList[numBuckets];
		for (int i = 0; i < numBuckets; i++) {
			bucketArray[i] = new LinkedList<MyMapNode>();
		}
	}

	private int getBucketIndex(String key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return index;
	}

	private MyMapNode getNode(LinkedList<MyMapNode> list, String key) {
		for (MyMapNode node : list) {
			if (node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}

	public void put(String key, int value) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> list = bucketArray[bucketIndex];
		MyMapNode node = getNode(list, key);
		if (node == null) {
			node = new MyMapNode(key, value);
			list.addLast(node);
			size++;
		} else {
			node.value = value;
		}
	}

	public int get(String key) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> list = bucketArray[bucketIndex];
		MyMapNode node = getNode(list, key);
		return node == null ? 0 : node.value;
	}

	public static void main(String[] args) {
		String sentence = "To be or not to be";
		MyHashTable map = new MyHashTable();

		// split the sentence into words and add them to the hash table
		String[] words = sentence.split(" ");
		for (String word : words) {
			int frequency = map.get(word);
			map.put(word, frequency + 1);
		}

		// print the frequency of each word in the sentence
		for (String word : words) {
			int frequency = map.get(word);
			System.out.println(word + ": " + frequency);
		}
	}
}
