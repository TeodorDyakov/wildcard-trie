# wildcard-trie

A trie that stores strings. It can be quiried using wildcard syntax. currently the '*' and '?' wildcards are supported.

Example use:
 ```
Trie t = new Trie(new String[] { "cafe", "coffee", "caffe", "cup", "java", "kaffe" });

		System.out.println(t.wildcardMatches("c*"));
		// [cafe, caffe, cup, coffee]
		System.out.println(t.wildcardMatches("c*e*"));
		// [cafe, caffe, coffee]
		System.out.println(t.wildcardMatches("?affe"));
		// [caffe, kaffe]
```
