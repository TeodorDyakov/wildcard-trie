package trie;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		Trie t = new Trie();
		t.add("tedo");
		t.add("tedi");
		t.add("tedy");
		t.add("tede");
		t.add("tedkish");
		List<String> str = t.wildcardMatches("*d?");
		System.out.println(str);
	}

}
