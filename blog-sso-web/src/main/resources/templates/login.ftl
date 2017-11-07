<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="${request.contextPath}/lib/bootstrap2/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${request.contextPath}/lib/html5shiv/html5shiv.min.js"></script>
    <script src="${request.contextPath}/lib/respond/respond.min.js"></script>
    <![endif]-->
    <style>
        .container{
            padding-top:15em ;
        }

    </style>
</head>
<body>


<div class="container">

    <form  id="loginForm" class="form-horizontal" action="${request.contextPath}/admin/login" method="post" onsubmit="return false">
        <div class="form-group has-feedback">
            <label for="inputEmail3" class=" col-sm-offset-3 col-sm-2 control-label">Email</label>
            <div class="col-sm-3">
                <input name="username" type="text" class="form-control" id="inputEmail3" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-offset-3 col-sm-2 control-label">Password</label>
            <div class="col-sm-3">
                <input name="password" type="password" class="form-control" id="inputPassword3" placeholder="Password">
            </div>
        </div>
        <div class="form-inline form-group">

                <label for="validatecode" class="col-sm-offset-3 col-sm-2 control-label">AuthCode</label>
                <div class="col-sm-2">
                    <input name="authcode" type="password" class="form-control" style="width: 100%" id="validatecode" placeholder="ValidateCode">
                </div>
                <div class="col-sm-1">
                    <img id="loginform:vCode" class="img-responsive"  src="${request.contextPath}/admin/vcode"
                    onclick="javascript:document.getElementById('loginform:vCode').src='${request.contextPath}/admin/vcode?'+Math.random();" />
                </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Remember me
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-10">
                <button id="loginButton" type="submit" class="btn btn-default">Sign in</button>
            </div>
        </div>
    </form>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${request.contextPath}/lib/jquery2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${request.contextPath}/lib/bootstrap2/js/bootstrap.min.js"></script>
<script>
    $(function () {
       $("#loginButton").on("click",function () {

          $.post("${request.contextPath}/admin/login",$("#loginForm").serialize(),function (data) {

              if(data.success==1){
                  alert("登录成功");
                  return;
              }
              alert(data.success+data.msg);

          },"json");
       });
    });
</script>
</body>
</html>