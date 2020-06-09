$(".card_title_in_modal").on("change", "input", function () {
    var card_title = $(this).val();
    var card_id = $(".card_title_in_modal").find("input[name='id']").val();
    $.ajax({
        url: "/card/update/title",
        method: "PUT",
        data: {
            "title": card_title,
            "id": card_id,
        }
    })
})

$("#description_text").on("change",function () {
    var description = $(this).val()
    var card_id = $(".card_title_in_modal").find("input[name='id']").val();
    $.ajax({
        url: "/card/update/desc",
        method: "PUT",
        data: {
            "description": description,
            "id": card_id,
        }
    })
})

$(document).on("click",".js-confirm-delete",function (e) {
    layer.confirm("删除附件是永久性操作，无法撤消。",  {icon: 3, title:'确认删除附件？'}, function(cindex){
        layer.close(cindex);
        var attach_id = $(e.target).parent().parent().parent().parent().parent().attr("id").slice(10);
        $.ajax({
            "type":"DELETE",
            "url":"/attachment/delete/"+attach_id,
            "success":function (res) {
                layer.msg(res, {time:1000, icon:5, shift:6}, function () {
                    $(e.target).parent().parent().parent().parent().parent().remove();
                });
            },
            "error":function (res) {
                layer.msg(res, {time:1000, icon:5, shift:6}, function () {

                });
            }
        })
    }, function(cindex){
        layer.close(cindex);
    });
})

// When element .js-confirm-edit clicked
$(document).on("click",'.js-confirm-edit',function(){
    var attach_id = $(this).closest("div").attr("id").slice(10);
    var attach_filename = $(this).closest("div").find(".attachment-thumbnail-name").text();
    layer.open({
        area:"auto",
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        closeBtn: 1, //不显示关闭按钮
        title:'编辑附件',
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        content:"<form class='layui-form'>" +
            " <div class='layui-form-item'>" +
            "    <label class='layui-form-label'>附件名称</label>" +
            "    <div class='layui-input-block'>" +
                    "<input type='hidden' name='id' value='"+attach_id+"'>" +
                    "<input type='text' name='filename' value='"+attach_filename+"' id='filename'>" +
            "    </div>" +
            "</div>" +
            "<div class='layui-form-item'>" +
                "<input class='layui-layer-btn' type='submit' value='更新'>"+
            "</div> " +
            "</form>"
    });
});

$(document).on("click",".layui-layer-btn",function (e) {
    // Stop default form Submit.
    e.preventDefault();
    var form = $(this).parents().find(".layui-form")[0];
    var data = new FormData(form);
    if (data!=null){

        $.ajax({
            type:"PUT",
            url:"/attachment/update",
            data: data,
            // prevent jQuery from automatically transforming the data into a query string
            processData: false,
            contentType: false,
            cache: false,
            timeout: 1000000,
            success:function (res) {
                layer.msg(res, {time:1000, icon:5, shift:6}, function () {

                });
            },
            error:function (res) {
                layer.msg(res, {time:1000, icon:5, shift:6}, function () {

                });

            }
        })
    }
})
