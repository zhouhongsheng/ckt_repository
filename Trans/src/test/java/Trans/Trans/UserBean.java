package Trans.Trans;

import java.util.List;

public class UserBean {

	private String objectid;
	private String nickname;
	private String location;
	private String phone;
	private String sioeyeid;
	private String hobbys;
	private String gender;
	private String description;
	private String userid;
	private String createdat;
	private String avatar;
	private String bgimage;
	private List<String> levels;
	private boolean sioeyeidchanged;
	
	
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSioeyeid() {
		return sioeyeid;
	}
	public void setSioeyeid(String sioeyeid) {
		this.sioeyeid = sioeyeid;
	}
	public String getHobbys() {
		return hobbys;
	}
	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getBgimage() {
		return bgimage;
	}
	public void setBgimage(String bgimage) {
		this.bgimage = bgimage;
	}
	public List<String> getLevels() {
		return levels;
	}
	public void setLevels(List<String> levels) {
		this.levels = levels;
	}
	public boolean isSioeyeidchanged() {
		return sioeyeidchanged;
	}
	public void setSioeyeidchanged(boolean sioeyeidchanged) {
		this.sioeyeidchanged = sioeyeidchanged;
	}
	
}
