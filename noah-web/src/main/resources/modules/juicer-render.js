juicer.set('strip', false);
juicer.set('cache', false);

function render(template, data, location) {
	return juicer(template, data);
}
