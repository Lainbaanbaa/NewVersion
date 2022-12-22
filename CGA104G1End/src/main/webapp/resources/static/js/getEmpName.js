/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

const dataUrl = "/CGA104G1/CheckLoginServlet?action=getEmpName";
$.ajax({
    url: dataUrl,
    method: 'POST',
    dataType: 'json',
    data: '',
    async: true,
    success: res => {
        console.log(res)
        if (res.empName === '你好 訪客') {
            $('#myDropdown').append('<a class="d-flex log" href="/CGA104G1/backend/login/backLogin.jsp"><button class="btn btn-warning">員工登入</button></a>')
        } else {
            $('#myDropdown').append(`<div class="identity">
             		<span class="name" id="myName"></span>&ensp;
					<FORM ACTION="/CGA104G1/backend/login/LoginServlet" method="post" style="display:inline-block">
						<input type="hidden" name="action" value="logout">
						<button class="btn btn-warning" type=submit>登出</button>
					</FORM>
					</div>`)
            $('#myName').text(res.empName)
        }
    },
    error: err => {
        console.log(err)
    },
})

