package com.bridgelabz.hashtable;

import java.util.LinkedList;

public class MyHashTable {

	private final int numBuckets;
	private final LinkedList<MyMapNode>[] buckets;

	public MyHashTable() {
		this.numBuckets = 10;
		this.buckets = new LinkedList[numBuckets];
	}

	private int getBucketIndex(String key) {
		int hashCode = Math.abs(key.hashCode());
		return hashCode % numBuckets;
	}

	public int get(String key) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> bucket = buckets[bucketIndex];
		if (bucket == null)
			return 0;
		MyMapNode node = bucket.peek();
		while (node != null) {
			if (node.key.equals(key))
				return node.value;
			node = node.next;
		}
		return 0;
	}

	public void add(String key, int value) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> bucket = buckets[bucketIndex];
		if (bucket == null) {
			bucket = new LinkedList<MyMapNode>();
			buckets[bucketIndex] = bucket;
		}
		MyMapNode node = bucket.peek();
		while (node != null) {
			if (node.key.equals(key)) {
				node.value = value;
				return;
			}
			node = node.next;
		}
		MyMapNode newNode = new MyMapNode(key, value);
		newNode.next = bucket.peek();
		bucket.push(newNode);
	}

	public void remove(String key) {
		int bucketIndex = getBucketIndex(key);
		LinkedList<MyMapNode> bucket = buckets[bucketIndex];
		if (bucket == null)
			return;
		MyMapNode prevNode = null;
		MyMapNode node = bucket.peek();
		while (node != null) {
			if (node.key.equals(key)) {
				if (prevNode == null)
					bucket.pop();
				else
					prevNode.next = node.next;
				return;
			}
			prevNode = node;
			node = node.next;
		}
	}

	public static void main(String[] args) {
		String phrase = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
		String[] words = phrase.split(" ");

		MyHashTable wordFrequency = new MyHashTable();
		for (String word : words) {
			int frequency = wordFrequency.get(word);
			wordFrequency.add(word, frequency + 1);
		}

		System.out.println("Before removing 'avoidable':");
		for (String word : words) {
			System.out.println(word + ": " + wordFrequency.get(word));
		}

		wordFrequency.remove("avoidable");

		System.out.println("\nAfter removing 'avoidable':");
		for (String word : words) {
			System.out.println(word + ": " + wordFrequency.get(word));
		}
	}
}
