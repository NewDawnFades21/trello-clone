var TODO = (function (window) {

    'use strict';

    var list_html = "<div class='list_wrapper'>" +
        "<div class='list_content z-depth-1'>" +
        "<div class='list_header'>" +
        "<textarea class='list_header_name'>{{input-value}}</textarea>" +
        "</div>" +
        "<div class='list_cards'></div>" +
        "<div class='card_composer'>" +
        "<div class='add_card_form'>" +
        "<textarea class='list_card_composer_textarea'></textarea>" +
        "<a class='waves-effect  waves-light btn card_save blue-grey lighten-5'>save</a>" +
        "<a class='waves-effect waves-light btn card_cancel blue-grey lighten-5'>cancel</a>" +
        "</div>" +
        "<a class='add_card' href='#''>Add a Card...</a>" +
        "</div>" +
        "</div>" +
        "</div>"

    var list_template = Handlebars.compile(list_html);

    var card_html = "<div class='list_card'>" +
        "<div class='list_card_detail'>" +
        "<a class='list_card_title modal-trigger modalLink' dir='auto' href='#modalLayer' id='{{card_id}}' >{{value}}</a>" +
        "</div>" +
        "</div>";

    var card_template = Handlebars.compile(card_html);

    var checklist_html = "<hr>" +
        "<p>{{title}}</p>" +
        "<div class='tableDiv' id='tablediv{{id}}'></div>" +
        "<form class='toDoCreateForm'>" +
        "<input type='hidden' name='checklistId' value='{{id}}'>" +
        "<input type='hidden' name='cardId' value='{{cardId}}'>" +
        "<input class='add_item'>" +
        "<div class='invalid-feedback'></div>" +
        "</form>";

    var checklist_template = Handlebars.compile(checklist_html);

    var attachment_html =
        "<div class='thumbnail'>" +
        "<img id='theImg' class='img-thumbnail' src='/uploads/{{filename}}'/>" +
        "</div>";
    var attachment_tamplate = Handlebars.compile(attachment_html);

    var comment_html = "<div class='comment' id='comment{{id}}'>" +
        "<div class='commenter'>{{userId}}</div>" +
        "<div class='comment_contents z-depth-1'>{{content}}</div>" +
        "<div class='comment_date'> {{formatTime createTime 'YYYY-MM-DD hh:mm:ss'}} </div>" +
        "<div class='comment_reply'> Reply</div>" +
        "</div>";

    var comment_template = Handlebars.compile(comment_html);

    function init() {
        $("#board_canvas").on("click", ".modalLink", show_modal);

        $(".btn-floating").on("click", create_list);
        $(".save").on("click", add_list);
        $("#board_canvas").on("click", ".add_card", add_card);
        $("#board_canvas").on("click", ".card_save", card_save);
        $("#board_canvas").on("click", ".card_cancel", card_cancel);
        $("#sortable").disableSelection();
        $(".add_list a.cancel").on("click", cancel);
        $(".add_list").removeClass("ui-sortable-handle");


        $(".list_header_name").on("change", change_deck_title);
        $(".attach_from_computer").on("click", file_upload);
        $(".comment_send").on("click", add_comment);
        $("#sortable").sortable({
            placeholder: "ui-state-highlight",
            cancel: ".add_list"
        });
        $("#board_canvas").sortable();
        $("#board_canvas").disableSelection();
        $(".members_btn").on("click", search_member);
        $(".due_date_btn").on("click", setting_date);
        $(".checklist_btn").on("click", setting_checklist);
        $("#attach_btn").on("click", addAttachment);
        $("#fileUpload").on("change", onChangeFileUpload);
        $(".add_checklist_btn").on("click", addCheckList);
        $(".file_attachment").on("click", setting_attachment);
        $(".datepicker").pickadate({
            selectMonths: true,
            selectYears: 15
        });
        $(".close_button").on("click", close_modal);
        $(".shadow_body").on("click", close_modal);
        $('.modal-trigger').leanModal();
    }

    function close_modal() {
        $("#modalLayer").fadeOut("slow");
        $(".shadow_body").fadeOut("slow");
    }

    function setting_attachment() {

        if ($(".modal_for_attachment").hasClass("clicked")) {
            $(".modal_for_attachment").removeClass("clicked").slideUp();
            return;
        }
        $(".modal_for_attachment").addClass("clicked").slideDown();
    }

    function setting_date() {

        if ($(".modal_for_due_date").hasClass("clicked")) {
            $(".modal_for_due_date").removeClass("clicked").slideUp();
            return;
        }

        $(".modal_for_due_date").addClass("clicked").slideDown();

    }

    function setting_checklist() {
        if ($(".modal_for_checklist").hasClass("clicked")) {
            $(".modal_for_checklist").removeClass("clicked").slideUp();
            return;
        }

        $(".modal_for_checklist").addClass("clicked").slideDown();
    }

    function search_member() {

        console.log("asd");
        if ($(".modal_for_members").hasClass("clicked")) {
            $(".modal_for_members").removeClass("clicked").slideUp();
            return;
        }

        $(".modal_for_members").addClass("clicked").slideDown();
    }

    function change_deck_title(e) {
        var title = $(e.target).val()
        // alert(title)
        var boardId = $(e.target).parents(".list_header").find("input[name='boardId']").val();
        var id = $(e.target).parents(".list_header").find("input[name='id']").val();
        $.ajax({
            url: "/deck/update",
            type: "PUT",
            data: {
                "id": id,
                "title": title,
                "boardId": boardId
            },
            success: function () {
                console.log("修改成功")
            },
            error: function () {
                console.log("修改失败")
            }
        })
    }

    function add_comment(e) {
        var user_id = $(".comment_frame").find("input[name='userId']").val()
        var card_id = $(".card_title_in_modal").find("input[name='id']").val();
        var content = $(".comment_contents").val()
        $.ajax({
            url: "/comment/add",
            type: "POST",
            data: {
                "userId": user_id,
                "cardId": card_id,
                "content": content
            },
            success: function (res) {
                console.log("comment:"+res)
                $(".comment").html($(comment_template(res))).appendTo(".comments")
                $(".comment_contents").val("")
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // $("#result").html(jqXHR.responseText);
                console.log("ERROR : ", jqXHR.responseText);
                // $("#submitButton").prop("disabled", false);
                layer.alert("Something goes wrong...", function (index) {
                    // 回调方法
                    layer.close(index);
                });

            }
        })

    }

    function addCheckList(e) {
        var user_id = $(".comment_frame").find("input[name='userId']").val()
        var card_id = $(".card_title_in_modal").find("input[name='id']").val();
        var title = $("#checklist").val()
        $.ajax({
            url: "/checklist/add",
            type: "POST",
            data: {
                "userId": user_id,
                "cardId": card_id,
                "title": title
            },
            success: function (checklist) {
                console.log(checklist)
                $(checklist_template(checklist)).appendTo("#checklists");
                $("#checklist").val("");
            }
        })
    }

    function onChangeFileUpload(e) {
        // Stop default form Submit.
        e.preventDefault();
        var card_id = $(".card_title_in_modal").find("input[name='id']").val();
        $('<input>').attr({
            type: 'hidden',
            id: 'cardId',
            name: 'cardId',
            value: card_id
        }).appendTo('#attach_form');
        // Get form
        var form = $('#attach_form')[0];

        var data = new FormData(form);
        if (data != null) {

            $.ajax({
                type: "POST",
                url: "/uploadMultiFiles",
                data: data,
                // prevent jQuery from automatically transforming the data into a query string
                processData: false,
                contentType: false,
                cache: false,
                timeout: 1000000,
                success: function (data, textStatus, jqXHR) {
                    $(attachment_tamplate({"filename": data})).appendTo(".modal_content");
                    console.log("SUCCESS : ", data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    // $("#result").html(jqXHR.responseText);
                    console.log("ERROR : ", jqXHR.responseText);
                    // $("#submitButton").prop("disabled", false);
                    layer.alert("Something goes wrong...", function (index) {
                        // 回调方法
                        layer.close(index);
                    });

                }
            });
        }

    }

    //上传文件
    function addAttachment(e) {
        var attach_url;
        attach_url = $(e.target).parent().find(".link_for_attachment").val()
        if (attach_url != '') {
            $.ajax({
                url: "/downloadFile",
                type: "POST",
                data: JSON.stringify({"attach_url": attach_url}),
                // prevent jQuery from automatically transforming the data into a query string
                processData: false,
                contentType: 'application/json',
                cache: false,
                timeout: 1000000,
                success: function (data, textStatus, jqXHR) {
                    $(attachment_tamplate({"filename": data})).appendTo(".modal_content");
                    console.log("SUCCESS : ", data);
                    $(e.target).parent().find(".link_for_attachment").val("")
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    // $("#result").html(jqXHR.responseText);
                    console.log("ERROR : ", jqXHR.responseText);
                    // $("#submitButton").prop("disabled", false);
                    layer.msg("403ERROR", {time: 2000, icon: 5, shift: 6}, function () {

                    });
                    $(e.target).parent().find(".link_for_attachment").val("")

                }
            })
        }
    }

    function month_written_english(month) {

        if (month === 1) {
            return "Jan";
        } else if (month === 2) {
            return "Feb";
        } else if (month === 3) {
            return "Mar";
        } else if (month === 4) {
            return "Apr";
        } else if (month === 5) {
            return "May";
        } else if (month === 6) {
            return "Jun";
        } else if (month === 7) {
            return "July";
        } else if (month === 8) {
            return "Aug";
        } else if (month === 9) {
            return "Sep";
        } else if (month === 10) {
            return "Oct";
        } else if (month === 11) {
            return "Nov";
        } else if (month === 12) {
            return "Dec";
        }
    }

    function file_upload() {
        $("#fileUpload").trigger("click");
    }

    function show_modal(e) {
        $(".shadow_body").fadeIn("slow");
        $("#modalLayer").fadeIn("slow");
        getCardInfo(e)
    }

    function getCardInfo(e) {
        $(e.target).attr("visited",true)
        var cardId = $(e.target).attr("cardId");
        if (cardId == null) {
            cardId = $(e.target).attr("id")
        }
        $.ajax({
            url: "/getCardInfo",
            data: {
                "cardId": cardId
            },
            success: function (res) {
                console.log(res)
                var cardId = res.id
                $(".card_title_in_modal input[name='id']").val(cardId);
                var title = res.title;
                $(".card_title_in_modal input[name='title']").val(title);
                var list_name = $(e.target).closest(".list_content").find(".list_header_name").val();
                $(".list_name").text(list_name);
                // console.log(JSON.stringify(res.checklists))
                // $("#checklists").html($(checklists_template({Checklists: res.checklists})))
                // console.log(JSON.stringify(res.comments))
                $(".comments").html("")
                $("#checklists").html("")
                $("#attachments").html("")
                for (var i = 0; i < res.comments.length; i++){
                    $(comment_template(res.comments[i])).appendTo(".comments")
                }
                for (var i = 0; i < res.checklists.length; i++) {
                    $(checklist_template(res.checklists[i])).appendTo("#checklists")
                    var checklistId = res.checklists[i].id
                    $("#tablediv" + checklistId).load("/todo/buildToDoTable", {checklistId: checklistId})
                }
                for (var i = 0; i < res.attachments.length; i++){
                    $(attachment_tamplate({"filename": res.attachments[i].filename})).appendTo("#attachments");
                }
            }

        })
    }


    function card_cancel(e) {

        $(e.target).closest(".card_composer .add_card_form").css('display', 'none');
        $(e.target).closest(".card_composer").find("a.add_card").css('display', 'block');
    }

    function cancel() {

        $(".btn-floating").css('display', 'block');
        $(".add_list_form").css('display', 'none');

    }

    function modal() {
        $('.modal-trigger').leanModal();
    }

    function create_list() {

        $(".btn-floating").css('display', 'none');
        $(".add_list_form").css('display', 'block');
    }

    function add_card(e) {
        // $(this).closest(".card_composer").find()
        $(e.target).parent().find(".add_card_form").css('display', 'block');
        $(e.target).parent().find("a.add_card").css('display', 'none');
    }

    function card_save(e) {

        var self = $(this)
        var deckId = self.parent(".add_card_form").find("input[name='deckId']").val();
        var title = self.parent(".add_card_form").find(".list_card_composer_textarea").val();
        var userId = $("#userId").val()
        $.ajax({
            url: "/card/add",
            type: "POST",
            data: {
                "title": title,
                "deckId": deckId,
                "userId": userId
            },
            success: function (card) {
                $(".add_card_form").css('display', 'none');
                var $list_wrapper = $(e.target).closest(".list_wrapper");
                var str = card_template({"card_id":card.id,"value": card.title});
                $list_wrapper.find(".list_cards").append(str);
                $(e.target).parent(".add_card_form").find(".list_card_composer_textarea").val("");
                $(e.target).parents(".card_composer").find("a.add_card").css('display', 'block');
                // window.location.href="/board/"+userId;
            },
            error: function () {
                console.log("添加失败")
            }
        })
    }

    function add_list() {

        var list_name = $("#add_list").val();
        var str = list_html.replace(/\{\{input-value\}\}/gi, list_name);
        $(".add_list").before(str);
        $("#add_list").val("");
        $(".add_list_form").css('display', 'none');
        $(".btn-floating").css('display', 'block');
    }

    return {
        "init": init
    }
})(window);

$(function () {
    TODO.init();
});

