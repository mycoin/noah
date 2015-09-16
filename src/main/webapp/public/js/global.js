var F = {};

/**
 * 更满足基本要求的基类
 * 
 * @return {Function}
 */
F.Class = function(prototype, Parent) {
	Parent = Parent || function() {
	};

	// 可以不通过new关键字创建对象
	Parent.create = function() {
		var inst = new Parent();
		if (typeof inst.init === 'function') {
			inst.init.apply(inst, arguments);
			inst.init = null;
		}
		return inst;
	};

	// 挂载基类函数
	prototype.Class = Parent;
	// 拓展原型链
	jQuery.extend(Parent.prototype, prototype);
	// 返回包装类
	return Parent;
};

// 格式化字符串
F.format = function(string, data) {
	data = data || {};

	return ('' + string).replace(/\{(=|:)?(\w*)\}/g,
			function(match, type, key) {
				if (data[key] === undefined) {
					return '';
				} else if (type === '=') {
					return data[key];
				} else if (type === ':') {
					return encodeURIComponent(data[key]);
				}
				return F.encode(data[key]);
			});
};

/**
 * 批量绑定事件
 * 
 * @param {element}
 *            element 待监听的节点对象
 * @param {string}
 *            type 事件名称
 * @param {function}
 *            listener 回调函数
 * @return {this}
 */
F.bind = function(element, type, listener) {
	if (typeof listenner === 'function') {
		if (type.indexOf(' ') === -1) {
			element.bind(type, listener);
		} else {
			var index = type.indexOf(' ');
			element.on(type.substr(0, index), type.substr(index + 1), listener);
		}
	} else {
		for ( var k in type) { // jshint ignore:line
			this.bind(element, k, type[k]);
		}
	}

	return element;
};

/**
 * Copy properties from the source object to the target object
 * 
 * @public
 * @param {Object}
 *            target the target object
 * @param {Object}
 *            source the source object
 * @param {Boolean}
 *            overwrite if overwrite the same property, default 'true'
 * @return target
 */
F.extend = function(target, source, overwrite) {
	if (undefined === overwrite) {
		overwrite = true;
	}
	for ( var key in source || {}) {
		if (source.hasOwnProperty(key)
				&& (overwrite || !target.hasOwnProperty(key))) {
			target[key] = source[key];
		}
	}
	return target;
};

/**
 * encoding the target string from HTML
 * 
 * @function
 * @public
 * 
 * @param source
 *            {String} the target string
 * @return {string} safe source
 */
F.encode = function(source) {
	source = ('' + source).replace(/&/g, '&amp;').replace(/\x3C/g, '&lt;')
			.replace(/\x3E/g, '&gt;').replace(/"/g, '&quot;').replace(/'/g,
					'&#39;');
	return source;
};