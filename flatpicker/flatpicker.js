const date = new Date();
let elements;
let elements2;
let text;
let dia;

const renderCalendar = () => {
  date.setDate(1);

  const monthDays = document.querySelector(".days");

  const lastDay = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0
  ).getDate();

  const prevLastDay = new Date(
    date.getFullYear(),
    date.getMonth(),
    0
  ).getDate();

  const firstDayIndex = date.getDay();

  const lastDayIndex = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0
  ).getDay();

  const nextDays = 7 - lastDayIndex - 1;

  const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  document.querySelector(".date h1").innerHTML = months[date.getMonth()];

  document.querySelector(".date p").innerHTML = new Date().toDateString();

  let days = "";

  for (let x = firstDayIndex; x > 0; x--) {
    days += `<div class="prev-date">${prevLastDay - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDay; i++) {
      days += `<div class="dayd" id="${i}">${i}</div>`;
  }

  for (let j = 1; j <= nextDays; j++) {
    days += `<div class="next-date">${j}</div>`;
    monthDays.innerHTML = days;
  }

   elements = document.getElementsByClassName("dayd");
   elements2 = document.querySelector('div.date h1');
   text = elements2.childNodes[0].textContent;

  for (let index = 0; index < elements.length; index++) {
    elements[index].addEventListener("click", buttonPressed = e =>{
      document.getElementById("dataCalendario").value = text + " " + e.target.id;
      dia = e.target.id;
      
    });
  }
};
/*















*/
document.querySelector(".prev").addEventListener("click", () => {
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
});

document.querySelector(".next").addEventListener("click", () => {
  date.setMonth(date.getMonth() + 1);
  
  renderCalendar();
});

renderCalendar();

  const preco1 = document.getElementById("preco1");
  const preco2 = document.getElementById("preco2");
  const preco3 = document.getElementById("preco3");
  
    preco1.addEventListener("click", buttonPressed = e =>{
      str = preco2.innerHTML;
      var toDate = new Date(date.getFullYear(), date.getMonth(), parseInt(dia));
      var today = new Date();

      var mySubString = str.substring(
        str.indexOf("</small>")+8, 
        str.lastIndexOf("<small")
      );

      dateDiff(toDate, mySubString,today);

      console.log(mySubString);
      
    });

    preco2.addEventListener("click", buttonPressed = e =>{
      str = preco2.innerHTML;
      var toDate = new Date(date.getFullYear(), date.getMonth(), parseInt(dia));
      var today = new Date();

      var mySubString = str.substring(
        str.indexOf("</small>")+8, 
        str.lastIndexOf("<small")
      );

      dateDiff(toDate, mySubString,today);
      
      console.log(mySubString);
      
    });

    preco3.addEventListener("click", buttonPressed = e =>{
      str = preco3.innerHTML;
      var toDate = new Date(date.getFullYear(), date.getMonth(), parseInt(dia));
      var today = new Date();
      

      var mySubString = str.substring(
        str.indexOf("</small>")+8, 
        str.lastIndexOf("<small")
      );
      
      dateDiff(toDate, mySubString, today);
      
      console.log(mySubString);
      
    });

  function dateDiff(toDate, mySubString, today) {
    const diffTime = Math.abs(today - toDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    let str = "Sua modalidade e data dos serviços foi seleciodado, o valor total ficou em  ";
    document.getElementById("precoTotal").value = "";
    console.log("today :" + today);
    console.log("data :" + toDate);

    if (diffDays >= 7) {
      let fixed = ((parseFloat(mySubString) * diffDays) / 7).toFixed(2);
      str += fixed;
      document.getElementById("precoTotal").value = str;
      
    } else {
      let fixed =(parseFloat(mySubString) * diffDays).toFixed(2);
      str += fixed;
      document.getElementById("precoTotal").value = str;
     
    }
  }

