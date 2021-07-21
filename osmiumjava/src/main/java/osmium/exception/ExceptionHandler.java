package osmium.exception;

import java.util.HashMap;

public class ExceptionHandler {

	private final static HashMap<String, Integer> exceptionTypeMap = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1964991243665460024L;
		{
			put("osmium.exception.exceptionTypes.EmptyDirectoryException", 2);
			put("osmium.exception.exceptionTypes.InvalidDirectoryException", 3);
			put("Unsupported", 4);
		}
	};

	public static int[] returnSuccess(int[] array) {
		return new int[] { 0, array[0], array[1] };
	}

	public static int[] returnFailure() {
		return new int[] { 1, 0, 0 };
	}

	public static int[] returnException(Exception e) {
		Integer v;
		int exceptionType = (v = exceptionTypeMap.get(e.getClass().getCanonicalName())) == null
				? exceptionTypeMap.get("Unsupported")
				: v.intValue();

		return new int[] { exceptionType, 0, 0 };
	}
}
