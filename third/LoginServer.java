package myPackage;
import java.io.IOException;

import java.sql.*;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class LoginServer 
{
	public static void main(String[] args) throws Exception
	{
		
		System.out.println("The chat server is running...");
		ExecutorService pool = Executors.newFixedThreadPool(500);
		try (ServerSocket listener = new ServerSocket(59001)) 
		{
			while (true)
			{
				pool.execute(new Handler(listener.accept()));
			}
		}
	}
	private static class Handler implements Runnable 
	{
		private String id;
		private Socket socket;
		private Scanner in;
		private PrintWriter out;
		private String pw;
		 String DB_URL = "jdbc:mysql://localhost/userlist?serverTimezone=UTC"; //접속할 DB 서버
			Connection conn=null;
			Statement state=null;
		public Handler(Socket socket)
		{
			this.socket = socket;
		}
		public void run() 
		{
			try {
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), true);
			String input=in.nextLine();
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql driver
			conn=DriverManager.getConnection(DB_URL,"root","574128"); //db주소와, 사용자, 비밀번호를 통해서 접근.
			state=conn.createStatement(); //mysql 연결
			while(true)
			{
				if(input.startsWith("logid")) //client에서 logid가 왔을 때, 로그인
				{
					String[]info=input.split(" "); //id와 password가 같이와서 나눠줌.
					id=info[0].substring(5); //id
					pw=info[1]; //password
					String sql="select password from info where userid like"+"'"+id+"'"; //id를 바탕으로 비밀번호를 찾음
					ResultSet rs=state.executeQuery(sql); //결과 값 저장.
					System.out.println("rs: "+ rs);
					while(rs.next()) //데이터베이스 스캔
					{
						String dbpw=rs.getString("password"); //조건문에 맞는 패스워드 저장\
						System.out.println("dbpw");
						if(pw.equalsIgnoreCase(dbpw)) //입력한 비밀번호와 아이디가 맞을 때, 로그인성공
						{
							out.println("access");//클라이언트에게 성공했다고 보내줌.
							String sql_update = "update info set login_count = login_count+1 where userid = "+ "'"+id+"'";
							state.executeUpdate(sql_update);
							
						}
						else
						{
						out.println("invalid"); //로그인 실패	
						}	
						}
					rs.close();
					conn.close();
					}
				else if(input.startsWith("Resister")) //회원가입
				{
					String [] nw=input.split(" "); //회원가입되서 온 정보를 나눠줌.
					String addname=nw[0].substring(8);
					int addbday=Integer.parseInt(nw[1]);
					String addemail=nw[2];
					String addid=nw[3];
					String addpw=nw[4];
					String mysql="insert into info values("+"'"+addname+"'"+","+addbday+","+"'"+addemail+"'"+","+"'"+addid+"'"+","+"'"+addpw+"'"+","+0+")";
					//데이터베이스에 저장시켜주는 구문
					state.executeUpdate(mysql); //데이터베이스 업데이트
					out.println("Welcome"); //회원가입 되었다는 것을 보내줌.
				}
			state.close();
			}
			}	
			catch (Exception e) 
			{
				System.out.println(e);
			} 
			finally {
				if (out != null) {
					
				}
				}
				try { socket.close(); }
				catch (IOException e){}
				}
			}}
	
	

	
	


