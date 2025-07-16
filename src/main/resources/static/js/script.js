console.log("script loaded");

let currentTheme=getTheme();
// initial 
changeTheme();

// TODO:
function changeTheme(){
    // set to web page
    document.querySelector('html').classList.add(currentTheme);

    // set to listener to change theme button
    const changeThemeButton = document.querySelector('#theme_change_button');
        changeThemeButton.addEventListener("click",(event)=>{
        const oldTheme = currentTheme;
        console.log("change theme button clicked");
    
        // if(currentTheme === "dark"){
        //     // change theme to light
        //     currentTheme="light";
        // } else {
        //     // change theme to dark
        //     currentTheme="dark";
        // }
        currentTheme = currentTheme === "dark" ? "light" : "dark";

        // Need to update in localstorage 
        setTheme(currentTheme);

         // remove the current theme
        document.querySelector('html').classList.remove(oldTheme);

        // set the current theme
        document.querySelector('html').classList.add(currentTheme);

        // ✅ Update button text after switching
        changeThemeButton.querySelector('span').textContent = currentTheme === "dark" ? "Light" : "Dark";


    });

}


// Set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

// get theme from localstorage
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}