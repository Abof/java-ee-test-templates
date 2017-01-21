package pl.abof.test_template.arquillian.wf.cdi.operation;

import javax.ejb.Local;

import pl.abof.test_template.arquillian.wf.cdi.Value;

@Local
public interface Operation{
	void perform(Value v);
	
	String getName();
}
