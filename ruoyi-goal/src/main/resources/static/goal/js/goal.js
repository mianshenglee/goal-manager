
function renderRows() {
    var config = {
        url: url,
        async: false,
        type: "get",
        dataType: "json",
        data: "",
        beforeSend: function () {
            $.modal.loading("正在处理中，请稍后...");
        },
        success: function(result) {
            var tmpl = $.templates("#goal-list-row-show"); // Get compiled template
            var data = {
                goalContent:"",
                curDate: getNowFormatMonth(),
                recordVersion:1,
                goalPhase:"month",
                goalType:goalType};           // Define data
            var html = tmpl.render(data);      // Render template using data - as HTML string
            $(this).parent(".goal-add-row").before(html);           // Insert HTML string into DOM
            //重新渲染控件
            $(".goal-process-slider").slider();
            bindTimeInput();
        }
    };
    $.ajax(config);

}
//更改进度条
function changeGoalProcess(rowElem,processValue) {
    //是否勾选”完成“
    rowElem.find(".slider-value-span").text(processValue);
    var $checkBoxElem = rowElem.find("input[name='goal-done']");
    if(processValue == 100){
        $checkBoxElem.prop("checked",true);
    }else{
        $checkBoxElem.prop("checked",false);
    }
    //记录版本
    var $recordVersionElem = rowElem.find("input[name='recordVersion']");
    var recordVersion = $recordVersionElem.val();
    $recordVersionElem.val(parseInt(recordVersion)+1);
}

//调用ajax后调用callback
function refreshTabAfterAjaxSuccess(url, data,filter,tabType) {
    var config = {
        url: url,
        async: false,
        type: "post",
        dataType: "json",
        data: data,
        beforeSend: function () {
            $.modal.loading("正在处理中，请稍后...");
        },
        success: function(result) {
            if (result.code == web_status.SUCCESS) {
                if(tabType == 'goal'){
                    refreshGoalTab(filter);
                } else if(tabType == 'idea'){
                    refreshIdeaTab(filter);
                }else if(tabType=='summary'){
                    refreshSummaryTab(filter);
                }else if(tabType=='hotspot'){
                    refreshHotspotTab(filter);
                }

                $.modal.alertSuccess(result.msg)
            } else if (result.code == web_status.WARNING) {
                $.modal.alertWarning(result.msg)
            } else {
                $.modal.alertError(result.msg);
            }
            $.modal.closeLoading();
        }
    };
    $.ajax(config);
}

function refreshGoalTab(filter) {
    var loadUrl = prefix +"/goaltab";
    loadUrl = buildLoadUrl(loadUrl,filter);
    $('#goal-tab').load(loadUrl,function (response,status) {
        if (status=="success") {
            //重新渲染控件
            $(".goal-process-slider").slider();
            bindTimeInput();
            //隐藏oper-info
            if($(".time-range").hasClass("active")){
                $(".oper-info").hide();
            }
        }
    });
}

function refreshIdeaTab(filter) {
    var loadUrl = prefix +"/ideatab";
    loadUrl = buildLoadUrl(loadUrl,filter);
    $('#idea-tab').load(loadUrl,function (response,status) {
        if (status=="success") {
            //重新渲染控件
            bindTimeInput();
            //隐藏oper-info
            if($(".time-range").hasClass("active")){
                $(".oper-info").hide();
            }
        }
    });
}

function refreshSummaryTab(filter) {
    var loadUrl = prefix +"/summarytab";
    loadUrl = buildLoadUrl(loadUrl,filter);
    $('#summary-tab').load(loadUrl,function (response,status) {
        if (status=="success") {
            //重新渲染控件
            bindTimeInput();
            //隐藏oper-info
            if($(".time-range").hasClass("active")){
                $(".oper-info").hide();
            }
        }
    });
}

function refreshHotspotTab(filter) {
    var loadUrl = prefix +"/hotspottab";
    loadUrl = buildLoadUrl(loadUrl,filter);
    $('#hotspot-tab').load(loadUrl,function (response,status) {
        if (status=="success") {
            //重新渲染控件
            bindTimeInput();
            //隐藏oper-info
            if($(".time-range").hasClass("active")){
                $(".oper-info").hide();
            }
        }
    });
}

