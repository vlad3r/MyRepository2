/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstjdbc;
import java.sql.*;
import java.io.*;
/**
 *
 * @author Vlad
 */

class DataBase{
public Connection con;
public DataBase(){
try{
con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/MyDB","postgres","1234");
        Statement statement = con.createStatement();
System.out.println("Good");        
}
catch(Exception e){}
}
public void show(){
try{PreparedStatement query=con.prepareStatement("Select * from journal order by 1");
            ResultSet result=query.executeQuery();
            
            while(result.next()){
            System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getShort(3)+" "+result.getShort(4)+" "+result.getShort(5));
            //result.next();
            }
            result.close();}
catch(Exception ex){
System.out.println(ex);
}
}
public void insert(String name,short value1,short value2,short value3){
try{PreparedStatement query=con.prepareStatement("Insert into journal(name,valueRus,valueMath,valuePhysics) values (?,?,?,?)");
      query.setString(1, name);
      query.setShort(2,value1);
      query.setShort(3,value2);
      query.setShort(4,value3);
      query.executeUpdate();
           }
catch(Exception ex){
System.out.println(ex);
}
}
public void update(int id,String subject,short value) 
{
  try{  
PreparedStatement updateQuery=null;
if ("Математика".equals(subject))
{
updateQuery=con.prepareStatement("Update journal set valuemath=? where id="+id);

}
if ("Русский язык".equals(subject))
{updateQuery=con.prepareStatement("Update journal set valuerus=? where id="+id);}
if ("Физика".equals(subject))
{updateQuery=con.prepareStatement("Update journal set valuephysics=? where id="+id);}
updateQuery.setShort(1,value);
updateQuery.executeUpdate();}
  catch (Exception e){
  System.out.println(e);
  }

}}
class DataBaseFind extends DataBase{
public DataBaseFind(){
super();
}
public void show (String name){
try{PreparedStatement query=con.prepareStatement("Select * from journal where name='"+name+"' order by 1");
            ResultSet result=query.executeQuery();
            
            while(result.next()){
            System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getShort(3)+" "+result.getShort(4)+" "+result.getShort(5));
            
           }
            result.close();}
catch(Exception ex){
System.out.println(ex);
}
}
}
public class FirstJDBC {
public static String getString() throws Exception{
    BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
    return br.readLine();
    }
    public static short getShort() throws Exception{
    return  Short.parseShort(getString());
    }
    public static int getInt() throws Exception{
    return  Integer.parseInt(getString());
    }
    /**
     * @param args
     * @param arg
     * @throws java.lang.Exception
     * @throws java.lang.Exceptions the command line arguments
     */
    public static void main(String[] args) throws Exception// throws SQLException 
    {
        // TODO code application logic here
        DataBaseFind db=new DataBaseFind();
        
        
        for (;;){
            try{System.out.println("Выберите действие:"+"\n"+
                    "1.Показ таблицы"+"\n"+"2.Вставка в таблицу"+"\n"+"3.Изменение данных по ключу"+
                    "\n"+"4.Поиск по имени"+"\n"+"5.Выход");
           short check=getShort();
           if(check==1)
               db.show();
           if(check==2){
           System.out.print("Введите имя и фамилию: ");    
           String name=getString();
           
           System.out.print("Оценка за Русский язык: ");
           short val1=getShort();
           System.out.print("Оценка за Математику: ");
           short val2=getShort();
           System.out.print("Оценка за Физику: ");
           short val3=getShort();
           db.insert(name,val1,val2,val3);
           }
           if (check==3){
               System.out.print("Введите id ученика: ");
               int id=getInt();
               System.out.print("Выберите предмет: ");
               String subject=getString();
               System.out.print("Измените оценку: ");
           db.update(id,subject,getShort());
           }
           if (check==4){
           System.out.print("Введите имя и фамилию: ");
           db.show(getString());
           }
           if (check==5){
           System.exit(0);
           }
            }
            catch (Exception e){
            System.out.println(e);
            }
           //db.insert(get, check, check, check);
        }
        /*String url="jdbc:postgresql://localhost:5432/MyDB";
        String userName="postgres";
        String password="1234";
        
        try (Connection connection =
                     DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement()) {
            //PreparedStatement queryInsert=connection.prepareStatement("Insert into mytable values(?,?)");
            //queryInsert.setInt(1,8);
            //queryInsert.setString(2, "Tema");
            //queryInsert.executeUpdate();
            PreparedStatement query=connection.prepareStatement("Select * from journal order by 1");
            ResultSet result=query.executeQuery();
            
            while(result.next()){
            System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getShort(3)+" "+result.getShort(4)+" "+result.getShort(5));
            //result.next();
            }
            result.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    */}
}
        
    
    

