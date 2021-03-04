package trie;

public class Example {

	public static void main(String[] args) {
		Trie t = new Trie(new String[] { "cafe", "coffee", "caffe", "cup", "java", "kaffe" });

		System.out.println(t.wildcardMatches("c*"));
		// [cafe, caffe, cup, coffee]
		System.out.println(t.wildcardMatches("c*e*"));
		// [cafe, caffe, coffee]
		System.out.println(t.wildcardMatches("?affe"));
		// [caffe, kaffe]
	}

}