function buildLoadUrl(loadUrl,filter) {
    var monthTag = filter.monthTag;
    var timeRange = filter.timeRange;
    var doneTag = filter.doneTag;
    var currentShowDate = filter.currentShowDate;
    if($.common.isNotEmpty(monthTag)){
        loadUrl += "?monthTag=" + monthTag;
    }
    if($.common.isNotEmpty(timeRange)){
        if(loadUrl.indexOf("?") > 0){
            loadUrl += "&timeRange=" + timeRange;
        }else{
            loadUrl += "?timeRange=" + timeRange;
        }
    }
    if($.common.isNotEmpty(doneTag)){
        if(loadUrl.indexOf("?") > 0){
            loadUrl += "&doneTag=" + doneTag;
        }else{
            loadUrl += "?doneTag=" + doneTag;
        }
    }
    if($.common.isNotEmpty(currentShowDate)){
        if(loadUrl.indexOf("?") > 0){
            loadUrl += "&currentShowDate=" + currentShowDate;
        }else{
            loadUrl += "?currentShowDate=" + currentShowDate;
        }
    }

    return loadUrl;
}

// 刷新iframe
function refreshTab() {
    var currentId = $('.page-tabs-content',parent.document).find('.active').attr('data-id');
    var target = $('.RuoYi_iframe[data-id="' + currentId + '"]',parent.document);
    var url = target.attr('src');
    target.attr('src', url).ready();
}

//获取当前时间，格式YYYY-MM
function getNowFormatMonth() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    var currentMonth = year + seperator1 + month;
    return currentMonth;
}
//绑定日期控件
function bindTimeInput() {
    // laydate time-input 时间控件绑定
    if ($(".time-input").length > 0) {
        layui.use('laydate', function () {
            var com = layui.laydate;
            $(".time-input").each(function (index, item) {
                var time = $(item);
                // 控制控件外观
                var type = time.attr("data-type") || 'date';
                // 控制回显格式
                var format = time.attr("data-format") || 'yyyy-MM-dd';
                // 控制日期控件按钮
                var buttons = time.attr("data-btn") || 'clear|now|confirm', newBtnArr = [];
                // 日期控件选择完成后回调处理
                var callback = time.attr("data-callback") || {};
                if (buttons) {
                    if (buttons.indexOf("|") > 0) {
                        var btnArr = buttons.split("|"), btnLen = btnArr.length;
                        for (var j = 0; j < btnLen; j++) {
                            if ("clear" === btnArr[j] || "now" === btnArr[j] || "confirm" === btnArr[j]) {
                                newBtnArr.push(btnArr[j]);
                            }
                        }
                    } else {
                        if ("clear" === buttons || "now" === buttons || "confirm" === buttons) {
                            newBtnArr.push(buttons);
                        }
                    }
                } else {
                    newBtnArr = ['clear', 'now', 'confirm'];
                }
                com.render({
                    elem: item,
                    theme: 'molv',
                    trigger: 'click',
                    type: type,
                    format: format,
                    btns: newBtnArr,
                    done: function (value, data) {
                        if (typeof window[callback] != 'undefined'
                            && window[callback] instanceof Function) {
                            window[callback](value, data);
                        }
                    }
                });
            });
        });
    }
}

function buildFilter(showCurrent) {
    //过滤
    var currentShowDate = $("#goal-tab").find(".currentShowDate").text();
    var monthTag = "";

    if($(".month-btn").hasClass("active")){
        monthTag = $(".month-btn").find(".active").attr("data-value");
        if(showCurrent){
            monthTag = "current_show";
        }
    }

    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var timeRange="";
    if($(".time-range").hasClass("active")){
        timeRange = startTime + "~" + endTime;
    }
    var doneTag = $(".tatus-btn-group").find(".active").attr("data-value");
    var filter = {
        monthTag:monthTag,
        timeRange:timeRange,
        doneTag:doneTag,
        currentShowDate:currentShowDate,
    }

    return filter;
}

function refreshAllTab(showCurrent) {
    //过滤
    var filter = buildFilter(showCurrent);
    refreshGoalTab(filter);
    refreshIdeaTab(filter);
    refreshSummaryTab(filter);
    refreshHotspotTab(filter);
}

