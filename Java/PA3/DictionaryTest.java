/* Samer Baslan
 * sbaslan
 * CMPS 12B
 * 07/23/2017
 * DictionaryTest (Test client for Dictionary ADT)
 * PA3
 */


class DictionaryTest{
	public static void main(String[] args){
		Dictionary A = new Dictionary();
		//System.out.println(A.isEmpty());
		A.insert("2", "c");
		//System.out.println(A);
		A.insert("1", "a");
		A.insert("3", "b");
		A.insert("5", "d");
		//System.out.println(A);
		//A.delete("5", "d"); exception
		A.delete("5");
		//System.out.println(A);
		//System.out.println(A.size());
		//System.out.println(A.isEmpty());
		//System.out.println(A.size());
		//A.insert("6"); exception 
		//A.insert("2", "e"); duplicate key exception
		//System.out.println(A);
		A.makeEmpty();
		A.insert("1", "a");
		A.insert("2", "b");
		A.insert("3", "c");
		A.insert("4", "c");
		//System.out.println(A);
		//System.out.println(A.lookup("3"));
		//System.out.println(A.findKey("2"));
		System.out.println(A.size());
		System.out.println(A.toString());

	}

}
