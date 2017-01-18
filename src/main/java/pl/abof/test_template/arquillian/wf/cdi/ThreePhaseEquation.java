package pl.abof.test_template.arquillian.wf.cdi;


import pl.abof.test_template.arquillian.wf.cdi.operation.Operation;

public class ThreePhaseEquation {

	private Operation firstOperation;
	private Operation secondOperation;
	private Operation thirdOperation;

	public ThreePhaseEquation(Operation firstOperation, Operation secondOperation, Operation thirdOperation) {
		super();
		this.firstOperation = firstOperation;
		this.secondOperation = secondOperation;
		this.thirdOperation = thirdOperation;
	}

	public int perform(int initialValue) {
		Value value = new Value(initialValue);
		
		firstOperation.perform(value);
		secondOperation.perform(value);
		thirdOperation.perform(value);
		
		return value.get();
	}

}
