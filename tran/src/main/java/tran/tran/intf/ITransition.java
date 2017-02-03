package tran.tran.intf;

import java.util.List;
import tran.tran.model.Transition;
import tran.tran.model.dto.TransitionDataDto;

public interface ITransition {

	public String createTran(TransitionDataDto transitionDataDto);
	
	public String tran(TransitionDataDto transitionDataDto);
	
	public List<Transition> queryTransitions(String username);
	
}
