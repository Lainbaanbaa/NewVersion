let orignalSetItem = sessionStorage.setItem;
sessionStorage.setItem = function (key, newValue) {
    let setPartEvent = new Event("setItemEvent");
    setPartEvent.newValue = newValue;
    window.dispatchEvent(setPartEvent);
    orignalSetItem.apply(this, arguments);
};
let part = sessionStorage.getItem("part");
if (part) {
    let cartNum = JSON.parse(part).length;
    document.getElementById("lblCartCount").append(cartNum);
} else {
    document.getElementById("lblCartCount").append('');
    window.addEventListener("setPartEvent", function (e) {
        if ((e.key = "part")) {
            let _this = sessionStorage.getItem("part");
            if (_this !== e.newValue) {
                if (e.newValue) {
                    let cartNum = JSON.parse(_this).length;
                    document.getElementById("lblCartCount").append(cartNum);
                }
            }
            document.getElementById("lblCartCount").append('');
        }
    });
}
