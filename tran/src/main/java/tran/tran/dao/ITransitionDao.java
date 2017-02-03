package tran.tran.dao;

import java.util.List;

import tran.tran.model.Transition;

public interface ITransitionDao {

	public List<Transition> queryTransitionsByUsername(String username);
	
	public void insertOrUpdate(Transition transition);
}
