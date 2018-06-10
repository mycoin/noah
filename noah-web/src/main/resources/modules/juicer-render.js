juicer.set('strip', false);
juicer.set('cache', false);

function render(template, data, location) {
	try {
		return juicer(template, data);
	} catch(e) {
		return "error"
	}
}
