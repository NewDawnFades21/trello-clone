//文档加载完后才执行js代码
$(document).ready(function () {

    //注册checkbox的监听器
    $(".ActiveCheck").change(function () {
        var self = $(this);
        var id = self.attr("id");
        var value = self.prop("checked");
        $.ajax({
            url: "/ToDoes/AjaxEdit",
            type: "POST",
            data: {
                "id": id,
                "value": value
            },
            success: function (result) {
                $("#tableDiv").html(result);
            },
        });
    });

});