package pl.abof.test_template.arquillian.wf.cdi;

import java.util.ArrayList;
import java.util.List;

public class Value {
	
	private int value = 0;
	private List<String> processingHistory = new ArrayList<>();

	public Value(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}

	public List<String> getProcessingHistory() {
		return processingHistory;
	}

	public void addProcessingHistoryEntry(String processingName) {
		this.processingHistory.add(processingName);
	}
}
