jQuery(function() {
    jQuery('li').each(function() {
        var el = jQuery(this);
        var type = el.data('task');
        var link = jQuery('.link', el);

        if (link[0]) {
            var config = taskMap[type];
            var opt = jQuery.extend(true, {
                data: {
                    app: '百度'
                }
            }, config);
            jQuery.ajax(link[0].href, opt).always(function(data, _, xhr) {
                if (data.status == 0 || xhr.responseText.indexOf('STATUS_OK') > -1) {
                    el.append('<span class="ok">' + (xhr.responseText) + '</span>');
                } else {
                    console.log(data)
                    el.append('<span class="error">' + (data.statusInfo || '未知异常') + '</span>');
                }
            });
        }
    });
});