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
/*报价单-增加*/
function admin_quotation_add(title,url,w,h){
    layer_show(title,url,w,h);
}

/*报价单-修改*/
function admin_quotation_edit(title,url,w,h){
    layer_show(title,url,w,h);
}

/* 删除选中报价单*/
function admin_quotation_del() {
    //复选框选择id集合
    var selectedIds=[];
    $(".text-c :checkbox").each(function (index, ele) {
        var id = $(this).val();
        var isSelected = this.checked;
        if (isSelected) {
            selectedIds.push(id);
        } else {
            selectedIds.removeObject(id);
        }
    });
    if(selectedIds == ""){
        errorMessage("请先选择一条记录!");
        return false;
    }

    layer.confirm('确认要删除吗？' ,function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"DELETE",
            dataType:"json",
            url: "/admin/quotation/batch/"+selectedIds,
            data:{
                "modifyTime":new Date().getTime()
            },
            statusCode: {
                200 : function(data){
                    succeedMessage(data.responseText);
                    window.location.reload();
                },
                404 : function(data){
                    errorMessage(data.responseText);
                },
                500 : function(){
                    errorMessage('系统错误!');
                }
            }
        });
    });
}

/*报价单-详情*/
function admin_quotation_commodity_info(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}


/*数据导入*/
function admin_quotation_excelImport(){
    layer.confirm('确认要导入数据么？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            type:"GET",
            url: "/admin/quotation/excelImport",
            statusCode: {
                200 : function(data){
                    succeedMessage(data.responseText);
                    window.location.reload();
                },
                404 : function(data){
                    errorMessage(data.responseText);
                },
                500 : function(){
                    errorMessage('系统错误!');
                }
            }
        });
    });
}

/*报价单-审核*/
function admin_quotation_aduit(id,status){
    var str = "确认审核通过吗？"
    if(status == '2'){
        var str = "确认审核拒绝吗？"
    }
    var confirm = layer.confirm(str ,function(index){
        $.ajax({
            type:"POST",
            dataType:"json",
            url: "/admin/quotation/auditQuotation",
            data:{
                "id": id,
                "auditorStatus": status,
            },
            statusCode: {
                200 : function(data){
                    succeedMessage(data.responseText);
                    window.location.reload();
                },
                404 : function(data){
                    errorMessage(data.responseText);
                },
                500 : function(){
                    errorMessage('系统错误!');
                }
            }
        });
    });
}
