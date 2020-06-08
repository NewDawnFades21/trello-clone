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

