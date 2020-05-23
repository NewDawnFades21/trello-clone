$(".card_title_in_modal").on("change", "input", function () {
    var card_title = $(this).val();
    var card_id = $(this).parent(".card_title_in_modal").find("input[name='id']").val();
    $.ajax({
        url: "/card/update",
        method: "PUT",
        data: {
            "title": card_title,
            "id": card_id,
        }
    })
})

