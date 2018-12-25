/**
 * 生成分页栏
 * @param $pagination
 * @param {string} url
 * @param {array} pageBtnText
 * @param {int} pageIndex
 * @param {int} pageCount
 */
function generatePageBar($pagination, pageBtnText, pageIndex, pageCount) {
	$pagination.empty();
	//插入“上一页”
	let adtClass1 = pageIndex === 1 ? " disabled" : "";
	$pagination.append(`
							<li class="page-item">
								<a class="page-link${adtClass1}" id="mo-pre-page" 
									href="#">上一页</a>
							</li>
	`);
	//插入索引
	for(let text of pageBtnText) {
		let index = text === "..." ? 0 : parseInt(text);
		let adtClass2 = pageIndex === index ? " active" : "";
		$pagination.append(`
							<li class="page-item">
								<a class="page-link${adtClass2} mo-change-page" 
									href="#">${text}</a>
							</li>
		`);
	}
	//插入“下一页”
	let adtClass3 = pageIndex === pageCount ? " disabled" : "";
	$pagination.append(`
							<li class="page-item">
								<a class="page-link${adtClass3}" id="mo-next-page" 
									href="#">下一页</a>
							</li>
	`);
}

/**
 * 绑定分页栏按钮
 * @param {string} url
 */
function bindingPageBar(url) {

}

export {generatePageBar};

