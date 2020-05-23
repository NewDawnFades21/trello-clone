//注册checkbox的监听器
$(document.body).on('change', '.ActiveCheck', (function () {
    var self = $(this);
    var id = self.attr("id");
    var description = self.parent().children("label").text();
    var isDone = self.prop("checked");
    var checklistId = $(this).closest(".tableDiv").attr("id").slice(8);
    $.ajax({
        url: "/todo/ajaxEdit",
        type: "PUT",
        data: {
            "id": id,
            "isDone": isDone,
            "description": description,
            "checklistId": checklistId
        },
        success: function (result) {
            $("#tablediv" + checklistId).html(result)
        },
    });
    return false
}))

$(document.body).on('submit', '.toDoCreateForm', (function () {
    var self = $(this);
    var description = self.children(".add_item").val();
    var checklistId = self.find("input[name='checklistId']").val();
    $.ajax({
        url: "/todo/ajaxCreate",
        type: "POST",
        data: {
            "checklistId": checklistId,
            "description": description,
            "isDone": false,
        },
        success: function (result) {
            $("#tablediv" + checklistId).html(result)
            self.children(".add_item").val("")
        },
    });
    return false;
}))


$(".add_list_form").find(".save").on("click", function () {
    var boardId = $(this).parents(".add_list_form").find("input[name='boardId']").val();
    var title = $(this).parents(".add_list_form").find("#add_list").val();
    $.ajax({
        url: "/deck/add",
        type: "POST",
        data: {
            "title": title,
            "boardId": boardId
        },
        success: function () {
            console.log("添加成功")
        },
        error: function () {
            console.log("添加失败")
        }
    })
})

// $(".add_card_form .card_save").on("click",function () {
//     var self = $(this)
//     var deckId = self.parent(".add_card_form").find("input[name='deckId']").val();
//     var title = self.parent(".add_card_form").find(".list_card_composer_textarea").val();
//     var userId = $("#userId").val()
//     $.ajax({
//         url:"/card/add",
//         type:"POST",
//         data:{
//             "title":title,
//             "deckId":deckId,
//             "userId":userId
//         },
//         success:function (card) {
//             console.log(card)
//             $(".add_card_form").css('display', 'none');
//             var card_text = $(e.target).parent(".add_card_form").find(".list_card_composer_textarea").val();
//             var $list_wrapper = $(e.target).closest(".list_wrapper");
//             var str = card_template({"value":card_text,"cardId":card.id});
//             $list_wrapper.find(".list_cards").append(str);
//             $(e.target).parent(".add_card_form").find(".list_card_composer_textarea").val("");
//             $(e.target).parents(".card_composer").find("a.add_card").css('display', 'block');
//         },
//         error:function () {
//             console.log("添加失败")
//         }
//     })
// })




