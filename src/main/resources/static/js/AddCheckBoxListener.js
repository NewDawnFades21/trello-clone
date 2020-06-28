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


$(".add_list_form").find(".save").on("click", function (e) {
    var boardId = $(e.target).parents(".add_list_form").find("input[name='boardId']").val();
    var title = $(e.target).parents(".add_list_form").find("#add_list").val();
    $.ajax({
        url: "/deck/add",
        type: "POST",
        data: {
            "title": title,
            "boardId": boardId
        },
        success: function (deck) {
            console.log("deck添加成功")
        //    刷新頁面，不然有bug
            location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // $("#result").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            // $("#submitButton").prop("disabled", false);
            layer.msg(jqXHR.responseText, {time: 2000, icon: 5, shift: 6}, function () {

            });

        }
    })
})






