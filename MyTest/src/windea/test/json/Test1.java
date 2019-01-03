package windea.test.json;

import windea.utils.JSONUtils;

public class Test1 {
	public static void main(String[] args) {
//		JSONReader json = new JSONReader(new FileReader("D:\\My Documents\\My Projects\\Java Projects\\Task\\Task_MealOrdering\\MyTest\\src\\windea\\test\\json\\test1.json"));
//		System.out.println(json);
//		System.out.println(json.readString());
//		JSONArray array = new JSONReader(new FileReader("D:\\My Documents\\My Projects\\Java Projects\\Task\\Task_MealOrdering\\MyTest\\src\\windea\\test\\json\\test1.json");
//		array
//		JSONArray array = new JSONArray(new JSONTokener(new FileReader("/test1.json")));
//		System.out.println( array.join("233"));
//		System.out.println(array.toString());
//		System.out.println(array.get(0));
//		System.out.println(array.getString(0));
//		System.out.println(array.opt(0));
//		for(var elem:array){
//			System.out.println(elem);
//		}

//		var json = JSONUtils.of("123",1);
//		System.out.println(json);
		System.out.println(JSONUtils.of("123", 123, "aaa", "aaa"));
		System.out.println(JSONUtils.of2(123));
		System.out.println(JSONUtils.of2(456, "fafefa", 677, 12345.111));
	}
}
