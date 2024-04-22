$(document).ready(function() {
    $.ajax({
        url: "getDefaultPhotoUrl",
        type: "POST",
        success: function(data) {
            if(data.code == 0){
                $("#photoUrl").attr("src", data.data);
            }
        },
        error: function() {
            alert(data.msg);
        }
    });
});

document.getElementById('phone').addEventListener('input', function(e) {
    var value = this.value;
    var regex = /^[0-9]*$/;

    if (!regex.test(value)) {
        this.value = value.replace(/[^0-9]/g, '');
    }
});

$(function () {
    new AjaxUpload('#uploadPhoto', {
        action: '/upload/photoFile',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 0) {
                $.ajax({
                    url: 'getUploadPhotoUrl', // 后端接口路径
                    type: 'POST',
                    success: function(data) {
                        if(data.code == 0){
                            $("#photoUrl").attr("src", data.data);
                        }
                    },
                    error: function() {
                        alert(data.msg);
                    }
                });
                return false;
            } else {
                alert("error");
            }
        }
    });
});

function submitTutorInfo() {
    var tutorName = $("#name").val();
    var tutorGender = $("#gender").val();
    var tutorPhoto = $("#photoUrl")[0].src;
    var tutorAge = $("#age").val();
    var tutorPhone = $("#phone").val();
    var area = $("#area").val();
    var briefIntroduction = $("#briefIntroduction").val();
    var captcha = $("#captcha").val();
    var params = {
        'tutorName' : tutorName,
        'tutorGender' : tutorGender,
        'tutorPhoto' : tutorPhoto,
        'tutorAge' : tutorAge,
        'tutorPhone' : tutorPhone,
        'area' : area,
        'briefIntroduction' : briefIntroduction,
        'captcha' : captcha
    };
    var url = "/submitJoinTutor";
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.code == 0) {
                swal({
                    title: "success",
                    text: "提交成功！",
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

function startCountdown() {
    var countdown = 60; // 倒计时时长，单位为秒
    var btn = document.getElementById("captchaBtn");
    var phone = $("#phone").val();

    $.ajax({
        url: "/getCaptcha",
        param: phone,
        type: "POST",
        success: function(data) {
            if(data.code == 0){
            // 禁用按钮并设置按钮文字为倒计时剩余时间
            btn.disabled = true;
            btn.textContent = countdown + " 秒后重新获取";

            // 开始倒计时
            var timer = setInterval(function() {
                countdown--;
                btn.textContent = countdown + " 秒后重新获取";

                if (countdown <= 0) {
                    clearInterval(timer);
                    btn.disabled = false;
                    btn.textContent = "获取验证码";
                }
            }, 1000); // 每隔1秒更新倒计时
        }
            else {
                swal(data.msg, {
                    icon: "error",
                });
            }
        },
        error: function() {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
}
document.getElementById('captchaBtn').addEventListener('click', getCode);

function getCode() {

}