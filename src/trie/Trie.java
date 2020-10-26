package trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Trie implements TrieInterface {
	/**
	 * starting node of the trie
	 */
	private final Node root;
	/**
	 * the number of unique words in the trie
	 */
	private int size;
	/**
	 * (matches any single character)
	 */
	public static final char WILDCARD_QUESTION_MARK = '?';
	/**
	 * matches any sequence of characters
	 */
	public static final char WILDCARD_STAR = '*';

	/**
	 * Constructs a new empty trie
	 */
	public Trie() {
		root = new Node();
		size = 0;
	}

	/* construct the trie from a Collection of Strings */
	public Trie(Collection<String> col) {
		this();
		for (String s : col) {
			this.add(s);
		}
	}

	/* construct the trie from an array of Strings */
	public Trie(String[] arr) {
		this();
		for (String s : arr) {
			this.add(s);
		}
	}

	/* adds a word to the trie */
	@Override
	public void add(String str) {
		Node curr = root;
		for (int i = 0; i < str.length(); i++) {
			if (curr.children.get(str.charAt(i)) == null) {
				curr.children.put(str.charAt(i), new Node());
			}
			curr = curr.children.get(str.charAt(i));
		}
		if (curr.isEnd == false) {
			size++;
		}
		curr.isEnd = true;
	}

	/*
	 * crawls to the ending node of a word and returns it or returns null if the
	 * word is not found in the trie
	 */
	private Node getNode(String str) {
		Node node = root;
		for (int i = 0; i < str.length(); i++) {
			Node child = node.children.get(str.charAt(i));
			if (child == null) {
				return null;
			}
			node = child;
		}
		return node;
	}

	/* returns true if there are any words in the trie with this prefix */
	public boolean hasPrefix(String str) {
		return getNode(str) != null;
	}

	/* returns a set of all words matching the string with wildcards */
	@Override
	public Set<String> wildcardMatches(String pattern) {
		Set<String> wildcardMatches = new HashSet<>();
		wildcardTraverse(pattern, new StringBuilder(), root, 0, wildcardMatches);
		return wildcardMatches;
	}

	/*
	 * traverses the trie and adds all words matching the string with wildcards * to
	 * list
	 */
	private void wildcardTraverse(String pattern, StringBuilder prefix, Node root, int len,
			Set<String> wildcardMatches) {
		if (root == null) {
			return;
		}
		if (len == pattern.length()) {
			if (root.isEnd) {
				wildcardMatches.add(prefix.toString());
			}
			return;
		}
		if (pattern.charAt(len) == WILDCARD_QUESTION_MARK) {
			for (Entry<Character, Node> e : root.children.entrySet()) {
				prefix.append(e.getKey());
				wildcardTraverse(pattern, prefix, e.getValue(), len + 1, wildcardMatches);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		} else if (pattern.charAt(len) == WILDCARD_STAR) {

			wildcardTraverse(pattern, prefix, root, len + 1, wildcardMatches);

			for (Entry<Character, Node> e : root.children.entrySet()) {
				prefix.append(e.getKey());
				wildcardTraverse(pattern, prefix, e.getValue(), len, wildcardMatches);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		} else {
			prefix.append(pattern.charAt(len));
			wildcardTraverse(pattern, prefix, root.children.get(pattern.charAt(len)), len + 1,
					wildcardMatches);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	/* returns a list of all words matching the string with wildcards */

	/* returns whether the trie contains a given word */
	@Override
	public boolean contains(String str) {
		Node node = getNode(str);
		return node != null && node.isEnd;
	}

	/* returns all words in the trie that start with this prefix */
	@Override
	public List<String> prefixedWords(String str) {
		Node curr = getNode(str);
		List<String> prefixedWords = new ArrayList<>();
		DFS(curr, str, prefixedWords);
		return prefixedWords;
	}

	/* traverses the trie depth first and adds all words to list */
	private static void DFS(Node root, String prefix, List<String> list) {
		if (root == null) {
			return;
		}
		if (root.isEnd) {
			list.add(prefix);
		}
		for (Entry<Character, Node> e : root.children.entrySet()) {
			DFS(e.getValue(), prefix + e.getKey(), list);
		}
	}

	/* a utility method to remove a word from the trie. */
	private boolean removeUtil(Node node, String str, int level) {
		if (node != null) {
			if (level == str.length()) {
				if (node.isEnd) {
					node.isEnd = false;
					if (node.children.isEmpty()) {
						return true;
					}
					return false;
				}
			} else {
				if (removeUtil(node.children.get(str.charAt(level)), str, level + 1)) {
					node.children.remove(str.charAt(level));
					return (!node.isEnd && node.children.isEmpty());
				}
			}
		}
		return false;
	}

	/* remove all elements from the trie */
	@Override
	public void clear() {
		root.children.clear();
		size = 0;
	}

	/* remove an element from the trie */
	@Override
	public void remove(String str) {
		if (this.contains(str)) {
			removeUtil(root, str, 0);
			size--;
		}
	}

	@Override
	public int size() {
		return size;
	}

}