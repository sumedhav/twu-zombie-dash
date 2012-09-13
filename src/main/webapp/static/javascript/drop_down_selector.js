$(document).ready(function(){
    var option=$("#country").text();
    option=option.replace(/'/g,"")
                    .replace(/,/g,"")
                    .replace(/\s/g,"")
                    .replace(/-/g,"")
                    .replace(/\(/g,"")
                    .replace(/\)/g,"")
                    .replace(/\./g,"");
    $("#"+option).attr("selected","true");
});

