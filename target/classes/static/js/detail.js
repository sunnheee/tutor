document.querySelectorAll('.tab-box-item').forEach(function (span) {
    span.addEventListener('click', function () {
        document.querySelectorAll('.tab-box-item').forEach(function (s) {
            s.classList.remove('select');
        });
        this.classList.add('select');
    });
});

document.querySelectorAll('.tab').forEach(function (span) {
    span.addEventListener('click', function () {
        document.querySelectorAll('.tab').forEach(function (s) {
            s.classList.remove('tab-select');
        });
        this.classList.add('tab-select');
    });
});

function showContent(content) {
    const intro = document.querySelector('.intro');
    const comment = document.querySelector('.comment');

    if (content === 'intro') {
        intro.classList.remove('hide');
        comment.classList.add('hide');
    } else if (content === 'comment') {
        intro.classList.add('hide');
        comment.classList.remove('hide');
    }
}

function getPhone() {
    var phone = $("#phone").val();
    swal({
        title: "联系方式",
        text: '家教联系电话：' + phone,
        icon: "",
    });
}

function sendComment() {
    var comment = $("#comment").val();
    var subId = $("#subId").val();
    var url = '/sendComment';
    var params = {
        'subId': subId,
        'comment': comment
    }
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
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
function like() {
    var id = $("#commentId").val();
    var url = '/like';
    var param = {
        'id' : id
    };
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: param,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
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

function addWish() {
    var subId = $("#subId").val();
    var url = '/addWish';
    var param = {
        'subId' : subId
};
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: param,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
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
function addCollection() {
    var subId = $("#subId").val();
    var url = '/addCollection';
    var param = {
      'subId' : subId
    };
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: param,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
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