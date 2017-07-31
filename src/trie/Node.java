package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * A node of the trie
 *
 */
public class Node {
	/**
	 * the children nodes of the node
	 */
	final Map<Character, Node> children;
	/**
	 * whether the node is an end of a word
	 */
	boolean isEnd;

	Node() {
		/*
		 * 0 initial capacity to reduce memory use
		 */
		children = new HashMap<>(0);
		isEnd = false;
	}

}
