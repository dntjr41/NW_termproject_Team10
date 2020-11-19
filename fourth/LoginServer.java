package myPackage2;

import java.io.IOException;
import java.util.Iterator;
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
	private static Set<String> userlist = new HashSet<>(); //가입자들의 정보 저장.
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
		private String logid="";
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
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql driver
			conn=DriverManager.getConnection(DB_URL,"root","12345"); //db주소와, 사용자, 비밀번호를 통해서 접근.
			state=conn.createStatement(); //mysql 연결
			boolean realuser=false;
			Statement state9=conn.createStatement();
			String list="select userid from info";
			ResultSet ulist=state9.executeQuery(list);//데이터베이스의 userid를 다 가져옴.
			while(ulist.next())
			{
				userlist.add(ulist.getString("userid"));//데이터베이스에서 가져온 userid를  server의 hashlist에저장.
			}
			while(true)
			{
			String input=in.nextLine();
			System.out.println(input);
				if(input.startsWith("logid")) //client에서 logid가 왔을 때, 로그인
				{
					String[]info=input.split(" "); //id와 password가 같이와서 나눠줌.
					id=info[0].substring(5); //id
					pw=info[1]; //password
					
					String sqlid="select userid from info where userid like"+"'"+id+"'";
					ResultSet rsi=state.executeQuery(sqlid);
					if ((rsi.next() == false || (id.isEmpty()) == true))
					{
						out.println("id invalid"); //로그인 실패
					}
					rsi.close();
					Statement state2=conn.createStatement();
					String sql="select * from info where userid like"+"'"+id+"'"; //id를 바탕으로 비밀번호를 찾음
					ResultSet rs=state2.executeQuery(sql); //결과 값 저장.
					Statement state3=conn.createStatement();
					while(rs.next()) //데이터베이스 스캔
					{
						String dbpw=rs.getString("password"); //조건문에 맞는 패스워드 저장\
						if(pw.equalsIgnoreCase(dbpw)) //입력한 비밀번호와 아이디가 맞을 때, 로그인성공
						{
							
							
							out.println("access:"+rs.getString("name")+":"+rs.getString("comment")+":"+id+":"+rs.getString("friend"));//클라이언트에게 성공했다고 보내줌.
							String sql_update = "update info set login_count = login_count+1 where userid = "+ "'"+id+"'";
							state3.executeUpdate(sql_update);
							logid=id; //로그인 되어있는 아이디 저장.
						}
						else
						{
						out.println("pw invalid"); //로그인 실패	
						}	
						}
					rs.close();
					state3.close();
					state2.close();
					
					}
				else if(input.startsWith("Resister")) //회원가입
				{
					String [] nw=input.split(" "); //회원가입되서 온 정보를 나눠줌.
					String addname=nw[0].substring(8);
					int addbday=Integer.parseInt(nw[1]);
					String addemail=nw[2];
					String addid=nw[3];
					String addpw=nw[4];
					String addnick = nw[5];
					Statement state4=conn.createStatement();
					String mysql="insert into info values("+"'"+addname+"'"+","+addbday+","+"'"+addemail+"'"+","+"'"+addid+"'"+","+"'"+addpw+"'"+","+0+","+"'"+"hello world"+"'"+","+"'"+null+"'"+"," + "'"+addnick+"'"+")";
					//String mysql="insert into info values("+"'"+addname+"'"+","+addbday+","+"'"+addemail+"'"+","+"'"+addid+"'"+","+"'"+addpw+"'"+","+0+","+"'"+""+"'"+","+"'"+"hello world"+"'"+")";
						
					//데이터베이스에 저장시켜주는 구문
					state4.executeUpdate(mysql); //데이터베이스 업데이트
					userlist.add(nw[3]); //가입하면, hashset에 userid 추가
					out.println("Welcome"); //회원가입 되었다는 것을 보내줌.
					state4.close();
					
				}
				else if(input.startsWith("Search"))//client에서 검색하라고 명령이 왓을경우
				{
					
					String sid=input.substring(6);
					Iterator<String>finduser=userlist.iterator();
					while(finduser.hasNext())
					{
						String comp=finduser.next();
						if(sid.contains(comp))
						{
							realuser=true; //hashset에서 해당 가입자가 있는지 스캔
						}
					}
						if(realuser==true) //가입자가 있을 경우 , 해당가입자의 정보 출력해서 client에게 보내줌.
						{
							String searchinfo="select * from info where userid like '"+sid+"'";//해당아이디의 정보를 찾음
							Statement st2=null;
							st2=conn.createStatement();
							ResultSet sch=st2.executeQuery(searchinfo); //찾은 정보를 저장.
							String fetch="";
							while(sch.next())
							{
								 fetch="Searching"+sch.getString("name")+" "+sch.getString("birthday")+" "+sch.getString("email")+" "+sch.getString("comment");
							//찾은 정보를 보내줌.
							}
							System.out.println(fetch);
							out.println(fetch);
							st2.close();
							realuser=false;
						}
						else if(realuser==false) //해당가입자가 없을 경우 client에게 보내줌.
						{
						out.println("No user");	
						}
					}
	
				else if(input.startsWith("ADDF")) //친구를 추가할때,
				{
					boolean check=false;
					String take="select friend from info where userid like"+"'"+logid+"'"; //현재 로그인되어 있는 사용자의 친구명단을 읽어옴.
					Statement state6=conn.createStatement();
					ResultSet fri=state6.executeQuery(take);
					String prev="";
					while(fri.next())
					{
						prev=fri.getString("friend"); //그 전에 있던 친구명단을 가져옴.

					}
					String ptocol=input.substring(4); //추가할 친구id를 가져옴
					Iterator <String> afinduser=userlist.iterator(); //친구로 추가할 id가 실제로 가입자인지 스캔
					while(afinduser.hasNext())
					{
						String comp2=afinduser.next();
						if(ptocol.contains(comp2))
						{
							check=true; //있으면 true로 바꿔줌
						}
					}

					if(check==true) //가입자가 있을 경우, 친구 추가 진행
					{
						
						if(prev!=null&&prev.contains(ptocol)) { //친구리스트에 친구가 겹치는 경우 
							out.println("DUPLICATION"); 
						}
						else {

							String flist=prev+" "+ptocol; //새로 친구리스트를 만들어줌
							state6.close();
							String fradd="update info set friend="+"'"+flist+"'"+"where userid like"+"'"+logid+"'"; //데이터베이스에 반영시켜줌
							Statement state7=conn.createStatement();
							state7.executeUpdate(fradd);
							state7.close();
							out.println("ADD"); //client에게 추가했다고 보내줌.
							check=false;
						}
						
					}
					else if(check==false) //가입자가 없을 경우, 사용자가 없다고 client에 보내줌.
					{
						out.println("No user");
					}
				}
				else if(input.startsWith("List")) //client에서 친구 리스트가 필요하다고 왓을때
				{
					String obj=input.substring(4); //리스트가 필요한 로그인 사용자 id를 불러온다.
					String comma="select friend from info where userid like"+"'"+obj+"'"; //해당 id의 친구 리스트 저장
					Statement state8=conn.createStatement();
					ResultSet frlist=state8.executeQuery(comma); //친구 리스트 조회
					String temp="";
					while(frlist.next())
					{
						temp=temp+frlist.getString("friend"); //temp에 친구리스트 저장
					}
					out.println("Frlist"+temp); //client에 친구리스트를 보내줌.
				}
				else if(input.startsWith("ChangeInfo")) {  // 내정보 변경 할 때 
					String[] ch_info = input.split(":");
					String ch_sql = "update info set nickname = "+"'"+ch_info[1]+"'," + " comment = " + "'"+ch_info[2]+"'"+" where userid = " +"'"+logid+"'";// 닉네임이랑 상태메시지 변경함
					Statement state10 = conn.createStatement();
					state10.executeUpdate(ch_sql);

					state10.close();
					
				}
				
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
	
	

	
	



