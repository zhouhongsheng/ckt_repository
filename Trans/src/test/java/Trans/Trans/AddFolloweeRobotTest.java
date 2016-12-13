package Trans.Trans;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import Trans.Trans.config.AppServerConfig;
import Trans.Trans.util.UtilTool;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = App.class)  
public class AddFolloweeRobotTest {
	
	
	@Autowired
	AppServerConfig config;

	
	@Test
	public void addFolloweeRobot() throws Exception {
		List<SimpleUserBean> list=new ArrayList<SimpleUserBean>();
		
		
		InputStream is = new FileInputStream("D:\\files\\followeerobot_prod.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(is));
		// 循环工作表Sheet
		XSSFSheet hssfSheet = wb.getSheetAt(0);
		if (hssfSheet == null) {
			return ;
		}
		XSSFRow hssfRow=null;
		XSSFCell idCell;
		XSSFCell sessiontokenCell;
		String objectid="";
		String sessiontoken="";
		// 循环行Row
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			SimpleUserBean simpleUserBean=new SimpleUserBean();
			hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				idCell= hssfRow.getCell(0);
				if (idCell != null) {
					idCell.setCellType(Cell.CELL_TYPE_STRING);
					objectid=String.valueOf(idCell.getStringCellValue());
				}
				sessiontokenCell= hssfRow.getCell(1);
				if (sessiontokenCell != null) {
					sessiontokenCell.setCellType(Cell.CELL_TYPE_STRING);
					sessiontoken=String.valueOf(sessiontokenCell.getStringCellValue());
				}
				simpleUserBean.setAppUserId(objectid);
				simpleUserBean.setSessionToken(sessiontoken);
				
				list.add(simpleUserBean);
			}
		}
		followeeRobot(list);
	}
	private boolean followeeRobot(List<SimpleUserBean> list){
		boolean result=false;
		
		String followeeids="";
		
		List<SimpleUserBean> exist=new ArrayList<>();
		int i=103;
		for (SimpleUserBean simpleUserBean : list) {
			
			exist.add(simpleUserBean);
			
			followeeids=getFolloweeRobots(list,exist);
			
			//调用接口
			String success=config.followeeRobot(simpleUserBean.getSessionToken(),followeeids);
			System.out.println(simpleUserBean.getAppUserId()+","+i);
			if(success!=null&&!"".equals(success)&&success.equals("true")){
				i++;
				continue;
			}else{
				break;
			}
		}
		return result;
	}
	private String getFolloweeRobots(List<SimpleUserBean> all,List<SimpleUserBean> exist){
		//自己
		SimpleUserBean selfSimpleUserBean=exist.get(0);
		
		
		int needFolloweeNum=UtilTool.getRandomNumByRange(5,10);
		
		
		SimpleUserBean simpleUserBean;
		
		for (int i = 0; i < needFolloweeNum;) {
			
			int randomNum=UtilTool.getRandomNumByRange(0,all.size());
			
			simpleUserBean=all.get(randomNum);
			if(exist.contains(simpleUserBean)){
				continue;
			}else{
				exist.add(simpleUserBean);
				i++;
			}
			
		}
		
		
		//去掉自己
		exist.remove(selfSimpleUserBean);
		
		StringBuffer sb=new StringBuffer();
		for (SimpleUserBean simpleUserBean2 : exist) {
			sb.append(simpleUserBean2.getAppUserId());
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		
		
		exist.clear();
		return sb.toString();
	}
}
