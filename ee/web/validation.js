let form = document.querySelector('.validateForm');
let chooseRBoxes = document.getElementsByName('chooseR');
let enterY = form.querySelector('.enterYInner');
let selectX = form.querySelector('.selectXInner');
let frame = document.getElementsByClassName('frame');
let svg = document.querySelector('.svg');


let styleY = enterY.style;
let styleX = selectX.style;
let chooseRTitle = form.querySelector('.chooseRTitle');
let styleR = chooseRTitle.style
let flagY = false;
let flagX = false;
let flagR = false;


// Если не выбрано R - подсвечиваем
function RisNotChosen() {

    chooseRTitle.style.color = 'red';
    chooseRTitle.style.fontWeight = 'bold';

    flagR = false;
}


// Обработчик события тыка на график
frame[0].addEventListener("click", function (event) {

    let x0 = frame[0].getBoundingClientRect().x;
    let y0 = frame[0].getBoundingClientRect().y;

    let centerX = x0 + 150;
    let centerY = y0 + 150;

    let currentX = (event.pageX - centerX);
    let currentY = (centerY - event.pageY);

    enterY.value = currentY + "graphic" + currentX;

    form.submit();

    enterY.value = "";
});

//Обрабатываем ввод координаты Y пользователем, блокируем ввод неправильных значений
enterY.addEventListener('input', function () {
    enterY.style = styleY;

    if (isNaN(enterY.value) && enterY.value && enterY.value != '-' || !isNaN(enterY.value) && (Number(enterY.value) < -3 || Number(enterY.value) > 5)) {
        setTimeout(function () {
            enterY.value = "";
            enterY.style.border = '3px solid red';
            enterY.blur();
            flagY = false;
        }, 200)
    } else {
        flagY = true;
    }
})

//При выборе одного варианта из селектора, меняем стиль на стандартный (без красной рамки)
selectX.addEventListener('change', function () {
    selectX.style = styleX;
    flagX = true;

})

var z = 0;

// //Проходим по всем чекбоксам и проверяем. выбран ли хоть какой-нибудь
for (var i = 0, length = chooseRBoxes.length; i < length; i++) {

    chooseRBoxes[i].addEventListener('change', function () {
        chooseRTitle.style = styleR;
        flagR = false;

        for (var j = 0, length = chooseRBoxes.length; j < length; j++) {

            if (chooseRBoxes[j].checked) {
                flagR = true;
            }
        }

    });
}


function checkY() {
//Если в поле, которое соответствует значению Y,
    //пусто или там находится знак "-" -> ошибка
    //(Остальные опасные ситуации блокируются ранее)

    if (!enterY.value || enterY.value == '-') {
        enterY.style.border = '3px solid red';
        flagY = false;
    } else {
        enterY.style = styleY;
        flagY = true;
    }

}

function checkX() {

    //Если пользователь не выбрал в селекторе никакое значение R
    //И оно осталось стандартным -> ошибка
    if (selectX.value == "Выберите X") {
        selectX.style.border = '3px solid red';
        flagX = false;

    } else {
        selectX.style = styleX;
        flagX = true;
    }
}


function checkFields(event) {
    event.preventDefault();

    checkY();

    checkX();

    for (var i = 0, length = chooseRBoxes.length; i < length; i++) {

        if (chooseRBoxes[i].checked) {
            var id = (chooseRBoxes[i].id);
            break
        }
    }


    //Если переменна id == null значит ни одна кнопка не выбрана -> ошибка
    if (!id) {
        RisNotChosen();
    }

    if (flagX == true && flagY == true && flagR == true) {
        form.submit();
    }

}

//Обработчик события нажатия на кнопку
form.addEventListener('submit', checkFields);


