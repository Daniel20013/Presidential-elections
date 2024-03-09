function existenceOfData(event) {
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let adress = document.getElementById("adress").value;
    let email = document.getElementById("email").value;
    let age = document.getElementById("age").value;
    let password = document.getElementById("password").value;
    if (firstName === "" || lastName === "" || adress === "" || email === "" || age === "" || password === "") {
        alert("All fields must be completed.");
        event.preventDefault();
    } else {
        document.getElementById("registerForm").submit();
    }
}