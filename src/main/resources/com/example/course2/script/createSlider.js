class Slider{
    img_src = [
        "img/slider/bg-vgtu-st7.jpg",
        "img/slider/bg-vgtu-st-1.jpg",
        "img/slider/bg-vgtu-pl-3.jpg",
    ];

    arrow_scr = [
        "img/slider/arrows/red-arrow64.png",
        "img/slider/arrows/red-arrow64.png"
    ];

    text_p = [
        "Учись и развивайся вместе с лучшими",
        "Создавай свою историю вместе с нами",
        "Открой для себя новые горизонты",
    ];

    html_scr = [
        "https://cchgeu.ru/",
        "https://old.education.cchgeu.ru/login/index.php",
        "https://yandex.ru/maps/193/voronezh/?from=api-maps&ll=39.167687%2C51.709283&origin=jsapi_2_0&pt=39.194473233478%2C51.65146790423~39.185727618385%2C51.684423345179~39.197791696448%2C51.663101530491~39.184811019601%2C51.757113659176~39.181613826455%2C51.757529132878~39.18812223876%2C51.684468580863~39.192216536867%2C51.652538868341~39.195132808909%2C51.651477975199~39.194521265254%2C51.650885328585~39.192655992882%2C51.651805935055&z=12",
    ];

    btn_text = [
        "Оффициальный сайт ВГТУ",
        "Оффициальный сайт ЭИОС",
        "Местонахождение корпусов",
    ]

    constructor(input_count) {
        for (let i = 0; i < input_count; i++) {
            $(".slider").append(`
                <input id="r${i}" value="${i}" type="radio" name="slider" checked="checked">
                <div class="imgBx" id="banner">
                    <img src="${this.img_src[i]}">
                    <div class="content">
                        <h2>Навигатор первокурсника<span>.</span></h2>
                        <p>${this.text_p[i]}</p>
                        <a href="${this.html_scr[i]}">${this.btn_text[i]}</a>
                    </div>
                </div>
            `);
        }
        $(".slider").append(`
            <div class="btnslider">
            <div class="left-rd">
                <span>
                    left-rd
                </span>
                <img src="${this.arrow_scr[0]}" alt="Load arrow">
            </div>
            <div class="right-rd">
                <span>
                    right-rd
                </span>
                <img src="${this.arrow_scr[1]}" alt="Load arrow">
            </div>
            </div>
        `);
    }
}

let slider = new Slider(3);


let len = null;
$(".slider").each(function () {
    let rr = $(this).find(':input')
    len = rr.length
});
const mass_radio = () => {
    let mass = [];
    for (let i = 0; i < len; i++)
        mass[i] = '#r' + i
    return mass
}
const mass = mass_radio()
const input_checked = () => {
    let value = $('input[name="slider"]:checked').val();
    //alert('btn '+value);
    return value
}

function to_left() {
    let state_radio = input_checked()
    console.log(state_radio)
    if ($('#r' + state_radio).prop('checked', true)) {
        let new_state
        if (state_radio == 0) {
            state_radio = len - 1
            new_state = '#r' + state_radio
        }
        else {
            state_radio--
            new_state = '#r' + state_radio
        }
        console.log(new_state)
        $(new_state).prop('checked', true);
    }
    else {
        console.log('false')
    }
}
function to_right() {
    let state_radio = input_checked()
    if ($('#r' + state_radio).prop('checked', true)) {
        let new_state
        if (state_radio == len - 1) {
            state_radio = 0
            new_state = '#r' + state_radio
        }
        else {
            state_radio++
            new_state = '#r' + state_radio
        }
        console.log(new_state)
        $(new_state).prop('checked', true);
    }
    else {
        console.log('false')
    }
}

$(".btnslider .left-rd").click(function () {
    to_left()
})

$(".btnslider .right-rd").click(function () {
    to_right()
})

let touchstartX = 0
let touchendX = 0
    
function checkDirection() {
    if (touchendX > touchstartX) {
        to_left()
    } 
    if (touchendX < touchstartX) {
        to_right()
    }
}

document.addEventListener('touchstart', e => {
    if (window.scrollY < 100) {
        touchstartX = e.changedTouches[0].screenX
    }
})

document.addEventListener('touchend', e => {
    if (window.scrollY < 100) {
        touchendX = e.changedTouches[0].screenX
        checkDirection()
    }  
})


