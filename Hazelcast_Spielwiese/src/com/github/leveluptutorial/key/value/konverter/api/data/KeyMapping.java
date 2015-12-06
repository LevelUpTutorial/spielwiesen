/**
 * 
 */
package com.github.leveluptutorial.key.value.konverter.api.data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JAN
 *
 */
public class KeyMapping {
	protected Set<String> inputKeys = new HashSet<String>();
	protected Set<String> outputKeys = new HashSet<String>();
	
	protected KeyMapping(){};
	public KeyMapping(Set<String> inputKeys, Set<String> outputKeys) {
		this.inputKeys = inputKeys;
		this.outputKeys = outputKeys;
	}

	public Set<String> getInputKeys() {
		return inputKeys;
	}

	public void setInputKeys(Set<String> inputKeys) {
		this.inputKeys = inputKeys;
	}

	public Set<String> getOutputKeys() {
		return outputKeys;
	}

	public void setOutputKeys(Set<String> outputKeys) {
		this.outputKeys = outputKeys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputKeys == null) ? 0 : inputKeys.hashCode());
		result = prime * result + ((outputKeys == null) ? 0 : outputKeys.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof KeyMapping)) {
			return false;
		}
		KeyMapping other = (KeyMapping) obj;
		if (inputKeys == null) {
			if (other.inputKeys != null) {
				return false;
			}
		} else if (!inputKeys.equals(other.inputKeys)) {
			return false;
		}
		if (outputKeys == null) {
			if (other.outputKeys != null) {
				return false;
			}
		} else if (!outputKeys.equals(other.outputKeys)) {
			return false;
		}
		return true;
	}
	
}
