function dateForSubApplications(event) {
    let firstName = document.getElementById("firstNameC").value;
    let lastName = document.getElementById("lastNameC").value;
    let adress = document.getElementById("adressC").value;
    let email = document.getElementById("emailC").value;
    let age = document.getElementById("ageC").value;
    let citizenship = document.getElementById("citizenshipC").value;
    let shortDescriptionC = document.getElementById("shortDescriptionC").value;
    if (firstName === "" || lastName === "" || adress === "" || email === "" || age === "" || citizenship === "" || shortDescriptionC === "") {
        event.preventDefault();
        alert("All fields must be completed.");
    } else {
        document.getElementById("registerForm").submit();
    }
}