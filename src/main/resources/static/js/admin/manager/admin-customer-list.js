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

/*客户-增加*/
function admin_customer_add(title,url,w,h){
    layer_show(title,url,w,h);
}

/*客户-删除*/
function admin_customer_del() {
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
            url: "/admin/customer/batch/"+selectedIds,
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

/*客户-编辑*/
function admin_customer_edit(title,url,w,h){
    layer_show(title,url,w,h);
}
