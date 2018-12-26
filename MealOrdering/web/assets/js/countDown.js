let interval;

/**
 * 开始倒计时，并在结束后跳转。
 * @param $elem
 * @param url {string} 跳转到的地址（相对于Web项目，不以/开头）
 */
$(
	function countDown() {
		interval = window.setInterval(function() {
			let $elem = $("#mo_second");
			if($elem.text() != 0) {
				$elem.text($elem.text() - 1);
			} else {
				window.clearInterval(interval);
				//得到目录地址名，例如：/client
				const pathName = window.location.pathname.substring(1);
				//得到目录名，例如：client
				const webName = pathName === '' ? '' : pathName.split('/', 1)[0];
				//得到完整的用于访问的url，例如：http://localhost:8080/bookstore/index.html
				location.href = `${window.location.protocol}//${window.location.host}/${webName}/index.jsp`;
			}
		}, 1000);
	}
);

