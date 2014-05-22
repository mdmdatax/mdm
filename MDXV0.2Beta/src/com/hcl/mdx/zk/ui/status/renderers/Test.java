package com.hcl.mdx.zk.ui.status.renderers;

public class Test {

	public int ari(int a){ 
		System.out.println(a);
		if(a == 1) return a;
		else{ 
			/*int b =1;
			for(int i = a; i > 0; i--){
				b = b *i;
				System.out.println(b);
			}*/
			return a* ari(a-1);
		}
	}
	public static void main(String[] args){
		Test test = new Test();
		System.out.println(test.ari(5));
	}
}
