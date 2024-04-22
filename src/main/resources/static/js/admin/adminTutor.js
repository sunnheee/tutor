$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/tutors/list',
        datatype: "json",
        colModel: [
            {label: '教师编号', name: 'tutorId', index: 'tutorId', width: 60, key: true},
            {label: '姓名', name: 'tutorName', index: 'tutorName', width: 60},
            {label: '性别', name: 'tutorGender', index: 'tutorGender', width: 60},
            {label: '年龄', name: 'tutorAge', index: 'tutorAge', width: 60},
            {label: '照片', name: 'tutorPhoto', index: 'tutorPhoto', width: 80, formatter: coverImageFormatter},
            {label: '联系电话', name: 'tutorPhone', index: 'tutorPhone', width: 60},
            {label: '地区', name: 'area', index: 'tutorPhone', width: 60},
            {label: '简介', name: 'briefIntroduction', index: 'briefIntroduction', width: 60},
            {label: '是否已删除', name: 'isExpire', index: 'isExpire', width: 60 ,formatter: deleteStatus},
            {
                label: '生效状态',
                name: 'effective',
                index: 'effective',
                width: 80,
                formatter: SubsEffectiveFormatter
            },
            {label: '创建时间', name: 'createTime', index: 'createTime', width: 60}
        ],
        height: 760,
        rowNum: 20,
        rowList: [20, 50, 80],
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
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function SubsEffectiveFormatter(cellvalue) {
        if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">已生效</button>";
        }
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">未生效</button>";
        }
    }

    function deleteStatus(cellvalue) {
        if (cellvalue == 1) {
            return "<span>是</span>";
        }
        if (cellvalue == 0) {
            return "<span>否</span>";
        }
    }

    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"80\" width=\"80\" alt='主图'/>";
    }

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

function Approval() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行审批操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/tutors/approval",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            swal("审批成功", {
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

function deleteTutor() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行删除操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/tutors/delete",
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