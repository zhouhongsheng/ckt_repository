package tran.tran.dao;

import java.util.List;

import tran.tran.model.Transition;

public interface ITransitionDao {

	public List<Transition> queryTransitionsByUsername(String userName);
	
	public void insertOrUpdate(Transition transition);
}
