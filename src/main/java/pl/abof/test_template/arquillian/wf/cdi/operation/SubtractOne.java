package pl.abof.test_template.arquillian.wf.cdi.operation;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import pl.abof.test_template.arquillian.wf.cdi.Value;

@Named("SubtractOne")
public class SubtractOne implements Operation {

	@Override
	public void perform(Value v) {
		v.set(v.get() - 1);
	}
	
}
