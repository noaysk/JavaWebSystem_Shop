package ken.bean;
import java.io.Serializable;

public class User implements Serializable{
	private int userID;						// ユーザID
	private String userName;			// 名前
	private String userName_kana;	// 名前フリガナ
	private String postCode;				// 郵便番号
	private String address;				// 住所 県名
	private String phoneNumber;		// 電話番号
	private String mailAddress;			//メールアドレス

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName_kana() {
		return userName_kana;
	}

	public void setUserName_kana(String userName_kana) {
		this.userName_kana = userName_kana;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName
				+ ", userName_kana=" + userName_kana + ", postCode=" + postCode
				+ ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", mailAddress=" + mailAddress + "]";
	}
}