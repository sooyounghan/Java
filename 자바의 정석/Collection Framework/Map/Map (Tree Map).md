-----
### TreeMap
-----
1. 이진 검색 트리의 형태로 키와 값의 쌍으로 이루어진 데이터를 저장하므로 검색과 정렬에 적합한 컬렉션 클래스
2. 검색에 관련된 대부분의 경우에서 HashMap이 TreeMap보다 더 뛰어나므로 HashMap을 사용하는 것이 좋음 (다만, 범위검색이나 정렬이 필요한 경우에는 TreeMap 사용이 더 좋음)
3. TreeMap 생성자와 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/4868ee36-5087-41ad-b171-a5775cd89bcc">
<img src="https://github.com/sooyounghan/Java/assets/34672301/442ac307-59fe-4349-85e7-ee467e5e8229">  
</div>

```java
import java.util.*;

class TreeMapEx1 {
	public static void main(String[] args) {
		String[] data = { "A","K","A","K","D","K","A","K","K","K","Z","D" };

		TreeMap map = new TreeMap();

		for(int i=0; i < data.length; i++) {
			if(map.containsKey(data[i])) {
				Integer value = (Integer)map.get(data[i]);
				map.put(data[i], new Integer(value.intValue() + 1));
			} else {
				map.put(data[i], new Integer(1));			
			}
		}

		Iterator it = map.entrySet().iterator();

		System.out.println("= 기본 정렬 =");
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			int value = ((Integer)entry.getValue()).intValue();
			System.out.println(entry.getKey() + " : " + printBar('#', value) + " " + value );
		}
		System.out.println();

		// map을 ArrayList로 변환한 다음에 Collectons.sort()로 정렬
		Set set = map.entrySet();
		List list = new ArrayList(set);	// ArrayList(Collection c) 
		
		// static void sort(List list, Comparator c)  
		Collections.sort(list, new ValueComparator());

		it = list.iterator();

		System.out.println("= 값의 크기가 큰 순서로 정렬 =");		
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			int value = ((Integer)entry.getValue()).intValue();
			System.out.println(entry.getKey() + " : " + printBar('#', value) + " " + value );
		}

	} // 	public static void main(String[] args) 

	static class ValueComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			if(o1 instanceof Map.Entry && o2 instanceof Map.Entry) {
				Map.Entry e1 = (Map.Entry)o1;
				Map.Entry e2 = (Map.Entry)o2;

				int v1 = ((Integer)e1.getValue()).intValue();
				int v2 = ((Integer)e2.getValue()).intValue();

				return  v2 - v1; // 내림차순 정렬
			} 
			return -1;
		}
	}	// 	static class ValueComparator implements Comparator {

	public static String printBar(char ch, int value) { 
		char[] bar = new char[value]; 

		for(int i=0; i < bar.length; i++) { 
			bar[i] = ch; 
		} 

		return new String(bar); 
	} 
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/d29050e4-1d66-4f7e-9078-012131459741">
</div>

  - 키가 오름차순으로 정렬 : 키가 String 인스턴스이므로 String에 정의된 정렬 기준으로 정렬된 것
  - Comparator를 구현한 클래스와 Collection.sort(List list, Comparator c)를 이용해서 값에 대한 내림차순으로 정렬하는 방법 보여줌
