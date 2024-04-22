function changePhone() {
    var phone = $("#phone").val();
    var userId = $("#userId").val();
    var params = {
        'phone' : phone,
        'userId' : userId
    }
    var url = "/changePhone";
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.code == 0) {
                swal({
                    title: "success",
                    text: "修改成功！",
                    icon: "success",
                    buttons: true,
                    dangerMode: true,
                }).then((flag) => {
                        if (flag) {
                            window.location.reload();
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

function togglePassword() {
    var passwordInput = document.getElementById("oldPassword");
    var toggleIcon = document.getElementById("togglePassword");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.classList.remove("fa-eye-slash");
        toggleIcon.classList.add("fa-eye");
    } else {
        passwordInput.type = "password";
        toggleIcon.classList.remove("fa-eye");
        toggleIcon.classList.add("fa-eye-slash");
    }
}

function togglePasswordNew() {
    var passwordInput = document.getElementById("newPassword");
    var toggleIcon = document.getElementById("togglePassword-new");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.classList.remove("fa-eye-slash");
        toggleIcon.classList.add("fa-eye");
    } else {
        passwordInput.type = "password";
        toggleIcon.classList.remove("fa-eye");
        toggleIcon.classList.add("fa-eye-slash");
    }
}

function changePassword() {
    var userId = $("#userId").val();
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var params = {
        'userId' : userId,
        'oldPassword' : oldPassword,
        'newPassword' : newPassword
    }
    var url = "/changePassword";
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.code == 0) {
                swal({
                    title: "success",
                    text: "修改成功，请重新登陆",
                    icon: "success",
                    buttons: true,
                    dangerMode: true,
                }).then((flag) => {
                        if (flag) {
                            window.location.href = '/loginView';
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