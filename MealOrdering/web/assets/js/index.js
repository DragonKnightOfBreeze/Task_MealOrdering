let weekHotMeals_id = [];
let latestNotice_id = null;

$(function() {
    //DONE 得到最近公告和本周热卖
    $.get("/mealordering/index",
        /** @property data.notice */
        /** @property data.weekHotMeals */
        function(data) {
            let $notice = $("#_art-latest-notice");
            latestNotice_id = data.notice.id;
            $notice.find("h4").text(data.notice.title);
            $notice.find("p").text(data.notice.detail);

            let $weekHot = $("._media-week-hot");
            for(let weekHot of data.weekHotMeals) {
                weekHotMeals_id.add(weekHot[0]);
                $(this).find("h4").text(weekHot[1]);
                $(this).find("img").prop("src", weekHot[2]);
                $(this).find("p").text(weekHot[3]);
                $(this).find("span").text(weekHot[4]);
            }
        }, "json"
    );
});
