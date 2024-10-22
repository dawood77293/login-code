package dbaccessinjava;
import java.sql.*;


public class DBAccessInJava{

                public static void main(String[] argv) {

                                System.out.println("-------- Oracle JDBC Connection Testing ------");
                Connection connection = null;
                
                                try {
                        Statement s=null;
                        ResultSet rs=null;
                                                Class.forName("oracle.jdbc.driver.OracleDriver");
                        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "dawood","Dnay123123");

                                

                                System.out.println("Oracle JDBC Driver Registered!");
                
            String grade="B+";
            String ID="620014";
            String fName="Tim";
            String lName="Anderson";
            String retrieveStatement = "Select * from STUDENT";
            s = connection.createStatement();
            rs = s.executeQuery (retrieveStatement);
            ResultSetMetaData RM = rs.getMetaData();
            showResult(RM,rs);

            String updateStatement = "update Student set Grade ='" + grade + "'";
            

            System.out.println("B+ to all");
            System.out.println(updateStatement);
            int count = s.executeUpdate(updateStatement);
            System.out.println("Number of students affected=" + count);
            String deleteStatement = "delete from Student where ID='" + ID +"'";
            System.out.println(deleteStatement);
            System.out.println("Deleting 620013");
            s.execute(deleteStatement);
            grade="C+";
            if (validateID(ID,rs))
System.out.println("Valid ID");
else
 	System.out.println("Invalid ID"); 

String insertStatement = "insert into Student values('" + ID + "','" + fName + "','" + lName + "','" + grade + "')";
            
System.out.println(insertStatement);
System.out.println("Inserting 620013");
s.execute(insertStatement);
            
retrieveStatement = "Select * from STUDENT";
            
rs = s.executeQuery (retrieveStatement);
           
if (validateID(ID,rs))
System.out.println("Valid ID");
else
 	System.out.println("Invalid ID"); 

            
            
            
            rs = s.executeQuery (retrieveStatement);
           
            showResult(RM,rs);
            rs.close();
            s.close();
            connection.close();
            
                                } 
                
                catch (Exception e) {

                                                System.out.println("Connection Failed! Check output console");
                                                e.printStackTrace();
                                                return;

                                }
                
                
        }
        static String leftText( String s , int newLen ) 
    {  
        while ( s.length() < newLen )  
        {  
            s = s+" ";  
        }  
        return s ;  
    }  
        static boolean validateID(String id, ResultSet rs) throws SQLException {

    // Iterate through the ResultSet to check all student IDs
    while (rs.next()) {

      
        String currentID = rs.getString("ID");

        
        if (currentID.equals(id)) {
            return true; 
        }
    }

   
    return false;
}

                static void showResult( ResultSetMetaData RM , ResultSet rs ) 
    {  
try {
        for(int i = 1; i <= RM.getColumnCount(); i++) {
System.out.print(RM.getColumnName(i));
for (int j = 0; j < 20 - RM.getColumnName(i).length(); j++)
System.out.print(" ");
}
        int count = 0;
            System.out.println();
            System.out.println("=====================================================================================");
            while ( rs.next ()){
                count++;
                String nextRec = "";
                
                for ( int x = 1; x <= RM.getColumnCount();  x++ )
                {
                    nextRec += leftText(rs.getString ( x ), 20);
                }
                System.out.println( nextRec );
}
            System.out.println("There are " + count + " students in the result");
}
        catch (Exception e) {

                                                System.out.println("Connection Failed! Check output console");
                                                e.printStackTrace();
                                                return;

                                }
}

}
