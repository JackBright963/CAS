
public class UserInfo {
	private String userID;
    private String username;
    private String surname;
    private String housenum;
    private String postcode;
    private String city;
    private String role;
    
    public UserInfo(String userLine) {
    	String[] splitUser = userLine.split(", ");
    	userID = splitUser[0];
    	username = splitUser[1];
    	surname = splitUser[2];
    	housenum = splitUser[3];
    	postcode = splitUser[4];
    	city = splitUser[5];
    	role = splitUser[6];   	
    }
    
    // add methods to get user ID and user postcode
    
    public String getID() {
    	return userID;
    }
    
    public String getPostcode() {
    	return postcode;
    }
    
    public boolean roleEquals(String userRole) {
    	if (role.equals(userRole)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean usernameEquals(String userName) {
    	if (username.equals(userName)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
