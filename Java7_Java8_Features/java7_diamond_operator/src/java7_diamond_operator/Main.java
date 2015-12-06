package java7_diamond_operator;

import java.util.HashMap;
import java.util.Map;

public class Main {
	/*
	 * Type Inference for Generic Instance Creation - 
	 * You can replace the type arguments required to invoke the constructor of a generic class with an empty set of type parameters (<>) 
	 * as long as the compiler can infer the type arguments from the context. This pair of angle brackets is informally called the diamond.
	 */
	@SuppressWarnings("unused")
	private static final Map<String, Object> map = new HashMap<>();
}
