package trie;

import java.util.List;
import java.util.Set;

public interface TrieInterface {
	/**
	 * Adds a string to the trie
	 * 
	 * @param str string to be added
	 */
	void add(String str);

	/**
	 * Return true if and only if the trie contains the given string
	 * 
	 * @param str string that is tested for containment
	 * @return true if the trie contains str
	 */
	boolean contains(String str);

	/**
	 * Return a list of all the strings in the trie that start with the given prefix
	 * If there are no such strings returns an empty list
	 * 
	 * @param prefix the prefix of the strings
	 * @return list of all the strings in the trie that start with the given string
	 * 
	 */
	List<String> prefixedWords(String prefix);

	/**
	 * Returns all string that match the given pattern where '?' can be any
	 * character 'a'-'z' Example: "bare", "bake", "bale", and "base" all match ba?e,
	 * since ? can be any lower-case English letter
	 * 
	 * @param pattern the pattern to match
	 * @return a set of all strings matching the pattern
	 */
	Set<String> wildcardMatches(String pattern);

	/**
	 * Removes all words from the trie and resets the size
	 */
	void clear();

	/**
	 * @return the number of unique words in the trie
	 */
	int size();

	/**
	 * Removes the given string from the trie
	 * 
	 * @param str string to remove
	 */
	void remove(String str);

}
