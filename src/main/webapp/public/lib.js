jQuery(function() {
    jQuery('li').each(
        function() {
            var el = jQuery(this);
            var type = el.data('task');
            var link = jQuery('.link', el);

            if (link[0] && link.is('A')) {
                var config = taskMap[type];
                var opt = jQuery.extend(true, {
                    data: {
                        app: '百度'
                    },
                    headers: {
                        'X-Requested-Token': csrfToken
                    }
                }, config);
                jQuery.ajax(link[0].href, opt).always(
                    function(data, _, xhr) {

                        csrfToken = csrfToken || xhr.getResponseHeader("X-Token");

                        if (_ === 'error') {
                            xhr = data;
                        }
                        if (data.status == 0 || xhr.responseText.indexOf('STATUS_OK') > -1) {
                            el.append('<span class="ok">' + (xhr.responseText) + '</span>');
                        } else {
                            el.append('<span class="error">' + (data.statusInfo || xhr.responseText || '未知异常') + '</span>');
                        }
                    });
            }
        });
});

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
}