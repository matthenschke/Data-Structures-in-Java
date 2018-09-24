package dynamic.array.data.structures;


public class DynamicArrayException extends Exception {
	
	//enum represents different types of exceptions
	enum ExceptionType {
		EMPTY_ARRAY, INDEX_OUT_OF_BOUNDS,VALUE_IS_NOT_IN_ARRAY 
	}
	//each message correlates with enum value
	static String [] messages = {"Empty Array", "Index is out of Bounds", 
			"The specified value is not in the array", };
	
	public DynamicArrayException(ExceptionType e) {
		super(messages[e.ordinal()]);
	}

}
