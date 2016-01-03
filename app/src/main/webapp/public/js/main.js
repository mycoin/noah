// JSON问题
Date.prototype.toJSON = function() {
    return isFinite(this.valueOf()) ? this.getFullYear() + '-' +
        pad(this.getMonth() + 1) + '-' +
        pad(this.getDate()) + ' ' +
        pad(this.getHours()) + ':' +
        pad(this.getMinutes()) + ':' +
        pad(this.getSeconds()) : null;
};

function pad(n) {
    // Format integers to have at least two digits.
    return n < 10 ? '0' + n : n;
}

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
                        console.log(data);
                        if (_ === 'error') {
                            xhr = data;
                        }
                        if (data.status == 0 || xhr.responseText.indexOf('status-ok') > -1) {
                            el.append('<span class="ok">' + (xhr.responseText) + '</span>');
                        } else {
                            el.append('<span class="error">' + (data.statusInfo || xhr.responseText || '未知异常') + '</span>');
                        }
                    });
            }
        });
});