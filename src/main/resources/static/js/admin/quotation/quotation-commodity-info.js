$(function () {

});

/*
 参数解释：
 title	标题
 url		请求的url
 id		需要操作的数据id
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 */

/*报价单商品-增加*/
function admin_quotation_commodity_add(title, url, w, h) {
    layer_show(title, url, w, h);
}

/*报价单商品-修改*/
function admin_quotation_commodity_edit(title, url, w, h) {
    layer_show(title, url, w, h);
}

/* 删除选中报价单商品*/
function admin_quotation_commodity_del() {
    //复选框选择id集合
    var selectedIds = [];
    $(".text-c :checkbox").each(function (index, ele) {
        var id = $(this).val();
        var isSelected = this.checked;
        if (isSelected) {
            selectedIds.push(id);
        } else {
            selectedIds.removeObject(id);
        }
    });
    if (selectedIds == "") {
        errorMessage("请先选择一条记录!");
        return false;
    }
    console.log(selectedIds);
    layer.confirm('确认要删除吗？', function (index) {
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type: "DELETE",
            dataType: "json",
            url: "/admin/quotation/commoditybatch/" + selectedIds,
            data: {
                "modifyTime": new Date().getTime()
            },
            statusCode: {
                200: function (data) {
                    succeedMessage(data.responseText);
                    window.location.reload();
                },
                404: function (data) {
                    errorMessage(data.responseText);
                },
                500: function () {
                    errorMessage('系统错误!');
                }
            }
        });
    });
}

/*报价单商品数据导入*/
function admin_quotation_commodity_ExcelImport(qid) {
    //格式校验z
    var filePath = $("#uploadFileCommodity").val();
    var fmtResult = false, fmts = ['xlsx', 'xls'];
    var fileFmt = filePath.substring(filePath.lastIndexOf('.') + 1, filePath.length);
    for (var i = 0; i < fmts.length; i++) {
        if (fileFmt.toLowerCase() == fmts[i].toLowerCase()) {
            fmtResult = true;
            break;
        }
    }
    if (!fmtResult) {
        layer.msg("您未上传文件，或者您上传文件类型有误！");
        return false;
    }
    layer.confirm('确认要导入数据么？', function (index) {
        //此处请求后台程序，下方是成功后的前台处理……
        //上传
        $.ajaxFileUpload({
            url: '/admin/quotation/commodityExcelImport?qid=' + qid,
            secureuri: false,
            fileElementId: "uploadFileCommodity",
            dataType: 'json',
            isAsyncResponse: false, // 使用异步响应结果
            success: function (data, status) {
                window.location.reload();
            }
        });
    });
}

//直接在本窗口进行打印
function windowprint() {
    var f = document.getElementById("printdiv");
    var tds = document.getElementsByClassName("td-manage");
    var tr1 = document.getElementById("d_operate");
    tr1.setAttribute('hidden', 'hidden');
    for (var i = 0; i < tds.length; i++) {
           tds[i].setAttribute('hidden', 'hidden')
    }
    f.style.display = "";
    window.print();
    tr1.removeAttribute('hidden');
    for (var i = 0; i < tds.length; i++) {
        tds[i].removeAttribute('hidden');
    }
}