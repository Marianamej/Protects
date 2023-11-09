const token = localStorage.getItem("token");
const email=localStorage.getItem("email")
const options = {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${JSON.parse(token)}`
    }
  };
  
  async function fetchDataAndPopulateHTML() {
    try {
      const response = await fetch(`http://localhost:8090/gamertx/register/${email}`, options);
     
      const person = await response.json();
      
      
      
      const namePersonElement = document.querySelector(".name_profile");
      namePersonElement.innerText = "Nombre: " + person.name;
      
      const userNameElement = document.querySelector(".userName");
      userNameElement.innerText = "Usuario: " + person.username;
      
      const emailPersonElement = document.querySelector(".email_person");
      emailPersonElement.innerText = "Email: " + person.email;
      
      const imgProfileElement = document.querySelector(".imgPorfile");
      imgProfileElement.src = person.imgProfile;
      
      const spampersonElement = document.querySelector(".spamperson");
      spampersonElement.innerText = "Fecha de nacimiento: " + person.birthdate;

      
    } catch (error) {
      console.error(error);
    }
  }
  
 
  fetchDataAndPopulateHTML();

  const miBoton=document.querySelector(".info__button")
  miBoton.addEventListener("click", function() {
    localStorage.clear();
    window.location.href = "home.html";
  });
 
