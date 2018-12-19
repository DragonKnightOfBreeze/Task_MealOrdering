let category = ["全部分类", "素食", "荤食", "甜点", "黑暗料理"];

$(function() {
    //DONE 得到最近公告和本周热卖
    $.get("/mealordering/index",
        /**
         * @param data.weekHotMeals
         * @param data.notice
         */
        function(data) {
            let $notice = $("#_art-latest-notice");
            $notice.find("h4").text(data.notice.title);
            $notice.find("p").text(data.notice.detail);
            let $weekHot = $("._media-week-hot");
            $weekHot.each(function(index) {
                $(this).find("img").prop("src", data.weekHotMeals[index][2]);
                $(this).find("h4").text(data.weekHotMeals[index][1]);
                $(this).find("p").text(data.weekHotMeals[index][3]);
                $(this).find("span").text(data.weekHotMeals[index][4]);
            })
        }, "json"
    );
});
