package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import trie.Trie;

public class TrieTest {

	/**
	 * @param trie
	 *            a trie structure
	 * @return a set of all the words contained in the trie
	 */
	private static Set<String> getAll(Trie trie) {
		return new HashSet<>(trie.prefixedWords(""));
	}

	static final Trie trie = new Trie();
	static {
		trie.add("cat");
		trie.add("cape");
		trie.add("bear");
		trie.add("core");
	}

	@Test
	public void containsTest() {
		assertTrue(trie.contains("bear"));
		assertTrue(trie.contains("core"));
		assertTrue(trie.contains("cape"));
		assertTrue(trie.contains("cat"));
	}

	@Test
	public void wildcardTest1() {
		Set<String> expected = new HashSet<>();
		expected.add("cat");
		assertEquals(expected, new HashSet<Object>(trie.wildcardMatches("ca?")));
	}

	@Test
	public void wildcardTest2() {
		Set<String> expected = new HashSet<>();
		expected.add("cat");
		assertEquals(expected, new HashSet<Object>(trie.wildcardMatches("?a?")));
	}

	@Test
	public void wildcardTest3() {
		Set<String> expected = new HashSet<>();
		expected.add("cape");
		expected.add("core");
		assertEquals(expected, new HashSet<Object>(trie.wildcardMatches("c??e")));
	}

	@Test
	public void wildcardTest4() {
		Set<String> expected = new HashSet<>();
		expected.add("cape");
		expected.add("core");
		expected.add("bear");
		assertEquals(expected, new HashSet<Object>(trie.wildcardMatches("????")));
	}

	@Test
	public void prefixedWordsTest1() {
		Set<String> expected = new HashSet<>();
		expected.add("cat");
		expected.add("cape");
		expected.add("core");
		assertEquals(expected, new HashSet<Object>(trie.prefixedWords("c")));
	}

	@Test
	public void prefixedWordsTest2() {
		Set<String> expected = new HashSet<>();
		expected.add("bear");
		assertEquals(expected, new HashSet<Object>(trie.prefixedWords("b")));
	}

	@Test
	public void prefixedWordsTest3() {
		Set<String> expected = new HashSet<>();
		assertEquals(expected, new HashSet<Object>(trie.prefixedWords("bbb")));
	}

	@Test
	public void prefixedWordsTest4() {
		Set<String> expected = new HashSet<>();
		expected.add("cape");
		expected.add("cat");
		assertEquals(expected, new HashSet<Object>(trie.prefixedWords("ca")));
	}

	@Test
	public void removeTest() {
		Trie trie = new Trie(new String[] { "cat", "cape", "bear", "core" });
		Set<String> expected = new HashSet<>();
		trie.remove("core");

		expected.add("cape");
		expected.add("cat");
		expected.add("bear");
		assertEquals(expected, (getAll(trie)));

		trie.remove("cat");
		expected.remove("cat");
		assertEquals(expected, (getAll(trie)));

		trie.add("cactus");
		expected.add("cactus");
		assertEquals(expected, (getAll(trie)));

		final String NOT_IN_TRIE = "asdfgh";
		trie.remove(NOT_IN_TRIE);
		assertEquals(expected, (getAll(trie)));
	}

	@Test
	public void sizeTest() {
		Trie trie = new Trie();
		assertTrue(trie.size() == 0);
		trie.add("core");
		trie.add("core");
		assertTrue(trie.size() == 1);
		trie.add("bar");
		assertTrue(trie.size() == 2);
		trie.remove("bar");
		assertTrue(trie.size() == 1);
		trie.remove("core");
		assertTrue(trie.size() == 0);
	}

	@Test
	public void clearTest() {
		Set<String> emptySet = new HashSet<>();
		Trie trie = new Trie();
		trie.add("core");
		trie.add("ab");
		trie.add("nc");
		trie.clear();
		assertEquals(emptySet, getAll(trie));
		assertTrue(trie.size() == 0);
	}
}