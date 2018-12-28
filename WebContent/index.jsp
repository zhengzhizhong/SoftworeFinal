<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@page  import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆</title>
 <script type="text/javascript">
 function seeE() { 
	    document.getElementById('randImage').src='./image.jsp?t='+new Date().getTime(); 
	  } 
        
     </script>
<script  type="text/javascript">
function  frmsubmit(){
	var  frm=document.getElementById("frmmain");
	frm.submit();
}
</script>
<style type="text/css">
.a1{
position: relative;/*相对定位*/
left:300px;/*在原来的位置向右移动*/
top:150px;/*在原来的位置向下移动*/
}

.box {
            position: absolute;
            right: 0;
            left: 0;
            top: 50%;
            bottom: 50%;
            margin: auto;
            text-align: center;
            width: 280px;
            height: 326px;
            color: black;
        }


.input-box input{ 
            color:black;
            width: 100x;
            height: 20px;
}
 .user-title {
            color:black;
            margin-top: 20px;
            font-size: 40px;
            text-align: center;
}

 body{
  background:url("res/shop.jpg");
 background-size:1400px 700px;
  }
</style>

</head>

<body >
<%   String  rememberedUserName="";
   String  rememberedPassWord="";
   Cookie[]  cookies=request.getCookies();
   if(cookies!=null){
   for(int i=0;i<cookies.length;i++){
	Cookie  c=cookies[i];
	if(c.getName().equals("userName"))
		rememberedUserName=c.getValue();
	if(c.getName().equals("passWord"))
		rememberedPassWord=c.getValue();
  } }
%>


<div class="box">
<div class="user-title">超市管理系统</div><br/>
---------------------------------------<br/>
<span style="font-size: 20px;">user login</span><br/><br/>
<form  action=" "  method="post" name="frmmain" id="frmmain" >
<div class="input-box">
账 &nbsp;号：<input  type="text"  name="userName"   value="<%= rememberedUserName%>"/><br/><br/>
</div>
<div class="input-box">
密 &nbsp;码：<input  type="password"  name="passWord" value="<%= rememberedPassWord%>"/><br/><br/>
</div>
记住密码<input type="checkbox" name="remember1" value="remember1"/>&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;
记住账号<input type="checkbox" name="remember2" value="remember2"/><br/><br>
验证码<input type="text" name="rand" size="5">
<img alt="code..." name="randImage" id="randImage" src="image.jsp"
 width="60" height="20" border="1" align="absmiddle" >
<a href="" onclick="seeE();return false;">换一张</a><br><br>
<input type="hidden" name="flag"  value="1">
<input type="submit" value="登录"  style="width:120px;height:30px;text-align:center;">

</form>
</div>
<%
String username=request.getParameter("userName");
String password=request.getParameter("passWord");
String  mrem1=request.getParameter("remember1");
String  mrem2=request.getParameter("remember2");
String rand = (String)session.getAttribute("rand"); 
String input = request.getParameter("rand");
int expiry=0;
if(mrem1!=null){
	expiry=60*60*24*7;

Cookie  c1=new Cookie("userName",username);
c1.setMaxAge(expiry);
response.addCookie(c1);
Cookie  c2=new Cookie("passWord",password);
c2.setMaxAge(expiry);
response.addCookie(c2);

}

if(mrem2!=null){
	expiry=60*60*24*7;

Cookie  c1=new Cookie("userName",username);
c1.setMaxAge(expiry);
response.addCookie(c1);
//

}
%>
<%String driverName="com.mysql.jdbc.Driver";
try{
       Class.forName(driverName);
}catch(ClassNotFoundException e){
    	e.printStackTrace();
}




if(request.getParameter("flag")!=null){

String  url="jdbc:mysql://127.0.0.1:3306/test?user=root&password=123456&characterEncoding=utf-8&serverTimezone=GMT";
try{
Connection con=DriverManager.getConnection(url);
PreparedStatement pstmt=null;
ResultSet rs = null;
Statement statement=con.createStatement();
String  sql = "select * from user where username=?and password=?" ;
pstmt=con.prepareStatement(sql);
pstmt.setString(1,username);
pstmt.setString(2,password);
rs=pstmt.executeQuery();

if(rs.next()){
	if(rand.equals(input)){

	response.sendRedirect("welcome.jsp");}
	else if(input==""){
		out.print("<script language=javascript>alert('请输入验证码！');history.go(-1)</script>");
	}
	else out.print("<script language=javascript>alert('验证码错误！！');history.go(-1)</script>");
}else{
	out.print("<script language=javascript>alert('账号或密码错误！');history.go(-1)</script>");
}


}catch(SQLException  e){
	e.printStackTrace();
}
}

%>

</body>

</html>
