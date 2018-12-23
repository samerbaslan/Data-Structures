//Samer Baslan
//sbaslan
//CMPS 12M
//08-13-2017
//ListTest.java
//Test Client for List.java

public class ListTest{
	public static void main(String [] args){
		
		List <Integer> A = new List <Integer>();
		List <String> S = new List <String>();
		List <String> B = new List <String>();
		List<List<String>> Z = new List<List<String>>();


		int i, j, k;
		
		S.add(1, "one");
		S.add(2, "two");
		S.add(3, "three");
		S.add(4, "four");
		S.add(5, "five");
		//System.out.println(S);
		//System.out.println(S.size());
		
		A.add(1, 0);
		A.add(2,9);
		A.add(3,8);
		A.add(4,7);
		A.add(5,6);
		//System.out.println(A);
		//System.out.println(A.size());
		
		Z.add(1, S);
		Z.add(2, S);
		Z.add(3, S);
		Z.add(4, S);
		Z.add(5,S);
		//System.out.println(Z);
		//Z.add(2, A); throws error
		//Z.remove(1);
		//System.out.println(Z);
		//System.out.println(S.get(2));
		//System.out.println(S.equals(Z));
		A.removeAll();
		S.removeAll();
		Z.removeAll();
		System.out.println(A.isEmpty());
		System.out.println(S.isEmpty());
		System.out.println(Z.isEmpty());
	}
}
