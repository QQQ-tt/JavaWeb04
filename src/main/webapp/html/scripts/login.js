function f() {
  $.ajax({
    url: 'loginUser',
    type: 'post',
    dataType: 'json',
    success: function (data) {
      if (data.name != null) {
        $("#login").text(data.name).attr("href", "javascript:;");
        $("#logging").text("退出").attr("href", "javascript:;").click(function () {
          empty();
        })
      }
    },
    error: function () {
      alert("失败");
    }
  })
}

function empty() {
  $.ajax({
    url: "empty",
    type: "post",
    success: function () {
      $("#login").text("登录").attr("href", "html/login.html");
    }
  })
}

f();