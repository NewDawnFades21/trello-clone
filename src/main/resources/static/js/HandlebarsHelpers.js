Handlebars.registerHelper('formatTime', function (date, format) {
    var mmnt = moment(date);
    return mmnt.format(format);
});

Handlebars.registerHelper('link', function(my_link) {

    return new Handlebars.SafeString(my_link);

});