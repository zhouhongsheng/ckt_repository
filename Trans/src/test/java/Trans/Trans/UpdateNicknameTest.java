package Trans.Trans;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;

import Trans.Trans.config.AppServerConfig;


@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = App.class)  

public class UpdateNicknameTest {
	
	@Autowired
	AppServerConfig config;
	
    @Test  
    public void watchStart() throws Exception {
    	
    	XSSFWorkbook wbwriter=new XSSFWorkbook();
		XSSFSheet sheet= wbwriter.createSheet("sheet");
		OutputStream stream=new FileOutputStream("D:\\files\\avatar_dev.xlsx");  ;
    	try{
    	InputStream is = new FileInputStream("D:\\files\\nicknameandobjectid_dev.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(is));
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
			XSSFSheet hssfSheet = wb.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			XSSFRow hssfRow=null;
			XSSFCell idCell;
			XSSFCell sessiontokenCell;
			XSSFCell nicknameCell;
			XSSFCell avatarCell;
			String objectid="";
			String sessiontoken="";
			String nickname="";
			String avatar="";
			AppUserBean appUserBean=null;
			StringBuffer sb=new StringBuffer();
			
			
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
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
					nicknameCell = hssfRow.getCell(2);
					if (nicknameCell != null) {
						nicknameCell.setCellType(Cell.CELL_TYPE_STRING);
						nickname=String.valueOf(nicknameCell.getStringCellValue());
					}
					avatarCell= hssfRow.getCell(3);
					if (avatarCell != null) {
						avatarCell.setCellType(Cell.CELL_TYPE_STRING);
						avatar=String.valueOf(avatarCell.getStringCellValue());
					}
					
					System.out.println(avatar);
					appUserBean = config.updateAvatar(sessiontoken,avatar);
					
					sb.append("update robot set nickname='");
					sb.append(nickname+"',avatar='");
					sb.append(appUserBean.getValue().getAvatar());
					sb.append("' where app_user_id='");
					sb.append(objectid);
					sb.append("';");
					
					writeAvatar(sheet,sb.toString(),rowNum);
					
					sb.delete(0,sb.length());
					appUserBean = config.updateNickname(nickname,sessiontoken);
					
					Assert.assertTrue("true".equalsIgnoreCase(appUserBean.getSuccess()));
				}
			}
		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally {
    		 //写入数据  
    		wbwriter.write(stream); 
    		  //关闭文件流  
	        stream.close(); 
		}
    }
    private void writeAvatar(XSSFSheet sheet1,String val,int i){
       
        //循环写入行数据  
        XSSFRow row =sheet1.createRow(i);  
        //循环写入列数据  
        Cell cellObjectid = row.createCell(0);  
        cellObjectid.setCellValue(val);
            
    }
}  