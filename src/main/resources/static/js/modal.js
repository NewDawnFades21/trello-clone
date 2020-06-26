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
    var id = $(e.target).closest(".layui-form").find("input[name='id']").val();
    var filename = $(e.target).closest(".layui-form").find("input[name='filename']").val();
    $.ajax({
        type:"PUT",
        url:"/attachment/update/"+id+"/"+filename,
        // prevent jQuery from automatically transforming the data into a query string
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success:function (res) {
            console.log(res)
            layer.msg("更新成功", {time:1000, icon:5, shift:6}, function () {

                $("#attachment"+id).find(".attachment-thumbnail-name").text(res.filename)
            });
        },
        error:function (res) {
            layer.msg(res, {time:1000, icon:5, shift:6}, function () {

            });
        }
    })
})

$(document).on("click",".comment_edit",function (e) {
    var display = $(e.target).closest(".comment").find(".comment_edit_btn").css("display")
    if(display!="none")
    {
        var content = $(e.target).closest(".comment").find(".comment_contents").text();
        var replace_html = "<div class='comment_contents z-depth-1'>"+content+"</div>";
        $(e.target).closest(".comment").find(".comment_contents").replaceWith(replace_html);

        $(e.target).closest(".comment").find(".comment_edit_btn").attr("style", "display:none");
    }else {
        var content = $(e.target).closest(".comment").find(".comment_contents").text();
        var replace_html = "<textarea class='comment_contents sub_comment z-depth-1'>"+content+"</textarea>";
        $(e.target).closest(".comment").find(".comment_contents").replaceWith(replace_html);
        $(e.target).closest(".comment").find(".comment_edit_btn").attr("style", "display:block");

    }
})

$(document).on("click",".comment_edit_btn",function (e) {
    var id = $(e.target).closest(".comment").attr("id").slice(7)
    var content = $(e.target).closest(".comment").find(".comment_contents").val();
    $.ajax({
        "type":"PUT",
        "url":"/comment/edit",
        "contentType": "application/json;charset=utf-8",
        "data":JSON.stringify({
            "id":id,
            "content":content
        }),
        "success":function (res) {
            if (res == 1) {
                var replace_html = "<div class='comment_contents z-depth-1'>"+content+"</div>"
                $(e.target).closest(".comment").find(".comment_contents").replaceWith(replace_html);
                $(e.target).remove();
            }else if (res == 0) {
                layer.msg("更新失败，未知错误", {time:1000, icon:5, shift:6}, function () {

                });
            }else {
                layer.msg(res, {time:1000, icon:5, shift:6}, function () {

                });
            }
        }
    })
})
$(document).on("click",".comment_delete",function (e) {
    var id = $(e.target).closest(".comment").attr("id").slice(7)
    layer.confirm("确认删除该评论？",  {icon: 3, title:'删除评论'}, function(cindex){
        layer.close(cindex);
        $.ajax({
            "type":"DELETE",
            "url":"/comment/delete/"+id,
            "success":function (res) {
                if (res == 1) {
                    $(e.target).closest(".comment").remove();
                }else {
                    layer.msg("更新失败，未知错误", {time:1000, icon:5, shift:6}, function () {

                    });
                }
            }
        })
    }, function(cindex){
        layer.close(cindex);
    });

})

/**
 * 回復功能
 */
$(document).on("click",".comment_huifu",function (e) {
    var comment_ele = $(e.target).closest(".comment");
    var user_reply_to = $(comment_ele).find(".commenter").text();
    var main = $("#main_comment")
    //連接到個人信息界面,emmmmm
    // main.val("reply to <a href='#'>"+user_reply_to+"</a>:");
    main.val("reply to "+user_reply_to+":");
    $("#main_comment").focus();
    $("#main_comment").replaceWith(main);
})

$(document).on("click","a.js-open-viewer",function (e) {
    $(this).magnificPopup({
        type: 'image',
        closeOnContentClick: true,
        mainClass: 'mfp-img-mobile',
        image: {
            verticalFit: true
        },
    }).magnificPopup('open');
    e.preventDefault();
})



$("a.js-open-viewer").magnificPopup({
    type: 'image',
    closeOnContentClick: true,
    mainClass: 'mfp-img-mobile',
    image: {
        verticalFit: true
    },
});

