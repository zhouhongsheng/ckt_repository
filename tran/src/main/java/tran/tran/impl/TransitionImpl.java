package tran.tran.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tran.tran.dao.IPreTargetMappingDao;
import tran.tran.dao.ITransitionDao;
import tran.tran.intf.IDataBase;
import tran.tran.intf.IDataSource;
import tran.tran.intf.ITransition;
import tran.tran.model.DataBase;
import tran.tran.model.DataSource;
import tran.tran.model.PreTargetMapping;
import tran.tran.model.Transition;
import tran.tran.model.dto.DataBaseDto;
import tran.tran.model.dto.DataSourceDto;
import tran.tran.model.dto.TransitionDataDto;

@Service
public class TransitionImpl implements ITransition {

	@Autowired
	private ITransitionDao iTransitionDao;
	@Autowired
	private IDataBase iDataBase;
	@Autowired
	private IDataSource iDataSource;
	@Autowired
	private IPreTargetMappingDao iPreTargetMappingDao;
	
	private String transition(TransitionDataDto transitionDataDto) {
		// 判断转换是否存在，存在更新，不存在返回
		String result = "";
		switch (transitionDataDto.getOperateType()) {
		case 1:
			result = insert(transitionDataDto);
			break;
		case 2:
			result = update(transitionDataDto);
		case 3:
			result = insertOrUpdate(transitionDataDto);
		default:
			break;
		}
		return result;
	}

	/*
	 * insert
	 * 
	 * @param tranData
	 * 
	 * @return String TODO
	 */
	private String insert(TransitionDataDto transitionDataDto) {
		String resultString = "";
//		//
//		List<String> tranArray = transitionDataDto.getTranArray();
//		List<String> prelist = new ArrayList<>();
//		List<String> targetlist = new ArrayList<>();
//		String[] array = new String[2];
//		for (String string : tranArray) {
//			array = string.split("=");
//			prelist.add(array[0]);
//			targetlist.add(array[1]);
//		}
//		StringBuffer queryPreColumns = new StringBuffer();
//		UtilTool.getQueryColumns(queryPreColumns, prelist);
//		// 获取源数据库连接
//		org.apache.tomcat.jdbc.pool.DataSource preDataSource = UtilTool.dataBase(transitionDataDto.getPreDataSource().getDataBase());
//		try {
//			Connection preconn = preDataSource.getConnection();
//			String sql = "select " + queryPreColumns.toString() + " from " + tranData.getPreTableName();
//			PreparedStatement ps = preconn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//
//			StringBuffer queryTargetColumns = new StringBuffer();
//			UtilTool.getQueryColumns(queryTargetColumns, targetlist);
//
//			// 获取目标数据库连接
//			DataSource targetDataSource = UtilTool.dataSource(tranData.getTargetDataSource());
//			Connection targetconn = targetDataSource.getConnection();
//
//			List<String> result = null;
//			StringBuffer resultsb = new StringBuffer();
//			while (rs.next()) {
//				result = new ArrayList<>();
//				String sql_target = "insert into " + tranData.getTargetTableName() + "(" + queryTargetColumns
//						+ ") values(";
//				for (String string : prelist) {
//
//					result.add(rs.getString(string));
//				}
//				UtilTool.getQueryContext(resultsb, result);
//
//				sql_target += resultsb.toString() + ")";
//				PreparedStatement psResult = targetconn.prepareStatement(sql_target);
//				psResult.execute();
//				resultsb.delete(0, resultsb.length());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			resultString = e.toString();
//		}
		return resultString;
	}

	private String update(TransitionDataDto transitionDataDto) {
		return "";
	}

	private String insertOrUpdate(TransitionDataDto transitionDataDto) {
		return "";
	}

	/*
	 * 
	 * @return
	 * 
	 * @see tran.tran.intf.TranIntf#queryTransitions() 2017年1月19日
	 */
	@Override
	public List<Transition> queryTransitions(String userId) {

		return iTransitionDao.queryTransitionsByUsername(userId);
	}

