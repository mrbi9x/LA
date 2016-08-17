// Hàm xử lý lấy dữ liệu từ form "mainform"
function getInputForm() {
	var frm = document.inputform;
	if(frm == undefined) frm = document.mainform;
	return frm;
}
// Hàm xử lý khi click link sort màn hình ADM002
function sort(url, sortType, sortDetail) {
	var frm = getInputForm();
	frm.sortType.value = sortType;
	frm.sortDetail.value = sortDetail;
	frm.isSortClick.value = 'yes';
	frm.submit();
}
//Hàm xử lý khi click link paging màn hình ADM002
function paging(url,pageId) {
	var frm = getInputForm();
	frm.currentPage.value = pageId;
	frm.isPagingClick.value = 'yes';
	frm.submit();
}
// Hàm xử lý ẩn hiện 1 class
function toggleElement(toggleClass) {
        $("." + toggleClass).toggle();
}
// Hàm xử lý validate khi chọn trình độ tiếng nhật Export CSV
function validateExport() {
	 if (($("input[name*='japanLevel']:checked").length) > 0 || $("input[name='ALL']:checked").length > 0) {
	        return true;
	    }else {
	    	$("#errMsgExport").text("Bạn phải chọn ít nhất 1 điều kiện để Export");
	    	return false;
	    }
}