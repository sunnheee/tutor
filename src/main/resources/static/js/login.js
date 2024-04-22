document.getElementById('phone').addEventListener('input', function(e) {
    var value = this.value;
    var regex = /^[0-9]*$/;

    if (!regex.test(value)) {
        this.value = value.replace(/[^0-9]/g, '');
    }
});

function login() {
    var phone = $("#phone").val();
    var password = $("#password").val();
    var captcha = $("#captcha").val();
    var param = {
        'phone':phone,
        'password':password,
        'captcha':captcha
    };
    var url = '/login';
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: param,
        success: function (result) {
            if (result.code == 0) {
                swal({
                    title: "登陆成功",
                    text: "是否跳转至首页?",
                    icon: "success",
                    buttons: true,
                    dangerMode: true,
                }).then((flag) => {
                        if (flag) {
                            window.location.href = '/index';
                        }
                    }
                )
                ;
            } else {
                swal(result.msg, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
}