	/*
	 * create transition
	 * 
	 * @param transitionDataDto
	 * 
	 * @return
	 * 
	 * @see
	 * tran.tran.intf.TranIntf#createTran(tran.tran.model.dto.TransitionDataDto)
	 * 2017年1月22日
	 */
	@Override
	@Transactional
	public String createTransition(TransitionDataDto transitionDataDto) {
		String result = "";
		try {
			DataSourceDto preDataSourceDto = transitionDataDto.getPreDataSourceDto();
			DataBaseDto preDataBaseDto = preDataSourceDto.getDataBaseDto();
			// 保存源数据源
			DataBase preDataBase=new DataBase(preDataBaseDto);
			iDataBase.insertDataBase(preDataBase);
			DataSource preDataSource=new DataSource(preDataSourceDto);
			iDataSource.insertDataSource(preDataSource);

			DataSourceDto targetDataSourceDto=transitionDataDto.getTargetDataSourceDto();
			DataBaseDto targetDataBaseDto=targetDataSourceDto.getDataBaseDto();
			// 保存目标数据源
			DataBase targetDataBase=new DataBase(targetDataBaseDto);
			iDataBase.insertDataBase(targetDataBase);
			DataSource targetDataSource=new DataSource(targetDataSourceDto);
			iDataSource.insertDataSource(targetDataSource);
			//保存transition
			Transition transition=new Transition();
			transition.setTranName(transitionDataDto.getTranName());
			transition.setUserId(transitionDataDto.getUserId());
			transition.setOperateType(transitionDataDto.getOperateType());
			transition.setPreDataSourceId(transitionDataDto.getPreDataSourceDto().getId());
			transition.setTargetDataSourceId(transitionDataDto.getTargetDataSourceDto().getId());
			iTransitionDao.insertOrUpdate(transition);
			//保存mapping
			List<String> tranArrayList=transitionDataDto.getTranArray();
			for (String string : tranArrayList) {
				PreTargetMapping preTargetMappingDto=new PreTargetMapping();
				preTargetMappingDto.setType(1);
				preTargetMappingDto.setTransitionId(transition.getId());
				preTargetMappingDto.setPreColumnName(string.split("=")[0]);
				preTargetMappingDto.setTargetColumnName(string.split("=")[1]);
				iPreTargetMappingDao.insert(preTargetMappingDto);
			}
			result=transition.getId();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}

	/*
	 * update transition
	 * @param transitionDataDto
	 * @return 
	 * @see tran.tran.intf.ITransition#updateTran(tran.tran.model.dto.TransitionDataDto)
	 * 2017年2月4日
	 */
	@Override
	@Transactional
	public String updateTransition(TransitionDataDto transitionDataDto) {
		String result = "";
		try{
		DataSourceDto preDataSourceDto = transitionDataDto.getPreDataSourceDto();
		DataBaseDto preDataBaseDto = preDataSourceDto.getDataBaseDto();
		// 保存源数据源
		DataBase preDataBase=new DataBase(preDataBaseDto);
		iDataBase.updateDataBase(preDataBase);
		DataSource preDataSource=new DataSource(preDataSourceDto);
		iDataSource.updateDataSource(preDataSource);

		DataSourceDto targetDataSourceDto=transitionDataDto.getTargetDataSourceDto();
		DataBaseDto targetDataBaseDto=targetDataSourceDto.getDataBaseDto();
		// 保存目标数据源
		DataBase targetDataBase=new DataBase(targetDataBaseDto);
		iDataBase.updateDataBase(targetDataBase);
		DataSource targetDataSource=new DataSource(targetDataSourceDto);
		iDataSource.updateDataSource(targetDataSource);
		//保存transition
		Transition transition=new Transition();
		transition.setTranName(transitionDataDto.getTranName());
		transition.setUserId(transitionDataDto.getUserId());
		transition.setOperateType(transitionDataDto.getOperateType());
		transition.setPreDataSourceId(transitionDataDto.getPreDataSourceDto().getId());
		transition.setTargetDataSourceId(transitionDataDto.getTargetDataSourceDto().getId());
		iTransitionDao.insertOrUpdate(transition);
		//保存mapping
		List<String> tranArrayList=transitionDataDto.getTranArray();
		for (String string : tranArrayList) {
			PreTargetMapping preTargetMappingDto=new PreTargetMapping();
			preTargetMappingDto.setType(1);
			preTargetMappingDto.setTransitionId(transition.getId());
			preTargetMappingDto.setPreColumnName(string.split("=")[0]);
			preTargetMappingDto.setTargetColumnName(string.split("=")[1]);
			iPreTargetMappingDao.update(preTargetMappingDto);
		}
		result=transition.getId();
		
		//transition
		transition(transitionDataDto);
		
		}catch(Exception e){
			e.printStackTrace();
			result=e.toString();
		}
		return result;
	}
}
