jQuery.extend({
    getIp : function() {
        var ip = null;
        $.ajax({
            url : "http://freegeoip.net/json/",
            async : false,
            type : 'get',
            dataType : "json",
            success : function(data) {
                ip = data.ip;
            }
        });
        return ip;
    },
    sendPostData : function(ip, lid) {
         	$.ajax({
                url:'http://192.168.0.15:8080/Analytics/addVisiter.d',
                headers : {"Access-Control-Allow-Origin" : "*"},
                type:'post',
                useDefaultXhrHeader: false,
                crossDomain: true,
                //dataType: 'json',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: {'ip'     :   ip,
                       'linkId' :   lid},
                success: function(data) {
                    console.log('success ' + data);
                },
                error: function(xhr, str){ console.log('error ' + xhr.responseText);}
		});
        return false;
    }
});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
$(window).on("load", function() {
    
    var lid = getUrlParameter("lid"),
        ip = $.getIp();
    
    if(typeof lid !== 'undefined' && lid !== null) {
        $.sendPostData(ip, lid);
    }
    
    
});