package pl.abof.test_template.arquillian.wf.cdi.operation;

import pl.abof.test_template.arquillian.wf.cdi.Value;

public interface Operation{
	void perform(Value v);
	
	default String getProcessName() {
		return getClass().getSimpleName();
	}
}
