Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});

Handlebars.registerHelper('link', function(my_link) {

    var url = Handlebars.escapeExpression(my_link);

    return new Handlebars.SafeString(url);

});