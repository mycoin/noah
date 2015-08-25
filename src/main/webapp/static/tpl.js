var tmpl = (function() {
	var cache = {};
	return function tmpl(str, data, key) {
		var strIsKey = !/\W/.test(str);
		key = key || (strIsKey ? str : null);
		var fn = key ? cache[key] = cache[key] || tmpl(strIsKey ? document.getElementById(str).innerHTML : str) :
			new Function("obj", "var p=[],print=function(){p.push.apply(p,arguments);}; with(obj){p.push('" + str
				.replace(/[\r\t\n]/g, " ")
				.split("\\'").join("\\\\'")
				.split("'").join("\\'")
				.split("<%").join("\t")
				.replace(/\t=(.*?)%>/g, "',$1,'")
				.split("\t").join("');")
				.split("%>").join("p.push('") + "');}return p.join('');");
		return data ? fn(data) : fn;
	};
})();