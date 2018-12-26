// let weekHotMeals_id = [];
// let latestNotice_id = null;
//
// $(function() {
// 	//DONE 定位最近的公告和本周推荐，插入内容
//     $.get("/mealordering/index",
//         /** @property data.notice */
//         /** @property data.weekHotMeals */
//         function(data) {
// 					let $notice = $("#mo_latest-notice");
//             latestNotice_id = data.notice.id;
// 					$notice.find("h3").text(data.notice.title);
//             $notice.find("p").text(data.notice.detail);
//
// 					let $weekHot = $(".mo_media-week-hot");
//             for(let weekHot of data.weekHotMeals) {
//                 weekHotMeals_id.add(weekHot[0]);
// 							$(this).find("h3").text(weekHot[1]);
//                 $(this).find("img").prop("src", weekHot[2]);
//                 $(this).find("p").text(weekHot[3]);
//                 $(this).find("span").text(weekHot[4]);
//             }
//         }, "json"
//     );
// });
