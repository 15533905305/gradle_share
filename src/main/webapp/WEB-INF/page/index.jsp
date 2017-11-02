<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <form id="formname">
        <span>用户名：</span><input type="text" name = "username" id = "username"><br />
        <span>密&nbsp;码：</span><input type = "password" id = "password" name = "password">
          <br />
        <button type="button" id = "btn">创建用户</button>
      </form>
  </body>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
  $('#btn').click(function () {
      var username= $('#username').val();
      var password= $('#password').val();
      $.ajax({
          type: "post",
          url:'${ctx}user/addUser',
          data:{username:username,password:password},
          success:function (data) {
              console.log(data);
              alert('创建用户成功');
          }
      })
  })

</script>
</html>
