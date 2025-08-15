function ready_to_set_password() {
    document.getElementById('st1').style.display = 'none';
    document.getElementById('st2').style.display = 'block';
    document.getElementById('st2ls').classList.add('selected');
    document.getElementById('st2ls').classList.remove('unselected');
    document.getElementById('st1ls').classList.remove('selected');
    document.getElementById('st1ls').classList.add('unselected');
    document.title = "设置所有者密码 - BitVX AppServer";
}

function set_password() {
    var v1 = document.getElementById('password').value;
    var v2 = document.getElementById('password').value;
    if (v1 === v2) {
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 201) {
                ready_to_finish();
            } else window.alert("你说的对但是请修复你的服务器。with error code " + xmlhttp.status);
        }
    } else window.alert("密码前后不一致，请重试哦亲。");
    xmlhttp.open("GET", "/setPassword/?password=" + v1);
    xmlhttp.send();
}

function ready_to_finish() {
    document.getElementById('st2').style.display = 'none';
    document.getElementById('st2ls').classList.remove('selected');
    document.getElementById('st2ls').classList.add('unselected');
    document.getElementById('st3').style.display = 'block';
    document.getElementById('st3ls').classList.add('selected');
    document.getElementById('st3ls').classList.remove('unselected');
    document.title = "恭喜完成向导！";
}

function go_dashboard() {
    location.assign("/dashboard");
}