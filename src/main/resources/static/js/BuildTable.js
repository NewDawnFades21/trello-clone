$(document).on("click", ".add_checklist_btn", function () {
    var url = '/todo/buildToDoTable';
    $(".tableDiv").load(url);
});
