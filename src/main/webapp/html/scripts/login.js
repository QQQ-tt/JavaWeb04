import './jquery.js'

function f() {
  $.ajax({
    url: 'loginUser',
    type: 'post',
    data: 'data',
    dataType: 'json',
    success: function (data) {
      alert("name" + data.name);
      alert(1);
    }
  })
}


f();