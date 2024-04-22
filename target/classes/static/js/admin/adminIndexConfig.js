$(function () {
    var configType = $("#configType").val();

    $("#jqGrid").jqGrid({
        url: '/admin/indexConfigs/list?configType=' + configType,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'configId', index: 'configId', width: 50, key: true, hidden: true},
            {label: '名称', name: 'configName', index: 'configName', width: 120},
            {label: '排序值', name: 'configRank', index: 'configRank', width: 120},
            {label: '对应教师编号', name: 'configTutorId', index: 'configTutorId', width: 120},
            {label: '对应课程编号', name: 'configSubId', index: 'configSubId', width: 120},
            {label: '是否失效', name: 'isExpire', index: 'isExpire', width: 80},
            {label: '添加时间', name: 'createTime', index: 'createTime', width: 120}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function configAdd() {
    reset();
    $('.modal-title').html('首页配置项添加');
    $('#indexConfigModal').modal('show');
    console.log();
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var configName = $("#configName").val();
    var configType = $("#configType").val();
    var configTutorId = $("#configTutorId").val();
    var configRank = $("#configRank").val();
    var configSubId = $("#configSubId").val();
    if (!validCN_ENString2_18(configName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的配置项名称！");
    } else {
        var data = {
            "configName": configName,
            "configType": configType,
            "configTutorId": configTutorId,
            "configRank": configRank,
            "configSubId": configSubId
        };
        var url = '/admin/indexConfigs/save';
        var id = getSelectedRowWithoutAlert();
        if (id != null) {
            url = '/admin/indexConfigs/update';
            data = {
                "configId": id,
                "configName": configName,
                "configType": configType,
                "configTutorId": configTutorId,
                "configRank": configRank,
                "configSubId": configSubId
            };
        }
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.code == 0) {
                    $('#indexConfigModal').modal('hide');
                    swal("保存成功", {
                        icon: "success",
                    });
                    reload();
                } else {
                    $('#indexConfigModal').modal('hide');
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
});

function configEdit() {
    reset();
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid("getRowData", id);
    $('.modal-title').html('首页配置项编辑');
    $('#indexConfigModal').modal('show');
    $("#configId").val(id);
    $("#configName").val(rowData.configName);
    $("#configTutorId").val(rowData.configTutorId);
    $("#configSubId").val(rowData.configSubId);
    $("#configRank").val(rowData.configRank);
}

function deleteConfig () {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/indexConfigs/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.msg, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}


function reset() {
    $("#configName").val('');
    $("#configTutorId").val(0);
    $("#configRank").val(0);
    $("#configSubId").val(0);
    $('#edit-error-msg').css("display", "none");
}