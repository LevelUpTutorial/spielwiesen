/**
 * 
 */
package com.github.leveluptutorial.key.value.konverter.api.data;

/**
 * @author JAN
 *
 */
public class SimpleKeyMapping extends KeyMapping {

	public SimpleKeyMapping(String inputKey, String outputKey) {
		this.inputKeys.add(inputKey);
		this.outputKeys.add(outputKey);
	}
	
	public String getInputKey() {
		return inputKeys.iterator().next();
	}
	
	public String getOutputKey() {
		return outputKeys.iterator().next();
	}
}
