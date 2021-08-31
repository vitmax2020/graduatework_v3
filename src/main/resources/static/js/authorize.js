async function authorize() {
    sessionStorage.removeItem('userKey');
    let usernameField = document.getElementById('username');
    let passwordField = document.getElementById('password');
    let username = usernameField.value;
    let password = passwordField.value;
    let request = {
        'username': username,
        'password': password
    }
    let response = await fetch('http://localhost:8080/api/authorize', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(request)

    });
    if (response.ok) {
        let answer = await response.json();
        sessionStorage.setItem('userKey', answer.token);
        window.location = "http://localhost:8080/single-post.html";
    } else if (response.status === 401) {
        alert('Ошибка имени пользователя или пароля');
    } else {
        alert(response.status);

    }
}