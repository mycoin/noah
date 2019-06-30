F = (function() {
	var context = function(name, defination) {
		if (arguments.length > 1) {
			this[name] = defination;
		} else if (name) {
			if (typeof name === 'object') {
				for ( var key in name) {
					if (name.hasOwnProperty(key)) {
						this[key] = name[key];
					}
				}
			} else {
				return this[name];
			}
		}
	};
	var extendIf = function(sourceMap, target) {
		var returnMap = {};
		for ( var key in target) {
			if (typeof sourceMap[key] === 'undefined') {
				returnMap[key] = target[key]
			} else {
				returnMap[key] = sourceMap[key]
			}

		}
		return returnMap;
	}
	return {
		context : context,
		extendIf : extendIf,
	}
})();

F.context({
	random: function randomString(len) {
	　　len = len || 32;
	　　var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	　　var maxPos = chars.length;
	　　var pwd = '';
	　　for (i = 0; i < len; i++) {
	　　　　pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	　　}
	　　return pwd;
	},
	sendRequest: function(url, options) {
		var handleResponse = function(error, data) {
			if (typeof options.callback === 'function') {
				options.callback(error, data);
			}
		};
		var config = F.extendIf(options, {
			contentType : 'application/json',
			dataType : 'json',
			data : {},
			method : 'POST',
			success : function(response) {
				handleResponse(null, response);
			},
			error : function(error) {
				handleResponse(error);
			}
		})
		if (config.contentType === 'application/json') {
			config.data = JSON.stringify(config.data);
		}
		jQuery.ajax(url, config)
	}

})

