package tran.tran.intf;

import java.util.List;
import tran.tran.model.Transition;
import tran.tran.model.dto.TransitionDataDto;

public interface ITransition {

	public String createTransition(TransitionDataDto transitionDataDto);
	
	public String updateTransition(TransitionDataDto transitionDataDto);
	
	public List<Transition> queryTransitions(String userId);
	
}
