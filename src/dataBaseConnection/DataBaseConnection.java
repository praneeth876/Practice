package dataBaseConnection;

public class DataBaseConnection {
	public static void main(String[] args)  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println("driver is loaded and registered");
}
}
