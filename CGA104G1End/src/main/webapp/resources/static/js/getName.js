/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

const dataUrl = "/CGA104G1/GetNameServlet?action=getMemName";
$.ajax({
    url: dataUrl,
    method: 'GET',
    dataType: 'json',
    data: '',
    async: true,
    success: res => {
        $('#memberImg').html(res.memberImg).css("margin-left", "20px")
        $('#profileImg').html(res.memberImg)
        $('#memberName').text(res.memberName)
        if (res.memberName === '你好 訪客') {
            $('#myDropdown').append('<li><a class="dropdown-item" href="/CGA104G1/frontend/memLogin/login.jsp">登入會員</a></li>')
            $('#memDrop').prepend('<li><a class="dropdown-item" href="/CGA104G1/frontend/memLogin/login.jsp">登入會員</a></li>')
        } else {
            $('#myDropdown').append('<li><button class="dropdown-item" id="logout" onclick="logOut()">登出</button></li>')
            $('#memDrop').prepend('<li><button class="dropdown-item" id="logout" onclick="logOut()">登出</button></li>')
        }
    },
    error: err => {
        console.log(err)
    },
})

function logOut() {
    const dataUrl = "/CGA104G1/GetNameServlet?action=logout";
    $.ajax({
        url: dataUrl,
        method: 'post',
        dataType: 'json',
        data: '',
        async: true,
        success: res => {
            console.log(res)
            if (res.success === 'success') {
                const Toast = Swal.mixin({
                    toast: true,
                    position: 'top-end',
                    showConfirmButton: false,
                    timer: 1000,
                    timerProgressBar: true,
                    didOpen: (toast) => {
                        toast.addEventListener('mouseenter', Swal.stopTimer)
                        toast.addEventListener('mouseleave', Swal.resumeTimer)
                    }
                })

                Toast.fire({
                    icon: 'success',
                    title: '已成功登出'
                })
                setTimeout(function () {
                    window.location.assign('/CGA104G1/frontend/index.html')
                }, 1001)
            }
        },
        error: err => {
            console.log(err)
        },
    })
}
