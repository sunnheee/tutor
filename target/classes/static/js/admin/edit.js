$(function () {
    new AjaxUpload('#uploadSubCoverImg', {
        action: '/upload/file',
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
                $("#subCoverImg").attr("src", r.data);
                $("#subCoverImg").attr("style", "width: 160px;height: 160px;display:block;object-fit: scale-down;");
                return false;
            } else {
                alert("error");
            }
        }
    });
});

$('#saveButton').click(function () {
    var subId = $('#subId').val();
    var subCategoryId = $('#levelTwo option:selected').val();
    var subName = $('#subName').val();
    var priceLesson = $('#priceLesson').val();
    var tutorId = $('#tutorId').val();
    var tutorName = $('#tutorName').val();
    var subSellStatus = $("input[name='subSellStatus']:checked").val();
    var subCoverImg = $('#subCoverImg')[0].src;
    if (isNull(subCategoryId)) {
        swal("请选择分类", {
            icon: "error",
        });
        return;
    }
    if (isNull(subName)) {
        swal("请输入科目名称", {
            icon: "error",
        });
        return;
    }
    if (!validLength(subName, 100)) {
        swal("请输入科目名称", {
            icon: "error",
        });
        return;
    }
    if (isNull(tutorId)) {
        swal("请输入对应教师id", {
            icon: "error",
        });
        return;
    }
    if (!validLength(tutorId, 100)) {
        swal("id过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(tutorName)) {
        swal("请输入对应教师姓名", {
            icon: "error",
        });
        return;
    }
    if (!validLength(tutorName, 100)) {
        swal("姓名过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(priceLesson) || priceLesson < 1) {
        swal("请输入课程价格", {
            icon: "error",
        });
        return;
    }
    if (isNull(subSellStatus)) {
        swal("请选择上架状态", {
            icon: "error",
        });
        return;
    }
    if (isNull(subCoverImg) || subCoverImg.indexOf('img-upload') != -1) {
        swal("封面图片不能为空", {
            icon: "error",
        });
        return;
    }
    var url = '/admin/subs/save';
    var swlMessage = '保存成功';
    var data = {
        "subCategoryId": subCategoryId,
        "subName": subName,
        "priceLesson": priceLesson,
        "tutorId": tutorId,
        "tutorName": tutorName,
        "status": subSellStatus,
        "tutorPhoto": subCoverImg,
    };
    if (subId > 0) {
        url = '/admin/subs/update';
        swlMessage = '修改成功';
        data = {
            "subId": subId,
            "subCategoryId": subCategoryId,
            "subName": subName,
            "priceLesson": priceLesson,
            "tutorId": tutorId,
            "tutorName": tutorName,
            "status": subSellStatus,
            "tutorPhoto": subCoverImg,
        };
    }
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.code == 0) {
                $('#subModal').modal('hide');
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: '返回列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/subs";
                })
            } else {
                $('#subsModal').modal('hide');
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
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/subs";
});

$('#levelOne').on('change', function () {
    $.ajax({
        url: '/admin/categories/listForSelect?categoryId=' + $(this).val(),
        type: 'GET',
        success: function (result) {
            if (result.code == 0) {
                var levelTwoSelect = '';
                var secondLevelCategories = result.data.secondLevelCategories;
                var length2 = secondLevelCategories.length;
                for (var i = 0; i < length2; i++) {
                    levelTwoSelect += '<option value=\"' + secondLevelCategories[i].categoryId + '\">' + secondLevelCategories[i].subName + '</option>';
                }
                $('#levelTwo').html(levelTwoSelect);
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
});