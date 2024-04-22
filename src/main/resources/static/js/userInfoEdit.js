$(document).ready(function() {
    $.ajax({
        url: "getDefaultAvatarUrl",
        type: "POST",
        success: function(data) {
            if(data.code == 0){
                $("#avatarImage").attr("src", data.data);
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
    new AjaxUpload('#uploadUserAvatar', {
        action: '/upload/avatarFile',
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
                    url: 'getUploadAvatarUrl', // 后端接口路径
                    type: 'POST',
                    success: function(data) {
                        if(data.code == 0){
                            $("#avatarImage").attr("src", data.data);
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

function saveUserInfo() {
    var userId = $("#userId").val();
    var phone = $("#phone").val();
    var userEmail = $("#email").val();
    var nickname = $("#nickname").val();
    var briefIntroduction = $("#briefIntroduction").val();
    var avatarUrl = $("#avatarImage")[0].src;
    var params = {
        'userId' : userId,
        'phone' : phone,
        'userEmail' : userEmail,
        'nickname' : nickname,
        'briefIntroduction' : briefIntroduction,
        'avatarUrl' : avatarUrl
    };
    var url = "/saveUserEdit";
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.code == 0) {
                swal({
                    title: "success",
                    text: "保存成功！",
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