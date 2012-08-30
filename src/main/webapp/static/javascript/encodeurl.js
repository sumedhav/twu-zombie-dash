function encodeUrl(url1,url2,url3) {
    var finalUrl = encodeURIComponent(url1) + url2 +
        encodeURIComponent(url3);
    window.location.href = finalUrl;
}